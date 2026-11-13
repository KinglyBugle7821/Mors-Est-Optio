package net.numericalk.meo;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.item.v1.ItemComponentTooltipProviderRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import net.minecraft.server.level.ServerPlayer;
import net.numericalk.meo.attachments.player.*;
import net.numericalk.meo.attachments.world.WorldSeasons;
import net.numericalk.meo.blocks.MEOBlockEntities;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.attachments.MEOAttachments;
import net.numericalk.meo.components.MEOComponents;
import net.numericalk.meo.entity.MEOEffects;
import net.numericalk.meo.event.*;
import net.numericalk.meo.items.MEOItems;
import net.numericalk.meo.attachments.world.WorldWindDirection;
import net.numericalk.meo.attachments.world.WorldWindStrength;
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
		MEOComponents.initialize();

		ItemTooltipCallback.EVENT.register((stack, context, type, tooltip) -> {
			Float waterAmount = stack.get(MEOComponents.WATER_AMOUNT);

			if (waterAmount != null) {
				tooltip.add(Component.translatable("item.mors-est-optio.water_pouch.info", waterAmount).withStyle(ChatFormatting.AQUA));
			}
		});
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MEO_CREATIVE_TAB_KEY, MEO_CREATIVE_TAB);

		LOGGER.info("Get Ready For A Whole New Experience");

		ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
			for (ServerPlayer player : minecraftServer.getPlayerList().getPlayers()) {
				PlayerTemperature.serverTick(player);
				PlayerNutrition.serverTick(player);
				PlayerInjury.serverTick(player);
				PlayerHydration.serverTick(player);
				PlayerStamina.serverTick(player);
				PlayerFatigue.serverTick(player);
				PlayerWetness.serverTick(player);
				PlayerExposure.serverTick(player);

				WorldWindDirection.serverTick(player);
				WorldWindStrength.serverTick(player);
				WorldSeasons.serverTick(player);


				MEOWindDriftingEvent.serverTick(player);
			}
		});

		MEOWoodBreakingEvent woodBreakingEvent = new MEOWoodBreakingEvent();
		MEOStoneBreakingEvent stoneBreakingEvent = new MEOStoneBreakingEvent();
		ServerLivingEntityEvents.AFTER_DAMAGE.register(new PlayerInjury().afterDamage);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
