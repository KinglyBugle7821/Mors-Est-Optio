package net.numericalk.meo.network.payload;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.numericalk.meo.MorsEstOptio;

public record SeasonDataPayload(int seasonId) implements CustomPacketPayload {
    public static final Type<SeasonDataPayload> TYPE = new Type<>(MorsEstOptio.id("season_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SeasonDataPayload> CODEC =
            StreamCodec.composite(
                    ByteBufCodecs.INT,
                    SeasonDataPayload::seasonId,

                    SeasonDataPayload::new
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
