package net.numericalk.meo.attachments.player;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.numericalk.meo.attachments.MEOAttachments;
import net.numericalk.meo.attachments.MEOCorrelations;
import net.numericalk.meo.entity.MEODamageTypes;

public class PlayerHydration {
    public static void serverTick(ServerPlayer player) {
        float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 0.5F);

        if (player.tickCount % 20 == 0){
            float hydrationDecreaser = MEOCorrelations.THCorrelation(player);
            hydration -= hydrationDecreaser;

            player.setAttached(MEOAttachments.HYDRATION, Math.clamp(hydration, 0f, 1f));

            if ((hydration <= 0.5f) && player.isInWater()){
                hydration = 0.5f;
                player.setAttached(MEOAttachments.HYDRATION, hydration);
            }
            if ((hydration <= 0.25f) && player.level().isRainingAt(player.blockPosition())){
                hydration = 0.25f;
                player.setAttached(MEOAttachments.HYDRATION, hydration);
            }

            if (hydration == 0){
                DamageSource damageSource = new DamageSource(
                        player.level().registryAccess()
                                .lookupOrThrow(Registries.DAMAGE_TYPE)
                                .get(MEODamageTypes.DEHYDRATION_DAMAGE.identifier()).orElseThrow());
                player.hurtServer(player.level(), damageSource, 1f);
            }
        }
        MobEffectInstance nausea = new MobEffectInstance(MobEffects.NAUSEA, 3 * 20, 0, false, false, false);

        if (hydration < 0.2f) player.addEffect(nausea);
        if (hydration < 0) player.setAttached(MEOAttachments.HYDRATION, 0f);
        if (hydration > 1) player.setAttached(MEOAttachments.HYDRATION, 1f);
    }
}
