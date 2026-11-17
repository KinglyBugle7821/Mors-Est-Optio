package net.numericalk.meo.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import net.numericalk.meo.attachments.MEOAttachments;
import net.numericalk.meo.misc.FoodNutrition;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FoodProperties.class)
public class EatingMixin {
    @Inject(method = "onConsume", at = @At("HEAD"))
    private void onFoodConsumed(Level level, LivingEntity user, ItemStack stack, Consumable consumable, CallbackInfo ci) {
        if (!(user instanceof Player player)) return;


        setNutrition(Items.BREAD, FoodNutrition.BREAD, player, stack);
        setNutrition(Items.POTATO, FoodNutrition.POTATO, player, stack);
        setNutrition(Items.BAKED_POTATO, FoodNutrition.BAKED_POTATO, player, stack);
        setNutrition(Items.DRIED_KELP, FoodNutrition.DRIED_KELP, player, stack);
        setNutrition(Items.MELON_SLICE, FoodNutrition.MELON_SLICE, player, stack);
        setNutrition(Items.PUMPKIN_PIE, FoodNutrition.PUMPKIN_PIE, player, stack);
        setNutrition(Items.COOKIE, FoodNutrition.COOKIE, player, stack);

        setNutrition(Items.RABBIT_STEW, FoodNutrition.RABBIT_STEW, player, stack);
        setNutrition(Items.BEEF, FoodNutrition.BEEF, player, stack);
        setNutrition(Items.COOKED_BEEF, FoodNutrition.COOKED_BEEF, player, stack);
        setNutrition(Items.CHICKEN, FoodNutrition.CHICKEN, player, stack);
        setNutrition(Items.COOKED_CHICKEN, FoodNutrition.COOKED_CHICKEN, player, stack);
        setNutrition(Items.RABBIT, FoodNutrition.RABBIT, player, stack);
        setNutrition(Items.COOKED_RABBIT, FoodNutrition.COOKED_RABBIT, player, stack);
        setNutrition(Items.COD, FoodNutrition.COD, player, stack);
        setNutrition(Items.COOKED_COD, FoodNutrition.COOKED_COD, player, stack);
        setNutrition(Items.SALMON, FoodNutrition.SALMON, player, stack);
        setNutrition(Items.COOKED_SALMON, FoodNutrition.COOKED_SALMON, player, stack);
        setNutrition(Items.TROPICAL_FISH, FoodNutrition.TROPICAL_FISH, player, stack);

        setNutrition(Items.APPLE, FoodNutrition.APPLE, player, stack);
        setNutrition(Items.BEETROOT, FoodNutrition.BEETROOT, player, stack);
        setNutrition(Items.CARROT, FoodNutrition.CARROT, player, stack);
        setNutrition(Items.CHORUS_FRUIT, FoodNutrition.CHORUS_FRUIT, player, stack);
        setNutrition(Items.GLOW_BERRIES, FoodNutrition.GLOW_BERRIES, player, stack);
        setNutrition(Items.SWEET_BERRIES, FoodNutrition.SWEET_BERRIES, player, stack);
        setNutrition(Items.GOLDEN_APPLE, FoodNutrition.GOLDEN_APPLE, player, stack);
        setNutrition(Items.ENCHANTED_GOLDEN_APPLE, FoodNutrition.ENCHANTED_GOLDEN_APPLE, player, stack);
        setNutrition(Items.GOLDEN_CARROT, FoodNutrition.GOLDEN_CARROT, player, stack);
        setNutrition(Items.MUSHROOM_STEW, FoodNutrition.MUSHROOM_STEW, player, stack);
        setNutrition(Items.BEETROOT_SOUP, FoodNutrition.BEETROOT_SOUP, player, stack);

        setNutrition(Items.PORKCHOP, FoodNutrition.PORKCHOP, player, stack);
        setNutrition(Items.COOKED_PORKCHOP, FoodNutrition.COOKED_PORKCHOP, player, stack);
        setNutrition(Items.MUTTON, FoodNutrition.MUTTON, player, stack);
        setNutrition(Items.COOKED_MUTTON, FoodNutrition.COOKED_MUTTON, player, stack);
    }
    @Unique
    private void setNutrition(Item food, FoodNutrition foodNutrition, Player player, ItemStack stack) {
        float playerCarbohydrates = player.getAttachedOrElse(MEOAttachments.CARBOHYDRATE, 1.0F);
        float playerProteins = player.getAttachedOrElse(MEOAttachments.PROTEIN, 1.0F);
        float playerFats = player.getAttachedOrElse(MEOAttachments.FAT, 1.0F);

        if (stack.is(food)) {
            player.setAttached(MEOAttachments.CARBOHYDRATE, Math.min(playerCarbohydrates + (foodNutrition.getCarbohydrates() * 0.75f), 1));
            player.setAttached(MEOAttachments.PROTEIN, Math.min(playerProteins + (foodNutrition.getProtein() * 0.75f), 1));
            player.setAttached(MEOAttachments.FAT, Math.min(playerFats + (foodNutrition.getFat() * 0.75f), 1));
        }
    }
}
