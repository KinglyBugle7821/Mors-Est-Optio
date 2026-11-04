package net.numericalk.meo.blocks;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.numericalk.meo.blocks.custom.BreakingLogBlock;
import net.numericalk.meo.blocks.custom.GrassSheafBlock;

import java.util.function.Function;

public class MEOBlocks {
    public static final Block GRASS_SHEAF = registerBlockItem(
            MEOBlockItemIds.GRASS_SHEAF,
            GrassSheafBlock::new,
            BlockBehaviour.Properties.of()
                    .mapColor(MapColor.COLOR_BROWN)
                    .instabreak()
                    .sound(SoundType.GRASS).ignitedByLava()
    );
    public static final Block BREAKING_OAK_LOG = registerBlockItem(
            MEOBlockItemIds.BREAKING_OAK_LOG,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                .mapColor(MapColor.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .strength(6, 2)
                .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BREAKING_SPRUCE_LOG = registerBlockItem(
            MEOBlockItemIds.BREAKING_SPRUCE_LOG,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(6, 2)
                    .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BREAKING_BIRCH_LOG = registerBlockItem(
            MEOBlockItemIds.BREAKING_BIRCH_LOG,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(6, 2)
                    .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BREAKING_JUNGLE_LOG = registerBlockItem(
            MEOBlockItemIds.BREAKING_JUNGLE_LOG,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(6, 2)
                    .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BREAKING_ACACIA_LOG = registerBlockItem(
            MEOBlockItemIds.BREAKING_ACACIA_LOG,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(6, 2)
                    .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BREAKING_DARK_OAK_LOG = registerBlockItem(
            MEOBlockItemIds.BREAKING_DARK_OAK_LOG,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(6, 2)
                    .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BREAKING_MANGROVE_LOG = registerBlockItem(
            MEOBlockItemIds.BREAKING_MANGROVE_LOG,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(6, 2)
                    .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BREAKING_CHERRY_LOG = registerBlockItem(
            MEOBlockItemIds.BREAKING_CHERRY_LOG,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(6, 2)
                    .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BREAKING_PALE_OAK_LOG = registerBlockItem(
            MEOBlockItemIds.BREAKING_PALE_OAK_LOG,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(6, 2)
                    .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BREAKING_WARPED_STEM = registerBlockItem(
            MEOBlockItemIds.BREAKING_WARPED_STEM,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(6, 2)
                    .sound(SoundType.WOOD).ignitedByLava()
    );
    public static final Block BREAKING_CRIMSON_STEM = registerBlockItem(
            MEOBlockItemIds.BREAKING_CRIMSON_STEM,
            BreakingLogBlock::new, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(6, 2)
                    .sound(SoundType.WOOD).ignitedByLava()
    );

    private static Block registerBlockItem(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        Block block = registerBlock(id.block(), blockFactory, properties);

        BlockItem blockItem = new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix().setId(id.item()));
        Registry.register(BuiltInRegistries.ITEM, id.item(), blockItem);

        return block;
    }
    private static Block registerBlock(ResourceKey<Block> id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
        Block block = blockFactory.apply(properties.setId(id));

        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }

    public static void initialize(){}
}
