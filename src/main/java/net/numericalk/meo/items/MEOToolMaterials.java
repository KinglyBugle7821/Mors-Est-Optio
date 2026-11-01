package net.numericalk.meo.items;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.numericalk.meo.datagen.MEOBlockTagProvider;
import net.numericalk.meo.datagen.MEOItemTagProvider;

public class MEOToolMaterials {
    public static final ToolMaterial FLINT_TOOL_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL, // incorrect blocks for drops
            32, // durability
            1.0F, // speed
            0.0F, // attack damage bonus
            15, // enchantment value
            MEOItemTagProvider.REPAIRS_FLINT_TOOL // repair items
    );
}
