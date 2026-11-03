package net.numericalk.meo.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.blocks.MEOBlockItemIds;
import net.numericalk.meo.items.MEOItemIds;

import java.util.concurrent.CompletableFuture;

public class MEOItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public static final TagKey<Item> REPAIRS_FLINT_TOOL = TagKey.create(Registries.ITEM, MorsEstOptio.id("repairs_flint_tool"));
    public static final TagKey<Item> STEEL_MATERIAL = TagKey.create(Registries.ITEM, MorsEstOptio.id("repairs_steel_tool"));

    public static final TagKey<Item> WOOD_LEVEL_AXE = TagKey.create(Registries.ITEM, MorsEstOptio.id("wood_level_axe"));
    public static final TagKey<Item> STONE_LEVEL_AXE = TagKey.create(Registries.ITEM, MorsEstOptio.id("stone_level_axe"));
    public static final TagKey<Item> IRON_LEVEL_AXE = TagKey.create(Registries.ITEM, MorsEstOptio.id("iron_level_axe"));
    public static final TagKey<Item> DIAMOND_LEVEL_AXE = TagKey.create(Registries.ITEM, MorsEstOptio.id("diamond_level_axe"));
    public static final TagKey<Item> NETHERITE_LEVEL_AXE = TagKey.create(Registries.ITEM, MorsEstOptio.id("netherite_level_axe"));

    public static final TagKey<Item> CAP = TagKey.create(Registries.ITEM, MorsEstOptio.id("cap"));
    public static final TagKey<Item> TUNIC = TagKey.create(Registries.ITEM, MorsEstOptio.id("tunic"));
    public static final TagKey<Item> PANTS = TagKey.create(Registries.ITEM, MorsEstOptio.id("pants"));
    public static final TagKey<Item> BOOTS = TagKey.create(Registries.ITEM, MorsEstOptio.id("boots"));

    public MEOItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        builder(STEEL_MATERIAL)
                .add(MEOItemIds.STEEL_INGOT);

        builder(ItemTags.HEAD_ARMOR)
                .add(MEOItemIds.STEEL_HELMET);
        builder(ItemTags.CHEST_ARMOR)
                .add(MEOItemIds.STEEL_CHESTPLATE);
        builder(ItemTags.LEG_ARMOR)
                .add(MEOItemIds.STEEL_LEGGINGS);
        builder(ItemTags.FOOT_ARMOR)
                .add(MEOItemIds.STEEL_BOOTS);

        builder(ItemTags.SWORDS)
                .add(MEOItemIds.STEEL_SWORD);
        builder(ItemTags.PICKAXES)
                .add(MEOItemIds.STEEL_PICKAXE);
        builder(ItemTags.AXES)
                .add(MEOItemIds.STEEL_AXE);
        builder(ItemTags.SPEARS)
                .add(MEOItemIds.STEEL_SPEAR);
        builder(ItemTags.HOES)
                .add(MEOItemIds.STEEL_HOE);
        builder(ItemTags.SHOVELS)
                .add(MEOItemIds.STEEL_SHOVEL);

        builder(WOOD_LEVEL_AXE)
                .add(MEOItemIds.FLINT_FLAKE)
                .add(ItemIds.WOODEN_AXE)
                .add(ItemIds.GOLDEN_AXE);
        builder(STONE_LEVEL_AXE)
                .add(ItemIds.STONE_AXE);
        builder(IRON_LEVEL_AXE)
                .add(ItemIds.IRON_AXE);
        builder(DIAMOND_LEVEL_AXE)
                .add(ItemIds.DIAMOND_AXE);
        builder(NETHERITE_LEVEL_AXE)
                .add(ItemIds.NETHERITE_AXE);

        builder(ItemTags.LOGS_THAT_BURN)
                .add(MEOBlockItemIds.BREAKING_OAK_LOG)
                .add(MEOBlockItemIds.BREAKING_SPRUCE_LOG)
                .add(MEOBlockItemIds.BREAKING_BIRCH_LOG)
                .add(MEOBlockItemIds.BREAKING_JUNGLE_LOG)
                .add(MEOBlockItemIds.BREAKING_ACACIA_LOG)
                .add(MEOBlockItemIds.BREAKING_DARK_OAK_LOG)
                .add(MEOBlockItemIds.BREAKING_CHERRY_LOG)
                .add(MEOBlockItemIds.BREAKING_PALE_OAK_LOG);

        builder(CAP).add(ItemIds.LEATHER_HELMET);
        builder(TUNIC).add(ItemIds.LEATHER_CHESTPLATE);
        builder(PANTS).add(ItemIds.LEATHER_LEGGINGS);
        builder(BOOTS).add(ItemIds.LEATHER_BOOTS);

        builder(ItemTags.AXES).add(MEOItemIds.FLINT_FLAKE);

        builder(REPAIRS_FLINT_TOOL).add(ItemIds.FLINT);
    }
}
