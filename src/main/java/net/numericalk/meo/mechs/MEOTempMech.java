package net.numericalk.meo.mechs;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.numericalk.meo.datagen.MEOItemTagProvider;
import net.numericalk.meo.attachments.MEOAttachments;
import net.numericalk.meo.entity.MEOEffects;
import net.numericalk.meo.attachments.MEOCorrelations;

public class MEOTempMech {
    public MEOTempMech(Player player){
        float playerTemperature = player.getAttachedOrCreate(
                MEOAttachments.TEMPERATURE
        );

        Level level = player.level();
        BlockPos playerPos = player.blockPosition();
        int playerY = playerPos.getY();

        // ENVIRONMENT TEMP
        // Biome
        float baseBiomeTemperature = level.getBiome(playerPos).value().getBaseTemperature();
        float biomeTemperature = (float) (
                (-1525.0 / 324.0) * Math.pow(baseBiomeTemperature, 3)
                        + (5165.0 / 648.0) * Math.pow(baseBiomeTemperature, 2)
                        + (5795.0 / 324.0) * baseBiomeTemperature
        );
        // Altitude
        float altitudeAdjustment = (float) ((0.0001105497 * Math.pow(playerY, 2)) - (0.15850906 * playerY) + 9.40260859);
        // Daytime
        int time = (int) (level.getOverworldClockTime() % 24000);
        float dayTimeAdjustment = (float) (
                (-11 * Math.pow(biomeTemperature, 2) + 197 * biomeTemperature + 2700) / 780.0 +
                        ((25 * Math.pow(biomeTemperature, 2) - 443 * biomeTemperature + 13756) / 4004.0) *
                                Math.cos(Math.PI * (time - 6000) / 12000.0)
        );
        // Shaded
        float skyLightLevel = level.getBrightness(LightLayer.SKY, playerPos);
        float shadedAdjustment = Math.max(Math.min(skyLightLevel, 15), 0)/3;
        // Weather
        float weatherAdjustment;
        if (level.isRaining() && !level.isThundering()) weatherAdjustment = -3;
        else if (level.isRaining() && level.isThundering()) weatherAdjustment = -5;
        else weatherAdjustment = 0;

        //LocalBlockThermalField
        float localBlockAdjustment = (float) LocalBlockThermalField.getTemperatureOffset(level, player);

                    //Player PlayerTemperature
        //Leather Armor Insulation
        boolean isWearingCap = player.getItemBySlot(EquipmentSlot.HEAD).is(MEOItemTagProvider.CAP);
        boolean isWearingTunic = player.getItemBySlot(EquipmentSlot.CHEST).is(MEOItemTagProvider.TUNIC);
        boolean isWearingPants = player.getItemBySlot(EquipmentSlot.LEGS).is(MEOItemTagProvider.PANTS);
        boolean isWearingBoots = player.getItemBySlot(EquipmentSlot.FEET).is(MEOItemTagProvider.BOOTS);
        float insulation = 1 - (1 - (isWearingCap ? 0.05f : 0.0f)) * (1 - (isWearingTunic ? 0.2f : 0.0f)) * (1 - (isWearingPants ? 0.15f : 0.0f)) * (1 - (isWearingBoots ? 0.1f : 0.0f));

        float waterTemp = biomeTemperature + dayTimeAdjustment * 0.1f;
        float seasonAdjustment = MEOCorrelations.STCorrelation(player);

        //Total
        float environmentTemperature =
                biomeTemperature
                        + altitudeAdjustment
                        + dayTimeAdjustment
                        + weatherAdjustment
                        + localBlockAdjustment
                        + shadedAdjustment
                        + seasonAdjustment;

        float exposureTemperature = environmentTemperature;

        if (player.isInWater()) {
            exposureTemperature = waterTemp;
        }

        float heatExchangeRate = 0.01f;

        float heatExchange = heatExchangeRate
                * (exposureTemperature - playerTemperature)
                * (1.0f - insulation);

        float heatHomeostasis = 0.1f * (37.0f - playerTemperature);

        float heatMetabolism = MEOCorrelations.NTCorrelation(player);

        float activityFactor =
                player.isSprinting() && player.isJumping() ? 1.0f :
                        player.isSprinting() ? 0.5f :
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

        float drinkingCooling = MEOCorrelations.HTCorrelation(player);
        float wetnessCooling = MEOCorrelations.WTCorrelation(player);
        float windCooling = MEOCorrelations.WiTCorrelation(player);

        float temperatureChange =
                heatExchange
                        + heatHomeostasis
                        + heatMetabolism
                        + heatExercise
                        + heatShivering
                        + heatSweating
                        + drinkingCooling
                        + wetnessCooling
                        + windCooling;

        playerTemperature += temperatureChange;

        player.setAttached(
                MEOAttachments.TEMPERATURE,
                playerTemperature
        );

        //HYPOTHERMIA/HYPERTHERMIA
        if (playerTemperature <= 34){
            playerHypothermia(player);
        }
        if (playerTemperature >= 40){
            playerHyperthermia(player);
        }
    }

    private void playerHyperthermia(Player player) {
        MobEffectInstance instance = new MobEffectInstance(MEOEffects.OVERHEATING, 3 * 20, 1, false, false, false);
        player.addEffect(instance);
    }

    private void playerHypothermia(Player player) {
        MobEffectInstance instance = new MobEffectInstance(MEOEffects.FREEZING, 3 * 20, 1, false, false, false);
        player.addEffect(instance);
    }
}