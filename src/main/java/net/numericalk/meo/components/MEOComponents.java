package net.numericalk.meo.components;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.misc.WaterQuality;

public class MEOComponents {
    public static final DataComponentType<Float> WATER_AMOUNT = Registry.register(
            BuiltInRegistries.DATA_COMPONENT_TYPE,
            MorsEstOptio.id("water_amount"),
            DataComponentType.<Float>builder().persistent(Codec.floatRange(0.0f, 100.0f)).networkSynchronized(ByteBufCodecs.FLOAT).build()
    );

    public static final DataComponentType<WaterQuality> WATER_QUALITY =
            Registry.register(
                    BuiltInRegistries.DATA_COMPONENT_TYPE,
                    Identifier.fromNamespaceAndPath(
                            MorsEstOptio.MOD_ID,
                            "water_quality"
                    ),
                    DataComponentType.<WaterQuality>builder()
                            .persistent(
                                    Codec.STRING.xmap(
                                            WaterQuality::valueOf,
                                            WaterQuality::name
                                    )
                            )
                            .networkSynchronized(
                                    ByteBufCodecs.STRING_UTF8.map(
                                            WaterQuality::valueOf,
                                            WaterQuality::name
                                    )
                            )
                            .build()
            );

    public static void initialize() {}
}
