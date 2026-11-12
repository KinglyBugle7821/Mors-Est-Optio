package net.numericalk.meo.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

import net.minecraft.server.level.ServerPlayer;
import net.numericalk.meo.attachments.MEOAttachments;
import net.numericalk.meo.network.payload.SeasonDataPayload;
import net.numericalk.meo.network.payload.WindDataPayload;

public class MEONetworking {

    public static void initialize() {

        PayloadTypeRegistry.clientboundPlay().register(
                WindDataPayload.TYPE,
                WindDataPayload.CODEC
        );
        PayloadTypeRegistry.clientboundPlay().register(
                SeasonDataPayload.TYPE,
                SeasonDataPayload.CODEC
        );

        ServerPlayConnectionEvents.JOIN.register(
                (handler, sender, server) -> {
                    syncPlayer(handler.player);
                }
        );
    }

    public static void syncPlayer(ServerPlayer player) {
        int direction = player.level().getAttachedOrElse(MEOAttachments.WIND_DIRECTION, 0);
        float strength = player.level().getAttachedOrElse(MEOAttachments.WIND_STRENGTH, 0.05F);
        int season = player.level().getAttachedOrElse(MEOAttachments.SEASON, 0);

        ServerPlayNetworking.send(player, new WindDataPayload(direction, strength));
        ServerPlayNetworking.send(player, new SeasonDataPayload(season));
    }
}
