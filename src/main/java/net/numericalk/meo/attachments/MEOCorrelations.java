package net.numericalk.meo.attachments;

import net.minecraft.world.entity.player.Player;

public class MEOCorrelations {
    public static float NSCorrelation(Player player){ // PlayerNutrition - PlayerStamina
        float nutritionSum = player.getAttachedOrElse(MEOAttachments.CARBOHYDRATE, 1.0F) +
                player.getAttachedOrElse(MEOAttachments.PROTEIN, 1.0F) +
                player.getAttachedOrElse(MEOAttachments.FAT, 1.0F);
        return -0.005f * nutritionSum + 0.025f;
    }

    public static float NTCorrelation(Player player){ // PlayerNutrition - PlayerTemperature
        float nutritionSum = player.getAttachedOrElse(MEOAttachments.CARBOHYDRATE, 1.0F) +
                player.getAttachedOrElse(MEOAttachments.FAT, 1.0F);
        return 0.07f * nutritionSum;
    }

    public static float HSCorrelation(Player player){ //PlayerHydration - PlayerStamina
        float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 1f);
        return -0.08f * hydration + 0.08f;
    }

    public static float HTCorrelation (Player player){ // PlayerHydration - PlayerTemperature
        float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 1.0f);
        float playerTemperature = player.getAttachedOrElse(MEOAttachments.TEMPERATURE, 37F);
        return (float) (Math.clamp((Math.log(-6 * hydration + 8) / Math.log(2)) - 3, -2, 0) *
                        Math.clamp((playerTemperature - 37) / 3f, 0f, 1f)
        );
    }
    public static float THCorrelation (Player player) { // PlayerTemperature - PlayerHydration
        float playerTemperature = player.getAttachedOrElse(MEOAttachments.TEMPERATURE, 37F);

        return 0.000446667f * ((float) Math.pow(playerTemperature - 37, 2))
                + 0.000993333f * (playerTemperature - 37)
                + 0.001f;
    }

    public static float HuNCorrelation(Player player){ // Hunger - PlayerNutrition
        float hunger = ((float) player.getFoodData().getFoodLevel());
        return -0.0025f * hunger + 0.05f;
    }

    public static float TNCorrelation(Player player) { //PlayerTemperature - PlayerNutrition
        float temperature = player.getAttachedOrElse(MEOAttachments.TEMPERATURE, 1.0f);
        return (7f/90000f) * ((float) Math.pow(temperature - 37, 2))
                - (1f/3000f) * (temperature - 37)
                + 0.001f;
    }

    public static float STCorrelation(Player player) { //Season - Temperature
        int seasonId = player.level().getAttachedOrElse(MEOAttachments.SEASON, 0);

        return seasonId == 0 ? 2 : seasonId == 1 ? 7 : seasonId == 2 ? -5 : seasonId == 3 ? -15 : 0;
    }

    public static float WTCorrelation (Player player) { //PlayerWetness - PlayerTemperature
        float wetness = player.getAttachedOrElse(MEOAttachments.WETNESS, 0.0F);
        return -(wetness/5);
    }

    public static float WiTCorrelation (Player player) { //Wind - PlayerTemperature
        float windStrength = player.level().getAttachedOrElse(MEOAttachments.WIND_STRENGTH, 0.05F);
        return -(windStrength/15);
    }
}
