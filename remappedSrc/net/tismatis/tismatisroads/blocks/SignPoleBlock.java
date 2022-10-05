package net.tismatis.tismatisroads.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import org.jetbrains.annotations.Nullable;

public class SignPoleBlock extends BaseRotateBlock{
    public SignPoleBlock(Settings settings) {
        super(settings.nonOpaque());
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }
}
