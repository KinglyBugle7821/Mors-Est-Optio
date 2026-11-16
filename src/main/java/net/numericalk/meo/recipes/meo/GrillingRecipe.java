package net.numericalk.meo.recipes.meo;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.numericalk.meo.recipes.MEORecipes;

public class GrillingRecipe implements Recipe<GrillingRecipeInput> {
    private final ItemStackTemplate result;
    private final Ingredient input;

    public GrillingRecipe(ItemStackTemplate result, Ingredient input){
        this.result = result;
        this.input = input;
    }

    public ItemStackTemplate getResult() {
        return this.result;
    }

    public Ingredient getInput() {
        return this.input;
    }

    @Override
    public boolean matches(GrillingRecipeInput input, Level level) {
        return this.input.test(input.input());
    }

    @Override
    public ItemStack assemble(GrillingRecipeInput input) {
        return this.result.create();
    }
    public static final MapCodec<GrillingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(GrillingRecipe::getResult),
                    Ingredient.CODEC.fieldOf("input").forGetter(GrillingRecipe::getInput)
            ).apply(instance, GrillingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, GrillingRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            GrillingRecipe::getResult,
            Ingredient.CONTENTS_STREAM_CODEC,
            GrillingRecipe::getInput,
            GrillingRecipe::new
    );
    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "grilling";
    }

    @Override
    public RecipeSerializer<? extends Recipe<GrillingRecipeInput>> getSerializer() {
        return MEORecipes.GRILLING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<GrillingRecipeInput>> getType() {
        return MEORecipes.GRILLING_RECIPE_TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return null;
    }
}
