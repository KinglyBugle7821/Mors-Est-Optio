package net.numericalk.meo.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.numericalk.meo.items.MEOItems;

import java.util.concurrent.CompletableFuture;

public class MEORecipeProvider extends FabricRecipeProvider {
    public MEORecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        return new RecipeProvider(provider, recipeOutput) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

                shapeless(RecipeCategory.MISC, MEOItems.FLINT_FLAKE)
                        .requires(Items.FLINT)
                        .unlockedBy(getHasName(Items.FLINT), has(Items.FLINT))
                        .save(output);
                shapeless(RecipeCategory.MISC, MEOItems.TWINE)
                        .requires(MEOItems.DRIED_GRASS_SHEAF, 2)
                        .unlockedBy(getHasName(MEOItems.DRIED_GRASS_SHEAF), has(MEOItems.DRIED_GRASS_SHEAF))
                        .save(output);
                shapeless(RecipeCategory.MISC, Items.FLINT)
                        .requires(MEOItems.GRAVEL_CLUMP, 2)
                        .unlockedBy(getHasName(MEOItems.GRAVEL_CLUMP), has(MEOItems.GRAVEL_CLUMP))
                        .save(output);
            }
        };
    }
    @Override
    public String getName() {
        return "MEORecipeProvider";
    }
}
