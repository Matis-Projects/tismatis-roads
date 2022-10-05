package net.tismatis.tismatisroads.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.tismatis.tismatisroads.TismatisRoadsShared;

public class TrafficLight extends BaseRotateBlock{

    public static IntProperty TRAFFIC_LIGHT_MODE = IntProperty.of("mode", 0, 2);
    public static long time = 0;

    public TrafficLight(Settings settings) {
        super(settings.of(Material.STONE).strength(15.0f).nonOpaque().noCollision());
        this.setDefaultState((BlockState)((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH)).with(TRAFFIC_LIGHT_MODE, 0));
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(System.currentTimeMillis() > time)
        {
            if(state.get(TRAFFIC_LIGHT_MODE) == 2)
            {
                time = Long.sum(System.currentTimeMillis(), 300);
                world.setBlockState(pos, state.with(TRAFFIC_LIGHT_MODE, 1));
            }else if(state.get(TRAFFIC_LIGHT_MODE) == 1)
            {
                time = Long.sum(System.currentTimeMillis(), 6000);
                world.setBlockState(pos, state.with(TRAFFIC_LIGHT_MODE, 0));
            }else if(state.get(TRAFFIC_LIGHT_MODE) == 0)
            {
                time = Long.sum(System.currentTimeMillis(), 6000);
                world.setBlockState(pos, state.with(TRAFFIC_LIGHT_MODE, 2));
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{FACING, TRAFFIC_LIGHT_MODE});
    }

}
