package net.numericalk.meo.datagen.world;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.datagen.world.custom.ReedFeature;

public abstract class MEOFeature<FC extends FeatureConfiguration> {

    public static ReedFeature REED_FEATURE;

    public static void initialize() {
        REED_FEATURE = Registry.register(
                BuiltInRegistries.FEATURE,
                MorsEstOptio.id("reed"),
                new ReedFeature(ProbabilityFeatureConfiguration.CODEC)
        );
    }
}
