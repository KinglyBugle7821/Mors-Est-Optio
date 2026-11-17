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

public class DryingRecipe implements Recipe<DryingRecipeInput> {
    private final ItemStackTemplate result;
    private final Ingredient input;

    public DryingRecipe(ItemStackTemplate result, Ingredient input) {
        this.result = result;
        this.input = input;
    }

    public ItemStackTemplate getResult() {
        return result;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    public boolean matches(DryingRecipeInput input, Level level) {
        return this.input.test(input.input());
    }

    @Override
    public ItemStack assemble(DryingRecipeInput input) {
        return this.result.create();
    }
    public static final MapCodec<DryingRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(DryingRecipe::getResult),
                    Ingredient.CODEC.fieldOf("input").forGetter(DryingRecipe::getInput)
            ).apply(instance, DryingRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, DryingRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            DryingRecipe::getResult,
            Ingredient.CONTENTS_STREAM_CODEC,
            DryingRecipe::getInput,
            DryingRecipe::new
    );
    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "drying";
    }

    @Override
    public RecipeSerializer<? extends Recipe<DryingRecipeInput>> getSerializer() {
        return MEORecipes.DRYING_RECIPE_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<DryingRecipeInput>> getType() {
        return MEORecipes.DRYING_RECIPE_TYPE;
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
