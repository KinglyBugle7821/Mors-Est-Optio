package net.numericalk.meo.datagen.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.AquaticFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import net.numericalk.meo.MorsEstOptio;

import java.util.List;

public class MEOPlacedFeatures {
    public static final ResourceKey<PlacedFeature> REED_RIVER = ResourceKey.create(Registries.PLACED_FEATURE,
            MorsEstOptio.id("reed_river"));
    public static final ResourceKey<PlacedFeature> REED_SWAMP = ResourceKey.create(Registries.PLACED_FEATURE,
            MorsEstOptio.id("reed_swamp"));

    public static final ResourceKey<PlacedFeature> LOOSE_STONE_SURFACE_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE,
            MorsEstOptio.id("loose_stone_surface_placed"));
    public static final ResourceKey<PlacedFeature> LOOSE_STONE_CAVE_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE,
            MorsEstOptio.id("loose_stone_placed_placed"));

    public static final ResourceKey<PlacedFeature> FLINT_NODULE_RIVER_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE,
            MorsEstOptio.id("flint_nodule_river_placed_key"));
    public static final ResourceKey<PlacedFeature> FLINT_NODULE_HILLS_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE,
            MorsEstOptio.id("flint_nodule_hills_placed_key"));
    public static final ResourceKey<PlacedFeature> FLINT_NODULE_CAVE_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE,
            MorsEstOptio.id("flint_nodule_cave_placed_key"));

    public static final ResourceKey<PlacedFeature> FALLEN_BRANCH_FOREST_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE,
            MorsEstOptio.id("fallen_branch_forest_placed_key"));
    public static final ResourceKey<PlacedFeature> FALLEN_BRANCH_PLAINS_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE,
            MorsEstOptio.id("fallen_branch_plains_placed_key"));

    private static List<PlacementModifier> reedPlacement(final int count) {
        return List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, CountPlacement.of(count), BiomeFilter.biome());
    }
    public static void configure(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        Holder.Reference<ConfiguredFeature<?, ?>> reedShort = configuredFeatures.getOrThrow(MEOConfiguredFeatures.REED_SHORT);
        Holder.Reference<ConfiguredFeature<?, ?>> reedTall = configuredFeatures.getOrThrow(MEOConfiguredFeatures.REED_TALL);

        PlacementUtils.register(context, REED_RIVER, reedShort, reedPlacement(48));
        PlacementUtils.register(context, REED_SWAMP, reedTall, reedPlacement(64));

        PlacementUtils.register(
                context,
                LOOSE_STONE_SURFACE_PLACED_KEY,
                configuredFeatures.getOrThrow(MEOConfiguredFeatures.LOOSE_STONE_CONFIGURED_KEY),
                RarityFilter.onAverageOnceEvery(3),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(32),
                RandomOffsetPlacement.ofTriangle(4, 1),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                FLINT_NODULE_RIVER_PLACED_KEY,
                configuredFeatures.getOrThrow(MEOConfiguredFeatures.FLINT_NODULE_CONFIGURED_KEY),
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                SurfaceWaterDepthFilter.forMaxDepth(2),
                CountPlacement.of(12),
                RandomOffsetPlacement.ofTriangle(3, 1),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                FLINT_NODULE_HILLS_PLACED_KEY,
                configuredFeatures.getOrThrow(MEOConfiguredFeatures.FLINT_NODULE_CONFIGURED_KEY),
                RarityFilter.onAverageOnceEvery(3),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(32),
                RandomOffsetPlacement.ofTriangle(4, 1),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE
                ),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                FALLEN_BRANCH_FOREST_PLACED_KEY,
                configuredFeatures.getOrThrow(MEOConfiguredFeatures.FALLEN_BRANCH_CONFIGURED_KEY),
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(8),
                RandomOffsetPlacement.ofTriangle(6, 2),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.ONLY_IN_AIR_PREDICATE
                ),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                FALLEN_BRANCH_PLAINS_PLACED_KEY,
                configuredFeatures.getOrThrow(MEOConfiguredFeatures.FALLEN_BRANCH_CONFIGURED_KEY),
                RarityFilter.onAverageOnceEvery(4),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                CountPlacement.of(5),
                RandomOffsetPlacement.ofTriangle(5, 1),
                BlockPredicateFilter.forPredicate(BlockPredicate.ONLY_IN_AIR_PREDICATE),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                LOOSE_STONE_CAVE_PLACED_KEY,
                configuredFeatures.getOrThrow(MEOConfiguredFeatures.LOOSE_STONE_CONFIGURED_KEY),
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.bottom(),
                        VerticalAnchor.absolute(48)
                ),
                CountPlacement.of(24),
                RandomOffsetPlacement.ofTriangle(4, 2),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.matchesBlocks(
                                BlockPos.ZERO,
                                Blocks.CAVE_AIR,
                                Blocks.AIR
                        )
                ),
                BiomeFilter.biome()
        );

        PlacementUtils.register(
                context,
                FLINT_NODULE_CAVE_PLACED_KEY,
                configuredFeatures.getOrThrow(MEOConfiguredFeatures.FLINT_NODULE_CONFIGURED_KEY),
                RarityFilter.onAverageOnceEvery(3),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(
                        VerticalAnchor.bottom(),
                        VerticalAnchor.absolute(32)
                ),
                CountPlacement.of(20),
                RandomOffsetPlacement.ofTriangle(3, 1),
                BlockPredicateFilter.forPredicate(
                        BlockPredicate.matchesBlocks(
                                BlockPos.ZERO,
                                Blocks.CAVE_AIR,
                                Blocks.AIR
                        )
                ),
                BiomeFilter.biome()
        );
    }
}
