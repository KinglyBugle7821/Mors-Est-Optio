package net.numericalk.meo.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MEODataGen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(MEOBlockTagProvider::new);
        pack.addProvider(MEOItemTagProvider::new);
        pack.addProvider(MEOBlockLootTableProvider::new);
    }
}
