package net.numericalk.meo.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.mechs.MEONutritionMech;

public class MEONutritionEvent {
    public static void serverTick(ServerPlayer player) {
        if (player.tickCount % 1200 == 0) {
            MEONutritionMech meoNutritionMech = new MEONutritionMech(player);
        }
        MobEffectInstance miningFatigue = new MobEffectInstance(MobEffects.MINING_FATIGUE, 3 * 20, 0, false, false, false);
        MobEffectInstance nausea = new MobEffectInstance(MobEffects.NAUSEA, 10 * 20, 1, false, false, false);
        MobEffectInstance weakness = new MobEffectInstance(MobEffects.WEAKNESS, 3 * 20, 1, false, false, false);
        MobEffectInstance slowness = new MobEffectInstance(MobEffects.SLOWNESS, 3 * 20, 1, false, false, false);

        if (player.getAttachedOrElse(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT, 0.5F) < 0.25F){
            player.addEffect(miningFatigue);
            player.addEffect(slowness);
        }
        if (player.getAttachedOrElse(MEOAttachments.PLAYER_PROTEIN_ATTACHMENT, 0.5F) < 0.25F){
            player.addEffect(weakness);
            player.addEffect(nausea);
        }
        if (player.getAttachedOrElse(MEOAttachments.PLAYER_FAT_ATTACHMENT, 0.5F) < 0.25F){
            player.addEffect(weakness);
        }
    }
}
