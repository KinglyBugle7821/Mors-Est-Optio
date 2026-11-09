package net.numericalk.meo.event;

import net.minecraft.server.level.ServerPlayer;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOStaminaEvent {
    public static void serverTick(ServerPlayer player) {
        float stamina = player.getAttachedOrElse(MEOAttachments.STAMINA, 1F);
        if (player.tickCount % 10 == 0){
            if (player.isJumping()) player.setAttached(MEOAttachments.STAMINA, Math.max(stamina - (stamina/25), 0));
            if (player.isSprinting()) player.setAttached(MEOAttachments.STAMINA, Math.max(stamina - (stamina/30), 0));

            //if player is not moving, add stamina gradually
        }
    }
}
