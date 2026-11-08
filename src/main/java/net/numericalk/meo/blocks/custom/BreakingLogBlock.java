package net.numericalk.meo.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BreakingLogBlock extends RotatedPillarBlock {

    public static final IntegerProperty BREAKING_STAGE = IntegerProperty.create("breaking_stage", 1, 3);

    public BreakingLogBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(BREAKING_STAGE, 1));
        registerDefaultState(defaultBlockState().setValue(AXIS, Direction.Axis.Y));
    }
    public static final VoxelShape BREAKING_1_SHAPE_Y = Block.box(1, 0, 1, 15, 16, 15);
    public static final VoxelShape BREAKING_2_SHAPE_Y = Block.box(3, 0, 3, 13, 16, 13);
    public static final VoxelShape BREAKING_3_SHAPE_Y = Block.box(6, 0, 6, 10, 16, 10);

    public static final VoxelShape BREAKING_1_SHAPE_Z = Block.box(1, 1, 0, 15, 15, 16);
    public static final VoxelShape BREAKING_2_SHAPE_Z = Block.box(3, 3, 0, 13, 13, 16);
    public static final VoxelShape BREAKING_3_SHAPE_Z = Block.box(6, 6, 0, 10, 10, 16);

    public static final VoxelShape BREAKING_1_SHAPE_X = Block.box(0, 1, 1, 16, 15, 15);
    public static final VoxelShape BREAKING_2_SHAPE_X = Block.box(0, 3, 3, 16, 13, 13);
    public static final VoxelShape BREAKING_3_SHAPE_X = Block.box(0, 6, 6, 16, 10, 10);


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BREAKING_STAGE, AXIS);
    }

    @Override
    protected VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
        int breakingStage = state.getValue(BREAKING_STAGE);
        Direction.Axis direction = state.getValue(RotatedPillarBlock.AXIS);

        switch (direction){
            case Direction.Axis.X -> {
                if (breakingStage == 2) return BREAKING_2_SHAPE_X;
                else if (breakingStage == 3) return BREAKING_3_SHAPE_X;
                else return BREAKING_1_SHAPE_X;
            }
            case Direction.Axis.Y -> {
                if (breakingStage == 2) return BREAKING_2_SHAPE_Y;
                else if (breakingStage == 3) return BREAKING_3_SHAPE_Y;
                else return BREAKING_1_SHAPE_Y;
            }
            case Direction.Axis.Z -> {
                if (breakingStage == 2) return BREAKING_2_SHAPE_Z;
                else if (breakingStage == 3) return BREAKING_3_SHAPE_Z;
                else return BREAKING_1_SHAPE_Z;
            }
        }

        return BREAKING_1_SHAPE_Y;
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
