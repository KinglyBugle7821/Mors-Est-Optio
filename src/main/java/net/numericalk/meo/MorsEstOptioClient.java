package net.numericalk.meo;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.numericalk.meo.screen.MEOEffectGUI;
import net.numericalk.meo.screen.MEOTempBarGUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MorsEstOptioClient implements ClientModInitializer {
	public static final String MOD_ID = "mors-est-optio";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitializeClient() {
		LOGGER.info("The world is waiting for you");

		HudElementRegistry.addFirst(MorsEstOptio.id("temp_bar"), MEOTempBarGUI.hudLayer());
		HudElementRegistry.addFirst(MorsEstOptio.id("effect_overlay"), MEOEffectGUI.hudLayer());
	}
}