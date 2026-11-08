package net.numericalk.meo.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

public class MEOLooseBlock extends FallingBlock {
    public MEOLooseBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends FallingBlock> codec() {
        return simpleCodec(MEOLooseBlock::new);
    }

    @Override
    public int getDustColor(BlockState blockState, BlockGetter level, BlockPos pos) {
        return blockState.getMapColor(level, pos).col;
    }
}
