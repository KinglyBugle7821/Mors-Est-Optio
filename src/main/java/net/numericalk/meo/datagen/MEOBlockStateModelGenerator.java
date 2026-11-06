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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.blocks.custom.BreakingLogBlock;
import net.numericalk.meo.blocks.custom.BreakingStoneBlock;

import java.util.Optional;

public class MEOBlockStateModelGenerator {
    public static final ModelTemplate BREAKING_LOG_STAGE_1 = block("breaking_log_stage_1", "_stage_1", TextureSlot.TOP, TextureSlot.SIDE);
    public static final ModelTemplate BREAKING_LOG_STAGE_2 = block("breaking_log_stage_2", "_stage_2", TextureSlot.TOP, TextureSlot.SIDE);
    public static final ModelTemplate BREAKING_LOG_STAGE_3 = block("breaking_log_stage_3", "_stage_3", TextureSlot.TOP, TextureSlot.SIDE);

    public static final ModelTemplate BREAKING_STONE_STAGE_1 =
            new ModelTemplate(
                    Optional.of(Identifier.withDefaultNamespace("block/cube_all")),
                    Optional.of("_stage_1"),
                    TextureSlot.ALL
            );

    public static final ModelTemplate BREAKING_STONE_STAGE_2 =
            new ModelTemplate(
                    Optional.of(Identifier.withDefaultNamespace("block/cube_all")),
                    Optional.of("_stage_2"),
                    TextureSlot.ALL
            );

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
    public static TextureMapping stonedCubeAll(Block block, int stage) {
        return new TextureMapping().put(
                TextureSlot.ALL,
                new Material(Identifier.fromNamespaceAndPath(
                        MorsEstOptio.MOD_ID,
                        "block/" + BuiltInRegistries.BLOCK.getKey(block).getPath()
                                + "_stage_" + stage
                ))
        );
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
    private static BlockModelDefinitionGenerator createBreakingStoneBlockStates(Block breakingStoneBlock, Identifier stage1Id, Identifier stage2Id) {
        MultiVariant stage1Model = BlockModelGenerators.plainVariant(stage1Id);
        MultiVariant stage2Model = BlockModelGenerators.plainVariant(stage2Id);

        return MultiVariantGenerator.dispatch(breakingStoneBlock)
                .with(PropertyDispatch.initial(BreakingStoneBlock.BREAKING_STAGE)
                        .select(1, stage1Model)
                        .select(2, stage2Model)
                );
    }

    public static void registerBreakingStone(BlockModelGenerators generator, Block breakingStoneBlock) {
        Identifier stage1Model = BREAKING_STONE_STAGE_1.create(breakingStoneBlock, stonedCubeAll(breakingStoneBlock, 1), generator.modelOutput);
        Identifier stage2Model = BREAKING_STONE_STAGE_2.create(breakingStoneBlock, stonedCubeAll(breakingStoneBlock, 2), generator.modelOutput);

        generator.blockStateOutput.accept(createBreakingStoneBlockStates(breakingStoneBlock, stage1Model, stage2Model));
        generator.registerSimpleItemModel(breakingStoneBlock, stage1Model);
    }

    public static void registerBreakingLog(BlockModelGenerators generator, Block breakingLogBlock, TextureMapping textures) {
        Identifier breakingLogStage1Model = BREAKING_LOG_STAGE_1.create(breakingLogBlock, textures, generator.modelOutput);
        Identifier breakingLogStage2Model = BREAKING_LOG_STAGE_2.create(breakingLogBlock, textures, generator.modelOutput);
        Identifier breakingLogStage3Model = BREAKING_LOG_STAGE_3.create(breakingLogBlock, textures, generator.modelOutput);
        generator.blockStateOutput.accept(createBreakingLogBlockStates(breakingLogBlock, breakingLogStage1Model, breakingLogStage2Model, breakingLogStage3Model));

        generator.registerSimpleItemModel(breakingLogBlock, breakingLogStage1Model);
    }

}
