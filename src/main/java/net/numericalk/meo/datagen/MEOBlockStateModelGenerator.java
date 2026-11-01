package net.numericalk.meo.datagen;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.BlockModelDefinitionGenerator;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.blocks.custom.BreakingLogBlock;

import java.util.Optional;

public class MEOBlockStateModelGenerator {
    public static final ModelTemplate BREAKING_LOG_STAGE_1 = block("breaking_log_stage_1", "_stage_1", TextureSlot.TOP, TextureSlot.SIDE);
    public static final ModelTemplate BREAKING_LOG_STAGE_2 = block("breaking_log_stage_2", "_stage_2", TextureSlot.TOP, TextureSlot.SIDE);
    public static final ModelTemplate BREAKING_LOG_STAGE_3 = block("breaking_log_stage_3", "_stage_3", TextureSlot.TOP, TextureSlot.SIDE);

    private static ModelTemplate block(String parent, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(MorsEstOptio.id("block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static ModelTemplate block(String parent, String variant, TextureSlot... requiredTextureKeys) {
        return new ModelTemplate(Optional.of(MorsEstOptio.id("block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }


    public static TextureMapping blockAndTopForEnds(Block block) {
        return new TextureMapping()
                .put(TextureSlot.TOP, new Material(ModelLocationUtils.getModelLocation(block, "_top")))
                .put(TextureSlot.BOTTOM, new Material(ModelLocationUtils.getModelLocation(block, "_top")))
                .put(TextureSlot.SIDE, new Material(ModelLocationUtils.getModelLocation(block)));
    }


    private static BlockModelDefinitionGenerator createBreakingLogBlockStates(Block breakingLogBlock, Identifier breakingLogStage1Id, Identifier breakingLogStage2Id, Identifier breakingLogStage3Id) {
        MultiVariant breakingLogStage1Model = BlockModelGenerators.plainVariant(breakingLogStage1Id);
        MultiVariant breakingLogStage2Model = BlockModelGenerators.plainVariant(breakingLogStage2Id);
        MultiVariant breakingLogStage3Model = BlockModelGenerators.plainVariant(breakingLogStage3Id);
        return MultiVariantGenerator.dispatch(breakingLogBlock)
                .with(PropertyDispatch.initial(BreakingLogBlock.AXIS, BreakingLogBlock.BREAKING_STAGE)
                        .select(Direction.Axis.X, 1, breakingLogStage1Model.with(BlockModelGenerators.X_ROT_90).with(BlockModelGenerators.Y_ROT_90))
                        .select(Direction.Axis.Y, 1, breakingLogStage1Model)
                        .select(Direction.Axis.Z, 1, breakingLogStage1Model.with(BlockModelGenerators.X_ROT_90))

                        .select(Direction.Axis.X, 2, breakingLogStage2Model.with(BlockModelGenerators.X_ROT_90).with(BlockModelGenerators.Y_ROT_90))
                        .select(Direction.Axis.Y, 2, breakingLogStage2Model)
                        .select(Direction.Axis.Z, 2, breakingLogStage2Model.with(BlockModelGenerators.X_ROT_90))

                        .select(Direction.Axis.X, 3, breakingLogStage3Model.with(BlockModelGenerators.X_ROT_90).with(BlockModelGenerators.Y_ROT_90))
                        .select(Direction.Axis.Y, 3, breakingLogStage3Model)
                        .select(Direction.Axis.Z, 3, breakingLogStage3Model.with(BlockModelGenerators.X_ROT_90))

                );
    }

    public static void registerBreakingLog(BlockModelGenerators generator, Block breakingLogBlock, TextureMapping textures) {
        Identifier breakingLogStage1Model = BREAKING_LOG_STAGE_1.create(breakingLogBlock, textures, generator.modelOutput);
        Identifier breakingLogStage2Model = BREAKING_LOG_STAGE_2.create(breakingLogBlock, textures, generator.modelOutput);
        Identifier breakingLogStage3Model = BREAKING_LOG_STAGE_3.create(breakingLogBlock, textures, generator.modelOutput);
        generator.blockStateOutput.accept(createBreakingLogBlockStates(breakingLogBlock, breakingLogStage1Model, breakingLogStage2Model, breakingLogStage3Model));

        generator.registerSimpleItemModel(breakingLogBlock, breakingLogStage1Model);
    }

}
