package net.numericalk.meo.attachments.player;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.numericalk.meo.attachments.MEOAttachments;
import net.numericalk.meo.attachments.MEOCorrelations;

public class PlayerStamina {
    public static void serverTick(ServerPlayer player) {
        if (player.tickCount % 10 != 0) return;

        float stamina = player.getAttachedOrElse(MEOAttachments.STAMINA, 1F);

        if (player.isSprinting()) {
            float staminaDecrease = MEOCorrelations.NSCorrelation(player) + MEOCorrelations.HSCorrelation(player);
            stamina -= staminaDecrease;
            float fatigue = player.getAttachedOrElse(MEOAttachments.FATIGUE, 0f);
            player.setAttached(MEOAttachments.FATIGUE, Math.min(fatigue + 0.002f, 1));
        }
        else stamina += 0.05f;

        stamina = Math.max(stamina, 0F);

        player.setAttached(MEOAttachments.STAMINA, Math.clamp(stamina, 0F, 1F));

        MobEffectInstance slowness = new MobEffectInstance(MobEffects.SLOWNESS, 5 * 20, 0, false, false, false);

        if (stamina <= 0) {
            player.addEffect(slowness);
        }
    }
}