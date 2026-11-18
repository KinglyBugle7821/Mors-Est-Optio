package net.numericalk.meo.datagen.world;

import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.SeagrassFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.blocks.custom.FallenFeatureBlock;
import net.numericalk.meo.datagen.world.custom.ReedFeature;

public class MEOConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> LOOSE_STONE_CONFIGURED_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MorsEstOptio.id("loose_stone")
            );
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLINT_NODULE_CONFIGURED_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MorsEstOptio.id("flint_nodule")
            );
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_BRANCH_CONFIGURED_KEY =
            ResourceKey.create(
                    Registries.CONFIGURED_FEATURE,
                    MorsEstOptio.id("fallen_branch")
            );
    public static final ResourceKey<ConfiguredFeature<?, ?>> REED_SHORT = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, MorsEstOptio.id("reed_short"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> REED_TALL = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, MorsEstOptio.id("reed_tall"));

    public static void configure(BootstrapContext<ConfiguredFeature<?,?>> context) {
        FeatureUtils.register(context, REED_SHORT, MEOFeature.REED_FEATURE, new ProbabilityFeatureConfiguration(0.3F));
        FeatureUtils.register(context, REED_TALL, MEOFeature.REED_FEATURE, new ProbabilityFeatureConfiguration(0.8F));

        FeatureUtils.register(
                context, LOOSE_STONE_CONFIGURED_KEY,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(
                        new WeightedStateProvider(fallenBlockPatchBuilder(MEOBlocks.LOOSE_STONE))));
        FeatureUtils.register(
                context, FLINT_NODULE_CONFIGURED_KEY,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(
                        new WeightedStateProvider(fallenBlockPatchBuilder(MEOBlocks.FLINT_NODULE))));
        FeatureUtils.register(
                context, FALLEN_BRANCH_CONFIGURED_KEY,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(
                        new WeightedStateProvider(fallenBlockPatchBuilder(MEOBlocks.FALLEN_BRANCH))));


    }

    private static WeightedList.Builder<BlockState> fallenBlockPatchBuilder(Block block) {
        WeightedList.Builder<BlockState> states = WeightedList.builder();

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            states.add(block.defaultBlockState().setValue(FallenFeatureBlock.FACING, direction), 1);
        }
        return states;
    }
}
