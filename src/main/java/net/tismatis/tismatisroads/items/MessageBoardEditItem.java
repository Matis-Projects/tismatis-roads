package net.tismatis.tismatisroads.items;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.tismatis.tismatisroads.blocks.MessageBoardBlockEntity;

public class MessageBoardEditItem extends Item {

    public MessageBoardEditItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockEntity entity = context.getWorld().getBlockEntity(context.getBlockPos());
        World world = context.getWorld();
        if(!world.isClient && (entity instanceof MessageBoardBlockEntity))
        {
            System.out.println("Editing Message board");
        }

        return super.useOnBlock(context);
    }
}
