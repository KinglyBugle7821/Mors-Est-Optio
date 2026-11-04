package net.numericalk.meo.items;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.blocks.MEOBlocks;

import java.util.function.Function;

public class MEOItems {
    public static final Item FLINT_FLAKE = register(
            MEOItemIds.FLINT_FLAKE,
            settings -> new AxeItem(MEOToolMaterials.FLINT_TOOL_MATERIAL, 0.0F, -3.0F, settings),
            new Item.Properties()
    );

    public static final Item DRIED_GRASS_SHEAF = register(
            MEOItemIds.DRIED_GRASS_SHEAF,
            Item::new,
            new Item.Properties()
    );

    public static final Item TWINE = register(
            MEOItemIds.TWINE,
            Item::new,
            new Item.Properties()
    );

    public static final Item STEEL_INGOT = register(
            MEOItemIds.STEEL_INGOT,
            Item::new,
            new Item.Properties()
    );

    public static final Item STEEL_SWORD = register(
            MEOItemIds.STEEL_SWORD,
            Item::new,
            new Item.Properties().sword(MEOToolMaterials.STEEL_TOOL_MATERIAL, 3.0f, -2.4f)
    );
    public static final Item STEEL_SPEAR = register(
            MEOItemIds.STEEL_SPEAR,
            Item::new,
            new Item.Properties().spear(MEOToolMaterials.STEEL_TOOL_MATERIAL, 1.05F, 1.075F, 0.5F, 3.0F, 10.0F, 6.5F, 5.1F, 10.0F, 4.6F)
    );
    public static final Item STEEL_PICKAXE = register(
            MEOItemIds.STEEL_PICKAXE,
            Item::new,
            new Item.Properties().pickaxe(MEOToolMaterials.STEEL_TOOL_MATERIAL, 1.0f, -2.8f)
    );
    public static final Item STEEL_AXE = register(
            MEOItemIds.STEEL_AXE,
            Item::new,
            new Item.Properties().axe(MEOToolMaterials.STEEL_TOOL_MATERIAL, 5.0f, -3f)
    );
    public static final Item STEEL_SHOVEL = register(
            MEOItemIds.STEEL_SHOVEL,
            settings -> new ShovelItem(MEOToolMaterials.STEEL_TOOL_MATERIAL, 1.5F, -3.0F, settings),
            new Item.Properties()
    );
    public static final Item STEEL_HOE = register(
            MEOItemIds.STEEL_HOE,
            settings -> new HoeItem(MEOToolMaterials.STEEL_TOOL_MATERIAL, -3.0F, 0F, settings),
            new Item.Properties()
    );

    public static final Item STEEL_HELMET = register(
            MEOItemIds.STEEL_HELMET,
            Item::new,
            new Item.Properties().humanoidArmor(MEOArmorMaterial.STEEL, ArmorType.HELMET)
                    .durability(ArmorType.HELMET.getDurability(MEOArmorMaterial.STEEL_BASE_DURABILITY))
    );
    public static final Item STEEL_CHESTPLATE = register(
            MEOItemIds.STEEL_CHESTPLATE,
            Item::new,
            new Item.Properties().humanoidArmor(MEOArmorMaterial.STEEL, ArmorType.CHESTPLATE)
                    .durability(ArmorType.CHESTPLATE.getDurability(MEOArmorMaterial.STEEL_BASE_DURABILITY))
    );

    public static final Item STEEL_LEGGINGS = register(
            MEOItemIds.STEEL_LEGGINGS,
            Item::new,
            new Item.Properties().humanoidArmor(MEOArmorMaterial.STEEL, ArmorType.LEGGINGS)
                    .durability(ArmorType.LEGGINGS.getDurability(MEOArmorMaterial.STEEL_BASE_DURABILITY))
    );

    public static final Item STEEL_BOOTS = register(
            MEOItemIds.STEEL_BOOTS,
            Item::new,
            new Item.Properties().humanoidArmor(MEOArmorMaterial.STEEL, ArmorType.BOOTS)
                    .durability(ArmorType.BOOTS.getDurability(MEOArmorMaterial.STEEL_BASE_DURABILITY))
    );

