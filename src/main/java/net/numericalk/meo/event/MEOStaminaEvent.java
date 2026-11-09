package net.numericalk.meo.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOStaminaEvent {
    public static void serverTick(ServerPlayer player) {
        if (player.tickCount % 10 != 0) return;

        float stamina = player.getAttachedOrElse(
                MEOAttachments.STAMINA, 1F
        );

        if (player.isJumping()) {
            stamina -= 0.04F;
        }

        if (player.isSprinting()) {
            stamina -= 0.025F;
        }

        stamina = Math.max(stamina, 0F);

        if (!player.isJumping() && !player.isSprinting()) {
            stamina += (1F - stamina) * 0.1F;
        }

        player.setAttached(
                MEOAttachments.STAMINA,
                Math.clamp(stamina, 0F, 1F)
        );

        MobEffectInstance slowness = new MobEffectInstance(MobEffects.SLOWNESS, 5 * 20, 2, false, false, false);

        if (stamina <= 0) player.addEffect(slowness);
    }
}