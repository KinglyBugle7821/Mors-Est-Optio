package net.numericalk.meo.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.numericalk.meo.blocks.MEOBlocks;
import net.numericalk.meo.blocks.custom.BreakingStoneBlock;
import net.numericalk.meo.datagen.MEOBlockTagProvider;
import net.numericalk.meo.items.MEOItems;

public class MEOStoneBreakingEvent {
    private static final int WOOD_DEGRADATION_AMOUNT = 1;
    private static final int COPPER_DEGRADATION_AMOUNT = 2;
    private static final int IRON_DEGRADATION_AMOUNT = 3;
    public MEOStoneBreakingEvent(){
        PlayerBlockBreakEvents.BEFORE.register((level, player, pos, state, be) -> {
            if (player.isCreative()) return true;

            Block[][] stoneDegradationMap = {
                {Blocks.STONE, MEOBlocks.BREAKING_STONE, Blocks.COBBLESTONE},
                {Blocks.DEEPSLATE, MEOBlocks.BREAKING_DEEPSLATE, Blocks.COBBLED_DEEPSLATE}
            };
            for (Block[] stoneArray : stoneDegradationMap){
                Block stone = stoneArray[0];
                Block breakingStone = stoneArray[1];
                Block cobblestone = stoneArray[2];

                if (!state.is(stone) &&
                        !state.is(breakingStone) &&
                        !state.is(cobblestone)){
                    continue;
                }
                if (player.isHolding(MEOItems.FLINT_PICK) || player.isHolding(Items.WOODEN_PICKAXE) || player.isHolding(Items.GOLDEN_PICKAXE) || player.isHolding(Items.STONE_PICKAXE))
                    return degradeStone(WOOD_DEGRADATION_AMOUNT,
                            state, stone, cobblestone, breakingStone, level, pos, player);
                else if (player.isHolding(Items.COPPER_PICKAXE))
                    return degradeStone(COPPER_DEGRADATION_AMOUNT,
                            state, stone, cobblestone, breakingStone, level, pos, player);
                else if (player.isHolding(Items.IRON_PICKAXE))
                    return degradeStone(IRON_DEGRADATION_AMOUNT,
                            state, stone, cobblestone, breakingStone, level, pos, player);
                else return degradeStone(WOOD_DEGRADATION_AMOUNT,
                            state, stone, cobblestone, breakingStone, level, pos, player);
            }
            return true;
        });
        PlayerBlockBreakEvents.AFTER.register((level, player, pos, state, be) -> {
            if (state.is(MEOBlockTagProvider.STONE_ORES)) {
                level.setBlock(pos, Blocks.STONE.defaultBlockState(), Block.UPDATE_ALL);
            }
            if (state.is(MEOBlockTagProvider.DEEPSLATE_ORES)) {
                level.setBlock(pos, Blocks.DEEPSLATE.defaultBlockState(), Block.UPDATE_ALL);
            }
            if (state.is(MEOBlockTagProvider.NETHER_ORES)) {
                level.setBlock(pos, Blocks.NETHERRACK.defaultBlockState(), Block.UPDATE_ALL);
            }
        });
    }
    public boolean degradeStone(int degradationAmount, BlockState state, Block stone, Block cobblestone, Block breakingStone, Level level, BlockPos pos, Player player){
        int degradation = state.is(stone) ? degradationAmount :
                state.is(breakingStone) && state.getValue(BreakingStoneBlock.BREAKING_STAGE) == 1 ? 1 + degradationAmount :
                state.is(cobblestone) ? 2 + degradationAmount:
                state.is(breakingStone) && state.getValue(BreakingStoneBlock.BREAKING_STAGE) == 2 ? 3 + degradationAmount : 0;
        switch (degradation){
            case 1 -> level.setBlock(pos, breakingStone.withPropertiesOf(state)
                            .setValue(BreakingStoneBlock.BREAKING_STAGE,
                            1), Block.UPDATE_ALL);
            case 2 -> level.setBlock(pos,
                    cobblestone.defaultBlockState(), Block.UPDATE_ALL);
            case 3 -> level.setBlock(pos, breakingStone.withPropertiesOf(state)
                            .setValue(BreakingStoneBlock.BREAKING_STAGE,
                            2), Block.UPDATE_ALL);
            default -> {
                switch (degradationAmount){
                    case COPPER_DEGRADATION_AMOUNT -> spawnItem(MEOItems.PEBBLE, 1, level, pos);
                    case IRON_DEGRADATION_AMOUNT -> {
                        if (state.is(breakingStone) && state.getValue(BreakingStoneBlock.BREAKING_STAGE) == 1) {
                            spawnItem(MEOItems.PEBBLE, 2, level, pos);
                        }
                    }
                }
                spawnItem(MEOItems.ROCK, 1, level, pos);
                return true;
            }
        }
        switch (degradationAmount){
            case WOOD_DEGRADATION_AMOUNT -> {
                if (state.is(stone)) spawnItem(MEOItems.PEBBLE, 1, level, pos);
                else if (state.is(breakingStone) && state.getValue(BreakingStoneBlock.BREAKING_STAGE) == 1) spawnItem(MEOItems.PEBBLE, 1, level, pos);
                else if (state.is(cobblestone)) spawnItem(MEOItems.PEBBLE, 1, level, pos);
            }
            case COPPER_DEGRADATION_AMOUNT -> {
                if (state.is(stone)) spawnItem(MEOItems.PEBBLE, 2, level, pos);
                else if (state.is(breakingStone) && state.getValue(BreakingStoneBlock.BREAKING_STAGE) == 1) spawnItem(MEOItems.PEBBLE, 2, level, pos);
                else if (state.is(cobblestone)) {
                    spawnItem(MEOItems.ROCK, 1, level, pos);
                }
                else if (state.is(breakingStone) && state.getValue(BreakingStoneBlock.BREAKING_STAGE) == 2) spawnItem(MEOItems.ROCK, 1, level, pos);
            }
            case IRON_DEGRADATION_AMOUNT -> {
                if (state.is(stone)) spawnItem(MEOItems.PEBBLE, 3, level, pos);
                else if (state.is(breakingStone) && state.getValue(BreakingStoneBlock.BREAKING_STAGE) == 1) {
                    spawnItem(MEOItems.PEBBLE, 2, level, pos);
                    spawnItem(MEOItems.ROCK, 1, level, pos);
                }
                else if (state.is(cobblestone)) {
                    spawnItem(MEOItems.ROCK, 1, level, pos);
                }
                else if (state.is(breakingStone) && state.getValue(BreakingStoneBlock.BREAKING_STAGE) == 2) spawnItem(MEOItems.ROCK, 1, level, pos);
            }
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
