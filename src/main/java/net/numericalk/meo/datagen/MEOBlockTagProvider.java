package net.numericalk.meo.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.blocks.MEOBlockItemIds;

import java.util.concurrent.CompletableFuture;

public class MEOBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
    public static final TagKey<Block> WOOD_BLOCKS = TagKey.create(Registries.BLOCK, MorsEstOptio.id("wood_blocks"));


    public MEOBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        builder(BlockTags.LOGS)
                .add(MEOBlockItemIds.BREAKING_OAK_LOG)
                .add(MEOBlockItemIds.BREAKING_SPRUCE_LOG)
                .add(MEOBlockItemIds.BREAKING_BIRCH_LOG)
                .add(MEOBlockItemIds.BREAKING_JUNGLE_LOG)
                .add(MEOBlockItemIds.BREAKING_ACACIA_LOG)
                .add(MEOBlockItemIds.BREAKING_DARK_OAK_LOG)
                .add(MEOBlockItemIds.BREAKING_CHERRY_LOG)
                .add(MEOBlockItemIds.BREAKING_PALE_OAK_LOG)
                .add(MEOBlockItemIds.BREAKING_WARPED_STEM)
                .add(MEOBlockItemIds.BREAKING_CRIMSON_STEM);
        builder(BlockTags.MINEABLE_WITH_AXE)
                .add(MEOBlockItemIds.BREAKING_OAK_LOG)
                .add(MEOBlockItemIds.BREAKING_SPRUCE_LOG)
                .add(MEOBlockItemIds.BREAKING_BIRCH_LOG)
                .add(MEOBlockItemIds.BREAKING_JUNGLE_LOG)
                .add(MEOBlockItemIds.BREAKING_ACACIA_LOG)
                .add(MEOBlockItemIds.BREAKING_DARK_OAK_LOG)
                .add(MEOBlockItemIds.BREAKING_CHERRY_LOG)
                .add(MEOBlockItemIds.BREAKING_PALE_OAK_LOG)
                .add(MEOBlockItemIds.BREAKING_WARPED_STEM)
                .add(MEOBlockItemIds.BREAKING_CRIMSON_STEM);

        builder(WOOD_BLOCKS)
                //LOGS
                .add(BlockItemIds.OAK_LOG)
                .add(BlockItemIds.SPRUCE_LOG)
                .add(BlockItemIds.BIRCH_LOG)
                .add(BlockItemIds.JUNGLE_LOG)
                .add(BlockItemIds.ACACIA_LOG)
                .add(BlockItemIds.DARK_OAK_LOG)
                .add(BlockItemIds.MANGROVE_LOG)
                .add(BlockItemIds.CHERRY_LOG)
                .add(BlockItemIds.PALE_OAK_LOG)
                .add(BlockItemIds.CRIMSON_STEM)
                .add(BlockItemIds.WARPED_STEM)
                .add(BlockItemIds.BAMBOO_BLOCK)

                .add(BlockItemIds.STRIPPED_OAK_LOG)
                .add(BlockItemIds.STRIPPED_SPRUCE_LOG)
                .add(BlockItemIds.STRIPPED_BIRCH_LOG)
                .add(BlockItemIds.STRIPPED_JUNGLE_LOG)
                .add(BlockItemIds.STRIPPED_ACACIA_LOG)
                .add(BlockItemIds.STRIPPED_DARK_OAK_LOG)
                .add(BlockItemIds.STRIPPED_MANGROVE_LOG)
                .add(BlockItemIds.STRIPPED_CHERRY_LOG)
                .add(BlockItemIds.STRIPPED_PALE_OAK_LOG)
                .add(BlockItemIds.STRIPPED_CRIMSON_STEM)
                .add(BlockItemIds.STRIPPED_WARPED_STEM)
                .add(BlockItemIds.STRIPPED_BAMBOO_BLOCK)

                //WOODS
                .add(BlockItemIds.OAK_WOOD)
                .add(BlockItemIds.SPRUCE_WOOD)
                .add(BlockItemIds.BIRCH_WOOD)
                .add(BlockItemIds.JUNGLE_WOOD)
                .add(BlockItemIds.ACACIA_WOOD)
                .add(BlockItemIds.DARK_OAK_WOOD)
                .add(BlockItemIds.MANGROVE_WOOD)
                .add(BlockItemIds.CHERRY_WOOD)
                .add(BlockItemIds.PALE_OAK_WOOD)
                .add(BlockItemIds.CRIMSON_HYPHAE)
                .add(BlockItemIds.WARPED_HYPHAE)

                .add(BlockItemIds.STRIPPED_OAK_WOOD)
                .add(BlockItemIds.STRIPPED_SPRUCE_WOOD)
                .add(BlockItemIds.STRIPPED_BIRCH_WOOD)
                .add(BlockItemIds.STRIPPED_JUNGLE_WOOD)
                .add(BlockItemIds.STRIPPED_ACACIA_WOOD)
                .add(BlockItemIds.STRIPPED_DARK_OAK_WOOD)
                .add(BlockItemIds.STRIPPED_MANGROVE_WOOD)
                .add(BlockItemIds.STRIPPED_CHERRY_WOOD)
                .add(BlockItemIds.STRIPPED_PALE_OAK_WOOD)
                .add(BlockItemIds.STRIPPED_CRIMSON_HYPHAE)
                .add(BlockItemIds.STRIPPED_WARPED_HYPHAE)

                //BUTTONS
                .add(BlockItemIds.OAK_BUTTON)
                .add(BlockItemIds.SPRUCE_BUTTON)
                .add(BlockItemIds.BIRCH_BUTTON)
                .add(BlockItemIds.JUNGLE_BUTTON)
                .add(BlockItemIds.ACACIA_BUTTON)
                .add(BlockItemIds.DARK_OAK_BUTTON)
                .add(BlockItemIds.MANGROVE_BUTTON)
                .add(BlockItemIds.CHERRY_BUTTON)
                .add(BlockItemIds.PALE_OAK_BUTTON)
                .add(BlockItemIds.CRIMSON_BUTTON)
                .add(BlockItemIds.WARPED_BUTTON)
                .add(BlockItemIds.BAMBOO_BUTTON)

                //DOORS
                .add(BlockItemIds.OAK_DOOR)
                .add(BlockItemIds.SPRUCE_DOOR)
                .add(BlockItemIds.BIRCH_DOOR)
                .add(BlockItemIds.JUNGLE_DOOR)
                .add(BlockItemIds.ACACIA_DOOR)
                .add(BlockItemIds.DARK_OAK_DOOR)
                .add(BlockItemIds.MANGROVE_DOOR)
                .add(BlockItemIds.CHERRY_DOOR)
                .add(BlockItemIds.PALE_OAK_DOOR)
                .add(BlockItemIds.CRIMSON_DOOR)
                .add(BlockItemIds.WARPED_DOOR)
                .add(BlockItemIds.BAMBOO_DOOR)

                //FENCE
                .add(BlockItemIds.OAK_FENCE)
                .add(BlockItemIds.SPRUCE_FENCE)
                .add(BlockItemIds.BIRCH_FENCE)
                .add(BlockItemIds.JUNGLE_FENCE)
                .add(BlockItemIds.ACACIA_FENCE)
                .add(BlockItemIds.DARK_OAK_FENCE)
                .add(BlockItemIds.MANGROVE_FENCE)
                .add(BlockItemIds.CHERRY_FENCE)
                .add(BlockItemIds.PALE_OAK_FENCE)
                .add(BlockItemIds.CRIMSON_FENCE)
                .add(BlockItemIds.WARPED_FENCE)
                .add(BlockItemIds.BAMBOO_FENCE)

                .add(BlockItemIds.OAK_FENCE_GATE)
                .add(BlockItemIds.SPRUCE_FENCE_GATE)
                .add(BlockItemIds.BIRCH_FENCE_GATE)
                .add(BlockItemIds.JUNGLE_FENCE_GATE)
                .add(BlockItemIds.ACACIA_FENCE_GATE)
                .add(BlockItemIds.DARK_OAK_FENCE_GATE)
                .add(BlockItemIds.MANGROVE_FENCE_GATE)
                .add(BlockItemIds.CHERRY_FENCE_GATE)
                .add(BlockItemIds.PALE_OAK_FENCE_GATE)
                .add(BlockItemIds.CRIMSON_FENCE_GATE)
                .add(BlockItemIds.WARPED_FENCE_GATE)
                .add(BlockItemIds.BAMBOO_FENCE_GATE)

                //SIGN
                .add(BlockItemIds.OAK_SIGN)
                .add(BlockItemIds.SPRUCE_SIGN)
                .add(BlockItemIds.BIRCH_SIGN)
                .add(BlockItemIds.JUNGLE_SIGN)
                .add(BlockItemIds.ACACIA_SIGN)
                .add(BlockItemIds.DARK_OAK_SIGN)
                .add(BlockItemIds.MANGROVE_SIGN)
                .add(BlockItemIds.CHERRY_SIGN)
                .add(BlockItemIds.PALE_OAK_SIGN)
                .add(BlockItemIds.CRIMSON_SIGN)
                .add(BlockItemIds.WARPED_SIGN)
                .add(BlockItemIds.BAMBOO_SIGN)

                .add(BlockItemIds.OAK_HANGING_SIGN)
                .add(BlockItemIds.SPRUCE_HANGING_SIGN)
                .add(BlockItemIds.BIRCH_HANGING_SIGN)
                .add(BlockItemIds.JUNGLE_HANGING_SIGN)
                .add(BlockItemIds.ACACIA_HANGING_SIGN)
                .add(BlockItemIds.DARK_OAK_HANGING_SIGN)
                .add(BlockItemIds.MANGROVE_HANGING_SIGN)
                .add(BlockItemIds.CHERRY_HANGING_SIGN)
                .add(BlockItemIds.PALE_OAK_HANGING_SIGN)
                .add(BlockItemIds.CRIMSON_HANGING_SIGN)
                .add(BlockItemIds.WARPED_HANGING_SIGN)
                .add(BlockItemIds.BAMBOO_HANGING_SIGN)

                //SHELF
                .add(BlockItemIds.OAK_SHELF)
                .add(BlockItemIds.SPRUCE_SHELF)
                .add(BlockItemIds.BIRCH_SHELF)
                .add(BlockItemIds.JUNGLE_SHELF)
                .add(BlockItemIds.ACACIA_SHELF)
                .add(BlockItemIds.DARK_OAK_SHELF)
                .add(BlockItemIds.MANGROVE_SHELF)
                .add(BlockItemIds.CHERRY_SHELF)
                .add(BlockItemIds.PALE_OAK_SHELF)
                .add(BlockItemIds.CRIMSON_SHELF)
                .add(BlockItemIds.WARPED_SHELF)
                .add(BlockItemIds.BAMBOO_SHELF)

                //PLANKS
                .add(BlockItemIds.OAK_PLANKS)
                .add(BlockItemIds.SPRUCE_PLANKS)
                .add(BlockItemIds.BIRCH_PLANKS)
                .add(BlockItemIds.JUNGLE_PLANKS)
                .add(BlockItemIds.ACACIA_PLANKS)
                .add(BlockItemIds.DARK_OAK_PLANKS)
                .add(BlockItemIds.MANGROVE_PLANKS)
                .add(BlockItemIds.CHERRY_PLANKS)
                .add(BlockItemIds.PALE_OAK_PLANKS)
                .add(BlockItemIds.CRIMSON_PLANKS)
                .add(BlockItemIds.WARPED_PLANKS)
                .add(BlockItemIds.BAMBOO_PLANKS)

                //PRESSURE PLATES
                .add(BlockItemIds.OAK_PRESSURE_PLATE)
                .add(BlockItemIds.SPRUCE_PRESSURE_PLATE)
                .add(BlockItemIds.BIRCH_PRESSURE_PLATE)
                .add(BlockItemIds.JUNGLE_PRESSURE_PLATE)
                .add(BlockItemIds.ACACIA_PRESSURE_PLATE)
                .add(BlockItemIds.DARK_OAK_PRESSURE_PLATE)
                .add(BlockItemIds.MANGROVE_PRESSURE_PLATE)
                .add(BlockItemIds.CHERRY_PRESSURE_PLATE)
                .add(BlockItemIds.PALE_OAK_PRESSURE_PLATE)
                .add(BlockItemIds.CRIMSON_PRESSURE_PLATE)
                .add(BlockItemIds.WARPED_PRESSURE_PLATE)
                .add(BlockItemIds.BAMBOO_PRESSURE_PLATE)

                //SLABS
                .add(BlockItemIds.OAK_SLAB)
                .add(BlockItemIds.SPRUCE_SLAB)
                .add(BlockItemIds.BIRCH_SLAB)
                .add(BlockItemIds.JUNGLE_SLAB)
                .add(BlockItemIds.ACACIA_SLAB)
                .add(BlockItemIds.DARK_OAK_SLAB)
                .add(BlockItemIds.MANGROVE_SLAB)
                .add(BlockItemIds.CHERRY_SLAB)
                .add(BlockItemIds.PALE_OAK_SLAB)
                .add(BlockItemIds.CRIMSON_SLAB)
                .add(BlockItemIds.WARPED_SLAB)
                .add(BlockItemIds.BAMBOO_SLAB)

                //STAIRS
                .add(BlockItemIds.OAK_STAIRS)
                .add(BlockItemIds.SPRUCE_STAIRS)
                .add(BlockItemIds.BIRCH_STAIRS)
                .add(BlockItemIds.JUNGLE_STAIRS)
                .add(BlockItemIds.ACACIA_STAIRS)
                .add(BlockItemIds.DARK_OAK_STAIRS)
                .add(BlockItemIds.MANGROVE_STAIRS)
                .add(BlockItemIds.CHERRY_STAIRS)
                .add(BlockItemIds.PALE_OAK_STAIRS)
                .add(BlockItemIds.CRIMSON_STAIRS)
                .add(BlockItemIds.WARPED_STAIRS)
                .add(BlockItemIds.BAMBOO_STAIRS)

                //TRAPDOORS
                .add(BlockItemIds.OAK_TRAPDOOR)
                .add(BlockItemIds.SPRUCE_TRAPDOOR)
                .add(BlockItemIds.BIRCH_TRAPDOOR)
                .add(BlockItemIds.JUNGLE_TRAPDOOR)
                .add(BlockItemIds.ACACIA_TRAPDOOR)
                .add(BlockItemIds.DARK_OAK_TRAPDOOR)
                .add(BlockItemIds.MANGROVE_TRAPDOOR)
                .add(BlockItemIds.CHERRY_TRAPDOOR)
                .add(BlockItemIds.PALE_OAK_TRAPDOOR)
                .add(BlockItemIds.CRIMSON_TRAPDOOR)
                .add(BlockItemIds.WARPED_TRAPDOOR)
                .add(BlockItemIds.BAMBOO_TRAPDOOR)

                //TYPE 3
                .add(BlockItemIds.CHISELED_BOOKSHELF)
                .add(BlockItemIds.BOOKSHELF)
                .add(BlockItemIds.CAMPFIRE)
                .add(BlockItemIds.SOUL_CAMPFIRE)
                .add(BlockItemIds.CHEST)
                .add(BlockItemIds.BARREL)
                .add(BlockItemIds.CRAFTING_TABLE)
                .add(BlockItemIds.CARTOGRAPHY_TABLE)
                .add(BlockItemIds.FLETCHING_TABLE)
                .add(BlockItemIds.SMITHING_TABLE)
                .add(BlockItemIds.COMPOSTER)
                .add(BlockItemIds.LADDER)
                .add(BlockItemIds.SCAFFOLDING)
                .add(BlockItemIds.LECTERN)
                .setReplace(true);
    }
}
