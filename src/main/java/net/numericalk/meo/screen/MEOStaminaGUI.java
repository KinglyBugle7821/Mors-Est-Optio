package net.numericalk.meo.screen;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.attachments.MEOAttachments;

public class MEOStaminaGUI {
    public static boolean visible = true;

    private static final Identifier ENERGY_BAR = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "textures/gui/sprites/hud/energy_bar.png");
    private static final Identifier ENERGY_BAR_EMPTY = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "textures/gui/sprites/hud/energy_bar_empty.png");

    public static HudElement hudLayer() {
        return (graphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();

            Player player = client.player;
            assert player != null;
            if (!visible) return;

            float stamina = player.getAttachedOrElse(MEOAttachments.STAMINA, 1F);

            int width = client.getWindow().getGuiScaledWidth();
            int height = client.getWindow().getGuiScaledHeight();

            int energyBarWidth = 20;
            int energyBarHeight = 22;

            int energyBarX = width / 2 - 120 - energyBarWidth;
            int energyBarY = height - energyBarHeight;

            int barWidth = 20;
            int barHeight = 22;

            int filledHeight = Math.round(stamina * barHeight);
            int emptyHeight = barHeight - filledHeight;

            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    ENERGY_BAR_EMPTY,
                    energyBarX, energyBarY,
                    0, 0,
                    barWidth, barHeight,
                    barWidth, barHeight
            );

            if (filledHeight > 0) {
                graphics.blit(
                        RenderPipelines.GUI_TEXTURED,
                        ENERGY_BAR,
                        energyBarX, energyBarY + emptyHeight,
                        0, emptyHeight,
                        barWidth, filledHeight,
                        barWidth, barHeight
                );
            }
        };
    }
}
