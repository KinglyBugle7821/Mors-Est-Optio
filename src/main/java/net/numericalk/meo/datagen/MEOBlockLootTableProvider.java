package net.numericalk.meo.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.advancements.predicates.*;
import net.minecraft.client.renderer.UniformValue;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.blocks.custom.GrassSheafBlock;
import net.numericalk.meo.items.MEOItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MEOBlockLootTableProvider extends FabricBlockLootSubProvider {
    protected MEOBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }
    HolderGetter<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

    @Override
    public void generate() {

        add(Blocks.DIRT, createClumpDrop(Blocks.DIRT, Blocks.DIRT.asItem(), MEOItems.DIRT_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.COARSE_DIRT, createClumpDrop(Blocks.COARSE_DIRT, Blocks.COARSE_DIRT.asItem(), MEOItems.DIRT_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.DIRT_PATH, createClumpDrop(Blocks.DIRT_PATH, Blocks.DIRT.asItem(), MEOItems.DIRT_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.GRASS_BLOCK, createClumpDrop(Blocks.GRASS_BLOCK, Blocks.DIRT.asItem(), MEOItems.DIRT_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.MYCELIUM, createClumpDrop(Blocks.MYCELIUM, Blocks.DIRT.asItem(), MEOItems.DIRT_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.PODZOL, createClumpDrop(Blocks.PODZOL, Blocks.DIRT.asItem(), MEOItems.DIRT_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.ROOTED_DIRT, createClumpDrop(Blocks.ROOTED_DIRT, Blocks.ROOTED_DIRT.asItem(), MEOItems.DIRT_CLUMP, 1f, 4f, ItemTags.SHOVELS));

        add(Blocks.STONE, createClumpDrop(Blocks.STONE, Blocks.COBBLESTONE.asItem(), MEOItems.ROCK, 1f, 1f, MEOItemTagProvider.DIAMOND_LEVEL_PICKAXE));
        add(Blocks.COBBLESTONE, createClumpDrop(Blocks.COBBLESTONE, Blocks.COBBLESTONE.asItem(), MEOItems.PEBBLE, 1f, 1f, MEOItemTagProvider.DIAMOND_LEVEL_PICKAXE));

        add(Blocks.DEEPSLATE, createClumpDrop(Blocks.DEEPSLATE, Blocks.COBBLED_DEEPSLATE.asItem(), MEOItems.ROCK, 1f, 1f, MEOItemTagProvider.DIAMOND_LEVEL_PICKAXE));
        add(Blocks.COBBLED_DEEPSLATE, createClumpDrop(Blocks.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE.asItem(), MEOItems.ROCK, 1f, 1f, MEOItemTagProvider.DIAMOND_LEVEL_PICKAXE));

        add(Blocks.GRANITE, createClumpDrop(Blocks.GRANITE, MEOItems.GRANITE_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.ANDESITE, createClumpDrop(Blocks.ANDESITE, MEOItems.ANDESITE_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.DIORITE, createClumpDrop(Blocks.DIORITE, MEOItems.DIORITE_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.DRIPSTONE_BLOCK, createClumpDrop(Blocks.DRIPSTONE_BLOCK, MEOItems.DRIPSTONE_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.POINTED_DRIPSTONE, createClumpDrop(Blocks.POINTED_DRIPSTONE, MEOItems.DRIPSTONE_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.SULFUR, createClumpDrop(Blocks.SULFUR, MEOItems.SULFUR_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.POTENT_SULFUR, createClumpDrop(Blocks.POTENT_SULFUR, MEOItems.SULFUR_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.CINNABAR, createClumpDrop(Blocks.CINNABAR, MEOItems.CINNABAR_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.OBSIDIAN, createClumpDrop(Blocks.OBSIDIAN, MEOItems.OBSIDIAN_SHARD, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.CRYING_OBSIDIAN, createClumpDrop(Blocks.CRYING_OBSIDIAN, MEOItems.OBSIDIAN_SHARD, 1f, 2f, ItemTags.PICKAXES));
        add(Blocks.TUFF, createClumpDrop(Blocks.TUFF, MEOItems.TUFF_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.CALCITE, createClumpDrop(Blocks.CALCITE, MEOItems.CALCITE_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.SANDSTONE, createClumpDrop(Blocks.SANDSTONE, MEOItems.SANDSTONE_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.RED_SANDSTONE, createClumpDrop(Blocks.RED_SANDSTONE, MEOItems.RED_SANDSTONE_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.NETHERRACK, createClumpDrop(Blocks.NETHERRACK, MEOItems.NETHERRACK_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.BLACKSTONE, createClumpDrop(Blocks.BLACKSTONE, MEOItems.BLACKSTONE_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.BASALT, createClumpDrop(Blocks.BASALT, MEOItems.BASALT_CHUNK, 1f, 4f, ItemTags.PICKAXES));
        add(Blocks.SMOOTH_BASALT, createClumpDrop(Blocks.SMOOTH_BASALT, MEOItems.BASALT_CHUNK, 1f, 4f, ItemTags.PICKAXES));

        add(Blocks.GRAVEL, createClumpDrop(Blocks.GRAVEL, Blocks.GRAVEL.asItem(), MEOItems.GRAVEL_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.MUD, createClumpDrop(Blocks.MUD, Blocks.MUD.asItem(), MEOItems.MUD_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.SAND, createClumpDrop(Blocks.SAND, Blocks.SAND.asItem(), MEOItems.SAND_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.RED_SAND, createClumpDrop(Blocks.RED_SAND, Blocks.RED_SAND.asItem(), MEOItems.RED_SAND_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.SOUL_SAND, createClumpDrop(Blocks.SOUL_SAND, Blocks.SOUL_SAND.asItem(), MEOItems.SOUL_CLUMP, 1f, 4f, ItemTags.SHOVELS));
        add(Blocks.SOUL_SOIL, createClumpDrop(Blocks.SOUL_SOIL, Blocks.SOUL_SOIL.asItem(), MEOItems.SOUL_CLUMP, 1f, 4f, ItemTags.SHOVELS));

        add(Blocks.OAK_LOG, createClumpDrop(Blocks.OAK_LOG, Blocks.OAK_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_OAK_LOG, createClumpDrop(Blocks.STRIPPED_OAK_LOG, Blocks.STRIPPED_OAK_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.OAK_WOOD, createClumpDrop(Blocks.OAK_WOOD, Blocks.OAK_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_OAK_WOOD, createClumpDrop(Blocks.STRIPPED_OAK_WOOD, Blocks.STRIPPED_OAK_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.SPRUCE_LOG, createClumpDrop(Blocks.SPRUCE_LOG, Blocks.SPRUCE_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_SPRUCE_LOG, createClumpDrop(Blocks.STRIPPED_SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.SPRUCE_WOOD, createClumpDrop(Blocks.SPRUCE_WOOD, Blocks.SPRUCE_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_SPRUCE_WOOD, createClumpDrop(Blocks.STRIPPED_SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.BIRCH_LOG, createClumpDrop(Blocks.BIRCH_LOG, Blocks.BIRCH_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_BIRCH_LOG, createClumpDrop(Blocks.STRIPPED_BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.BIRCH_WOOD, createClumpDrop(Blocks.BIRCH_WOOD, Blocks.BIRCH_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_BIRCH_WOOD, createClumpDrop(Blocks.STRIPPED_BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.JUNGLE_LOG, createClumpDrop(Blocks.JUNGLE_LOG, Blocks.JUNGLE_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_JUNGLE_LOG, createClumpDrop(Blocks.STRIPPED_JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.JUNGLE_WOOD, createClumpDrop(Blocks.JUNGLE_WOOD, Blocks.JUNGLE_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_JUNGLE_WOOD, createClumpDrop(Blocks.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.ACACIA_LOG, createClumpDrop(Blocks.ACACIA_LOG, Blocks.ACACIA_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_ACACIA_LOG, createClumpDrop(Blocks.STRIPPED_ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.ACACIA_WOOD, createClumpDrop(Blocks.ACACIA_WOOD, Blocks.ACACIA_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_ACACIA_WOOD, createClumpDrop(Blocks.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.DARK_OAK_LOG, createClumpDrop(Blocks.DARK_OAK_LOG, Blocks.DARK_OAK_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_DARK_OAK_LOG, createClumpDrop(Blocks.STRIPPED_DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.DARK_OAK_WOOD, createClumpDrop(Blocks.DARK_OAK_WOOD, Blocks.DARK_OAK_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_DARK_OAK_WOOD, createClumpDrop(Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.MANGROVE_LOG, createClumpDrop(Blocks.MANGROVE_LOG, Blocks.MANGROVE_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_MANGROVE_LOG, createClumpDrop(Blocks.STRIPPED_MANGROVE_LOG, Blocks.STRIPPED_MANGROVE_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.MANGROVE_WOOD, createClumpDrop(Blocks.MANGROVE_WOOD, Blocks.MANGROVE_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_MANGROVE_WOOD, createClumpDrop(Blocks.STRIPPED_MANGROVE_WOOD, Blocks.STRIPPED_MANGROVE_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.CHERRY_LOG, createClumpDrop(Blocks.CHERRY_LOG, Blocks.CHERRY_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_CHERRY_LOG, createClumpDrop(Blocks.STRIPPED_CHERRY_LOG, Blocks.STRIPPED_CHERRY_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.CHERRY_WOOD, createClumpDrop(Blocks.CHERRY_WOOD, Blocks.CHERRY_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_CHERRY_WOOD, createClumpDrop(Blocks.STRIPPED_CHERRY_WOOD, Blocks.STRIPPED_CHERRY_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.PALE_OAK_LOG, createClumpDrop(Blocks.PALE_OAK_LOG, Blocks.PALE_OAK_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_PALE_OAK_LOG, createClumpDrop(Blocks.STRIPPED_PALE_OAK_LOG, Blocks.STRIPPED_PALE_OAK_LOG.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.PALE_OAK_WOOD, createClumpDrop(Blocks.PALE_OAK_WOOD, Blocks.PALE_OAK_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_PALE_OAK_WOOD, createClumpDrop(Blocks.STRIPPED_PALE_OAK_WOOD, Blocks.STRIPPED_PALE_OAK_WOOD.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.CRIMSON_STEM, createClumpDrop(Blocks.CRIMSON_STEM, Blocks.CRIMSON_STEM.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_CRIMSON_STEM, createClumpDrop(Blocks.STRIPPED_CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.CRIMSON_HYPHAE, createClumpDrop(Blocks.CRIMSON_HYPHAE, Blocks.CRIMSON_HYPHAE.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_CRIMSON_HYPHAE, createClumpDrop(Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.WARPED_STEM, createClumpDrop(Blocks.WARPED_STEM, Blocks.WARPED_STEM.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_WARPED_STEM, createClumpDrop(Blocks.STRIPPED_WARPED_STEM, Blocks.STRIPPED_WARPED_STEM.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.WARPED_HYPHAE, createClumpDrop(Blocks.WARPED_HYPHAE, Blocks.WARPED_HYPHAE.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));
        add(Blocks.STRIPPED_WARPED_HYPHAE, createClumpDrop(Blocks.STRIPPED_WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE.asItem(), MEOItems.WOOD_CHIP, 1f, 4f, ItemTags.AXES));

        add(Blocks.SHORT_GRASS, block -> this.createGrassDrops(block)
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(MEOBlocks.GRASS_SHEAF)
                                .when(MatchTool.toolMatches(
                                        ItemPredicate.Builder.item()
                                                .of(itemLookup, MEOItems.FLINT_FLAKE)
                                ))
                        )
                )
        );
        add(MEOBlocks.GRASS_SHEAF,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(
                                                AlternativesEntry.alternatives(
                                                        LootItem.lootTableItem(MEOItems.DRIED_GRASS_SHEAF)
                                                                .when(
                                                                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(MEOBlocks.GRASS_SHEAF)
                                                                                .setProperties(
                                                                                        StatePropertiesPredicate.Builder.properties()
                                                                                                .hasProperty(GrassSheafBlock.IS_DRY, true)
                                                                                )
                                                                ),
                                                        LootItem.lootTableItem(MEOBlocks.GRASS_SHEAF)
                                                )
                                        )
                        )
        );
    }
    private LootTable.Builder createClumpDrop(Block silkTouchBlock, Item toolBlock, Item dropItem, float min, float max, TagKey<Item> toolTag) {
        HolderGetter<Enchantment> enchantmentLookup =
                registries.lookupOrThrow(Registries.ENCHANTMENT);

        DataComponentMatchers silkTouchMatcher = DataComponentMatchers.Builder.components()
                .partial(
                        DataComponentPredicates.ENCHANTMENTS,
                        EnchantmentsPredicate.enchantments(
                                List.of(
                                        new EnchantmentPredicate(
                                                enchantmentLookup.getOrThrow(Enchantments.SILK_TOUCH),
                                                MinMaxBounds.Ints.atLeast(1)
                                        )
                                )
                        )
                )
                .build();

        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(AlternativesEntry.alternatives(

                                LootItem.lootTableItem(silkTouchBlock)
                                        .when(MatchTool.toolMatches(
                                                ItemPredicate.Builder.item()
                                                        .withComponents(silkTouchMatcher)
                                        )),

                                LootItem.lootTableItem(toolBlock)
                                        .when(MatchTool.toolMatches(
                                                ItemPredicate.Builder.item()
                                                        .of(itemLookup, toolTag)
                                        )),

                                LootItem.lootTableItem(dropItem)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(min, max)
                                        ))
                                        .apply(ApplyExplosionDecay.explosionDecay())
                        ))
                );
    }

    private LootTable.Builder createClumpDrop(Block silkTouchBlock, Item dropItem, float min, float max, TagKey<Item> toolTag) {
        HolderGetter<Enchantment> enchantmentLookup =
                registries.lookupOrThrow(Registries.ENCHANTMENT);

        DataComponentMatchers silkTouchMatcher = DataComponentMatchers.Builder.components()
                .partial(
                        DataComponentPredicates.ENCHANTMENTS,
                        EnchantmentsPredicate.enchantments(
                                List.of(
                                        new EnchantmentPredicate(
                                                enchantmentLookup.getOrThrow(Enchantments.SILK_TOUCH),
                                                MinMaxBounds.Ints.atLeast(1)
                                        )
                                )
                        )
                )
                .build();

        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(AlternativesEntry.alternatives(

                                LootItem.lootTableItem(silkTouchBlock)
                                        .when(MatchTool.toolMatches(
                                                ItemPredicate.Builder.item()
                                                        .withComponents(silkTouchMatcher)
                                        )),

                                LootItem.lootTableItem(dropItem)
                                        .when(MatchTool.toolMatches(
                                                ItemPredicate.Builder.item()
                                                        .of(itemLookup, toolTag)
                                        ))
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(min, max)
                                        ))
                                        .apply(ApplyExplosionDecay.explosionDecay())
                        ))
                );
    }
}
