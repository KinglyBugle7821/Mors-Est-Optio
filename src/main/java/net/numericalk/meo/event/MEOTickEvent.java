package net.numericalk.meo.event;

import net.minecraft.server.level.ServerPlayer;
import net.numericalk.meo.mechs.MEOTempMech;
import net.numericalk.meo.mechs.MEOWaterCooling;

public class MEOTickEvent {
    public static void serverTick(ServerPlayer player) {
        if (player.tickCount % 10 == 0) {
            MEOTempMech meoTempMech = new MEOTempMech(player);
            MEOWaterCooling.tick(player);
        }
    }
}
