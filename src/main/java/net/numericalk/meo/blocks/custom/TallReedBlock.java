package net.numericalk.meo.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.numericalk.meo.blocks.MEOBlocks;
import org.jspecify.annotations.Nullable;

public class TallReedBlock extends DoublePlantBlock implements LiquidBlockContainer {
    public static final MapCodec<TallSeagrassBlock> CODEC = simpleCodec(TallSeagrassBlock::new);
    public static final EnumProperty<DoubleBlockHalf> HALF = DoublePlantBlock.HALF;
    private static final VoxelShape SHAPE = Block.column(12.0, 0.0, 16.0);

    public TallReedBlock(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<? extends DoublePlantBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected boolean mayPlaceOn(final BlockState state, final BlockGetter level, final BlockPos pos) {
        return state.isFaceSturdy(level, pos, Direction.UP) && !state.is(BlockTags.CANNOT_SUPPORT_SEAGRASS);
    }

    @Override
    protected ItemStack getCloneItemStack(final LevelReader level, final BlockPos pos, final BlockState state, final boolean includeData) {
        return new ItemStack(MEOBlocks.REED);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(final BlockPlaceContext context) {
        BlockState state = super.getStateForPlacement(context);
        if (state != null) {
            FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
            if (fluidState.is(FluidTags.WATER) && fluidState.isFull()) {
                return state;
            }
        }

        return null;
    }

    @Override
    protected boolean canSurvive(final BlockState state, final LevelReader level, final BlockPos pos) {
        FluidState fluidState = level.getFluidState(pos);
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER) {
            BlockState belowState = level.getBlockState(pos.below());
            return belowState.is(this)
                    && belowState.getValue(HALF) == DoubleBlockHalf.LOWER
                    && !fluidState.is(FluidTags.WATER);
        }

        return super.canSurvive(state, level, pos)
                && fluidState.is(FluidTags.WATER)
                && fluidState.isFull();
    }
    @Override
    protected FluidState getFluidState(final BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER
                ? Fluids.WATER.getSource(false)
                : Fluids.EMPTY.defaultFluidState();
    }
    @Override
    public boolean canPlaceLiquid(final @Nullable LivingEntity user, final BlockGetter level, final BlockPos pos, final BlockState state, final Fluid type) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER;
    }

    @Override
    public boolean placeLiquid(final LevelAccessor level, final BlockPos pos, final BlockState state, final FluidState fluidState) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER;
    }
}
