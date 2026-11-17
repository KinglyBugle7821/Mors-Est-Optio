package net.numericalk.meo.recipes;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.recipes.meo.DryingRecipe;
import net.numericalk.meo.recipes.meo.GrillingRecipe;

public class MEORecipes {
    public static final RecipeSerializer<GrillingRecipe> GRILLING_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            MorsEstOptio.id("grilling"),
            new RecipeSerializer<>(GrillingRecipe.CODEC, GrillingRecipe.STREAM_CODEC)
    );
    public static final RecipeType<GrillingRecipe> GRILLING_RECIPE_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            MorsEstOptio.id("grilling"),
            new RecipeType<GrillingRecipe>() { }
    );

    public static final RecipeSerializer<DryingRecipe> DRYING_RECIPE_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER,
            MorsEstOptio.id("drying"),
            new RecipeSerializer<>(DryingRecipe.CODEC, DryingRecipe.STREAM_CODEC)
    );
    public static final RecipeType<DryingRecipe> DRYING_RECIPE_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE,
            MorsEstOptio.id("drying"),
            new RecipeType<DryingRecipe>() { }
    );

    public static void initialize(){}
}
