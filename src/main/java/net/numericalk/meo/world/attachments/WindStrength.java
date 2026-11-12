package net.numericalk.meo.world.attachments;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.network.WindNetworking;

public class WindStrength {
    public static void serverTick(Player player) {
        Level level = player.level();

        float windStrength = level.getAttachedOrElse(MEOAttachments.WIND_STRENGTH, 0.05f);

        float strength = level.getAttachedOrElse(MEOAttachments.WIND_STRENGTH_TARGET, 0.05f);

        if (player.tickCount % 4000 == 0) {
            int randomChance = RandomSource.create().nextInt(1, 100);

            if (randomChance < 50) strength *= nextFloat(0f, 0.2f);
            else if (randomChance < 75) strength *= nextFloat(0.2f, 0.5f);
            else if (randomChance < 92) strength *= nextFloat(0.5f, 0.8f);
            else strength *= nextFloat(0.8f, 1f);

            level.setAttached(MEOAttachments.WIND_STRENGTH_TARGET, strength);
        }

        float difference = strength - windStrength;

        if (difference > 0) windStrength = Math.min(windStrength + 0.01f, 1f);
        else if (difference < 0) windStrength = Math.max(windStrength - 0.01f, 0f);

        level.setAttached(MEOAttachments.WIND_STRENGTH, windStrength);

        if (player instanceof ServerPlayer serverPlayer && player.tickCount % 5 == 0) {
            WindNetworking.syncPlayer(serverPlayer);
        }
    }

    public static float nextFloat(float min, float max) {
        return min + RandomSource.create().nextFloat() * (max - min);
    }
}
