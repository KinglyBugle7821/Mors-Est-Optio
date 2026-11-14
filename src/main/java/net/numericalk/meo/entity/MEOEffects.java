package net.numericalk.meo.entity;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.entity.effects.*;

public class MEOEffects {
    public static final Holder<MobEffect> FREEZING = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, MorsEstOptio.id("freezing"), new FreezingEffect());
    public static final Holder<MobEffect> OVERHEATING = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, MorsEstOptio.id("overheating"), new OverheatingEffect());
    public static final Holder<MobEffect> BLEEDING = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, MorsEstOptio.id("bleeding"), new BleedingEffect());
    public static final Holder<MobEffect> FEVER = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, MorsEstOptio.id("fever"), new FeverEffect());
    public static final Holder<MobEffect> INFECTION = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, MorsEstOptio.id("infection"), new InfectionEffect());

    public static void initialize(){}
}
