package net.numericalk.meo.screen;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.entity.MEOAttachments;

public class MEONutritionGUI {
    private static final Identifier NUTRITION_BAR = Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "textures/gui/sprites/hud/nutrition_bar.png");

    public static final Identifier CARBS_ICON = Identifier.withDefaultNamespace("textures/item/wheat.png");
    public static final Identifier PROTEINS_ICON = Identifier.withDefaultNamespace("textures/item/cooked_beef.png");
    public static final Identifier FATS_ICON = Identifier.withDefaultNamespace("textures/item/honey_bottle.png");

    public static HudElement hudLayer(){
        return (graphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();

            Player player = client.player;
            assert player != null;

            float playerCarbohydrates = player.getAttachedOrElse(MEOAttachments.CARBOHYDRATE, 1.0F);
            float playerProteins = player.getAttachedOrElse(MEOAttachments.PROTEIN, 1.0F);
            float playerFats = player.getAttachedOrElse(MEOAttachments.FAT, 1.0F);

            int width = client.getWindow().getGuiScaledWidth();
            int height = client.getWindow().getGuiScaledHeight();

            int carbBarHeight = Math.round(Math.clamp(20 * playerCarbohydrates + 1, 1, 21));
            int proteinBarHeight = Math.round(Math.clamp(20 * playerProteins + 1, 1, 21));
            int fatBarHeight = Math.round(Math.clamp(20 * playerFats + 1, 1, 21));

            int barWidth = 7;
            int barHeight = 22;

            int barX = width / 2 + 100 - barWidth;
            int barY = height - barHeight;

            //Carbs
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    NUTRITION_BAR,
                    barX, barY,
                    0, 0,
                    barWidth, barHeight,
                    barWidth, barHeight);
            graphics.fillGradient(
                    barX + 1,
                    barY + barHeight - 1 - carbBarHeight,
                    barX + barWidth - 1,
                    barY + barHeight - 1,
                    0xFF22D91C,
                    0xFF0F690D
            );
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    CARBS_ICON,
                    barX + 1, barY - 8,
                    0, 0,
                    7, 7,
                    16, 16,
                    16, 16
            );

            //PROTEINS
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    NUTRITION_BAR,
                    barX + 7, barY,
                    0, 0,
                    barWidth, barHeight,
                    barWidth, barHeight);
            graphics.fillGradient(
                    barX + 8,
                    barY + barHeight - 1 - proteinBarHeight,
                    barX + 13,
                    barY + barHeight - 1,
                    0xFFCC8B1B,
                    0xFF69470D
            );
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    PROTEINS_ICON,
                    barX + 8, barY - 8,
                    0, 0,
                    7, 7,
                    16, 16,
                    16, 16
            );

            //FATS
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    NUTRITION_BAR,
                    barX + 14, barY,
                    0, 0,
                    barWidth, barHeight,
                    barWidth, barHeight);
            graphics.fillGradient(
                    barX + 15,
                    barY + barHeight - 1 - fatBarHeight,
                    barX + 20,
                    barY + barHeight - 1,
                    0xFFD6D91C,
                    0xFF66690D
            );
            graphics.blit(
                    RenderPipelines.GUI_TEXTURED,
                    FATS_ICON,
                    barX + 15, barY - 8,
                    0, 0,
                    7, 7,
                    16, 16,
                    16, 16
            );
        };
    }
}
