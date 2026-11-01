package net.numericalk.meo.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.items.MEOItems;

public class MEOModelProvider extends FabricModelProvider {

    public MEOModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
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
        itemModelGenerators.generateFlatItem(MEOItems.FLINT_FLAKE, ModelTemplates.FLAT_HANDHELD_ITEM);
    }

    @Override
    public String getName() {
        return "MEOModelProvider";
    }
}
