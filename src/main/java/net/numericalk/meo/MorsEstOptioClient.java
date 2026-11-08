package net.numericalk.meo;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.KeyMapping;
import net.numericalk.meo.screen.MEOEffectGUI;
import net.numericalk.meo.screen.MEOInjuryGUI;
import net.numericalk.meo.screen.MEONutritionGUI;
import net.numericalk.meo.screen.MEOTempBarGUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MorsEstOptioClient implements ClientModInitializer {
	public static final String MOD_ID = "mors-est-optio";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static boolean injuryGuiOpen = false;

	@Override
	public void onInitializeClient() {
		LOGGER.info("The world is waiting for you");

		HudElementRegistry.addFirst(MorsEstOptio.id("temp_bar"), MEOTempBarGUI.hudLayer());
		HudElementRegistry.addFirst(MorsEstOptio.id("effect_overlay"), MEOEffectGUI.hudLayer());
		HudElementRegistry.addFirst(MorsEstOptio.id("nutrition"), MEONutritionGUI.hudLayer());

		HudElementRegistry.addFirst(MorsEstOptio.id("injury"), MEOInjuryGUI.hudLayer());

		KeyMapping.Category MEO_CATEGORY = KeyMapping.Category.register(
				MorsEstOptio.id("meo_category")
		);
		KeyMapping openInjuryKey = KeyMappingHelper.registerKeyMapping(
				new KeyMapping(
						"key.mors-est-optio.open_injury", // The translation key for the key mapping.
						InputConstants.Type.KEYSYM, // The type of the keybinding; KEYSYM for keyboard, MOUSE for mouse.
						InputConstants.KEY_I, // The keycode of the key.
						MEO_CATEGORY // The category of the mapping.
				));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (openInjuryKey.consumeClick()) {
				if (client.player == null) return;

				MEOInjuryGUI.visible = !MEOInjuryGUI.visible;
			}
		});
	}
}