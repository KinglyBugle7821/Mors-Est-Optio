package net.numericalk.meo.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.numericalk.meo.datagen.MEOBlockTagProvider;
import net.numericalk.meo.items.MEOItems;

public class MEOSpecializedBreakingEvent {
    public static void flintFlakeFormation(Level level, Player player, BlockState state, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            ItemStack stack = player.getMainHandItem();
            if (stack.is(Items.FLINT) && state.is(MEOBlockTagProvider.STONE_BLOCKS)) {
                ItemEntity item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), MEOItems.FLINT_FLAKE.getDefaultInstance());
                serverLevel.addFreshEntity(item);
                stack.consume(1, player);
            }
        }
    }
}
