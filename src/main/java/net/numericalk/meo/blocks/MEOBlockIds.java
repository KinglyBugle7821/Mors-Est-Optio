package net.numericalk.meo.blocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.numericalk.meo.MorsEstOptio;

public class MEOBlockIds {

    private static ResourceKey<Block> create(String name) {
        Identifier id = MorsEstOptio.id(name);
        return ResourceKey.create(Registries.BLOCK, id);
    }
}
