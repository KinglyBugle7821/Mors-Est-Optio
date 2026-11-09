package net.numericalk.meo.event;

import net.minecraft.server.level.ServerPlayer;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOHydrationEvent {
    public static void serverTick(ServerPlayer player) {
        float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 0.5F);
        if (player.tickCount % 1200 == 0){
            player.setAttached(MEOAttachments.HYDRATION, Math.max(hydration - 0.1f, 0));
        }
        if (hydration < 0) player.setAttached(MEOAttachments.HYDRATION, 0f);
        if (hydration > 1) player.setAttached(MEOAttachments.HYDRATION, 1f);
    }
}
