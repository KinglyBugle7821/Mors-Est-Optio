package net.numericalk.meo.screen;

import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElement;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOInjuryGUI {
    public static boolean visible = false;
    public static HudElement hudLayer() {
        return (graphics, deltaTracker) -> {
            Minecraft client = Minecraft.getInstance();
            Player player = client.player;
            assert player != null;
            if (!visible) return;

            float headInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_HEAD_INJURY, 0f);
            float bodyInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_BODY_INJURY, 0f);
            float armsInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_ARMS_INJURY, 0f);
            float legsInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_LEGS_INJURY, 0f);

            int width = client.getWindow().getGuiScaledWidth();
            int height = client.getWindow().getGuiScaledHeight();

            int legsWidth = 38;
            int legsHeight = 50;

            int armsWidth = 18;
            int armsHeight = 50;

            int bodyWidth = 38;
            int bodyHeight = 50;

            int headSize = 38;

            int headInjuryOpacity = Math.round(Math.clamp(255 * headInjury, 0, 255));
            int bodyInjuryOpacity = Math.round(Math.clamp(255 * bodyInjury, 0, 255));
            int armsInjuryOpacity = Math.round(Math.clamp(255 * armsInjury, 0, 255));
            int legsInjuryOpacity = Math.round(Math.clamp(255 * legsInjury, 0, 255));

            //HEAD
            int headX0 = armsWidth;
            int headY0 = height - legsHeight - bodyHeight - headSize;
            int headX1 = armsWidth + headSize;
            int headY1 = height - legsHeight - bodyHeight;
            int xMidPointHead = Math.round((headX0 + headX1)/2f);
            int yMidPointHead = Math.round((headY0 + headY1)/2f);

            graphics.fill(
                    headX0, headY0,
                    headX1, headY1, 0xFFA4A4A4
                    );
            graphics.fill(
                    headX0, headY0,
                    headX1, headY1, 0x00FF0000 + (headInjuryOpacity * ((int) Math.pow(16, 6)))
            );
            graphics.centeredText(client.font, Math.round(headInjury * 100) + "%", xMidPointHead, yMidPointHead, 0xFF000000);
            //ARMS
                //RIGHT
            int rArmX0 = 0;
            int rArmY0 = height - legsHeight - armsHeight;
            int rArmX1 = armsWidth;
            int rArmY1 = height - legsHeight;
            int xMidPointRArm = Math.round((rArmX0 + rArmX1)/2f);
            int yMidPointRArm = Math.round((rArmY0 + rArmY1)/2f);

            graphics.fill(
                    rArmX0, rArmY0,
                    rArmX1, rArmY1, 0xFFA4A4A4
            );
            graphics.fill(
                    rArmX0, rArmY0,
                    rArmX1, rArmY1, 0x00FF0000 + (armsInjuryOpacity * ((int) Math.pow(16, 6)))
            );
            graphics.centeredText(client.font, Math.round(armsInjury * 100) + "%", xMidPointRArm, yMidPointRArm, 0xFF000000);

            //LEFT
            int lArmX0 = armsWidth + bodyWidth;
            int lArmY0 = height - legsHeight - bodyHeight;
            int lArmX1 = 2 * armsWidth + bodyWidth;
            int lArmY1 = height - legsHeight;
            int xMidPointLArm = Math.round((lArmX0 + lArmX1)/2f);
            int yMidPointLArm = Math.round((lArmY0 + lArmY1)/2f);

            graphics.fill(
                    lArmX0, lArmY0,
                    lArmX1, lArmY1, 0xFFA4A4A4
                    );
            graphics.fill(
                    lArmX0, lArmY0,
                    lArmX1, lArmY1, 0x00FF0000 + (armsInjuryOpacity * ((int) Math.pow(16, 6)))
            );
            graphics.centeredText(client.font, Math.round(armsInjury * 100) + "%", xMidPointLArm, yMidPointLArm, 0xFF000000);

            //BODY
            int bodyX0 = armsWidth;
            int bodyY0 = height - legsHeight - bodyHeight;
            int bodyX1 = armsWidth + bodyWidth;
            int bodyY1 = height - legsHeight;
            int xMidPointBody = Math.round((bodyX0 + bodyX1)/2f);
            int yMidPointBody = Math.round((bodyY0 + bodyY1)/2f);

            graphics.fill(
                    bodyX0, bodyY0,
                    bodyX1, bodyY1, 0xFFA0A0A0
            );
            graphics.fill(
                    bodyX0, bodyY0,
                    bodyX1, bodyY1, 0x00FF0000 + (bodyInjuryOpacity * ((int) Math.pow(16, 6)))
            );
            graphics.centeredText(client.font, Math.round(bodyInjury * 100) + "%", xMidPointBody, yMidPointBody, 0xFF000000);

            //LEGS
            int legsX0 = armsWidth;
            int legsY0 = height - legsHeight;
            int legsX1 = armsWidth + legsWidth;
            int legsY1 = height;
            int xMidPointLegs = Math.round((legsX0 + legsX1)/2f);
            int yMidPointLegs = Math.round((legsY0 + legsY1)/2f);

            graphics.fill(
                    legsX0, legsY0,
                    legsX1, legsY1, 0xFFA4A4A4
            );
            graphics.fill(
                    legsX0, legsY0,
                    legsX1, legsY1, 0x00FF0000 + (legsInjuryOpacity * ((int) Math.pow(16, 6)))
            );
            graphics.centeredText(client.font, Math.round(legsInjury * 100) + "%", xMidPointLegs, yMidPointLegs, 0xFF000000);
        };
    }
}
