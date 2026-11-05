package net.numericalk.meo.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.blocks.custom.BreakingLogBlock;
import net.numericalk.meo.datagen.MEOItemTagProvider;
import net.numericalk.meo.items.MEOItems;

public class MEOWoodBreakingEvent {
    private static final int WOOD_DEGRADATION_AMOUNT = 1;
    private static final int COPPER_DEGRADATION_AMOUNT = 2;
    private static final int IRON_DEGRADATION_AMOUNT = 3;
    public MEOWoodBreakingEvent(){
        PlayerBlockBreakEvents.BEFORE.register((level, player, pos, state, be) -> {

            if (player.isCreative()) return true;

            Object[][] logDegradationMap = {
                    {Blocks.OAK_LOG, MEOBlocks.BREAKING_OAK_LOG, MEOItems.OAK_BARK},
                    {Blocks.SPRUCE_LOG, MEOBlocks.BREAKING_SPRUCE_LOG, MEOItems.SPRUCE_BARK},
                    {Blocks.BIRCH_LOG, MEOBlocks.BREAKING_BIRCH_LOG, MEOItems.BIRCH_BARK},
                    {Blocks.JUNGLE_LOG, MEOBlocks.BREAKING_JUNGLE_LOG, MEOItems.JUNGLE_BARK},
                    {Blocks.ACACIA_LOG, MEOBlocks.BREAKING_ACACIA_LOG, MEOItems.ACACIA_BARK},
                    {Blocks.DARK_OAK_LOG, MEOBlocks.BREAKING_DARK_OAK_LOG, MEOItems.DARK_OAK_BARK},
                    {Blocks.MANGROVE_LOG, MEOBlocks.BREAKING_MANGROVE_LOG, MEOItems.MANGROVE_BARK},
                    {Blocks.CHERRY_LOG, MEOBlocks.BREAKING_CHERRY_LOG, MEOItems.CHERRY_BARK},
                    {Blocks.PALE_OAK_LOG, MEOBlocks.BREAKING_PALE_OAK_LOG, MEOItems.PALE_OAK_BARK},
                    {Blocks.WARPED_STEM, MEOBlocks.BREAKING_WARPED_STEM, MEOItems.WARPED_BARK},
                    {Blocks.CRIMSON_STEM, MEOBlocks.BREAKING_CRIMSON_STEM, MEOItems.CRIMSON_BARK},
                    {Blocks.STRIPPED_OAK_LOG, MEOBlocks.BREAKING_OAK_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_SPRUCE_LOG, MEOBlocks.BREAKING_SPRUCE_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_BIRCH_LOG, MEOBlocks.BREAKING_BIRCH_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_JUNGLE_LOG, MEOBlocks.BREAKING_JUNGLE_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_ACACIA_LOG, MEOBlocks.BREAKING_ACACIA_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_DARK_OAK_LOG, MEOBlocks.BREAKING_DARK_OAK_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_MANGROVE_LOG, MEOBlocks.BREAKING_MANGROVE_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_CHERRY_LOG, MEOBlocks.BREAKING_CHERRY_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_PALE_OAK_LOG, MEOBlocks.BREAKING_PALE_OAK_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_WARPED_STEM, MEOBlocks.BREAKING_WARPED_STEM, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_CRIMSON_STEM, MEOBlocks.BREAKING_CRIMSON_STEM, MEOItems.WOOD_CHIP},
                    {Blocks.OAK_WOOD, MEOBlocks.BREAKING_OAK_LOG, MEOItems.OAK_BARK},
                    {Blocks.SPRUCE_WOOD, MEOBlocks.BREAKING_SPRUCE_LOG, MEOItems.SPRUCE_BARK},
                    {Blocks.BIRCH_WOOD, MEOBlocks.BREAKING_BIRCH_LOG, MEOItems.BIRCH_BARK},
                    {Blocks.JUNGLE_WOOD, MEOBlocks.BREAKING_JUNGLE_LOG, MEOItems.JUNGLE_BARK},
                    {Blocks.ACACIA_WOOD, MEOBlocks.BREAKING_ACACIA_LOG, MEOItems.ACACIA_BARK},
                    {Blocks.DARK_OAK_WOOD, MEOBlocks.BREAKING_DARK_OAK_LOG, MEOItems.DARK_OAK_BARK},
                    {Blocks.MANGROVE_WOOD, MEOBlocks.BREAKING_MANGROVE_LOG, MEOItems.MANGROVE_BARK},
                    {Blocks.CHERRY_WOOD, MEOBlocks.BREAKING_CHERRY_LOG, MEOItems.CHERRY_BARK},
                    {Blocks.PALE_OAK_WOOD, MEOBlocks.BREAKING_PALE_OAK_LOG, MEOItems.PALE_OAK_BARK},
                    {Blocks.WARPED_HYPHAE, MEOBlocks.BREAKING_WARPED_STEM, MEOItems.WARPED_BARK},
                    {Blocks.CRIMSON_HYPHAE, MEOBlocks.BREAKING_CRIMSON_STEM, MEOItems.CRIMSON_BARK},
                    {Blocks.STRIPPED_OAK_WOOD, MEOBlocks.BREAKING_OAK_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_SPRUCE_WOOD, MEOBlocks.BREAKING_SPRUCE_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_BIRCH_WOOD, MEOBlocks.BREAKING_BIRCH_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_JUNGLE_WOOD, MEOBlocks.BREAKING_JUNGLE_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_ACACIA_WOOD, MEOBlocks.BREAKING_ACACIA_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_DARK_OAK_WOOD, MEOBlocks.BREAKING_DARK_OAK_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_MANGROVE_WOOD, MEOBlocks.BREAKING_MANGROVE_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_CHERRY_WOOD, MEOBlocks.BREAKING_CHERRY_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_PALE_OAK_WOOD, MEOBlocks.BREAKING_PALE_OAK_LOG, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_WARPED_HYPHAE, MEOBlocks.BREAKING_WARPED_STEM, MEOItems.WOOD_CHIP},
                    {Blocks.STRIPPED_CRIMSON_HYPHAE, MEOBlocks.BREAKING_CRIMSON_STEM, MEOItems.WOOD_CHIP}
            };
            for (Object[] logArray : logDegradationMap) {
                Block log = (Block) logArray[0];
                Block breakingLog = (Block) logArray[1];
                Item logBark = (Item) logArray[2];

                if (!state.is(log) && !state.is(breakingLog)) {
                    continue;
                }
                if (player.isHolding(MEOItems.FLINT_FLAKE)
                        || player.isHolding(Items.WOODEN_AXE)
                        || player.isHolding(Items.STONE_AXE)
                        || player.isHolding(Items.GOLDEN_AXE)) {
                    if (state.is(log)) spawnItem(logBark, 1, level, pos);
                    else if (state.is(breakingLog) && state.getValue(BreakingLogBlock.BREAKING_STAGE) == 1) spawnItem(MEOItems.WOOD_CHIP, 1, level, pos);
                    else if (state.is(breakingLog) && state.getValue(BreakingLogBlock.BREAKING_STAGE) == 2) spawnItem(MEOItems.WOOD_CHIP, 1, level, pos);
                    else if (state.is(breakingLog) && state.getValue(BreakingLogBlock.BREAKING_STAGE) == 3) spawnItem(Items.STICK, 1, level, pos);

                    return degradeLog(
                            WOOD_DEGRADATION_AMOUNT,
                            state, log, breakingLog, level, pos, player, logBark
                    );
                } else if (player.isHolding(Items.COPPER_AXE)) {
                    if (state.is(log)) {
                        spawnItem(logBark, 1, level, pos);
                        spawnItem(MEOItems.WOOD_CHIP, 1, level, pos);
                    }
                    else if (state.is(breakingLog) && state.getValue(BreakingLogBlock.BREAKING_STAGE) == 1) {
                        spawnItem(MEOItems.WOOD_CHIP, 2, level, pos);
                    }
                    else if (state.is(breakingLog) && state.getValue(BreakingLogBlock.BREAKING_STAGE) == 2) {
                        spawnItem(MEOItems.WOOD_CHIP, 1, level, pos);
                        spawnItem(Items.STICK, 1, level, pos);
                    }
                    else if (state.is(breakingLog) && state.getValue(BreakingLogBlock.BREAKING_STAGE) == 3) spawnItem(Items.STICK, 1, level, pos);

                    return degradeLog(
                            COPPER_DEGRADATION_AMOUNT,
                            state, log, breakingLog, level, pos, player, logBark
                    );
                } else if (player.isHolding(Items.IRON_AXE)) {
                    if (state.is(log)) {
                        spawnItem(logBark, 1, level, pos);
                        spawnItem(MEOItems.WOOD_CHIP, 2, level, pos);
                    }
                    else if (state.is(breakingLog) && state.getValue(BreakingLogBlock.BREAKING_STAGE) == 1) {
                        spawnItem(MEOItems.WOOD_CHIP, 2, level, pos);
                        spawnItem(Items.STICK, 1, level, pos);
                    }
                    else if (state.is(breakingLog) && state.getValue(BreakingLogBlock.BREAKING_STAGE) == 2) {
                        spawnItem(MEOItems.WOOD_CHIP, 1, level, pos);
                        spawnItem(Items.STICK, 1, level, pos);
                    }
                    else if (state.is(breakingLog) && state.getValue(BreakingLogBlock.BREAKING_STAGE) == 3) spawnItem(Items.STICK, 1, level, pos);

                    return degradeLog(
                            IRON_DEGRADATION_AMOUNT,
                            state, log, breakingLog, level, pos, player, logBark
                    );
                } else {
                    return degradeLog(
                            WOOD_DEGRADATION_AMOUNT,
                            state, log, breakingLog, level, pos, player, logBark
                    );
                }
            }
            return true;
        });
    }
    public boolean degradeLog(int degradationAmount, BlockState state, Block log, Block breakingLog, Level level, BlockPos pos, Player player, Item logBark){
        int degradation = state.is(log) ? degradationAmount :
                state.is(breakingLog) ? state.getValue(BreakingLogBlock.BREAKING_STAGE) + degradationAmount : 0;
        if (degradation >= 4) return true;
        else {
            level.setBlock(
                    pos,
                    breakingLog.withPropertiesOf(state).setValue(
                            BreakingLogBlock.BREAKING_STAGE,
                            degradation),
                    Block.UPDATE_ALL);
            if (!player.getMainHandItem().isEmpty()) player.getMainHandItem().hurtAndBreak(1, player, EquipmentSlot.MAINHAND);
        }
        return false;
    }
    public void spawnItem(Item item, int amount, Level level, BlockPos pos){
        if (level.isClientSide()) return;
        for (int i = 0; i < amount; i++){
            ItemEntity itemEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), item.getDefaultInstance());

            level.addFreshEntity(itemEntity);
        }
    }
}
