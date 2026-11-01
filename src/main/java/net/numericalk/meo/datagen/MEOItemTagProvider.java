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
import net.numericalk.meo.items.MEOItemIds;

import java.util.concurrent.CompletableFuture;

public class MEOItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public static final TagKey<Item> REPAIRS_FLINT_TOOL = TagKey.create(Registries.ITEM, MorsEstOptio.id("repairs_flint_tool"));

    public static final TagKey<Item> CAP = TagKey.create(Registries.ITEM, MorsEstOptio.id("cap"));
    public static final TagKey<Item> TUNIC = TagKey.create(Registries.ITEM, MorsEstOptio.id("tunic"));
    public static final TagKey<Item> PANTS = TagKey.create(Registries.ITEM, MorsEstOptio.id("pants"));
    public static final TagKey<Item> BOOTS = TagKey.create(Registries.ITEM, MorsEstOptio.id("boots"));

    public MEOItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        builder(CAP).add(ItemIds.LEATHER_HELMET);
        builder(TUNIC).add(ItemIds.LEATHER_CHESTPLATE);
        builder(PANTS).add(ItemIds.LEATHER_LEGGINGS);
        builder(BOOTS).add(ItemIds.LEATHER_BOOTS);

        builder(ItemTags.AXES).add(MEOItemIds.FLINT_FLAKE);

        builder(REPAIRS_FLINT_TOOL).add(ItemIds.FLINT);
    }
}
