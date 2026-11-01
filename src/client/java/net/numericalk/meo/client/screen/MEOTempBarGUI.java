package net.numericalk.meo.client.screen;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.mechs.MEOTempMech;

public class MEOTempBarGUI {
    //TODO : ADD HYPOTHERMIA IF PLAYER TEMPERATURE DROPS BELOW 34C
    //TODO : ADD

    private static final Identifier THERMOMETER = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "textures/gui/sprites/hud/thermometer.png");
    private static final Identifier THERMOMETER_ARROW = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "textures/gui/sprites/hud/thermometer_arrow.png");

    public static HudElement hudLayer() {
        return (graphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();

            int width = client.getWindow().getGuiScaledWidth();
            int height = client.getWindow().getGuiScaledHeight();

            Player player = client.player;
            assert player != null;

            float playerTemperature = player.getAttachedOrElse(MEOAttachments.PLAYER_TEMPERATURE_ATTACHMENT, 37f);
            int temperature = Math.round(7.8f * Math.max(Math.min(playerTemperature, 42), 32) - 288.6f);

            int barWidth = 81;
            int barHeight = 9;

            int barX = width / 2 + 91 - barWidth;
            int barY = height - 49 - barHeight - 2;

            int arrowWidth = 8;
            int arrowHeight = 8;

            int arrowX = barX + (barWidth - arrowWidth) / 2 + temperature;
            int arrowY = barY + (barHeight - arrowHeight) / 2 - 2;
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    THERMOMETER,
                    barX, barY,
                    0, 0,
                    81, 9,
                    81, 9
            );
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    THERMOMETER_ARROW,
                    arrowX, arrowY,
                    0, 0,
                    8, 8,
                    8, 8
                    );
            graphics.text(client.font, "Player Temperature: " + playerTemperature, 10, 10, 0xFFFFFFFF, false);
            graphics.text(client.font, "GUI Temperature Offset: " + temperature, 10, 20, 0xFFFFFFFF, false);
        };
    }
}
