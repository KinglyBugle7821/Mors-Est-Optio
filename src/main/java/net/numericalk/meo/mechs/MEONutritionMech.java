package net.numericalk.meo.mechs;

import net.minecraft.world.entity.player.Player;
import net.numericalk.meo.entity.MEOAttachments;

public class MEONutritionMech {
    public MEONutritionMech(Player player){
        float playerCarbohydrates = player.getAttachedOrCreate(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT);
        float playerProteins = player.getAttachedOrCreate(MEOAttachments.PLAYER_PROTEIN_ATTACHMENT);
        float playerFat = player.getAttachedOrCreate(MEOAttachments.PLAYER_FAT_ATTACHMENT);

        player.setAttached(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT, Math.max(playerCarbohydrates - (1/20f), 0));
        player.setAttached(MEOAttachments.PLAYER_PROTEIN_ATTACHMENT, Math.max(playerProteins - (1/20f), 0));
        player.setAttached(MEOAttachments.PLAYER_FAT_ATTACHMENT, Math.max(playerFat - (1/20f), 0));
    }
}
