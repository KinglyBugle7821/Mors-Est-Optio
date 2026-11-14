package net.numericalk.meo.misc;

public enum FoodNutrition {
    BREAD(0.8F, 0.15F, 0.05F),
    POTATO(0.9F, 0.08F, 0.02F),
    BAKED_POTATO(0.9F, 0.08F, 0.02F),
    DRIED_KELP(0.75F, 0.15F, 0.10F),
    MELON_SLICE(0.9F, 0.05F, 0.05F),
    PUMPKIN_PIE(0.65F, 0.05F, 0.30F),
    COOKIE(0.65F, 0.05F, 0.30F),

    BEEF(0.0F, 0.65F, 0.35F),
    COOKED_BEEF(0.0F, 0.60F, 0.40F),
    CHICKEN(0.0F, 0.75F, 0.25F),
    COOKED_CHICKEN(0.0F, 0.75F, 0.25F),
    RABBIT(0.0F, 0.85F, 0.15F),
    COOKED_RABBIT(0.0F, 0.85F, 0.15F),
    COD(0.0F, 0.85F, 0.15F),
    COOKED_COD(0.0F, 0.9F, 0.1F),
    SALMON(0.0F, 0.55F, 0.45F),
    COOKED_SALMON(0.0F, 0.55F, 0.45F),
    TROPICAL_FISH(0.0F, 0.85F, 0.15F),

    APPLE(0.95F, 0.02F, 0.03F),
    BEETROOT(0.85F, 0.1F, 0.05F),
    CARROT(0.9F, 0.05F, 0.05F),
    CHORUS_FRUIT(0.95F, 0.03F, 0.02F),
    GLOW_BERRIES(0.9F, 0.05F, 0.05F),
    SWEET_BERRIES(0.9F, 0.05F, 0.05F),
    GOLDEN_APPLE(0.95F, 0.02F, 0.03F),
    ENCHANTED_GOLDEN_APPLE(0.95F, 0.02F, 0.03F),
    GOLDEN_CARROT(0.9F, 0.05F, 0.05F),
    MUSHROOM_STEW(0.65F, 0.25F, 0.1F),
    BEETROOT_SOUP(0.85F, 0.1F, 0.05F),

    PORKCHOP(0.0F, 0.45F, 0.55F),
    COOKED_PORKCHOP(0.0F, 0.4F, 0.6F),
    MUTTON(0.0F, 0.45F, 0.55F),
    COOKED_MUTTON(0.0F, 0.4F, 0.6F);

    private final float carbohydrates;
    private final float protein;
    private final float fat;

    FoodNutrition(float carbohydrates, float protein, float fat) {
        this.carbohydrates = carbohydrates;
        this.protein = protein;
        this.fat = fat;
    }

    public float[] getNutritionValue() {
        return new float[]{carbohydrates, protein, fat};
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public float getProtein() {
        return protein;
    }

    public float getFat() {
        return fat;
    }
}