package net.numericalk.meo.blocks.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class BreakingStoneBlock extends Block {
    public static final IntegerProperty BREAKING_STAGE = IntegerProperty.create("breaking_stage", 1, 2);

    public BreakingStoneBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(BREAKING_STAGE, 1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BREAKING_STAGE);
    }
}
