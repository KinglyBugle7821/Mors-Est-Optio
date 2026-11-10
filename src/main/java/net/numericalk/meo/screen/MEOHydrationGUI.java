package net.numericalk.meo.screen;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOHydrationGUI {
    private static final Identifier THIRST_BAR = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "textures/gui/sprites/hud/thirst_bar.png");

    public static HudElement hudLayer() {
        return (graphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();

            int width = client.getWindow().getGuiScaledWidth();
            int height = client.getWindow().getGuiScaledHeight();

            Player player = client.player;
            assert player != null;

            float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 1.0F);

            int barWidth = 81;
            int barHeight = 9;

            int hydrationWidth = Math.round(hydration * 81f);

            int barX = width / 2 - 10 - barWidth;
            int barY = height - 49 - barHeight - 2;

            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    THIRST_BAR,
                    barX, barY,
                    0, 0,
                    barWidth, barHeight,
                    81, 9
            );
            graphics.fill(
                    barX + 1, barY + 1,
                    Math.max(barX + hydrationWidth - 1, barX + 1), barY + barHeight - 1,
                    0xFF0E87CC
            );
        };
    }
}