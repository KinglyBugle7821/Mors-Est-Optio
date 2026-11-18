package net.numericalk.meo.datagen.world;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;

public class MEOBiomeModifications {
    public MEOBiomeModifications(){
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_RIVER),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                MEOPlacedFeatures.REED_RIVER
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.HAS_SWAMP_HUT),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                MEOPlacedFeatures.REED_SWAMP
        );

        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                MEOPlacedFeatures.LOOSE_STONE_SURFACE_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                MEOPlacedFeatures.LOOSE_STONE_CAVE_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_RIVER),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                MEOPlacedFeatures.FLINT_NODULE_RIVER_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_HILL).or(BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION,
                MEOPlacedFeatures.FLINT_NODULE_HILLS_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.UNDERGROUND_DECORATION,
                MEOPlacedFeatures.FLINT_NODULE_CAVE_PLACED_KEY
        );

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_FOREST),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                MEOPlacedFeatures.FALLEN_BRANCH_FOREST_PLACED_KEY
        );
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                MEOPlacedFeatures.FALLEN_BRANCH_PLAINS_PLACED_KEY
        );
    }
}
