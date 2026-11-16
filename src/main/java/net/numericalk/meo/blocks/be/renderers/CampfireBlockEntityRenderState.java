package net.numericalk.meo.blocks.be.renderers;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.numericalk.meo.blocks.be.CampfireBlockEntity;

public class CampfireBlockEntityRenderState extends BlockEntityRenderState {
    public final NonNullList<ItemStack> items = NonNullList.withSize(CampfireBlockEntity.CONTAINER_SIZE, ItemStack.EMPTY);
}