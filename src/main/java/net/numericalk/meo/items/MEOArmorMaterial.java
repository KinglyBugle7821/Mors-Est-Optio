package net.numericalk.meo.items;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.datagen.MEOItemTagProvider;

import java.util.Map;

public class MEOArmorMaterial {
    public static final int STEEL_BASE_DURABILITY = 30;
    public static final ResourceKey<EquipmentAsset> STEEL_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, MorsEstOptio.id("steel"));
    public static final ArmorMaterial STEEL = new ArmorMaterial(
            STEEL_BASE_DURABILITY,
            Map.of(
                    ArmorType.HELMET, 3,
                    ArmorType.CHESTPLATE, 6,
                    ArmorType.LEGGINGS, 8,
                    ArmorType.BOOTS, 3
            ),
            10,
            SoundEvents.ARMOR_EQUIP_IRON,
            2.0F,
            0.0F,
            MEOItemTagProvider.STEEL_MATERIAL,
            STEEL_ARMOR_MATERIAL_KEY
    );
}
