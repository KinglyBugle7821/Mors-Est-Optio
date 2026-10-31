package net.numericalk.meo.mechs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public final class LocalBlockThermalField {

    private static final int MAX_SCAN_RADIUS = 7;

    private static final double MIN_ADJUSTMENT = -15.0;
    private static final double MAX_ADJUSTMENT = 20.0;

    private record ThermalSource(
            double strength,
            int radius,
            Predicate<BlockState> activeWhen
    ) {}

    private static final Map<Block, ThermalSource> SOURCES = createSources();

    private static Map<Block, ThermalSource> createSources() {
        Map<Block, ThermalSource> sources = new HashMap<>();

        register(sources, Blocks.TORCH, 2.0, 4);
        register(sources, Blocks.WALL_TORCH, 2.0, 4);

        register(sources, Blocks.SOUL_TORCH, 1.0, 4);
        register(sources, Blocks.SOUL_WALL_TORCH, 1.0, 4);

        registerLit(sources, Blocks.CAMPFIRE, 10.0, 7);
        registerLit(sources, Blocks.SOUL_CAMPFIRE, 8.0, 7);

        registerLit(sources, Blocks.FURNACE, 4.0, 5);
        registerLit(sources, Blocks.BLAST_FURNACE, 5.0, 5);
        registerLit(sources, Blocks.SMOKER, 4.0, 5);

        register(sources, Blocks.LAVA, 15.0, 7);
        register(sources, Blocks.MAGMA_BLOCK, 4.0, 3);

        register(sources, Blocks.SNOW_BLOCK, -2.0, 4);
        register(sources, Blocks.ICE, -3.0, 4);
        register(sources, Blocks.PACKED_ICE, -4.0, 5);
        register(sources, Blocks.BLUE_ICE, -6.0, 6);
        register(sources, Blocks.POWDER_SNOW, -6.0, 6);

        return Map.copyOf(sources);
    }

    private static void register(
            Map<Block, ThermalSource> sources,
            Block block,
            double strength,
            int radius
    ) {
        sources.put(
                block,
                new ThermalSource(strength, radius, state -> true)
        );
    }

    private static void registerLit(
            Map<Block, ThermalSource> sources,
            Block block,
            double strength,
            int radius
    ) {
        sources.put(
                block,
                new ThermalSource(
                        strength,
                        radius,
                        state -> state.hasProperty(BlockStateProperties.LIT)
                                && state.getValue(BlockStateProperties.LIT)
                )
        );
    }

    public static double getTemperatureOffset(Level level, Player player) {

        double playerX = player.getX();
        double playerY = player.getY() + player.getBbHeight() * 0.5;
        double playerZ = player.getZ();

        int centerX = (int) Math.floor(playerX);
        int centerY = (int) Math.floor(playerY);
        int centerZ = (int) Math.floor(playerZ);

        double totalTemperature = 0.0;

        double maxDistanceSquared =
                MAX_SCAN_RADIUS * MAX_SCAN_RADIUS;

        BlockPos.MutableBlockPos mutablePos =
                new BlockPos.MutableBlockPos();

        for (int x = centerX - MAX_SCAN_RADIUS;
             x <= centerX + MAX_SCAN_RADIUS; x++) {

            for (int z = centerZ - MAX_SCAN_RADIUS;
                 z <= centerZ + MAX_SCAN_RADIUS; z++) {

                if (!level.hasChunk(x >> 4, z >> 4)) {
                    continue;
                }

                for (int y = centerY - MAX_SCAN_RADIUS;
                     y <= centerY + MAX_SCAN_RADIUS; y++) {

                    double dx = (x + 0.5) - playerX;
                    double dy = (y + 0.5) - playerY;
                    double dz = (z + 0.5) - playerZ;

                    double distanceSquared =
                            dx * dx + dy * dy + dz * dz;

                    if (distanceSquared > maxDistanceSquared) {
                        continue;
                    }

                    mutablePos.set(x, y, z);

                    BlockState state = level.getBlockState(mutablePos);

                    ThermalSource source = SOURCES.get(state.getBlock());

                    if (source == null || !source.activeWhen().test(state)) {
                        continue;
                    }

                    double radius = source.radius();

                    if (distanceSquared >= radius * radius) {
                        continue;
                    }

                    double distance = Math.sqrt(distanceSquared);

                    double falloff = 1.0 - (distance / radius);

                    double contribution = source.strength() * falloff;

                    totalTemperature += contribution;
                }
            }
        }

        return Math.max(
                MIN_ADJUSTMENT,
                Math.min(MAX_ADJUSTMENT, totalTemperature)
        );
    }
}