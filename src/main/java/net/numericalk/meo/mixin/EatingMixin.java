package net.numericalk.meo.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.level.Level;
import net.numericalk.meo.entity.MEOAttachments;
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

        setNutrition(Items.BREAD, 0.8F, 0.15F, 0.05F, player, stack);
        setNutrition(Items.POTATO, 0.9F, 0.08F, 0.02F, player, stack);
        setNutrition(Items.BAKED_POTATO, 0.9F, 0.08F, 0.02F, player, stack);
        setNutrition(Items.DRIED_KELP, 0.75F, 0.15F, 0.10F, player, stack);
        setNutrition(Items.MELON_SLICE, 0.9F, 0.05F, 0.05F, player, stack);
        setNutrition(Items.PUMPKIN_PIE, 0.65F, 0.05F, 0.30F, player, stack);
        setNutrition(Items.COOKIE, 0.65F, 0.05F, 0.30F, player, stack);

        setNutrition(Items.BEEF, 0.0F, 0.65F, 0.35F, player, stack);
        setNutrition(Items.COOKED_BEEF, 0.0F, 0.60F, 0.40F, player, stack);
        setNutrition(Items.CHICKEN, 0.0F, 0.75F, 0.25F, player, stack);
        setNutrition(Items.COOKED_CHICKEN, 0.0F, 0.75F, 0.25F, player, stack);
        setNutrition(Items.RABBIT, 0.0F, 0.85F, 0.15F, player, stack);
        setNutrition(Items.COOKED_RABBIT, 0.0F, 0.85F, 0.15F, player, stack);
        setNutrition(Items.COD, 0.0F, 0.85F, 0.15F, player, stack);
        setNutrition(Items.COOKED_COD, 0.0F, 0.9F, 0.1F, player, stack);
        setNutrition(Items.SALMON, 0.0F, 0.55F, 0.45F, player, stack);
        setNutrition(Items.COOKED_SALMON, 0.0F, 0.55F, 0.45F, player, stack);
        setNutrition(Items.TROPICAL_FISH, 0.0F, 0.85F, 0.15F, player, stack);

        setNutrition(Items.APPLE, 0.95F, 0.02F, 0.03F, player, stack);
        setNutrition(Items.BEETROOT, 0.85F, 0.1F, 0.05F, player, stack);
        setNutrition(Items.CARROT, 0.9F, 0.05F, 0.05F, player, stack);
        setNutrition(Items.CHORUS_FRUIT, 0.95F, 0.03F, 0.02F, player, stack);
        setNutrition(Items.GLOW_BERRIES, 0.9F, 0.05F, 0.05F, player, stack);
        setNutrition(Items.SWEET_BERRIES, 0.9F, 0.05F, 0.05F, player, stack);
        setNutrition(Items.GOLDEN_APPLE, 0.95F, 0.02F, 0.03F, player, stack);
        setNutrition(Items.ENCHANTED_GOLDEN_APPLE, 0.95F, 0.02F, 0.03F, player, stack);
        setNutrition(Items.GOLDEN_CARROT, 0.9F, 0.05F, 0.05F, player, stack);
        setNutrition(Items.MUSHROOM_STEW, 0.65F, 0.25F, 0.1F, player, stack);
        setNutrition(Items.BEETROOT_SOUP, 0.85F, 0.1F, 0.05F, player, stack);

        setNutrition(Items.PORKCHOP, 0.0F, 0.45F, 0.55F, player, stack);
        setNutrition(Items.COOKED_PORKCHOP, 0.0F, 0.4F, 0.6F, player, stack);
        setNutrition(Items.MUTTON, 0.0F, 0.45F, 0.55F, player, stack);
        setNutrition(Items.COOKED_MUTTON, 0.0F, 0.4F, 0.6F, player, stack);
    }
    @Unique
    private void setNutrition(Item food, float carbs, float proteins, float fats, Player player, ItemStack stack){
        float playerCarbohydrates = player.getAttachedOrElse(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT, 0.5F);
        float playerProteins = player.getAttachedOrElse(MEOAttachments.PLAYER_PROTEIN_ATTACHMENT, 0.5F);
        float playerFats = player.getAttachedOrElse(MEOAttachments.PLAYER_FAT_ATTACHMENT, 0.5F);

        if (stack.is(food)){
            player.setAttached(MEOAttachments.PLAYER_CARBOHYDRATE_ATTACHMENT, Math.min(playerCarbohydrates + (carbs * 0.75f), 1));
            player.setAttached(MEOAttachments.PLAYER_PROTEIN_ATTACHMENT, Math.min(playerProteins + (proteins * 0.75f), 1));
            player.setAttached(MEOAttachments.PLAYER_FAT_ATTACHMENT, Math.min(playerFats + (fats * 0.75f), 1));
        }
    }
}
