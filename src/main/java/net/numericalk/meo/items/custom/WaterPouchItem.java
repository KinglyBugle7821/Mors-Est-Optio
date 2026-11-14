package net.numericalk.meo.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.numericalk.meo.attachments.MEOAttachments;
import net.numericalk.meo.components.MEOComponents;
import net.numericalk.meo.datagen.MEOBlockTagProvider;
import net.numericalk.meo.misc.WaterQuality;
import org.jspecify.annotations.Nullable;

public class WaterPouchItem extends Item {
    public WaterPouchItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {
        if (!stack.has(MEOComponents.WATER_AMOUNT)) return;

        if (stack.getOrDefault(MEOComponents.WATER_AMOUNT, 0f) <= 0
                && stack.getOrDefault(
                MEOComponents.WATER_QUALITY,
                WaterQuality.UNKNOWN
        ) != WaterQuality.UNKNOWN) {

            stack.set(MEOComponents.WATER_QUALITY, WaterQuality.UNKNOWN);
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        BlockHitResult hitResult = Item.getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = hitResult.getBlockPos();
            if (level.mayInteract(player, pos)
                    && level.getFluidState(pos).is(FluidTags.WATER)
                    && level.getFluidState(pos).isSource()) {

                float currentWater = stack.getOrDefault(MEOComponents.WATER_AMOUNT, 0.0f);
                if (currentWater >= 100.0f) {return InteractionResult.FAIL;}

                if (!level.isClientSide()) {
                    boolean dirty = isWaterDirty(level, pos);
                    boolean murky = isWaterMurky(level, pos);
                    boolean toxic = isWaterToxic(level, pos);
                    stack.set(
                            MEOComponents.WATER_QUALITY,
                            toxic ? WaterQuality.TOXIC :
                                    dirty ? WaterQuality.DIRTY :
                                            murky ? WaterQuality.MURKY :
                                                    WaterQuality.CLEAN
                    );
                    stack.set(MEOComponents.WATER_AMOUNT, 100.0f);

                    level.playSound(
                            null,
                            player.getX(), player.getY(), player.getZ(),
                            SoundEvents.BOTTLE_FILL,
                            SoundSource.NEUTRAL,
                            1.0f, 1.0f
                    );

                    level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);
                }

                return InteractionResult.SUCCESS;
            }
        }

        return super.use(level, player, hand);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = context.getItemInHand();

        if (state.is(Blocks.WATER_CAULDRON)) {
            int waterLevel = state.getValue(BlockStateProperties.LEVEL_CAULDRON);
            float waterAmount = stack.getOrDefault(MEOComponents.WATER_AMOUNT, 0.0f);
            if (waterLevel <= 0 || waterAmount >= 100.0f) return InteractionResult.PASS;
            if (!level.isClientSide()) {
                float addedWater = Math.min(
                        100.0f - waterAmount,
                        100.0f / 3.0f
                );
                float newWaterAmount = waterAmount + addedWater;
                if (waterLevel == 1){
                    level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), Block.UPDATE_ALL);
                } else {
                    level.setBlock(pos, state.setValue(
                            BlockStateProperties.LEVEL_CAULDRON, waterLevel - 1), Block.UPDATE_ALL
                    );
                }
                stack.set(MEOComponents.WATER_AMOUNT, newWaterAmount);
                stack.set(MEOComponents.WATER_QUALITY, WaterQuality.CLEAN);

                level.playSound(
                        null,
                        pos,
                        SoundEvents.BOTTLE_FILL,
                        SoundSource.BLOCKS,
                        1.0f,
                        1.0f
                );
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof ServerPlayer player) {
            if (!stack.has(MEOComponents.WATER_AMOUNT)) return stack;

            float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 1f);
            float waterAmount = stack.getOrDefault(MEOComponents.WATER_AMOUNT, 0.0f);

            float hydrationGain = Math.min(1.0f - hydration, waterAmount / 100.0f);

            hydration += hydrationGain;
            waterAmount -= hydrationGain * 100.0f;

            hydration = Math.clamp(hydration, 0.0f, 1.0f);
            waterAmount = Math.clamp(waterAmount, 0.0f, 100.0f);

            player.setAttached(MEOAttachments.HYDRATION, hydration);
            stack.set(MEOComponents.WATER_AMOUNT, (float) Math.ceil(waterAmount));

            MobEffectInstance nausea = new MobEffectInstance(MobEffects.NAUSEA, 10 * 20, 0, false, false, false);
            MobEffectInstance poison = new MobEffectInstance(MobEffects.POISON, 10 * 20, 0, false, false, false);

            WaterQuality quality = stack.get(MEOComponents.WATER_QUALITY);
            assert quality != null;

            if (quality != WaterQuality.CLEAN) {
                player.addEffect(nausea);
            }
            if (quality.equals(WaterQuality.TOXIC)) {
                player.addEffect(poison);
            }
        }

        return super.finishUsingItem(stack, level, entity);
    }
    private boolean isWaterDirty(Level level, BlockPos center) {
        for (BlockPos pos : BlockPos.betweenClosed(
                center.offset(-1, -1, -1),
                center.offset(1, 1, 1)
        )) {
            BlockState state = level.getBlockState(pos);

            if (state.is(MEOBlockTagProvider.DIRTY_BLOCKS)) {
                return true;
            }
        }
        return false;
    }
    private boolean isWaterMurky(Level level, BlockPos center) {
        for (BlockPos pos : BlockPos.betweenClosed(
                center.offset(-1, -1, -1),
                center.offset(1, 1, 1)
        )){
            BlockState state = level.getBlockState(pos);

            if (state.is(MEOBlockTagProvider.STONE_BLOCKS)){
                return true;
            }
        }
        return false;
    }
    private boolean isWaterToxic(Level level, BlockPos center) {
        for (BlockPos pos : BlockPos.betweenClosed(
                center.offset(-1, -1, -1),
                center.offset(1, 1, 1)
        )){
            BlockState state = level.getBlockState(pos);

            if (state.is(MEOBlockTagProvider.TOXIC_BLOCKS)){
                return true;
            }
        }
        return false;
    }
}
