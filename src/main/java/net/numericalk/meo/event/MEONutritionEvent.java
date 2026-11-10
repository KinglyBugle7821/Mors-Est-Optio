package net.numericalk.meo.event;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.Level;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.entity.MEODamageTypes;
import net.numericalk.meo.mechs.MEONutritionMech;

public class MEONutritionEvent {
    public static void serverTick(ServerPlayer player) {
        float carbs = player.getAttachedOrElse(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT, 0.5F);
        float proteins = player.getAttachedOrElse(MEOAttachments.PLAYER_PROTEIN_ATTACHMENT, 0.5F);
        float fats = player.getAttachedOrElse(MEOAttachments.PLAYER_FAT_ATTACHMENT, 0.5F);

        ServerLevel level = player.level();

        if (player.tickCount % 20 == 0) {
            MEONutritionMech meoNutritionMech = new MEONutritionMech(player);
        }
        if (player.tickCount % 20 == 0 && (carbs == 0 || proteins == 0 || fats == 0)) {
            DamageSource damageSource = new DamageSource(
                    level.registryAccess()
                            .lookupOrThrow(Registries.DAMAGE_TYPE)
                            .get(MEODamageTypes.MALNUTRITION_DAMAGE.identifier()).orElseThrow());
            player.hurtServer(level, damageSource, 1f);
        }
        MobEffectInstance miningFatigue = new MobEffectInstance(MobEffects.MINING_FATIGUE, 3 * 20, 0, false, false, false);
        MobEffectInstance nausea = new MobEffectInstance(MobEffects.NAUSEA, 10 * 20, 1, false, false, false);
        MobEffectInstance weakness = new MobEffectInstance(MobEffects.WEAKNESS, 3 * 20, 1, false, false, false);
        MobEffectInstance slowness = new MobEffectInstance(MobEffects.SLOWNESS, 3 * 20, 1, false, false, false);

        if (carbs < 0.05F){
            player.addEffect(miningFatigue);
            player.addEffect(slowness);
        }
        if (proteins < 0.05F){
            player.addEffect(weakness);
            player.addEffect(nausea);
        }
        if (fats < 0.05F){
            player.addEffect(weakness);
        }

    }
}
