package net.numericalk.meo.blocks;

import net.minecraft.references.BlockItemId;
import net.minecraft.references.BlockItemIds;
import net.minecraft.resources.Identifier;
import net.numericalk.meo.MorsEstOptio;

public class MEOBlockItemIds {
    public static final BlockItemId LOOSE_DIRT = create("loose_dirt");

    public static final BlockItemId LOOSE_STONE = create("loose_stone");
    public static final BlockItemId FLINT_NODULE = create("flint_nodule");
    public static final BlockItemId FALLEN_BRANCH = create("fallen_branch");
    public static final BlockItemId WILD_POTATO = create("wild_potato");
    public static final BlockItemId WILD_CARROT = create("wild_carrot");

    public static final BlockItemId REED = create("reed");
    public static final BlockItemId TALL_REED = create("tall_reed");

    public static final BlockItemId CAMPFIRE = create("campfire");
    public static final BlockItemId CAMPFIRE_BASE = create("campfire_base");

    public static final BlockItemId BREAKING_STONE = create("breaking_stone");
    public static final BlockItemId BREAKING_DEEPSLATE = create("breaking_deepslate");

    public static final BlockItemId GRASS_SHEAF = create("grass_sheaf");

    public static final BlockItemId DRYING_TRAY = create("drying_tray");

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
