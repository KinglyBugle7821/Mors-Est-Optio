package net.numericalk.meo.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.numericalk.meo.MorsEstOptio;

public class MEOItemIds {
    public static final ResourceKey<Item> WOOD_CHIP = create("wood_chip");

    public static final ResourceKey<Item> OAK_BARK = create("oak_bark");
    public static final ResourceKey<Item> SPRUCE_BARK = create("spruce_bark");
    public static final ResourceKey<Item> BIRCH_BARK = create("birch_bark");
    public static final ResourceKey<Item> JUNGLE_BARK = create("jungle_bark");
    public static final ResourceKey<Item> ACACIA_BARK = create("acacia_bark");
    public static final ResourceKey<Item> DARK_OAK_BARK = create("dark_oak_bark");
    public static final ResourceKey<Item> MANGROVE_BARK = create("mangrove_bark");
    public static final ResourceKey<Item> CHERRY_BARK = create("cherry_bark");
    public static final ResourceKey<Item> PALE_OAK_BARK = create("pale_oak_bark");
    public static final ResourceKey<Item> CRIMSON_BARK = create("crimson_bark");
    public static final ResourceKey<Item> WARPED_BARK = create("warped_bark");

    public static final ResourceKey<Item> STEEL_INGOT = create("steel_ingot");

    public static final ResourceKey<Item> FLINT_FLAKE = create("flint_flake");

    public static final ResourceKey<Item> STEEL_SWORD = create("steel_sword");
    public static final ResourceKey<Item> STEEL_SPEAR = create("steel_spear");
    public static final ResourceKey<Item> STEEL_PICKAXE = create("steel_pickaxe");
    public static final ResourceKey<Item> STEEL_AXE = create("steel_axe");
    public static final ResourceKey<Item> STEEL_SHOVEL = create("steel_shovel");
    public static final ResourceKey<Item> STEEL_HOE = create("steel_hoe");

    public static final ResourceKey<Item> STEEL_HELMET = create("steel_helmet");
    public static final ResourceKey<Item> STEEL_CHESTPLATE = create("steel_chestplate");
    public static final ResourceKey<Item> STEEL_LEGGINGS = create("steel_leggings");
    public static final ResourceKey<Item> STEEL_BOOTS = create("steel_boots");

    public static ResourceKey<Item> create(String name) {
        return ResourceKey.create(Registries.ITEM, MorsEstOptio.id(name));
    }
}
