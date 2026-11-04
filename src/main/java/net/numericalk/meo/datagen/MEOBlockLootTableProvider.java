package net.numericalk.meo.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.blocks.custom.GrassSheafBlock;
import net.numericalk.meo.items.MEOItems;

import java.util.concurrent.CompletableFuture;

public class MEOBlockLootTableProvider extends FabricBlockLootSubProvider {
    protected MEOBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        HolderGetter<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

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
}
