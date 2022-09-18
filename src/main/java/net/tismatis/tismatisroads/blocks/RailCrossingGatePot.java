package net.tismatis.tismatisroads.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class RailCrossingGatePot extends BaseRotateBlock{
    public static final BooleanProperty OPEN = Properties.OPEN;
    public RailCrossingGatePot(Settings settings) {
        super(settings.of(Material.STONE).strength(15.0f).nonOpaque().noCollision());
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            if(world.isReceivingRedstonePower(pos))
            {
                world.setBlockState(pos, world.getBlockState(pos).with(OPEN, true));
                emitsRedstonePower(state);
            }else{
                world.setBlockState(pos, world.getBlockState(pos).with(OPEN, false));
                emitsRedstonePower(state);
            }
        }
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return (Boolean)state.get(OPEN);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, OPEN});
    }

    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        if((Boolean)state.get(OPEN)) { return 1; }else{ return 0; }
    }

}
