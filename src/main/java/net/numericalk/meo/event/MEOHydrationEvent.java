package net.numericalk.meo.event;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.entity.MEODamageTypes;

public class MEOHydrationEvent {
    public static void serverTick(ServerPlayer player) {
        float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 0.5F);
        if (player.tickCount % 1200 == 0){
            player.setAttached(MEOAttachments.HYDRATION, Math.max(hydration - 0.1f, 0));
        }
        if (player.tickCount % 20 == 0 && hydration == 0){
            DamageSource damageSource = new DamageSource(
                    player.level().registryAccess()
                            .lookupOrThrow(Registries.DAMAGE_TYPE)
                            .get(MEODamageTypes.DEHYDRATION_DAMAGE.identifier()).orElseThrow());
            player.hurtServer(player.level(), damageSource, 1f);
        }
        MobEffectInstance nausea = new MobEffectInstance(MobEffects.NAUSEA, 10 * 20, 1, false, false, false);

        if (hydration < 0.2f) player.addEffect(nausea);
        if (hydration < 0) player.setAttached(MEOAttachments.HYDRATION, 0f);
        if (hydration > 1) player.setAttached(MEOAttachments.HYDRATION, 1f);
    }
}
