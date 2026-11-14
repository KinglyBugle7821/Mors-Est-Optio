package net.numericalk.meo.attachments.player;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.numericalk.meo.attachments.MEOAttachments;
import net.numericalk.meo.attachments.MEOCorrelations;
import net.numericalk.meo.entity.MEODamageTypes;

public class PlayerNutrition {
    public static void serverTick(ServerPlayer player) {
        float carbs = player.getAttachedOrElse(MEOAttachments.CARBOHYDRATE, 1.0F);
        float proteins = player.getAttachedOrElse(MEOAttachments.PROTEIN, 1.0F);
        float fats = player.getAttachedOrElse(MEOAttachments.FAT, 1.0F);

        ServerLevel level = player.level();

        if (player.tickCount % 20 == 0) {
            carbs -= MEOCorrelations.TNCorrelation(player) + MEOCorrelations.HuNCorrelation(player);
            proteins -= MEOCorrelations.TNCorrelation(player) + MEOCorrelations.HuNCorrelation(player);
            fats -= MEOCorrelations.TNCorrelation(player) + MEOCorrelations.HuNCorrelation(player);

            player.setAttached(MEOAttachments.CARBOHYDRATE, Math.max(carbs, 0));
            player.setAttached(MEOAttachments.PROTEIN, Math.max(proteins, 0));
            player.setAttached(MEOAttachments.FAT, Math.max(fats, 0));
        }
        if (player.tickCount % 20 == 0 && (carbs == 0 || proteins == 0 || fats == 0)) {
            DamageSource damageSource = new DamageSource(
                    level.registryAccess()
                            .lookupOrThrow(Registries.DAMAGE_TYPE)
                            .get(MEODamageTypes.MALNUTRITION_DAMAGE.identifier()).orElseThrow());
            player.hurtServer(level, damageSource, 1f);
        }
        MobEffectInstance miningFatigue = new MobEffectInstance(MobEffects.MINING_FATIGUE, 3 * 20, 0, false, false, false);
        MobEffectInstance nausea = new MobEffectInstance(MobEffects.NAUSEA, 10 * 20, 0, false, false, false);
        MobEffectInstance weakness = new MobEffectInstance(MobEffects.WEAKNESS, 3 * 20, 0, false, false, false);
        MobEffectInstance slowness = new MobEffectInstance(MobEffects.SLOWNESS, 3 * 20, 0, false, false, false);
        MobEffectInstance hunger = new MobEffectInstance(MobEffects.HUNGER, 3 * 20, 1, false, false, false);

        if (carbs <= 0.25 || proteins <= 0.25 || fats <= 0.25){
            player.addEffect(hunger);
        }

        if (carbs == 0F){
            player.addEffect(miningFatigue);
            player.addEffect(slowness);
        }
        if (proteins == 0F){
            player.addEffect(weakness);
            player.addEffect(nausea);
        }
        if (fats == 0F){
            player.addEffect(weakness);
        }

    }
}
