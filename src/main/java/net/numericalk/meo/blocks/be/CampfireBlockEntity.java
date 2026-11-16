package net.numericalk.meo.blocks.be;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;
import net.numericalk.meo.blocks.MEOBlockEntities;
import net.numericalk.meo.blocks.custom.CampfireBlock;
import net.numericalk.meo.misc.ImplementedContainer;
import net.numericalk.meo.recipes.MEORecipes;
import net.numericalk.meo.recipes.meo.GrillingRecipeInput;

public class CampfireBlockEntity extends BlockEntity implements ImplementedContainer {
    public static final int CONTAINER_SIZE = 3;

    private final NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);

    public CampfireBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MEOBlockEntities.CAMPFIRE_BLOCK_ENTITY, worldPosition, blockState);
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level == null || level.isClientSide()) return;
        BlockState state = getBlockState();
        level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.items.replaceAll(ignored -> ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, this.items);
        isCooking[0] = input.getBooleanOr("isCooking0", false);
        isCooking[1] = input.getBooleanOr("isCooking1", false);
        isCooking[2] = input.getBooleanOr("isCooking2", false);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        ContainerHelper.saveAllItems(output, this.items);
        output.putBoolean("isCooking0", isCooking[0]);
        output.putBoolean("isCooking1", isCooking[1]);
        output.putBoolean("isCooking2", isCooking[2]);
        super.saveAdditional(output);

    }
    private static final int GRILLING_TIME = 20 * 60 * 3;
    private final int[] grillingTimes = new int[CONTAINER_SIZE];

    private final boolean[] isCooking = new boolean[]{false, false, false};

    public static final int START_FIRE_TIME = 20 * 60 * 3;
    public static final int ADD_FIRE_TIME = 20 * 20;
    public static final int MAX_FIRE_TIME = 20 * 60 * 7;
    private int fireTime = 0;

    public static void serverTick(Level level, BlockPos pos, BlockState state, CampfireBlockEntity be) {
        if (level.isClientSide()) return;

        if (!state.getValue(CampfireBlock.LIT) && be.fireTime > 0){
            level.setBlock(pos, state.setValue(CampfireBlock.LIT, true), Block.UPDATE_ALL);
        } else if (!state.getValue(CampfireBlock.LIT) && be.fireTime <= 0){
            be.fireTime = 0;
        } else if (state.getValue(CampfireBlock.LIT) && be.fireTime > 0){
            be.fireTime--;
        } else if (state.getValue(CampfireBlock.LIT) && be.fireTime <= 0){
            level.setBlock(pos, state.setValue(CampfireBlock.LIT, false), Block.UPDATE_ALL);
        }

        boolean cookingStateChanged = false;
        for (int i = 0; i < be.getContainerSize(); i++) {
            ItemStack stack = be.getItem(i);
            if (stack.isEmpty() || !state.getValue(CampfireBlock.LIT)
                    || !state.getValue(CampfireBlock.CAN_GRILL)) {
                be.grillingTimes[i] = 0;
                if (be.isCooking[i]) {
                    be.isCooking[i] = false;
                    cookingStateChanged = true;
                }
                continue;
            }

            var recipe = level.getServer().getRecipeManager().getRecipeFor(
                    MEORecipes.GRILLING_RECIPE_TYPE, new GrillingRecipeInput(stack), level);
            if (recipe.isEmpty()) {
                be.grillingTimes[i] = 0;
                if (be.isCooking[i]) {
                    be.isCooking[i] = false;
                    cookingStateChanged = true;
                }
                continue;
            }

            if (!be.isCooking[i]) {
                be.isCooking[i] = true;
                cookingStateChanged = true;
            }
            if (++be.grillingTimes[i] >= GRILLING_TIME) {
                ItemStack result = recipe.get().value().assemble(new GrillingRecipeInput(stack));
                if (stack.getCount() > 1) {
                    stack.shrink(1);
                    be.setItem(i, stack);
                    net.minecraft.world.level.block.Block.popResource(level, pos, result);
                } else {
                    be.setItem(i, result);
                }
                be.grillingTimes[i] = 0;
                be.isCooking[i] = false;
                cookingStateChanged = true;
            }
        }

        if (cookingStateChanged) be.setChanged();
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, CampfireBlockEntity be) {
        if (!state.getValue(CampfireBlock.LIT) || !state.getValue(CampfireBlock.CAN_GRILL)) return;

        for (int i = 0; i < be.getContainerSize(); i++) {
            ItemStack stack = be.getItem(i);
            double zOffset = i == 0 ? 0.3 : i == 1 ? 0.5 : 0.7;

            if (!stack.isEmpty() && be.isCooking[i])
                level.addParticle(
                    ParticleTypes.SMOKE,
                    pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + zOffset,
                    0, 0.05, 0);
        }
    }

    public int getFireTime() {
        return fireTime;
    }

    public void setFireTime(int fireTime) {
        this.fireTime = fireTime;
    }
}
