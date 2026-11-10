package net.numericalk.meo.mechs;

import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.entity.MEOAttachments;
import net.numericalk.meo.event.MEOCorrelations;

public class MEONutritionMech {
    public MEONutritionMech(Player player){
        float playerCarbohydrates = player.getAttachedOrElse(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT, 1.0F);
        float playerProteins = player.getAttachedOrElse(MEOAttachments.PLAYER_PROTEIN_ATTACHMENT, 1.0F);
        float playerFat = player.getAttachedOrElse(MEOAttachments.PLAYER_FAT_ATTACHMENT, 1.0F);

        playerCarbohydrates -= MEOCorrelations.TNCorrelation(player) + MEOCorrelations.HuNCorrelation(player);
        playerProteins -= MEOCorrelations.TNCorrelation(player) + MEOCorrelations.HuNCorrelation(player);
        playerFat -= MEOCorrelations.TNCorrelation(player) + MEOCorrelations.HuNCorrelation(player);

        player.setAttached(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT, Math.max(playerCarbohydrates, 0));
        player.setAttached(MEOAttachments.PLAYER_PROTEIN_ATTACHMENT, Math.max(playerProteins, 0));
        player.setAttached(MEOAttachments.PLAYER_FAT_ATTACHMENT, Math.max(playerFat, 0));
    }
}
