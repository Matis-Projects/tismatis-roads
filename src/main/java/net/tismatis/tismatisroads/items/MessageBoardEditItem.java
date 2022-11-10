package net.tismatis.tismatisroads.items;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.tismatis.tismatisroads.blocks.MessageBoard;

import static net.tismatis.tismatisroads.blocks.LineBlockSpecial.REVERSE;

public class MessageBoardEditItem extends Item {

    public MessageBoardEditItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState bs = context.getWorld().getBlockState(context.getBlockPos());
        World world = context.getWorld();
        if(!world.isClient && (bs.getBlock() instanceof MessageBoard))
        {
            System.out.println("Editing Message board");
        }

        return super.useOnBlock(context);
    }
}
