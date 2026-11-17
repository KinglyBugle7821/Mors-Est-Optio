package net.numericalk.meo.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.numericalk.meo.blocks.MEOBlockEntities;
import net.numericalk.meo.blocks.be.DryingTrayBlockEntity;
import org.jspecify.annotations.Nullable;

public class DryingTrayBlock extends BaseEntityBlock {
    public DryingTrayBlock(Properties properties) {
        super(properties);
    }

    public static final VoxelShape VOXEL_SHAPE = Block.box(0, 0, 0, 16, 2, 16);

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!(level.getBlockEntity(pos) instanceof DryingTrayBlockEntity be)) {
            return InteractionResult.PASS;
        }
        if (stack.isEmpty()) {
            be.setChanged();
            return pickUpItem(level, pos, player);
        } else {
            if (level.isClientSide()) {
                return InteractionResult.SUCCESS;
            }
            if (be.getItem(0).isEmpty()){
                be.setItem(0, stack.copyWithCount(1));
                stack.consume(1, player);
                level.playLocalSound(player, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.PLAYERS, 1f, 1f);
                return InteractionResult.SUCCESS;
            }
            be.setChanged();
            return InteractionResult.PASS;
        }
    }
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return pickUpItem(level, pos, player);
    }
    private InteractionResult pickUpItem(Level level, BlockPos pos, Player player) {
        if (!(level.getBlockEntity(pos) instanceof DryingTrayBlockEntity be)) {
            return InteractionResult.PASS;
        }

        ItemStack storedItem = be.getItem(0);
        if (!storedItem.isEmpty()) {
            if (!level.isClientSide()) {
                ItemStack pickedUp = storedItem.copy();
                if (!player.getInventory().add(pickedUp)) {
                    player.drop(pickedUp, false);
                }
                be.setItem(0, ItemStack.EMPTY);
            }
            level.playLocalSound(player, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.PLAYERS, 1f, 1f);
            be.setChanged();
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if (level.isClientSide()) return createTickerHelper(type, MEOBlockEntities.DRYING_TRAY_BLOCK_ENTITY, DryingTrayBlockEntity::clientTick);
        return createTickerHelper(type, MEOBlockEntities.DRYING_TRAY_BLOCK_ENTITY, DryingTrayBlockEntity::serverTick);
    }
    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return !state.is(BlockTags.AIR);
    }
    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        BlockPos below = pos.below();
        return this.mayPlaceOn(level.getBlockState(below), level, below);
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
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        return VOXEL_SHAPE;
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
        return simpleCodec(DryingTrayBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DryingTrayBlockEntity(pos, state);
    }
}
