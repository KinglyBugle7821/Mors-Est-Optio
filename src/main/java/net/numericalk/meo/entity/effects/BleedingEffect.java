package net.numericalk.meo.entity.effects;

import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.numericalk.meo.entity.MEODamageTypes;

public class BleedingEffect extends MobEffect {
    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF0000);
    }
    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }
    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        DamageSource damageSource = new DamageSource(
                level.registryAccess()
                        .lookupOrThrow(Registries.DAMAGE_TYPE)
                        .get(MEODamageTypes.BLEEDING_DAMAGE.identifier()).orElseThrow());
        if (entity.tickCount % 20 == 0) {
            entity.hurtServer(level, damageSource, 1.0f);
        }

        return super.applyEffectTick(level, entity, amplifier);
    }
}
