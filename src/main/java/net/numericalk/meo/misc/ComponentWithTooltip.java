package net.numericalk.meo.misc;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipProvider;

import java.util.function.Consumer;

public record ComponentWithTooltip(float waterAmount) implements TooltipProvider {
    @Override
    public void addToTooltip(Item.TooltipContext tooltip, Consumer<Component> textConsumer, TooltipFlag type, DataComponentGetter components) {
        textConsumer.accept(Component.translatable("item.mors-est-optio.water_pouch.info", Math.ceil(this.waterAmount)).withStyle(ChatFormatting.DARK_AQUA));
    }
}