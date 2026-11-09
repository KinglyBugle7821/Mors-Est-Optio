package net.numericalk.meo.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.numericalk.meo.entity.MEOAttachments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class WaterDrinkingMixin {

    @Inject(method = "finishUsingItem", at = @At("HEAD"))
    private void meo$onConsume(
            ItemStack stack,
            Level level,
            LivingEntity entity,
            CallbackInfoReturnable<ItemStack> cir
    ) {
        if (!(entity instanceof Player player)) return;
        if (level.isClientSide()) return;

        if (stack.is(Items.POTION)) {
            float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 0.5f);
            player.setAttached(MEOAttachments.HYDRATION,  Math.min(hydration + 0.2F, 1F));
        }
    }
}