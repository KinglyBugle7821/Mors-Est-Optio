package net.numericalk.meo.entity.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class FeverEffect extends MobEffect {
    public FeverEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF0000);
    }
}
