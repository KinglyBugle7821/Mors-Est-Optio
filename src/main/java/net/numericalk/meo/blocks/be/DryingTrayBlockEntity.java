package net.numericalk.meo.blocks.be;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.numericalk.meo.recipes.MEORecipes;
import net.numericalk.meo.recipes.meo.DryingRecipeInput;
import org.jspecify.annotations.Nullable;
import net.numericalk.meo.blocks.MEOBlockEntities;
import net.numericalk.meo.misc.ImplementedContainer;

public class DryingTrayBlockEntity extends BlockEntity implements ImplementedContainer {
    public static final int CONTAINER_SIZE = 1;
    private final NonNullList<ItemStack> items = NonNullList.withSize(CONTAINER_SIZE, ItemStack.EMPTY);


    public DryingTrayBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MEOBlockEntities.DRYING_TRAY_BLOCK_ENTITY, worldPosition, blockState);
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
    public net.minecraft.nbt.CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        this.items.replaceAll(ignored -> ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, this.items);
        dryingTime = input.getIntOr("dryingTime", DRYING_TIME);
        isDrying = input.getBooleanOr("isDrying", false);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        ContainerHelper.saveAllItems(output, this.items);
        output.putInt("dryingTime", dryingTime);
        output.putBoolean("isDrying", isDrying);
        super.saveAdditional(output);
    }
    private boolean isDrying = false;

    public static void clientTick(Level level, BlockPos pos, BlockState state, DryingTrayBlockEntity be) {
        ItemStack stack = be.getItem(0);

        if (!stack.isEmpty() && be.isDrying) {
            RandomSource random = RandomSource.create();
            level.addParticle(ParticleTypes.SMOKE,
                    pos.getX() + random.nextDouble(), pos.getY() + 0.1, pos.getZ() + random.nextDouble(),
                    0,0.05,0);
        }
    }

    public static final int DRYING_TIME = 20 * 60 * 3;
    private int dryingTime = DRYING_TIME;

    public static void serverTick(Level level, BlockPos pos, BlockState state, DryingTrayBlockEntity be) {
        ItemStack stack = be.getItem(0);
        var recipe = level.getServer().getRecipeManager().getRecipeFor(
                MEORecipes.DRYING_RECIPE_TYPE, new DryingRecipeInput(stack), level);
        boolean hasRecipe = recipe.isPresent();
        if (canDry(level, pos) && hasRecipe && !stack.isEmpty()){
            if (!be.isDrying) {
                be.isDrying = true;
                be.setChanged();
            }
            be.dryingTime--;
            if (be.dryingTime <= 0){
                ItemStack result = recipe.get().value().assemble(new DryingRecipeInput(stack));
                if (stack.getCount() > 1) {
                    stack.shrink(1);
                    be.setItem(0, stack);
                    net.minecraft.world.level.block.Block.popResource(level, pos, result);
                } else {
                    be.setItem(0, result);
                }
                be.dryingTime = DRYING_TIME;
                be.setChanged();
            }
        } else {
            be.dryingTime = DRYING_TIME;
            if (be.isDrying) {
                be.isDrying = false;
                be.setChanged();
            }
        }
    }

    private static boolean canDry(Level level, BlockPos pos) {
        return !level.isRainingAt(pos) &&
                level.getOverworldClockTime() % 24000 >= 0 && level.getOverworldClockTime() % 24000 < 13000;
    }

    @Override
    public NonNullList<ItemStack> getItems() {
        return this.items;
    }
}
