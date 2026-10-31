package net.numericalk.meo.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Function;

@Mixin(Blocks.class)
public class SlowBlockBreakingMixin {
	@Unique
	private static final float WOOD_HARDNESS = 6.0f;
	@Unique
	private static final float WOOD_RESISTANCE = 2.0f;

	@Unique
	private static final float TOUGH_STONE_HARDNESS = 9.0f;
	@Unique
	private static final float STONE_HARDNESS = 7.0f;
	@Unique
	private static final float STONE_RESISTANCE = 6.0f;

	@Unique
	private static final float SOIL_HARDNESS = 1.5f;
	@Unique
	private static final float SOIL_RESISTANCE = 0.5f;

	@Inject(method = "register(Lnet/minecraft/resources/ResourceKey;Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)Lnet/minecraft/world/level/block/Block;", at = @At("HEAD"))
	private static void modifyBlockStrength(ResourceKey<Block> key, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties properties, CallbackInfoReturnable<Block> cir) {
		String path = key.toString();
		boolean isWood = path.contains("oak") || path.contains("spruce") ||
				path.contains("birch") || path.contains("jungle") ||
				path.contains("acacia") || path.contains("mangrove") ||
				path.contains("cherry")|| path.contains("crimson") ||
				path.contains("warped") || path.contains("bamboo")||
				path.contains("bookshelf") || path.contains("campfire") ||
				path.contains("chest") || path.contains("barrel") ||
				path.contains("table") || path.contains("composter") ||
				path.contains("lectern");
		boolean isNotFoliage = !path.contains("leaves") && !path.contains("sapling") && !path.contains("enchanting");

		boolean isStrongStone = path.contains("deepslate");
		boolean isStone = path.contains("stone") || path.contains("brick") ||
				path.contains("quartz") || path.contains("prismarine") ||
				path.contains("granite") || path.contains("diorite") ||
				path.contains("andesite") || path.contains("ore") ||
				path.contains("tuff") || path.contains("calcite") ||
				path.contains("basalt") || path.contains("packed_mud") ||
				path.contains("sulfur") || path.contains("cinnabar") ||
				path.contains("deepslate");
		boolean isNotFalseStone = !path.contains("redstone") && !path.contains("glowstone") &&
				!path.contains("resin") && !path.contains("nether") && !path.contains("core") &&
				!path.contains("spore");
		boolean isSoil = path.contains("soil") || path.contains("grass_block") ||
				path.contains("podzol") || path.contains("mycelium") ||
				path.contains("dirt") || path.contains("farmland") ||
				path.contains("mud") || path.contains("clay") ||
				path.contains("sand") || path.contains("gravel");
		if (isWood && isNotFoliage){
			properties.strength(WOOD_HARDNESS, WOOD_RESISTANCE).requiresCorrectToolForDrops();
		}
		if (isStone && isNotFalseStone) {
			if (isStrongStone) {
				properties.strength(TOUGH_STONE_HARDNESS, STONE_RESISTANCE);
			} else {
				properties.strength(STONE_HARDNESS, STONE_RESISTANCE);
			}
		}
		if (isSoil) {
			properties.strength(SOIL_HARDNESS, SOIL_RESISTANCE).requiresCorrectToolForDrops();
		}
	}
}