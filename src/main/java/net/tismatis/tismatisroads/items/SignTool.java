package net.tismatis.tismatisroads.items;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import static net.tismatis.tismatisroads.blocks.SignBlock.SIGN_ID;

public class SignTool extends Item {

    public SignTool(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState bs = context.getWorld().getBlockState(context.getBlockPos());
        String[] st = this.getTranslationKey().replace("signitem_", "signblock_").split("_");
        String[] stt = bs.getBlock().getTranslationKey().split("_");
        if(bs.getBlock().getTranslationKey().contains("signblock_") && stt.length > 1 && Integer.parseInt(stt[1]) == Integer.parseInt(st[1]))
        {
            // debug --> context.getPlayer().sendMessage(Text.of(st[2]), true);
            context.getPlayer().playSound(SoundEvents.BLOCK_STONE_PRESSURE_PLATE_CLICK_ON, 1.0F, 1.0F);
            context.getWorld().setBlockState(context.getBlockPos(), context.getWorld().getBlockState(context.getBlockPos()).with(SIGN_ID, Integer.parseInt(st[2])));
            //context.getWorld().setBlockState(context.getBlockPos(), context.getWorld().getBlockState(context.getBlockPos()).with(REVERSE, !bs.get(REVERSE)));
        }
        return super.useOnBlock(context);
    }
}
