package net.numericalk.meo;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.numericalk.meo.blocks.MEOBlockEntities;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.entity.MEOEffects;
import net.numericalk.meo.event.MEOTemperatureTickEvent;
import net.numericalk.meo.event.MEOWoodBreakingEvent;
import net.numericalk.meo.items.MEOItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.numericalk.meo.items.MEOItems.MEO_CREATIVE_TAB;
import static net.numericalk.meo.items.MEOItems.MEO_CREATIVE_TAB_KEY;

public class MorsEstOptio implements ModInitializer {
	public static final String MOD_ID = "mors-est-optio";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//TODO: MODIFY STONES LOOT TABLE
	//TODO: ADD STONE STAGES

	//TODO: ADD LOOT TABLES
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
				MEOTemperatureTickEvent.serverTick(player);
			}
		});
		MEOWoodBreakingEvent woodBreakingEvent = new MEOWoodBreakingEvent();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
