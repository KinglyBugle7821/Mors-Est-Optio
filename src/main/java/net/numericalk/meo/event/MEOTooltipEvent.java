package net.numericalk.meo.event;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.numericalk.meo.components.MEOComponents;
import net.numericalk.meo.misc.FoodNutrition;
import net.numericalk.meo.misc.WaterQuality;
import org.lwjgl.glfw.GLFW;

import java.util.List;

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

                String spoilTimeText = days + " days and less than " + hour + " hours ";

                if (days > 0 || hour > 0){
                    tooltip.add(Component.translatable("item.mors-est-optio.food.spoil_time_info", spoilTimeText).withStyle(ChatFormatting.GRAY));
                }
                tooltip.add(Component.literal(isSpoiled ? "§4Spoiled" : "Not Spoiled Yet"));
            }

            addFoodTooltip(stack, Items.BREAD, tooltip, FoodNutrition.BREAD);
            addFoodTooltip(stack, Items.POTATO, tooltip, FoodNutrition.POTATO);
            addFoodTooltip(stack, Items.BAKED_POTATO, tooltip, FoodNutrition.BAKED_POTATO);
            addFoodTooltip(stack, Items.DRIED_KELP, tooltip, FoodNutrition.DRIED_KELP);
            addFoodTooltip(stack, Items.MELON_SLICE, tooltip, FoodNutrition.MELON_SLICE);
            addFoodTooltip(stack, Items.PUMPKIN_PIE, tooltip, FoodNutrition.PUMPKIN_PIE);
            addFoodTooltip(stack, Items.COOKIE, tooltip, FoodNutrition.COOKIE);

            addFoodTooltip(stack, Items.BEEF, tooltip, FoodNutrition.BEEF);
            addFoodTooltip(stack, Items.COOKED_BEEF, tooltip, FoodNutrition.COOKED_BEEF);
            addFoodTooltip(stack, Items.CHICKEN, tooltip, FoodNutrition.CHICKEN);
            addFoodTooltip(stack, Items.COOKED_CHICKEN, tooltip, FoodNutrition.COOKED_CHICKEN);
            addFoodTooltip(stack, Items.RABBIT, tooltip, FoodNutrition.RABBIT);
            addFoodTooltip(stack, Items.COOKED_RABBIT, tooltip, FoodNutrition.COOKED_RABBIT);
            addFoodTooltip(stack, Items.COD, tooltip, FoodNutrition.COD);
            addFoodTooltip(stack, Items.COOKED_COD, tooltip, FoodNutrition.COOKED_COD);
            addFoodTooltip(stack, Items.SALMON, tooltip, FoodNutrition.SALMON);
            addFoodTooltip(stack, Items.COOKED_SALMON, tooltip, FoodNutrition.COOKED_SALMON);
            addFoodTooltip(stack, Items.TROPICAL_FISH, tooltip, FoodNutrition.TROPICAL_FISH);

            addFoodTooltip(stack, Items.APPLE, tooltip, FoodNutrition.APPLE);
            addFoodTooltip(stack, Items.BEETROOT, tooltip, FoodNutrition.BEETROOT);
            addFoodTooltip(stack, Items.CARROT, tooltip, FoodNutrition.CARROT);
            addFoodTooltip(stack, Items.CHORUS_FRUIT, tooltip, FoodNutrition.CHORUS_FRUIT);
            addFoodTooltip(stack, Items.GLOW_BERRIES, tooltip, FoodNutrition.GLOW_BERRIES);
            addFoodTooltip(stack, Items.SWEET_BERRIES, tooltip, FoodNutrition.SWEET_BERRIES);
            addFoodTooltip(stack, Items.GOLDEN_APPLE, tooltip, FoodNutrition.GOLDEN_APPLE);
            addFoodTooltip(stack, Items.ENCHANTED_GOLDEN_APPLE, tooltip, FoodNutrition.ENCHANTED_GOLDEN_APPLE);
            addFoodTooltip(stack, Items.GOLDEN_CARROT, tooltip, FoodNutrition.GOLDEN_CARROT);
            addFoodTooltip(stack, Items.MUSHROOM_STEW, tooltip, FoodNutrition.MUSHROOM_STEW);
            addFoodTooltip(stack, Items.BEETROOT_SOUP, tooltip, FoodNutrition.BEETROOT_SOUP);

            addFoodTooltip(stack, Items.PORKCHOP, tooltip, FoodNutrition.PORKCHOP);
            addFoodTooltip(stack, Items.COOKED_PORKCHOP, tooltip, FoodNutrition.COOKED_PORKCHOP);
            addFoodTooltip(stack, Items.MUTTON, tooltip, FoodNutrition.MUTTON);
            addFoodTooltip(stack, Items.COOKED_MUTTON, tooltip, FoodNutrition.COOKED_MUTTON);
        });
    }
    private void addFoodTooltip(ItemStack stack, Item item, List<Component> tooltip, FoodNutrition foodNutrition){
        boolean shiftDown = GLFW.glfwGetKey(
                Minecraft.getInstance().getWindow().handle(),
                GLFW.GLFW_KEY_LEFT_SHIFT
        ) == GLFW.GLFW_PRESS
                || GLFW.glfwGetKey(
                Minecraft.getInstance().getWindow().handle(),
                GLFW.GLFW_KEY_RIGHT_SHIFT
        ) == GLFW.GLFW_PRESS;

        if (stack.is(item)) {
            if (shiftDown) {
                tooltip.add(Component.translatable("item.mors-est-optio.food.nutrition.carbohydrates", foodNutrition.getCarbohydrates()).withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.translatable("item.mors-est-optio.food.nutrition.proteins", foodNutrition.getProtein()).withStyle(ChatFormatting.GRAY));
                tooltip.add(Component.translatable("item.mors-est-optio.food.nutrition.fats", foodNutrition.getFat()).withStyle(ChatFormatting.GRAY));
            } else {
                tooltip.add(Component.translatable("item.mors-est-optio.info.press_shift").withStyle(ChatFormatting.GRAY));
            }
        }
    }
}
