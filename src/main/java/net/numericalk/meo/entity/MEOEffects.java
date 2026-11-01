package net.numericalk.meo.entity;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.entity.effects.FreezingEffect;
import net.numericalk.meo.entity.effects.OverheatingEffect;

public class MEOEffects {
    public static final Holder<MobEffect> FREEZING = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, MorsEstOptio.id("freezing"), new FreezingEffect());
    public static final Holder<MobEffect> OVERHEATING = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, MorsEstOptio.id("overheating"), new OverheatingEffect());

    public static void initialize(){}
}
