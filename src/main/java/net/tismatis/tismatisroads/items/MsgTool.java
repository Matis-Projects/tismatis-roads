package net.tismatis.tismatisroads.items;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.tismatis.tismatisroads.blocks.MessageBoard;

import static net.tismatis.tismatisroads.blocks.MessageBoard.BASE_ID;

public class MsgTool extends Item {

    public MsgTool(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState bs = context.getWorld().getBlockState(context.getBlockPos());
        if (bs.getBlock() instanceof MessageBoard) {
            String[] st = this.getTranslationKey().replace("msgitem", "msgboard").split("_");
            context.getPlayer().playSound(SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, 1.0F, 1.0F);
            context.getWorld().setBlockState(context.getBlockPos(), context.getWorld().getBlockState(context.getBlockPos()).with(BASE_ID, Integer.parseInt(st[1])));
        }
        return super.useOnBlock(context);
    }
}
