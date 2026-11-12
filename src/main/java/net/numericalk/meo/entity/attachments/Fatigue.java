package net.numericalk.meo.entity.attachments;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.numericalk.meo.entity.MEOAttachments;

public class Fatigue {
    public static void serverTick(ServerPlayer player) {
        float fatigue = player.getAttachedOrElse(MEOAttachments.FATIGUE, 0f);

        if (player.isSleepingLongEnough()){
            player.setAttached(MEOAttachments.FATIGUE, 0f);
        }
        if (fatigue >= 0.9){
            MobEffectInstance slowness = new MobEffectInstance(MobEffects.SLOWNESS, 5 * 20, 1, false, false, false);
            MobEffectInstance miningFatigue = new MobEffectInstance(MobEffects.MINING_FATIGUE, 5 * 20, 0, false, false, false);
            MobEffectInstance weakness = new MobEffectInstance(MobEffects.WEAKNESS, 5 * 20, 1, false, false, false);

            player.addEffect(slowness);
            player.addEffect(miningFatigue);
            player.addEffect(weakness);
        }
    }
}
