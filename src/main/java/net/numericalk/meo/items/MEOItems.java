package net.numericalk.meo.items;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.equipment.ArmorType;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.components.MEOComponents;
import net.numericalk.meo.items.custom.WaterPouchItem;

import java.util.function.Function;

public class MEOItems {
    public static final Item FLINT_FLAKE = register(
            MEOItemIds.FLINT_FLAKE,
            Item::new,
            new Item.Properties().axe(MEOToolMaterials.FLINT_TOOL_MATERIAL, 0.0F, -3F)
    );

    public static final Item WATER_POUCH = register(
            MEOItemIds.WATER_POUCH,
            WaterPouchItem::new,
            new Item.Properties()
                    .stacksTo(1)
                    .component(MEOComponents.WATER_AMOUNT, 100.0f)
                    .component(
                            DataComponents.CONSUMABLE,
                            Consumable.builder()
                                    .consumeSeconds(2.0f)
                                    .animation(ItemUseAnimation.DRINK)
                                    .build()
                    )
    );

    public static final Item GRANITE_CHUNK = register(MEOItemIds.GRANITE_CHUNK, Item::new, new Item.Properties());
    public static final Item ANDESITE_CHUNK = register(MEOItemIds.ANDESITE_CHUNK, Item::new, new Item.Properties());
    public static final Item DIORITE_CHUNK = register(MEOItemIds.DIORITE_CHUNK, Item::new, new Item.Properties());
    public static final Item DRIPSTONE_CHUNK = register(MEOItemIds.DRIPSTONE_CHUNK, Item::new, new Item.Properties());
    public static final Item SULFUR_CHUNK = register(MEOItemIds.SULFUR_CHUNK, Item::new, new Item.Properties());
    public static final Item CINNABAR_CHUNK = register(MEOItemIds.CINNABAR_CHUNK, Item::new, new Item.Properties());
    public static final Item OBSIDIAN_SHARD = register(MEOItemIds.OBSIDIAN_SHARD, Item::new, new Item.Properties());
    public static final Item TUFF_CHUNK = register(MEOItemIds.TUFF_CHUNK, Item::new, new Item.Properties());
    public static final Item CALCITE_CHUNK = register(MEOItemIds.CALCITE_CHUNK, Item::new, new Item.Properties());
    public static final Item SANDSTONE_CHUNK = register(MEOItemIds.SANDSTONE_CHUNK, Item::new, new Item.Properties());
    public static final Item RED_SANDSTONE_CHUNK = register(MEOItemIds.RED_SANDSTONE_CHUNK, Item::new, new Item.Properties());
    public static final Item NETHERRACK_CHUNK = register(MEOItemIds.NETHERRACK_CHUNK, Item::new, new Item.Properties());
    public static final Item BLACKSTONE_CHUNK = register(MEOItemIds.BLACKSTONE_CHUNK, Item::new, new Item.Properties());
    public static final Item BASALT_CHUNK = register(MEOItemIds.BASALT_CHUNK, Item::new, new Item.Properties());
    public static final Item END_STONE_CHUNK = register(MEOItemIds.END_STONE_CHUNK, Item::new, new Item.Properties());

    public static final Item DRIED_GRASS_SHEAF = register(
            MEOItemIds.DRIED_GRASS_SHEAF,
            Item::new,
            new Item.Properties()
    );

    public static final Item PEBBLE = register(
            MEOItemIds.PEBBLE,
            Item::new,
            new Item.Properties()
    );
    public static final Item ROCK = register(
            MEOItemIds.ROCK,
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

    public static final Item FLINT_HATCHET = register(
            MEOItemIds.FLINT_HATCHET,
            Item::new,
            new Item.Properties().axe(MEOToolMaterials.REINFORCED_FLINT_TOOL_MATERIAL, 1.0f, -3f)
    );
    public static final Item FLINT_PICK = register(
            MEOItemIds.FLINT_PICK,
            Item::new,
            new Item.Properties().pickaxe(MEOToolMaterials.REINFORCED_FLINT_TOOL_MATERIAL, 1.0f, -3f)
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

    public static final Item CLAY_CLUMP = register(
            MEOItemIds.CLAY_CLUMP,
            Item::new,
            new Item.Properties()
    );
    public static final Item DIRT_CLUMP = register(
            MEOItemIds.DIRT_CLUMP,
            Item::new,
            new Item.Properties()
    );
    public static final Item GRAVEL_CLUMP = register(
            MEOItemIds.GRAVEL_CLUMP,
            Item::new,
            new Item.Properties()
    );
    public static final Item MUD_CLUMP = register(
            MEOItemIds.MUD_CLUMP,
            Item::new,
            new Item.Properties()
    );
    public static final Item SAND_CLUMP = register(
            MEOItemIds.SAND_CLUMP,
            Item::new,
            new Item.Properties()
    );
    public static final Item RED_SAND_CLUMP = register(
            MEOItemIds.RED_SAND_CLUMP,
            Item::new,
            new Item.Properties()
    );
    public static final Item SOUL_CLUMP = register(
            MEOItemIds.SOUL_CLUMP,
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

                output.accept(MEOBlocks.LOOSE_DIRT);

                output.accept(MEOItems.TWINE);

                output.accept(MEOItems.CLAY_CLUMP);
                output.accept(MEOItems.DIRT_CLUMP);
                output.accept(MEOItems.GRAVEL_CLUMP);
                output.accept(MEOItems.MUD_CLUMP);
                output.accept(MEOItems.SAND_CLUMP);
                output.accept(MEOItems.RED_SAND_CLUMP);
                output.accept(MEOItems.SOUL_CLUMP);

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

                output.accept(MEOItems.GRANITE_CHUNK);
                output.accept(MEOItems.ANDESITE_CHUNK);
                output.accept(MEOItems.DIORITE_CHUNK);
                output.accept(MEOItems.DRIPSTONE_CHUNK);
                output.accept(MEOItems.SULFUR_CHUNK);
                output.accept(MEOItems.CINNABAR_CHUNK);
                output.accept(MEOItems.OBSIDIAN_SHARD);
                output.accept(MEOItems.TUFF_CHUNK);
                output.accept(MEOItems.CALCITE_CHUNK);
                output.accept(MEOItems.SANDSTONE_CHUNK);
                output.accept(MEOItems.RED_SANDSTONE_CHUNK);
                output.accept(MEOItems.NETHERRACK_CHUNK);
                output.accept(MEOItems.BLACKSTONE_CHUNK);
                output.accept(MEOItems.BASALT_CHUNK);
                output.accept(MEOItems.END_STONE_CHUNK);

                output.accept(MEOItems.FLINT_HATCHET);
                output.accept(MEOItems.FLINT_PICK);

                output.accept(MEOItems.FLINT_FLAKE);

                output.accept(MEOBlocks.BREAKING_STONE);
                output.accept(MEOBlocks.BREAKING_DEEPSLATE);

                output.accept(MEOBlocks.BREAKING_OAK_LOG);
                output.accept(MEOBlocks.BREAKING_SPRUCE_LOG);
                output.accept(MEOBlocks.BREAKING_BIRCH_LOG);
                output.accept(MEOBlocks.BREAKING_JUNGLE_LOG);
                output.accept(MEOBlocks.BREAKING_ACACIA_LOG);
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
