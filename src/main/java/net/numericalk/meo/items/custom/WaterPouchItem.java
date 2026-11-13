package net.numericalk.meo.items.custom;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.numericalk.meo.attachments.MEOAttachments;
import net.numericalk.meo.components.MEOComponents;

public class WaterPouchItem extends Item {
    public WaterPouchItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        if (!level.isClientSide() && entity instanceof ServerPlayer player) {
            if (!stack.has(MEOComponents.WATER_AMOUNT)) return stack;

            float hydration = player.getAttachedOrElse(MEOAttachments.HYDRATION, 1f);
            float waterAmount = stack.getOrDefault(MEOComponents.WATER_AMOUNT, 0.0f);

            float hydrationGain = Math.min(1.0f - hydration, waterAmount / 100.0f);

            hydration += hydrationGain;
            waterAmount -= hydrationGain * 100.0f;

            hydration = Math.clamp(hydration, 0.0f, 1.0f);
            waterAmount = Math.clamp(waterAmount, 0.0f, 100.0f);

            player.setAttached(MEOAttachments.HYDRATION, hydration);
            stack.set(MEOComponents.WATER_AMOUNT, waterAmount);
        }

        return stack;
    }
}
