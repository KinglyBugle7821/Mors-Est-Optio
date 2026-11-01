package net.numericalk.meo.items;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.numericalk.meo.MorsEstOptio;

import java.util.function.Function;

public class MEOItems {
    public static final Item FLINT_FLAKE = register(
            MEOItemIds.FLINT_FLAKE,
            settings -> new AxeItem(MEOToolMaterials.FLINT_TOOL_MATERIAL, 0.0F, -3.0F, settings),
            new Item.Properties()
    );
    public static Item register(ResourceKey<Item> itemKey, Function<Item.Properties, Item> itemFactory, Item.Properties settings) {

        Item item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
    public static final ResourceKey<CreativeModeTab> MEO_CREATIVE_TAB_KEY = ResourceKey.create(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), MorsEstOptio.id("meo_creative_tab")
    );
    public static final CreativeModeTab MEO_CREATIVE_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(MEOItems.FLINT_FLAKE))
            .title(Component.translatable("creativeTab.meoCreativeTab"))
            .displayItems((params, output) -> {
                output.accept(MEOItems.FLINT_FLAKE);



//                ItemStack stack = new ItemStack(Items.SEA_PICKLE);
//                stack.set(DataComponents.ITEM_NAME, Component.literal("Pickle Rick"));
//                stack.set(DataComponents.LORE, new ItemLore(List.of(Component.literal("I'm pickle riiick!!"))));
//                output.accept(stack);
            })
            .build();

    public static void initialize() {
    }
}
