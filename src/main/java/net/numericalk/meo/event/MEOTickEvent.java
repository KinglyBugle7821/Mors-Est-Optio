package net.numericalk.meo.event;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.entity.MEOEffects;
import net.numericalk.meo.entity.effects.FreezingEffect;
import net.numericalk.meo.mechs.MEOTempMech;
import net.numericalk.meo.mechs.MEOWaterCooling;

public class MEOTickEvent {
    private static final Identifier FREEZING_ID =
            Identifier.fromNamespaceAndPath(MorsEstOptio.MOD_ID, "freezing");

    public static void serverTick(ServerPlayer player) {
        if (player.tickCount % 10 == 0) {
            MEOTempMech meoTempMech = new MEOTempMech(player);
            MEOWaterCooling.tick(player);
        }
        var freezingEffect = player.getEffect(MEOEffects.FREEZING);

        if (freezingEffect != null) {
            applySlowness(player, freezingEffect.getAmplifier());
        }
        else removeSlowness(player);
    }
    public static void applySlowness(LivingEntity entity, int amplifier) {
        var attribute = entity.getAttribute(Attributes.MOVEMENT_SPEED);

        if (attribute == null) return;

        if (attribute.getModifier(FREEZING_ID) == null) {
            attribute.addTransientModifier(
                    new AttributeModifier(
                            FREEZING_ID,
                            -0.2 * amplifier,
                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                    )
            );
        }
    }
    public static void removeSlowness(LivingEntity entity) {
        var attribute = entity.getAttribute(Attributes.MOVEMENT_SPEED);

        if (attribute != null) {
            attribute.removeModifier(FREEZING_ID);
        }
    }
}
