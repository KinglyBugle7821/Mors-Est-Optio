package net.numericalk.meo.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.numericalk.meo.MorsEstOptio;

public class MEODamageTypes {
    public static final ResourceKey<DamageType> OVERHEATING_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, MorsEstOptio.id("overheating"));
    public static final ResourceKey<DamageType> BLEEDING_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, MorsEstOptio.id("bleeding"));
    public static final ResourceKey<DamageType> MALNUTRITION_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, MorsEstOptio.id("malnutrition"));
    public static final ResourceKey<DamageType> DEHYDRATION_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, MorsEstOptio.id("dehydration"));
    public static final ResourceKey<DamageType> INFECTION_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, MorsEstOptio.id("infection"));
}
