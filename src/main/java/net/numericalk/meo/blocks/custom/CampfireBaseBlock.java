package net.numericalk.meo.blocks.custom;

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
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.numericalk.meo.blocks.MEOBlocks;

public class CampfireBaseBlock extends Block {
    public static final IntegerProperty BUILDING_STAGE = IntegerProperty.create("building_stage", 0, 3);

    public static final VoxelShape SHAPE_0 = Block.box(3, 0, 3, 13, 1, 13);
    public static final VoxelShape SHAPE_1 = Block.box(3, 0, 3, 13, 12, 13);

    public CampfireBaseBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(BUILDING_STAGE, 0));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.is(Items.STICK)){
            if (state.getValue(BUILDING_STAGE) < 3) {
                level.setBlock(pos, state.setValue(BUILDING_STAGE, state.getValue(BUILDING_STAGE) + 1), Block.UPDATE_ALL);
                stack.consume(1, player);
                level.playLocalSound(player, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.PLAYERS, 1f, 1f);
                return InteractionResult.SUCCESS;
            } else {
                level.setBlock(pos, MEOBlocks.CAMPFIRE.defaultBlockState(), Block.UPDATE_ALL);
                stack.consume(1, player);
                level.playLocalSound(player, SoundEvents.ITEM_FRAME_ADD_ITEM, SoundSource.PLAYERS, 1f, 1f);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BUILDING_STAGE);
    }
    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return !state.is(BlockTags.AIR);
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
        int buildingStage = state.getValue(BUILDING_STAGE);

        if (buildingStage == 0){
            return SHAPE_0;
        } else return SHAPE_1;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getBlockSupportShape(state, level, pos);
    }
    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return this.getBlockSupportShape(state, level, pos);
    }
}
