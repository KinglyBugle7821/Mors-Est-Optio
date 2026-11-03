package net.numericalk.meo.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.numericalk.meo.datagen.MEOItemTagProvider;

public class MEOToolMaterials {
    public static final ToolMaterial FLINT_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            32,
            1.0F,
            0.0F,
            15,
            MEOItemTagProvider.REPAIRS_FLINT_TOOL
    );

    public static final ToolMaterial STEEL_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            1250,
            7.0F,
            3.0F,
            10,
            MEOItemTagProvider.STEEL_MATERIAL
    );
}
