
package net.numericalk.meo.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOWindDriftingEvent {

    private static final double WIND_CHECK_DISTANCE = 16.0;
    private static final double WIND_ACCELERATION = 0.025;

    // Sample different heights of the player's body.
    private static final double[] HEIGHT_SAMPLES = {
            0.2, 0.5, 0.8
    };


    public static void serverTick(ServerPlayer player) {

        Level level = player.level();

        int direction = level.getAttachedOrElse(
                MEOAttachments.WIND_DIRECTION, 0
        );

        float strength = level.getAttachedOrElse(
                MEOAttachments.WIND_STRENGTH, 0.05f
        );

        strength = Math.max(0.0f, Math.min(strength, 1.0f));

        if (player.isInWater() || player.isInPowderSnow) {
            return;
        }

        if (strength <= 0.5f) {
            return;
        }

        double radians = Math.toRadians(direction);

        Vec3 windDirection = new Vec3(
                Math.sin(radians),
                0,
                Math.cos(radians)
        );

        float windExposure = getWindExposure(
                player,
                windDirection,
                WIND_CHECK_DISTANCE
        );

        if (windExposure <= 0.0f) {
            return;
        }

        double moveAmount = 0.05f * strength - 0.025f;

        Vec3 windVelocity = windDirection.scale(
                moveAmount * windExposure
        );

        player.setDeltaMovement(
                player.getDeltaMovement().add(windVelocity)
        );

        player.hurtMarked = true;
    }


    public static float getWindExposure(
            ServerPlayer player,
            Vec3 windDirection,
            double distance
    ) {

        Level level = player.level();

        int unobstructedRays = 0;

        for (double heightRatio : HEIGHT_SAMPLES) {

            Vec3 target = player.position().add(
                    0,
                    player.getBbHeight() * heightRatio,
                    0
            );

            Vec3 start = target.subtract(
                    windDirection.scale(distance)
            );

            BlockHitResult hit = level.clip(
                    new ClipContext(
                            start,
                            target,
                            ClipContext.Block.COLLIDER,
                            ClipContext.Fluid.NONE,
                            player
                    )
            );

            if (hit.getType() == HitResult.Type.MISS) {
                unobstructedRays++;
            }
        }

        return (float) unobstructedRays / HEIGHT_SAMPLES.length;
    }
}
