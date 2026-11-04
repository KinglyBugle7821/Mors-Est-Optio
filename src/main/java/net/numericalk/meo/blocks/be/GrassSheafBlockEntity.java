package net.numericalk.meo.blocks.be;

import net.minecraft.client.particle.Particle;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.numericalk.meo.blocks.MEOBlockEntities;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.blocks.custom.GrassSheafBlock;
import org.jspecify.annotations.Nullable;

public class GrassSheafBlockEntity extends BlockEntity {
    private static final int DRYING_TIME = 20 *60 * 3; // 5 mins
    private int dryingTime = DRYING_TIME;

    public GrassSheafBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MEOBlockEntities.GRASS_SHEAF_BLOCK_ENTITY, worldPosition, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, GrassSheafBlockEntity be) {
        if (isWeatherClear(level, pos, be)){
            be.dryingTime--;
            if (be.dryingTime <= 0 && !state.getValue(GrassSheafBlock.IS_DRY)){
                level.setBlock(pos, state.setValue(GrassSheafBlock.IS_DRY, true), Block.UPDATE_ALL);
                be.dryingTime = DRYING_TIME;
                setChanged(level, pos, state);
            } else if (be.dryingTime <= 0 && state.getValue(GrassSheafBlock.IS_DRY)){
                level.setBlock(pos, Blocks.FIRE.defaultBlockState(), Block.UPDATE_ALL);
                be.dryingTime = DRYING_TIME;
                setChanged(level, pos, state);
            }
            setChanged(level, pos, state);
        }
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        output.putInt("drying_time", dryingTime);

        super.saveAdditional(output);
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);

        dryingTime = input.getIntOr("drying_time", 20 * 60 * 3);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level == null) return;
        BlockState state = getBlockState();
        level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    private static boolean isWeatherClear(Level level, BlockPos pos, GrassSheafBlockEntity be) {
        boolean isNight = (((int) (level.getOverworldClockTime() % 24000)) >= 13000 || ((int) (level.getOverworldClockTime() % 24000)) < 1000);
        if (!level.isRainingAt(pos) && level.getBrightness(LightLayer.SKY, pos) >= 12 && !isNight) return true;
        else if (level.isRainingAt(pos)) {
            be.dryingTime = DRYING_TIME;
            return false;
        } else if (level.getBrightness(LightLayer.SKY, pos) < 12) return false;
        else if (isNight) return false;
        else return false;
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, GrassSheafBlockEntity be) {
        if (isWeatherClear(level, pos, be)){
            RandomSource random = RandomSource.create();
            level.addParticle(ParticleTypes.SMOKE,
                    pos.getX() + random.nextDouble(), pos.getY() + 0.5, pos.getZ() + random.nextDouble(),
                    0,0.05,0);
        }
    }
}
