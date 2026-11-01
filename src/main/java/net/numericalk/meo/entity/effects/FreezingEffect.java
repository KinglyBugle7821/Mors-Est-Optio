package net.numericalk.meo.entity.effects;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.MorsEstOptio;

public class FreezingEffect extends MobEffect {

    public FreezingEffect() {
        super(MobEffectCategory.HARMFUL, 0xd63fff);
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
                        .get(DamageTypes.FREEZE.identifier()).orElseThrow());
        if (entity.tickCount % 20 == 0) {
            entity.hurtServer(level, damageSource, 1.0f * amplifier);
        }

        return super.applyEffectTick(level, entity, amplifier);
    }

}
