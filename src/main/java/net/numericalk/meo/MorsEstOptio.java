package net.numericalk.meo;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.effect.MobEffect;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.entity.MEOEffects;
import net.numericalk.meo.entity.effects.FreezingEffect;
import net.numericalk.meo.event.MEOTickEvent;
import net.numericalk.meo.items.MEOItemIds;
import net.numericalk.meo.items.MEOItems;
import org.joml.Matrix3x2fStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.numericalk.meo.items.MEOItems.MEO_CREATIVE_TAB;
import static net.numericalk.meo.items.MEOItems.MEO_CREATIVE_TAB_KEY;

public class MorsEstOptio implements ModInitializer {
	public static final String MOD_ID = "mors-est-optio";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {

		MEOAttachments.initialize();
		MEOEffects.initialize();
		MEOItems.initialize();

		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MEO_CREATIVE_TAB_KEY, MEO_CREATIVE_TAB);

		LOGGER.info("Get Ready For A Whole New Experience");

		ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
			for (ServerPlayer player : minecraftServer.getPlayerList().getPlayers()) {
				MEOTickEvent.serverTick(player);
			}
		});
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
