package net.numericalk.meo.network.payload;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.numericalk.meo.MorsEstOptio;

public record WindDataPayload(
        int direction,
        float strength
) implements CustomPacketPayload {

    public static final Type<WindDataPayload> TYPE =
            new Type<>(MorsEstOptio.id("wind_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, WindDataPayload> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT,
                    WindDataPayload::direction,

                    ByteBufCodecs.FLOAT,
                    WindDataPayload::strength,

                    WindDataPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
