package net.numericalk.meo.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.numericalk.meo.blocks.MEOBlockEntities;
import net.numericalk.meo.blocks.be.CampfireBlockEntity;
import net.numericalk.meo.datagen.MEOItemTagProvider;
import org.jspecify.annotations.Nullable;

public class CampfireBlock extends BaseEntityBlock {
    public static final BooleanProperty LIT = BooleanProperty.create("lit");
    public static final BooleanProperty CAN_GRILL = BooleanProperty.create("can_grill");

    public static final VoxelShape SHAPE_0 = Block.box(3, 0, 3, 13, 12, 13);
    public static final VoxelShape SHAPE_1 = Shapes.or(
            Block.box(7, 15, 1, 9, 16, 15),
            Block.box(7, 0, 0, 9, 16, 1),
            Block.box(7, 0, 15, 9, 16, 16),
            Block.box(3, 0, 3, 13, 12, 13)
    );
    public CampfireBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(LIT, false));
        registerDefaultState(defaultBlockState().setValue(CAN_GRILL, false));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, CAN_GRILL);
    }
    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return !state.is(BlockTags.AIR);
    }

    public static int getLightEmission(BlockState currentBlockState) {
        boolean lit = currentBlockState.getValue(LIT);

        return lit ? 15 : 0;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if (level.isClientSide()) return createTickerHelper(type, MEOBlockEntities.CAMPFIRE_BLOCK_ENTITY, CampfireBlockEntity::clientTick);
        return createTickerHelper(type, MEOBlockEntities.CAMPFIRE_BLOCK_ENTITY, CampfireBlockEntity::serverTick);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.isEmpty()) {
            return pickUpItem(level, pos, player);
        }
        if (!(level.getBlockEntity(pos) instanceof CampfireBlockEntity be)) {
            return InteractionResult.PASS;
        }
        if (stack.is(Items.STICK) && !state.getValue(CAN_GRILL)){
            level.setBlock(pos, state.setValue(CAN_GRILL, true), Block.UPDATE_ALL);
            stack.consume(1, player);
            level.playLocalSound(player, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.PLAYERS, 1f, 1f);
            return InteractionResult.SUCCESS;
        }
        if (canLitFire(stack, state, level, player)){
            be.setFireTime(CampfireBlockEntity.START_FIRE_TIME);
            return InteractionResult.SUCCESS;
        }
        if (canAddFireTime(stack, state, be)){
            level.playLocalSound(player, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1f, 1f);
            be.setFireTime(be.getFireTime() + CampfireBlockEntity.ADD_FIRE_TIME);
            stack.consume(1, player);
            return InteractionResult.SUCCESS;
        }
        if (stack.is(MEOItemTagProvider.RAW_FOOD) && state.getValue(CAN_GRILL)){
            if (level.isClientSide()) {
                return InteractionResult.SUCCESS;
            }
            for (int i = 0; i < be.getContainerSize(); i++) {
                if (be.getItem(i).isEmpty()){
                    be.setItem(i, stack.copyWithCount(1));
                    stack.consume(1, player);
                    level.playLocalSound(player, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.PLAYERS, 1f, 1f);
                    return InteractionResult.SUCCESS;
                }
            }
            return InteractionResult.PASS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return player.getItemInHand(player.getUsedItemHand()).isEmpty() ? pickUpItem(level, pos, player) : InteractionResult.PASS;
    }

    private InteractionResult pickUpItem(Level level, BlockPos pos, Player player) {
        if (!(level.getBlockEntity(pos) instanceof CampfireBlockEntity campfireBlockEntity)) {
            return InteractionResult.PASS;
        }

        for (int i = campfireBlockEntity.getContainerSize() - 1; i >= 0; i--) {
            ItemStack storedItem = campfireBlockEntity.getItem(i);
            if (!storedItem.isEmpty()) {
                if (!level.isClientSide()) {
                    ItemStack pickedUp = storedItem.copy();
                    if (!player.getInventory().add(pickedUp)) {
                        player.drop(pickedUp, false);
                    }
                    campfireBlockEntity.setItem(i, ItemStack.EMPTY);
                }
                level.playLocalSound(player, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.PLAYERS, 1f, 1f);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    private boolean canLitFire(ItemStack stack, BlockState state, Level level, Player player) {
        if (!state.getValue(LIT)){
            if (stack.is(Items.FLINT_AND_STEEL)){
                level.playLocalSound(player, SoundEvents.FLINTANDSTEEL_USE, SoundSource.PLAYERS, 1f, 1f);
                stack.hurtAndBreak(1, player, player.getUsedItemHand());
                return true;
            } else if (stack.is(Items.FIRE_CHARGE)){
                level.playLocalSound(player, SoundEvents.FIRECHARGE_USE, SoundSource.PLAYERS, 1f, 1f);
                stack.consume(1, player);
                return true;
            } else return false;
        } else return false;
    }
    private boolean canAddFireTime(ItemStack stack, BlockState state, CampfireBlockEntity be) {
        return (state.getValue(LIT) && stack.is(MEOItemTagProvider.CAMPFIRE_FUELS)) &&
                !(be.getFireTime() > CampfireBlockEntity.MAX_FIRE_TIME);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if (level instanceof ServerLevel serverLevel) {
            if (!onState.getValue(LIT)) return;
            DamageSource damageSource = new DamageSource(
                    level.registryAccess()
                            .lookupOrThrow(Registries.DAMAGE_TYPE)
                            .get(DamageTypes.ON_FIRE.identifier()).orElseThrow());
            if (entity.tickCount % 20 == 0) entity.hurtServer(serverLevel, damageSource, 1);
        }
    }

    @Override
    protected BlockState updateShape(
            final BlockState state,
            final LevelReader level,
            final ScheduledTickAccess ticks,
            final BlockPos pos,
            final Direction directionToNeighbour,
            final BlockPos neighbourPos,
            final BlockState neighbourState,
            final RandomSource random
    ) {
        return !state.canSurvive(level, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, level, ticks, pos, directionToNeighbour, neighbourPos, neighbourState, random);
    }

    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        BlockPos below = pos.below();
        return this.mayPlaceOn(level.getBlockState(below), level, below);
    }
    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        if (state.getValue(CAN_GRILL)) return SHAPE_1;
        else return SHAPE_0;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getBlockSupportShape(state, level, pos);
    }
    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getBlockSupportShape(state, level, pos);
    }
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(CampfireBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CampfireBlockEntity(pos, state);
    }
}
