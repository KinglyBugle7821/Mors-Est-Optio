package net.numericalk.meo.mechs;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.world.entity.player.Player;

public class MEOWaterCooling {
    private static final Map<UUID, Float> WATER_COOLING =
            new HashMap<>();

    public static void addWaterCooling(Player player, float amount) {
        UUID uuid = player.getUUID();

        WATER_COOLING.merge(uuid, amount, Float::sum);
    }

    public static float getWaterCooling(Player player) {
        return WATER_COOLING.getOrDefault(player.getUUID(), 0f);
    }

    public static void tick(Player player) {
        UUID uuid = player.getUUID();
        float cooling = getWaterCooling(player);

        if (cooling > 0f) {
            WATER_COOLING.put(uuid, Math.max(0f, cooling - 0.05f));
        }
    }
}