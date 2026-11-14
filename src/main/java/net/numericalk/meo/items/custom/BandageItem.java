package net.numericalk.meo.items.custom;

import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.numericalk.meo.attachments.MEOAttachments;

public class BandageItem extends Item {
    public BandageItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (entity instanceof Player player){
            float headInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_HEAD_INJURY, 0f);
            float bodyInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_BODY_INJURY, 0f);
            float armsInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_ARMS_INJURY, 0f);
            float legsInjury = player.getAttachedOrElse(MEOAttachments.PLAYER_LEGS_INJURY, 0f);

            reduceInjury(MEOAttachments.PLAYER_HEAD_INJURY, headInjury, player);
            reduceInjury(MEOAttachments.PLAYER_BODY_INJURY, bodyInjury, player);
            reduceInjury(MEOAttachments.PLAYER_ARMS_INJURY, armsInjury, player);
            reduceInjury(MEOAttachments.PLAYER_LEGS_INJURY, legsInjury, player);
        }
        return super.finishUsingItem(stack, level, entity);
    }

    private void reduceInjury(AttachmentType<Float> injuryAttachment, float injury, Player player) {
        float injuryReduced = 0.05f;
        if (injuryReduced < injury) player.setAttached(injuryAttachment, injury - injuryReduced);
        else player.setAttached(injuryAttachment, 0f);
    }
}
