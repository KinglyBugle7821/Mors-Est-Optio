package net.numericalk.meo.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

import net.minecraft.server.level.ServerPlayer;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.network.payload.WindDataPayload;

public class WindNetworking {

    public static void initialize() {

        PayloadTypeRegistry.clientboundPlay().register(
                WindDataPayload.TYPE,
                WindDataPayload.CODEC
        );

        ServerPlayConnectionEvents.JOIN.register(
                (handler, sender, server) -> {
                    syncPlayer(handler.player);
                }
        );
    }

    public static void syncPlayer(ServerPlayer player) {

        int direction = player.level().getAttachedOrElse(
                MEOAttachments.WIND_DIRECTION, 0
        );

        float strength = player.level().getAttachedOrElse(
                MEOAttachments.WIND_STRENGTH, 0.05F
        );

        ServerPlayNetworking.send(
                player,
                new WindDataPayload(direction, strength)
        );
    }
}
