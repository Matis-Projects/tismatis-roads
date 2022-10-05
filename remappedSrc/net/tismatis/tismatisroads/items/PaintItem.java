package net.tismatis.tismatisroads.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.tismatis.tismatisroads.blocks.LineBlockSpecial.REVERSE;

public class PaintItem extends Item {

    public static final String[] paint_wh = "_rotate_direct_a/_rotate_direct_b".split("/");

    public PaintItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState bs = context.getWorld().getBlockState(context.getBlockPos());
        if(bs.getBlock().getTranslationKey().contains("block.tismatis-roads.standard_line_"))
        {
            // debug --> context.getPlayer().sendMessage(Text.of(bs.getBlock().getTranslationKey()), true);
            for(String curr : paint_wh)
            {
                if(bs.getBlock().getTranslationKey().endsWith(curr))
                {
                    context.getPlayer().playSound(SoundEvents.BLOCK_WOOL_PLACE, 1.0F, 1.0F);
                    context.getWorld().setBlockState(context.getBlockPos(), context.getWorld().getBlockState(context.getBlockPos()).with(REVERSE, !bs.get(REVERSE)));
                    break;
                }
            }
        }
        return super.useOnBlock(context);
    }
}
