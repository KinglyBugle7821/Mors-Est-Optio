package net.numericalk.meo.mechs;

import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.entity.attachments.MEOCorrelations;

public class MEONutritionMech {
    public MEONutritionMech(Player player){
        float playerCarbohydrates = player.getAttachedOrElse(MEOAttachments.CARBOHYDRATE, 1.0F);
        float playerProteins = player.getAttachedOrElse(MEOAttachments.PROTEIN, 1.0F);
        float playerFat = player.getAttachedOrElse(MEOAttachments.FAT, 1.0F);

        playerCarbohydrates -= MEOCorrelations.TNCorrelation(player) + MEOCorrelations.HuNCorrelation(player);
        playerProteins -= MEOCorrelations.TNCorrelation(player) + MEOCorrelations.HuNCorrelation(player);
        playerFat -= MEOCorrelations.TNCorrelation(player) + MEOCorrelations.HuNCorrelation(player);

        player.setAttached(MEOAttachments.CARBOHYDRATE, Math.max(playerCarbohydrates, 0));
        player.setAttached(MEOAttachments.PROTEIN, Math.max(playerProteins, 0));
        player.setAttached(MEOAttachments.FAT, Math.max(playerFat, 0));
    }
}
