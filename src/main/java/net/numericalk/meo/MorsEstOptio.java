package net.numericalk.meo;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.event.MEOTickEvent;
import org.joml.Matrix3x2fStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MorsEstOptio implements ModInitializer {
	public static final String MOD_ID = "mors-est-optio";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		LOGGER.info("Get Ready For A Whole New Experience");

		ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
			for (ServerPlayer player : minecraftServer.getPlayerList().getPlayers()) {
				MEOTickEvent.serverTick(player);
			}
		});

		System.out.println(
				"Temperature attachment synced: "
						+ MEOAttachments.PLAYER_TEMPERATURE_ATTACHMENT.isSynced()
		);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
