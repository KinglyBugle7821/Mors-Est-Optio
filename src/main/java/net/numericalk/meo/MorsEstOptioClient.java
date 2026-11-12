package net.numericalk.meo;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.KeyMapping;
import net.numericalk.meo.network.MEONetworking;
import net.numericalk.meo.network.payload.SeasonDataPayload;
import net.numericalk.meo.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;

import net.numericalk.meo.network.ClientEnvironmentData;
import net.numericalk.meo.network.payload.WindDataPayload;

public class MorsEstOptioClient implements ClientModInitializer {
	public static final String MOD_ID = "mors-est-optio";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("The world is waiting for you");

		HudElementRegistry.addFirst(MorsEstOptio.id("temp_bar"), MEOTempBarGUI.hudLayer());
		HudElementRegistry.addFirst(MorsEstOptio.id("effect_overlay"), MEOEffectGUI.hudLayer());
		HudElementRegistry.addFirst(MorsEstOptio.id("nutrition"), MEONutritionGUI.hudLayer());
		HudElementRegistry.addFirst(MorsEstOptio.id("hydration"), MEOHydrationGUI.hudLayer());

		HudElementRegistry.addFirst(MorsEstOptio.id("stamina"), MEOStaminaGUI.hudLayer());
		HudElementRegistry.addFirst(MorsEstOptio.id("fatigue"), MEOFatigueGUI.hudLayer());

		HudElementRegistry.addFirst(MorsEstOptio.id("injury"), MEOInjuryGUI.hudLayer());

		HudElementRegistry.addFirst(MorsEstOptio.id("open_attributes"), MEOAttributesGUI.hudLayer());
		MEONetworking.initialize();

		ClientPlayNetworking.registerGlobalReceiver(WindDataPayload.TYPE,
				(payload, context) -> {
					context.client().execute(() ->
							ClientEnvironmentData.updateWind(
									payload.direction(),
									payload.strength()
							)
					);
				}
		);
		ClientPlayNetworking.registerGlobalReceiver(SeasonDataPayload.TYPE,
				(payload, context) -> {
					context.client().execute(() ->
							ClientEnvironmentData.updateSeason(payload.seasonId())
							);
				}
		);

		ClientPlayConnectionEvents.DISCONNECT.register(
				(handler, client) -> ClientEnvironmentData.reset()
		);

		KeyMapping.Category MEO_CATEGORY = KeyMapping.Category.register(
				MorsEstOptio.id("meo_category")
		);
		KeyMapping openInjuryKey = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"key.mors-est-optio.open_injury",
						InputConstants.Type.KEYSYM,
						InputConstants.KEY_I,
						MEO_CATEGORY
				));
		KeyMapping switchHUDKey = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"key.mors-est-optio.switch_hud",
						InputConstants.Type.KEYSYM,
						InputConstants.KEY_J,
						MEO_CATEGORY
				));
		KeyMapping openMEOAttributesKey = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"key.mors-est-optio.open_meo_attributes_key",
						InputConstants.Type.KEYSYM,
						InputConstants.KEY_K,
						MEO_CATEGORY
				));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openInjuryKey.consumeClick()) {
				if (client.player == null) return;

				MEOInjuryGUI.visible = !MEOInjuryGUI.visible;
			}
			while (switchHUDKey.consumeClick()){
				if (client.player == null) return;

				MEOFatigueGUI.visible = !MEOFatigueGUI.visible;
				MEOStaminaGUI.visible = !MEOStaminaGUI.visible;
			}
			while (openMEOAttributesKey.consumeClick()) {
				if (client.player == null) return;

				MEOAttributesGUI.visible = !MEOAttributesGUI.visible;
			}
		});
	}
}