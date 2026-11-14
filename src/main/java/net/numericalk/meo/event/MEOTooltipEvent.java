package net.numericalk.meo.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.numericalk.meo.components.MEOComponents;
import net.numericalk.meo.misc.WaterQuality;

public class MEOTooltipEvent {
    public MEOTooltipEvent(){
        ItemTooltipCallback.EVENT.register((stack, context, type, tooltip) -> {
            Float waterAmount = stack.get(MEOComponents.WATER_AMOUNT);
            WaterQuality waterQuality = stack.get(MEOComponents.WATER_QUALITY);
            Float spoilTime = stack.get(MEOComponents.SPOIL_TIME);
            Boolean isSpoiled = stack.get(MEOComponents.IS_SPOILED);

            if (waterAmount != null && waterQuality != null) {
                String waterQualityText =
                        waterQuality == WaterQuality.CLEAN ? "Clean" :
                                waterQuality == WaterQuality.MURKY ? "Murky" :
                                        waterQuality == WaterQuality.DIRTY ? "Dirty" :
                                                waterQuality == WaterQuality.TOXIC ? "Toxic" :
                                                        waterQuality == WaterQuality.UNKNOWN ? "-" : "Unknown";

                tooltip.add(Component.translatable("item.mors-est-optio.water_pouch.amount_info", Math.ceil(waterAmount)).withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.translatable("item.mors-est-optio.water_pouch.quality_info", waterQualityText).withStyle(ChatFormatting.GRAY));
            }
            if (spoilTime != null && isSpoiled != null){
                int days = (int) Math.floor(spoilTime/4);
                int hour = (int) Math.floor((spoilTime % 4) * 6);

                String spoilTimeText = days + "d " + hour + "h ";

                tooltip.add(Component.translatable("item.mors-est-optio.food.spoil_time_info", spoilTimeText).withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.literal(isSpoiled ? "Spoiled" : "Not Spoiled Yet").withStyle(ChatFormatting.GRAY));
            }
        });
    }
}
