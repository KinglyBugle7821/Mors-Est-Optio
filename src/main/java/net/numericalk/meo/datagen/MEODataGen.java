package net.numericalk.meo.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.numericalk.meo.datagen.world.MEOConfiguredFeatures;
import net.numericalk.meo.datagen.world.MEOPlacedFeatures;

public class MEODataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(MEOBlockTagProvider::new);
        pack.addProvider(MEOItemTagProvider::new);
        pack.addProvider(MEOBlockLootTableProvider::new);
        pack.addProvider(MEOModelProvider::new);
        pack.addProvider(MEORecipeProvider::new);
        pack.addProvider(MEOWorldGenProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONFIGURED_FEATURE, MEOConfiguredFeatures::configure);
        registryBuilder.add(Registries.PLACED_FEATURE, MEOPlacedFeatures::configure);
    }
}