    public static final Item WOOD_CHIP = register(
            MEOItemIds.WOOD_CHIP,
            Item::new,
            new Item.Properties()
    );

    public static final Item OAK_BARK = register(
            MEOItemIds.OAK_BARK,
            Item::new,
            new Item.Properties()
    );
    public static final Item SPRUCE_BARK = register(
            MEOItemIds.SPRUCE_BARK,
            Item::new,
            new Item.Properties()
    );
    public static final Item BIRCH_BARK = register(
            MEOItemIds.BIRCH_BARK,
            Item::new,
            new Item.Properties()
    );
    public static final Item JUNGLE_BARK = register(
            MEOItemIds.JUNGLE_BARK,
            Item::new,
            new Item.Properties()
    );
    public static final Item ACACIA_BARK = register(
            MEOItemIds.ACACIA_BARK,
            Item::new,
            new Item.Properties()
    );
    public static final Item DARK_OAK_BARK = register(
            MEOItemIds.DARK_OAK_BARK,
            Item::new,
            new Item.Properties()
    );
    public static final Item MANGROVE_BARK = register(
            MEOItemIds.MANGROVE_BARK,
            Item::new,
            new Item.Properties()
    );
    public static final Item CHERRY_BARK = register(
            MEOItemIds.CHERRY_BARK,
            Item::new,
            new Item.Properties()
    );
    public static final Item PALE_OAK_BARK = register(
            MEOItemIds.PALE_OAK_BARK,
            Item::new,
            new Item.Properties()
    );
    public static final Item CRIMSON_BARK = register(
            MEOItemIds.CRIMSON_BARK,
            Item::new,
            new Item.Properties()
    );
    public static final Item WARPED_BARK = register(
            MEOItemIds.WARPED_BARK,
            Item::new,
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
                output.accept(MEOBlocks.GRASS_SHEAF);
                output.accept(MEOItems.DRIED_GRASS_SHEAF);

                output.accept(MEOItems.TWINE);

                output.accept(MEOItems.WOOD_CHIP);

                output.accept(MEOItems.OAK_BARK);
                output.accept(MEOItems.SPRUCE_BARK);
                output.accept(MEOItems.BIRCH_BARK);
                output.accept(MEOItems.JUNGLE_BARK);
                output.accept(MEOItems.ACACIA_BARK);
                output.accept(MEOItems.DARK_OAK_BARK);
                output.accept(MEOItems.MANGROVE_BARK);
                output.accept(MEOItems.CHERRY_BARK);
                output.accept(MEOItems.PALE_OAK_BARK);
                output.accept(MEOItems.CRIMSON_BARK);
                output.accept(MEOItems.WARPED_BARK);

                output.accept(MEOItems.FLINT_FLAKE);
                output.accept(MEOBlocks.BREAKING_OAK_LOG);
                output.accept(MEOBlocks.BREAKING_SPRUCE_LOG);
                output.accept(MEOBlocks.BREAKING_BIRCH_LOG);
                output.accept(MEOBlocks.BREAKING_JUNGLE_LOG);
                output.accept(MEOBlocks.BREAKING_DARK_OAK_LOG);
                output.accept(MEOBlocks.BREAKING_MANGROVE_LOG);
                output.accept(MEOBlocks.BREAKING_CHERRY_LOG);
                output.accept(MEOBlocks.BREAKING_PALE_OAK_LOG);
                output.accept(MEOBlocks.BREAKING_WARPED_STEM);
                output.accept(MEOBlocks.BREAKING_CRIMSON_STEM);

                output.accept(MEOItems.STEEL_INGOT);

                output.accept(MEOItems.STEEL_SWORD);
                output.accept(MEOItems.STEEL_PICKAXE);
                output.accept(MEOItems.STEEL_AXE);
                output.accept(MEOItems.STEEL_SPEAR);
                output.accept(MEOItems.STEEL_HOE);
                output.accept(MEOItems.STEEL_SHOVEL);

                output.accept(MEOItems.STEEL_HELMET);
                output.accept(MEOItems.STEEL_CHESTPLATE);
                output.accept(MEOItems.STEEL_LEGGINGS);
                output.accept(MEOItems.STEEL_BOOTS);
            })
            .build();

    public static void initialize() {
    }
}
