
package net.numericalk.meo.attachments.world;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.numericalk.meo.attachments.MEOAttachments;
import net.numericalk.meo.network.MEONetworking;

public class WorldWindDirection {
    public static void serverTick(Player player) {
        Level level = player.level();

        int windDirection = level.getAttachedOrElse(
                MEOAttachments.WIND_DIRECTION, 0
        );

        int direction = level.getAttachedOrElse(
                MEOAttachments.WIND_DIRECTION_TARGET, 0
        );

        if (level.getGameTime() % 12000 == 0) {
            direction = RandomSource.create().nextInt(360);

            level.setAttached(
                    MEOAttachments.WIND_DIRECTION_TARGET, direction
            );
        }

        int difference = (direction - windDirection + 540) % 360 - 180;

        if (difference > 0) windDirection = (windDirection + 1) % 360;
        else if (difference < 0) windDirection = (windDirection + 359) % 360;

        level.setAttached(MEOAttachments.WIND_DIRECTION, windDirection);

        if (player instanceof ServerPlayer serverPlayer
                && player.tickCount % 5 == 0) {
            MEONetworking.syncPlayer(serverPlayer);
        }
    }
}
