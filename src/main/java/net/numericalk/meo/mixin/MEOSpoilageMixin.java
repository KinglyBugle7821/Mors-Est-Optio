
package net.numericalk.meo.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;

import net.numericalk.meo.components.MEOComponents;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Consumable.class)
public class MEOSpoilageMixin {
    @Inject(method = "onConsume", at = @At("HEAD"))
    private void meo$onConsume(Level level, LivingEntity user, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (level.isClientSide()) return;
        if (user instanceof ServerPlayer player) {
            if (stack.get(MEOComponents.IS_SPOILED) != null && stack.get(MEOComponents.SPOIL_TIME) != null){
                boolean isSpoiled = stack.get(MEOComponents.IS_SPOILED);
                if (isSpoiled) {
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 20 * 60, 0, false, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.NAUSEA, 20 * 60, 0, false, false, false));
                }
            }
        }
    }
}
