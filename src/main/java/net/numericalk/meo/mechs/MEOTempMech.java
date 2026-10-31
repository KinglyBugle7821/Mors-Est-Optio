package net.numericalk.meo.mechs;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.numericalk.meo.datagen.MEOItemTagProvider;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOTempMech {
    public MEOTempMech(Player player){
        float playerTemperature = player.getAttachedOrCreate(
                MEOAttachments.PLAYER_TEMPERATURE_ATTACHMENT
        );

        Level level = player.level();
        BlockPos playerPos = player.blockPosition();

                    //Environment Temp
        //Biome Temp
        float baseTemperature = level.getBiome(playerPos).value().getBaseTemperature();
        float biomeTemperature;
        if (baseTemperature <= 0.8) biomeTemperature = -15 + 35 * ((baseTemperature + 0.7f)/1.5f);
        else biomeTemperature = 20 + 25 * ((baseTemperature - 0.8f)/1.2f);

                    //Adjustments
        //Altitude
        float altitudeAdjustment = 0.1f * Math.max(-320, 62 - playerPos.getY());

        //Daytime
        int time = (int) (level.getOverworldClockTime() % 24000);
        double timeTemperatureConstant = Math.cos(2 * Math.PI * (time - 6000) / 24000.0);
        float daytimeAdjustment = (float) (5 * timeTemperatureConstant);

        //Shaded
        float skyLightLevel = level.getBrightness(LightLayer.SKY, playerPos);
        float shadedAdjustment = Math.max(Math.min(skyLightLevel, 15), 0)/3;

        //Weather
        float weatherAdjustment;
        if (level.isRaining() && !level.isThundering()) weatherAdjustment = -3;
        else if (level.isRaining() && level.isThundering()) weatherAdjustment = -5;
        else weatherAdjustment = 0;

        //LocalBlockThermalField
        float localBlockAdjustment = (float) LocalBlockThermalField.getTemperatureOffset(level, player);

                    //Player Temperature
        //Leather Armor Insulation
        boolean isWearingCap = player.getItemBySlot(EquipmentSlot.HEAD).is(MEOItemTagProvider.CAP);
        boolean isWearingTunic = player.getItemBySlot(EquipmentSlot.CHEST).is(MEOItemTagProvider.TUNIC);
        boolean isWearingPants = player.getItemBySlot(EquipmentSlot.LEGS).is(MEOItemTagProvider.PANTS);
        boolean isWearingBoots = player.getItemBySlot(EquipmentSlot.FEET).is(MEOItemTagProvider.BOOTS);
        float insulation = 1 - (1 - (isWearingCap ? 0.05f : 0.0f)) * (1 - (isWearingTunic ? 0.2f : 0.0f)) * (1 - (isWearingPants ? 0.15f : 0.0f)) * (1 - (isWearingBoots ? 0.1f : 0.0f));

        float waterTemp = biomeTemperature + daytimeAdjustment * 0.25f;

        //Total
        float environmentTemperature =
                biomeTemperature
                        + altitudeAdjustment
                        + daytimeAdjustment
                        + weatherAdjustment
                        + localBlockAdjustment
                        + shadedAdjustment;

        float exposureTemperature = environmentTemperature;

        if (player.isInWater()) {
            exposureTemperature = waterTemp;
        }

        float heatExchangeRate = 0.01f;

        float heatExchange = heatExchangeRate
                * (exposureTemperature - playerTemperature)
                * (1.0f - insulation);

        float heatHomeostasis = 0.05f * (37.0f - playerTemperature);

        float heatMetabolism = 0.14f;

        float activityFactor =
                player.isSprinting() && player.isJumping() ? 1.0f :
                        player.isSprinting() ? 0.7f :
                                player.isJumping() ? 0.2f : 0.0f;

        float heatExercise = 0.05f * activityFactor;

        float heatShivering = 0.05f * Mth.clamp(
                (36.0f - playerTemperature) / (36.0f - 32.0f),
                0.0f, 1.0f
        );

        float heatSweating = -0.05f * Mth.clamp(
                (playerTemperature - 37.5f) / (40.0f - 37.5f),
                0.0f, 1.0f
        );

        float drinkingCooling =
                MEOWaterCooling.getWaterCooling(player) > 0.0f
                        ? -0.02f : 0.0f;

        float temperatureChange =
                heatExchange
                        + heatHomeostasis
                        + heatMetabolism
                        + heatExercise
                        + heatShivering
                        + heatSweating
                        + drinkingCooling;

        playerTemperature += temperatureChange;

        player.setAttached(
                MEOAttachments.PLAYER_TEMPERATURE_ATTACHMENT,
                playerTemperature
        );
    }
}
