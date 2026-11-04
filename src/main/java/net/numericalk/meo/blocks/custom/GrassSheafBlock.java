package net.numericalk.meo.blocks.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.numericalk.meo.blocks.MEOBlockEntities;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.blocks.be.GrassSheafBlockEntity;
import org.jspecify.annotations.Nullable;

public class GrassSheafBlock extends BaseEntityBlock {
    public static final BooleanProperty IS_DRY = BooleanProperty.create("is_dry");

    public static final VoxelShape VOXEL_SHAPE = Block.box(5, 0, 0, 11, 4, 16);

    public GrassSheafBlock(Properties properties) {
        super(properties);

        registerDefaultState(defaultBlockState().setValue(IS_DRY, false));
    }
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(IS_DRY);
    }
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState blockState, BlockEntityType<T> type) {
        if (!level.isClientSide()){
            return createTickerHelper(type, MEOBlockEntities.GRASS_SHEAF_BLOCK_ENTITY, GrassSheafBlockEntity::tick);
        } else {
            return createTickerHelper(type, MEOBlockEntities.GRASS_SHEAF_BLOCK_ENTITY, GrassSheafBlockEntity::clientTick);
        }
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
        return simpleCodec(GrassSheafBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new GrassSheafBlockEntity(pos, state);
    }
}
