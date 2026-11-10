package net.numericalk.meo.screen;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOAttributesGUI {
    public static boolean visible = false;

    public static HudElement hudLayer() {
        return (graphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();

            Player player = client.player;
            assert player != null;
            if (!visible) return;

            float playerTemperature = player.getAttachedOrElse(MEOAttachments.PLAYER_TEMPERATURE_ATTACHMENT, 37F);
            float carbohydrates = player.getAttachedOrElse(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT, 1.0F);
            float proteins = player.getAttachedOrElse(MEOAttachments.PLAYER_PROTEIN_ATTACHMENT, 1.0F);
            float fats = player.getAttachedOrElse(MEOAttachments.PLAYER_FAT_ATTACHMENT, 1.0F);
            float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 1.0F);
            float stamina = player.getAttachedOrElse(MEOAttachments.STAMINA, 1.0F);
            float headInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_HEAD_INJURY, 0F);
            float bodyInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_BODY_INJURY, 0F);
            float armsInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_ARMS_INJURY, 0F);
            float legsInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_LEGS_INJURY, 0F);
            float fatigue = player.getAttachedOrElse(MEOAttachments.FATIGUE, 0F);

            graphics.text(client.font, "Player Temperature: " + playerTemperature, 0, 0, 0xFFFFFFFF);
            graphics.text(client.font, "Carbohydrates: " + carbohydrates, 0, 10, 0xFFFFFFFF);
            graphics.text(client.font, "Proteins: " + proteins, 0, 20, 0xFFFFFFFF);
            graphics.text(client.font, "Fats: " + fats, 0, 30, 0xFFFFFFFF);
            graphics.text(client.font, "Hydration: " + hydration, 0, 40, 0xFFFFFFFF);
            graphics.text(client.font, "Stamina: " + stamina, 0, 50, 0xFFFFFFFF);
            graphics.text(client.font, "Head Injury: " + headInjury, 0, 60, 0xFFFFFFFF);
            graphics.text(client.font, "Body Injury: " + bodyInjury, 0, 70, 0xFFFFFFFF);
            graphics.text(client.font, "Arms Injury: " + armsInjury, 0, 80, 0xFFFFFFFF);
            graphics.text(client.font, "Legs Injury: " + legsInjury, 0, 90, 0xFFFFFFFF);
            graphics.text(client.font, "Fatigue: " + fatigue, 0, 100, 0xFFFFFFFF);
        };
    }
}
