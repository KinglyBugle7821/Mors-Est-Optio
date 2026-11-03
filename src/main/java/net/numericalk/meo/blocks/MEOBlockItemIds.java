package net.numericalk.meo.blocks;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.numericalk.meo.MorsEstOptio;

public class MEOBlockItemIds {
    public static final BlockItemId BREAKING_OAK_LOG = create("breaking_oak_log");
    public static final BlockItemId BREAKING_SPRUCE_LOG = create("breaking_spruce_log");
    public static final BlockItemId BREAKING_BIRCH_LOG = create("breaking_birch_log");
    public static final BlockItemId BREAKING_JUNGLE_LOG = create("breaking_jungle_log");
    public static final BlockItemId BREAKING_ACACIA_LOG = create("breaking_acacia_log");
    public static final BlockItemId BREAKING_DARK_OAK_LOG = create("breaking_dark_oak_log");
    public static final BlockItemId BREAKING_MANGROVE_LOG = create("breaking_mangrove_log");
    public static final BlockItemId BREAKING_CHERRY_LOG = create("breaking_cherry_log");
    public static final BlockItemId BREAKING_PALE_OAK_LOG = create("breaking_pale_oak_log");
    public static final BlockItemId BREAKING_CRIMSON_STEM = create("breaking_crimson_stem");
    public static final BlockItemId BREAKING_WARPED_STEM = create("breaking_warped_stem");



    private static BlockItemId create(String name) {
        Identifier id = MorsEstOptio.id(name);
        return BlockItemId.create(id, id);
    }
}
