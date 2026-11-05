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
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyExplosionDecay;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
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

        add(Blocks.DIRT, createClumpDrop(Blocks.DIRT, MEOItems.DIRT_CLUMP));
        add(Blocks.COARSE_DIRT, createClumpDrop(Blocks.COARSE_DIRT, MEOItems.DIRT_CLUMP));
        add(Blocks.DIRT_PATH, createClumpDrop(Blocks.DIRT_PATH, Blocks.DIRT, MEOItems.DIRT_CLUMP));
        add(Blocks.GRASS_BLOCK, createClumpDrop(Blocks.GRASS_BLOCK, Blocks.DIRT, MEOItems.DIRT_CLUMP));
        add(Blocks.MYCELIUM, createClumpDrop(Blocks.MYCELIUM, Blocks.DIRT, MEOItems.DIRT_CLUMP));
        add(Blocks.PODZOL, createClumpDrop(Blocks.PODZOL, Blocks.DIRT, MEOItems.DIRT_CLUMP));
        add(Blocks.ROOTED_DIRT, createClumpDrop(Blocks.ROOTED_DIRT, MEOItems.DIRT_CLUMP));

        add(Blocks.GRAVEL, createClumpDrop(Blocks.GRAVEL, MEOItems.GRAVEL_CLUMP));
        add(Blocks.MUD, createClumpDrop(Blocks.MUD, MEOItems.MUD_CLUMP));
        add(Blocks.SAND, createClumpDrop(Blocks.SAND, MEOItems.SAND_CLUMP));
        add(Blocks.RED_SAND, createClumpDrop(Blocks.RED_SAND, MEOItems.RED_SAND_CLUMP));
        add(Blocks.SOUL_SAND, createClumpDrop(Blocks.SOUL_SAND, MEOItems.SOUL_CLUMP));
        add(Blocks.SOUL_SOIL, createClumpDrop(Blocks.SOUL_SOIL, MEOItems.SOUL_CLUMP));

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
    private LootTable.Builder createClumpDrop(Block block, Item clumpItem) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
                        .add(
                                AlternativesEntry.alternatives(
                                        LootItem.lootTableItem(block)
                                                .when(MatchTool.toolMatches(
                                                        ItemPredicate.Builder.item()
                                                                .of(itemLookup, ItemTags.SHOVELS)
                                                )),

                                        LootItem.lootTableItem(clumpItem)
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(1.0F, 4.0F)
                                                ))
                                                .apply(ApplyExplosionDecay.explosionDecay())
                                )
                        )
                );
    }


    private LootTable.Builder createClumpDrop(Block block, Block baseBlock, Item clumpItem) {
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

                                // Silk Touch: drop the original block
                                LootItem.lootTableItem(block)
                                        .when(MatchTool.toolMatches(
                                                ItemPredicate.Builder.item()
                                                        .withComponents(silkTouchMatcher)
                                        )),

                                // Shovel: drop the base block
                                LootItem.lootTableItem(baseBlock)
                                        .when(MatchTool.toolMatches(
                                                ItemPredicate.Builder.item()
                                                        .of(itemLookup, ItemTags.SHOVELS)
                                        )),

                                // Default: drop 1-4 clumps
                                LootItem.lootTableItem(clumpItem)
                                        .apply(SetItemCountFunction.setCount(
                                                UniformGenerator.between(1.0F, 4.0F)
                                        ))
                                        .apply(ApplyExplosionDecay.explosionDecay())
                        ))
                );
    }
}
