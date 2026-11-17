package net.numericalk.meo.blocks.be.renderers;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.numericalk.meo.blocks.be.DryingTrayBlockEntity;

public class DryingTrayBlockEntityRenderState extends BlockEntityRenderState {
    public final NonNullList<ItemStack> items = NonNullList.withSize(DryingTrayBlockEntity.CONTAINER_SIZE, ItemStack.EMPTY);
}
