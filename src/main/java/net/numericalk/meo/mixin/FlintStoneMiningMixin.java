package net.numericalk.meo.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.datagen.MEOBlockTagProvider;
import net.numericalk.meo.items.MEOItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class FlintStoneMiningMixin {
    @Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
    private void morsEstOptio$increaseFlintStoneMiningSpeed(BlockState state, CallbackInfoReturnable<Float> cir) {
        if (((((ItemStack) (Object) this).is(Items.FLINT))) && state.is(MEOBlockTagProvider.STONE_BLOCKS)) {
            cir.setReturnValue(cir.getReturnValue() * 4.0F);
        }
    }
}
