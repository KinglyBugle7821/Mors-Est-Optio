package net.numericalk.meo;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerPlayer;
import net.numericalk.meo.blocks.MEOBlockEntities;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.entity.MEOEffects;
import net.numericalk.meo.entity.attachments.*;
import net.numericalk.meo.event.*;
import net.numericalk.meo.items.MEOItems;
import net.numericalk.meo.world.attachments.WindDirection;
import net.numericalk.meo.world.attachments.WindStrength;
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
		MEOBlocks.initialize();
		MEOBlockEntities.initialize();

		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MEO_CREATIVE_TAB_KEY, MEO_CREATIVE_TAB);

		LOGGER.info("Get Ready For A Whole New Experience");

		ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
			for (ServerPlayer player : minecraftServer.getPlayerList().getPlayers()) {
				Temperature.serverTick(player);
				Nutrition.serverTick(player);
				Injury.serverTick(player);
				Hydration.serverTick(player);
				Stamina.serverTick(player);
				Fatigue.serverTick(player);
				Wetness.serverTick(player);
				WindDirection.serverTick(player);
				WindStrength.serverTick(player);
				MEOWindDriftingEvent.serverTick(player);
			}
		});
		MEOWoodBreakingEvent woodBreakingEvent = new MEOWoodBreakingEvent();
		MEOStoneBreakingEvent stoneBreakingEvent = new MEOStoneBreakingEvent();
		ServerLivingEntityEvents.AFTER_DAMAGE.register(new Injury().afterDamage);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
