package net.numericalk.meo.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.numericalk.meo.MorsEstOptio;

public class MEOItemIds {
    public static final ResourceKey<Item> FLINT_FLAKE = create("flint_flake");

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, MorsEstOptio.id(name));
    }
}
