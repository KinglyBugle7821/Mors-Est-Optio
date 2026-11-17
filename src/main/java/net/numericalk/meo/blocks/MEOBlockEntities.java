package net.numericalk.meo.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.numericalk.meo.MorsEstOptio;
import net.numericalk.meo.blocks.be.CampfireBlockEntity;
import net.numericalk.meo.blocks.be.DryingTrayBlockEntity;
import net.numericalk.meo.blocks.be.GrassSheafBlockEntity;

public class MEOBlockEntities {
    public static final BlockEntityType<GrassSheafBlockEntity> GRASS_SHEAF_BLOCK_ENTITY =
            register("grass_sheaf", GrassSheafBlockEntity::new, MEOBlocks.GRASS_SHEAF);
    public static final BlockEntityType<CampfireBlockEntity> CAMPFIRE_BLOCK_ENTITY =
            register("campfire", CampfireBlockEntity::new, MEOBlocks.CAMPFIRE);
    public static final BlockEntityType<DryingTrayBlockEntity> DRYING_TRAY_BLOCK_ENTITY =
            register("drying_tray", DryingTrayBlockEntity::new, MEOBlocks.DRYING_TRAY);


    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> entityFactory,
            Block... blocks
    ) {
        Identifier id = MorsEstOptio.id(name);
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, FabricBlockEntityTypeBuilder.<T>create(entityFactory, blocks).build());
    }
    public static void initialize(){}
}
