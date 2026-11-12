package net.numericalk.meo.world.attachments;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.network.WindNetworking;

public class WindStrength {
    private static float strength = 0.05f;
    public static void serverTick(Player player) {
        Level level = player.level();

        float windStrength = level.getAttachedOrElse(MEOAttachments.WIND_STRENGTH, 0.05f);

        if (player.tickCount % 300 == 0) {
            int randomChance = RandomSource.create().nextInt(1, 100);
            boolean common = 1 <= randomChance && randomChance < 50;
            boolean uncommon = 50 <= randomChance && randomChance < 75;
            boolean rare = 75 <= randomChance && randomChance < 92;
            boolean superRare = 92 <= randomChance && randomChance <= 100;

            System.out.println(common ? "Common" : uncommon ? "Uncommon" : rare ? "Rare" : "Super Rare");

            if (common) strength = nextFloat(0, 0.2f);
            else if (uncommon) strength = nextFloat(0.2f, 0.5f);
            else if (rare) strength = nextFloat(0.5f, 0.8f);
            else if (superRare) strength = nextFloat(0.8f, 1f);
        }

        float difference = strength - windStrength;
        if (difference > 0)
            windStrength = Math.min(windStrength + 0.01F, 1.0F);
        else if (difference < 0)
            windStrength= Math.max(windStrength - 0.01F, 0.0F);

        level.setAttached(MEOAttachments.WIND_STRENGTH, windStrength);

        if (player instanceof ServerPlayer serverPlayer
                && player.tickCount % 5 == 0) {

            WindNetworking.syncPlayer(serverPlayer);
        }
    }
    public static float nextFloat(float min, float max) {
        RandomSource random = RandomSource.create();
        return min + random.nextFloat() * (max - min);
    }
}
