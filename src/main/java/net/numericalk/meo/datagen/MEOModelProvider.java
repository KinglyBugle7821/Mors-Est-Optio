package net.numericalk.meo.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.level.block.Blocks;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.items.MEOItems;

public class MEOModelProvider extends FabricModelProvider {

    public MEOModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(MEOBlocks.LOOSE_DIRT);

        MEOBlockStateModelGenerator.registerBreakingStone(
                blockModelGenerators,
                MEOBlocks.BREAKING_STONE
        );

        MEOBlockStateModelGenerator.registerBreakingStone(
                blockModelGenerators,
                MEOBlocks.BREAKING_DEEPSLATE
        );

        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_OAK_LOG,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_OAK_LOG)
        );
        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_SPRUCE_LOG,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_SPRUCE_LOG)
        );
        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_BIRCH_LOG,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_BIRCH_LOG)
        );
        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_JUNGLE_LOG,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_JUNGLE_LOG)
        );
        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_ACACIA_LOG,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_ACACIA_LOG)
        );
        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_DARK_OAK_LOG,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_DARK_OAK_LOG)
        );
        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_MANGROVE_LOG,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_MANGROVE_LOG)
        );
        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_CHERRY_LOG,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_CHERRY_LOG)
        );
        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_PALE_OAK_LOG,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_PALE_OAK_LOG)
        );
        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_WARPED_STEM,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_WARPED_STEM)
        );
        MEOBlockStateModelGenerator.registerBreakingLog(
                blockModelGenerators,
                MEOBlocks.BREAKING_CRIMSON_STEM,
                MEOBlockStateModelGenerator.blockAndTopForEnds(Blocks.STRIPPED_CRIMSON_STEM)
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(MEOItems.WATER_POUCH, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.BANDAGE, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.FLINT_FLAKE, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.PEBBLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.ROCK, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.FLINT_HATCHET, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.FLINT_PICK, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.WOOD_CHIP, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.DRIED_GRASS_SHEAF, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.TWINE, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.CLAY_CLUMP, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.DIRT_CLUMP, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.GRAVEL_CLUMP, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.MUD_CLUMP, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.SAND_CLUMP, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.RED_SAND_CLUMP, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.SOUL_CLUMP, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.GRANITE_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.ANDESITE_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.DIORITE_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.DRIPSTONE_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.SULFUR_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.CINNABAR_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.OBSIDIAN_SHARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.TUFF_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.CALCITE_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.SANDSTONE_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.RED_SANDSTONE_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.NETHERRACK_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.BLACKSTONE_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.BASALT_CHUNK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.END_STONE_CHUNK, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.OAK_BARK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.SPRUCE_BARK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.BIRCH_BARK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.JUNGLE_BARK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.ACACIA_BARK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.DARK_OAK_BARK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.MANGROVE_BARK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.CHERRY_BARK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.PALE_OAK_BARK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.CRIMSON_BARK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.WARPED_BARK, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.STEEL_INGOT, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.STEEL_HELMET, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.STEEL_CHESTPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.STEEL_LEGGINGS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.STEEL_BOOTS, ModelTemplates.FLAT_ITEM);

        itemModelGenerators.generateFlatItem(MEOItems.STEEL_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateSpear(MEOItems.STEEL_SPEAR);
        itemModelGenerators.generateFlatItem(MEOItems.STEEL_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.STEEL_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.STEEL_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerators.generateFlatItem(MEOItems.STEEL_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

    }

    @Override
    public String getName() {
        return "MEOModelProvider";
    }
}
