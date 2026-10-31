package net.numericalk.meo.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.numericalk.meo.mechs.MEOTempMech;
import net.numericalk.meo.mechs.MEOWaterCooling;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public abstract class WaterDrinkingCoolingMixin {

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
            MEOWaterCooling.addWaterCooling(player, 1f);
        }
    }
}