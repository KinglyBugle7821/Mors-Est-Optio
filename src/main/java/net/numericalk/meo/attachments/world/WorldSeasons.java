package net.numericalk.meo.attachments.world;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.numericalk.meo.attachments.MEOAttachments;

public class WorldSeasons {
    public static void serverTick(ServerPlayer player) {
        Level level = player.level();
        int seasonId = level.getAttachedOrElse(MEOAttachments.SEASON, 0);

        if (level.getGameTime() % 600 == 0){
            seasonId = (seasonId + 1) % 4;
            level.setAttached(MEOAttachments.SEASON, seasonId);
        }
    }
}
