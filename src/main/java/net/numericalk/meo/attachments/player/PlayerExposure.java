package net.numericalk.meo.attachments.player;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.numericalk.meo.attachments.MEOAttachments;

public class PlayerExposure {
    public static void serverTick(ServerPlayer player) {
        Level level = player.level();

        double roofDistance = 10;
        double wallDistance = 16;

        double x = player.getX();
        double z = player.getZ();

        Vec3 roofStart = new Vec3(
                x,
                player.getY() + player.getBbHeight() + 0.1,
                z
        );

        Vec3 wallStart = new Vec3(
                x,
                player.getY() + player.getBbHeight() * 0.5,
                z
        );

        boolean roofCovered = isBlocked(
                player, level, roofStart,
                new Vec3(0, 1, 0), roofDistance
        );

        boolean northCovered = isBlocked(
                player, level, wallStart,
                new Vec3(0, 0, -1), wallDistance
        );

        boolean southCovered = isBlocked(
                player, level, wallStart,
                new Vec3(0, 0, 1), wallDistance
        );

        boolean eastCovered = isBlocked(
                player, level, wallStart,
                new Vec3(1, 0, 0), wallDistance
        );

        boolean westCovered = isBlocked(
                player, level, wallStart,
                new Vec3(-1, 0, 0), wallDistance
        );

        float protection = 0f;

        if (roofCovered) protection += 0.5f;
        if (northCovered) protection += 0.125f;
        if (southCovered) protection += 0.125f;
        if (eastCovered) protection += 0.125f;
        if (westCovered) protection += 0.125f;

        float exposure = Math.clamp(1f - protection, 0f, 1f);

        player.setAttached(MEOAttachments.EXPOSURE, exposure);
    }
    private static boolean isBlocked(
            ServerPlayer player,
            Level level,
            Vec3 start,
            Vec3 direction,
            double distance
    ) {
        Vec3 end = start.add(direction.scale(distance));

        BlockHitResult hit = level.clip(
                new ClipContext(
                        start,
                        end,
                        ClipContext.Block.COLLIDER,
                        ClipContext.Fluid.NONE,
                        player
                )
        );

        return hit.getType() == HitResult.Type.BLOCK;
    }
}
