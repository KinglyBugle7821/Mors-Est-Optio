package net.numericalk.meo.components;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.Item;
import net.numericalk.meo.MorsEstOptio;

public class MEOComponents {
    public static final DataComponentType<Float> WATER_AMOUNT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            MorsEstOptio.id("water_amount"),
            DataComponentType.<Float>builder().persistent(Codec.floatRange(0.0f, 100.0f)).networkSynchronized(ByteBufCodecs.FLOAT).build()
    );
    public static void initialize() {}
}
