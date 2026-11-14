package net.numericalk.meo.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;
import net.numericalk.meo.components.MEOComponents;
import net.numericalk.meo.datagen.MEOItemTagProvider;

import java.util.HashSet;
import java.util.Set;

public class MEOSpoilageEvent {
    private static final Set<LevelChunk> LOADED_CHUNKS = new HashSet<>();

    public static void initialize() {
        ServerChunkEvents.CHUNK_LOAD.register((world, chunk, generated) -> {
            LOADED_CHUNKS.add(chunk);
        });

        ServerChunkEvents.CHUNK_UNLOAD.register((world, chunk) -> {
            LOADED_CHUNKS.remove(chunk);
        });
    }
    public static void tickContainer(Container container, Level level) {
        for (int i = 0; i < container.getContainerSize(); i++) {
            ItemStack stack = container.getItem(i);

            initializeSpoilTime(stack);

            if (stack.isEmpty()) continue;

            if (level.getGameTime() % (20 * 60 * 5) == 0) {
                reduceSpoilTime(stack);
            }
        }
    }

    public static void tickLevel(ServerLevel level) {
        for (LevelChunk chunk : new HashSet<>(LOADED_CHUNKS)) {

            if (chunk.getLevel() != level) continue;

            for (BlockEntity blockEntity : chunk.getBlockEntities().values()) {
                if (blockEntity instanceof Container container) {
                    tickContainer(container, level);
                }
            }
        }
    }
    public static void serverTick(Player player){
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);

            initializeSpoilTime(stack);

            if (player.level().getGameTime() % (20 * 60 * 5) == 0){
                reduceSpoilTime(stack);
            }
        }
    }
    private static void initializeSpoilTime(ItemStack stack) {
        setSpoilTime(Items.RABBIT_STEW, 1 * 4f, stack);

        setSpoilTime(Items.SUSPICIOUS_STEW, 1 * 4f, stack);
        setSpoilTime(Items.RABBIT_STEW, 1 * 4f, stack);
        setSpoilTime(Items.COOKED_PORKCHOP, 1 * 4f, stack);
        setSpoilTime(Items.COOKED_BEEF, 1 * 4f, stack);
        setSpoilTime(Items.BEETROOT_SOUP, 1 * 4f, stack);
        setSpoilTime(Items.COOKED_CHICKEN, 1 * 4f, stack);
        setSpoilTime(Items.COOKED_MUTTON, 1 * 4f, stack);
        setSpoilTime(Items.COOKED_SALMON, 1 * 4f, stack);
        setSpoilTime(Items.MUSHROOM_STEW, 1 * 4f, stack);
        setSpoilTime(Items.COOKED_COD, 1 * 4f, stack);
        setSpoilTime(Items.COOKED_RABBIT, 1 * 4f, stack);

        setSpoilTime(Items.BEEF, 1 * 4f, stack);
        setSpoilTime(Items.PORKCHOP, 1 * 4f, stack);
        setSpoilTime(Items.RABBIT, 1 * 4f, stack);
        setSpoilTime(Items.CHICKEN, 1 * 4f, stack);
        setSpoilTime(Items.COD, 1 * 4f, stack);
        setSpoilTime(Items.MUTTON, 1 * 4f, stack);
        setSpoilTime(Items.SALMON, 1 * 4f, stack);
        setSpoilTime(Items.PUFFERFISH, 1 * 4f, stack);
        setSpoilTime(Items.TROPICAL_FISH, 1 * 4f, stack);

        setSpoilTime(Items.APPLE, 2 * 4f, stack);
        setSpoilTime(Items.CHORUS_FRUIT, 2 * 4f, stack);
        setSpoilTime(Items.CARROT, 2 * 4f, stack);
        setSpoilTime(Items.GLOW_BERRIES, 2 * 4f, stack);
        setSpoilTime(Items.MELON_SLICE, 2 * 4f, stack);
        setSpoilTime(Items.SWEET_BERRIES, 2 * 4f, stack);
        setSpoilTime(Items.BEETROOT, 2 * 4f, stack);
        setSpoilTime(Items.POTATO, 2 * 4f, stack);

        setSpoilTime(Items.PUMPKIN_PIE, 4 * 4f, stack);
        setSpoilTime(Items.COOKIE, 4 * 4f, stack);
        setSpoilTime(Items.DRIED_KELP, 4 * 4f, stack);
        setSpoilTime(Items.BAKED_POTATO, 4 * 4f, stack);
        setSpoilTime(Items.BREAD, 4 * 4f, stack);
        setSpoilTime(Items.CAKE, 4 * 4f, stack);

    }

    private static void reduceSpoilTime(ItemStack stack) {
        float spoilTime = stack.getOrDefault(MEOComponents.SPOIL_TIME, 1f);
        spoilTime = Math.max(0, spoilTime - 1);
        stack.set(MEOComponents.SPOIL_TIME, spoilTime);
        if (spoilTime <= 0) {
            stack.set(MEOComponents.IS_SPOILED, true);
        }
    }

    public static void setSpoilTime(Item food,float spoilTime, ItemStack stack){
        if ((!stack.has(MEOComponents.SPOIL_TIME) && !stack.has(MEOComponents.IS_SPOILED)) && stack.is(food)){
            stack.set(MEOComponents.SPOIL_TIME, spoilTime);
            stack.set(MEOComponents.IS_SPOILED, false);
        }
    }
}