package net.numericalk.meo.attachments.player;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.numericalk.meo.attachments.MEOAttachments;

public class PlayerWetness {
    public static void serverTick(ServerPlayer player) {
        if (player.tickCount % 20 == 0){
            Level level = player.level();
            BlockPos pos = player.blockPosition();

            float wetness = player.getAttachedOrElse(MEOAttachments.WETNESS, 0F);
            if (player.isInWater()){
                wetness += 1;
                player.setAttached(MEOAttachments.WETNESS, Math.clamp(wetness, 0, 1));
            } else if (player.isInShallowWater()){
                wetness += 0.5f;
                player.setAttached(MEOAttachments.WETNESS, Math.clamp(wetness, 0, 1));
            }

            if (level.isRainingAt(pos)){
                wetness += 1f/30f;
                player.setAttached(MEOAttachments.WETNESS, Math.clamp(wetness, 0, 1));
            } else if (level.isRainingAt(pos) && level.isThundering()){
                wetness += 1f/15f;
                player.setAttached(MEOAttachments.WETNESS, Math.clamp(wetness, 0, 1));
            } else {
                float biomeTemp = level.getBiome(pos).value().getBaseTemperature();

                wetness -= (1f/80f) * biomeTemp;
                player.setAttached(MEOAttachments.WETNESS, Math.clamp(wetness, 0, 1));
            }
        }
    }
}
