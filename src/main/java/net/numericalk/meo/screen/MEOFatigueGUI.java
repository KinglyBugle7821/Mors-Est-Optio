package net.numericalk.meo.screen;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOFatigueGUI {
    public static boolean visible = false;

    private static final Identifier FATIGUE_BAR = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "textures/gui/sprites/hud/fatigue_bar.png");
    private static final Identifier FATIGUE_BAR_EMPTY = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "textures/gui/sprites/hud/fatigue_bar_empty.png");

    public static HudElement hudLayer() {

        return (graphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();

            Player player = client.player;
            assert player != null;
            if (!visible) return;

            float fatigue = player.getAttachedOrElse(MEOAttachments.FATIGUE, 0f);

            int width = client.getWindow().getGuiScaledWidth();
            int height = client.getWindow().getGuiScaledHeight();

            int fatigueBarWidth = 22;
            int fatigueBarHeight = 22;

            int fatigueBarX = width / 2 - 120 - fatigueBarWidth;
            int fatigueBarY = height - fatigueBarHeight;

            int barWidth = 22;
            int barHeight = 22;

            int filledHeight = Math.round(fatigue * barHeight);
            int emptyHeight = barHeight - filledHeight;

            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    FATIGUE_BAR_EMPTY,
                    fatigueBarX, fatigueBarY,
                    0, 0,
                    barWidth, barHeight,
                    barWidth, barHeight
            );

            if (filledHeight > 0) {
                graphics.blit(
                        RenderPipelines.GUI_TEXTURED,
                        FATIGUE_BAR,
                        fatigueBarX, fatigueBarY + emptyHeight,
                        0, emptyHeight,
                        barWidth, filledHeight,
                        barWidth, barHeight
                );
            }
            graphics.text(client.font, "Fatigue: " + fatigue, 10, 20, 0xFFFFFFFF);

        };

    }
}
