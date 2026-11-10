package net.numericalk.meo.event;

import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.entity.MEOAttachments;

public class MEOCorrelations {
    public static float NSCorrelation(Player player){ // Nutrition - Stamina
        float nutritionSum = player.getAttachedOrElse(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT, 0.5F) +
                player.getAttachedOrElse(MEOAttachments.PLAYER_PROTEIN_ATTACHMENT, 0.5F) +
                player.getAttachedOrElse(MEOAttachments.PLAYER_FAT_ATTACHMENT, 0.5F);
        return -0.005f * nutritionSum + 0.025f;
    }

    public static float NTCorrelation(Player player){ // Nutrition - Temperature
        float nutritionSum = player.getAttachedOrElse(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT, 0.5F) +
                player.getAttachedOrElse(MEOAttachments.PLAYER_FAT_ATTACHMENT, 0.5F);
        return 0.07f * nutritionSum;
    }

    public static float HSCorrelation(Player player){ //Hydration - Stamina
        float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 1f);
        return -0.08f * hydration + 0.08f;
    }

    public static float HTCorrelation (Player player){ // Hydration - Temperature
        float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 1.0f);
        float playerTemperature = player.getAttachedOrElse(MEOAttachments.PLAYER_TEMPERATURE_ATTACHMENT, 37F);
        return (float) (Math.clamp((Math.log(-6 * hydration + 8) / Math.log(2)) - 3, -2, 0) *
                        Math.clamp((playerTemperature - 37) / 3f, 0f, 1f)
        );
    }
    public static float THCorrelation (Player player) { // Temperature - Hydration
        float playerTemperature = player.getAttachedOrElse(MEOAttachments.PLAYER_TEMPERATURE_ATTACHMENT, 37F);

        return 0.000446667f * ((float) Math.pow(playerTemperature - 37, 2))
                + 0.000993333f * (playerTemperature - 37)
                + 0.001f;
    }

    public static float HuNCorrelation(Player player){ // Hunger - Nutrition
        float hunger = ((float) player.getFoodData().getFoodLevel());
        return -0.0025f * hunger + 0.05f;
    }

    public static float TNCorrelation(Player player) { //Temperature - Nutrition
        float temperature = player.getAttachedOrElse(MEOAttachments.PLAYER_TEMPERATURE_ATTACHMENT, 1.0f);
        return (7f/90000f) * ((float) Math.pow(temperature - 37, 2))
                - (1f/3000f) * (temperature - 37)
                + 0.001f;
    }
}
