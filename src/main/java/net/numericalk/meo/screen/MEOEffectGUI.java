package net.numericalk.meo.screen;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOEffectGUI {
    private static final Identifier FREEZING_GUI = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID,"textures/misc/freezing_outline.png");
    private static final Identifier OVERHEAT_GUI = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "textures/misc/overheat_outline.png");
    private static final Identifier NORMAL_GUI = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "textures/misc/neutral.png");
    public static HudElement hudLayer(){
        return (graphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();

            int width = client.getWindow().getGuiScaledWidth();
            int height = client.getWindow().getGuiScaledHeight();

            Player player = client.player;
            assert player != null;

            float playerTemperature = player.getAttachedOrElse(MEOAttachments.TEMPERATURE, 37f);
            float opacity = Math.max(Math.min(Math.max(playerTemperature-35, -1), 0), Math.min(playerTemperature, 40)-39);
            float absoluteOpacity = Math.abs(opacity);

            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    opacity < 0 ? FREEZING_GUI : opacity > 0 ? OVERHEAT_GUI : NORMAL_GUI,
                    0, 0,
                    0, 0,
                    width, height,
                    width, height,
                    ARGB.white(absoluteOpacity)
                    );

        };
    }
}
