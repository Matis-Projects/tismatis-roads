package net.tismatis.tismatisroads.blockentities;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.tismatis.tismatisroads.TismatisRoadsShared;

public class CraftingMachineEntity extends BlockEntity {
	public CraftingMachineEntity(BlockPos pos, BlockState state) {
		super(TismatisRoadsShared.CRAFTING_MACHINE_ENTITY, pos, state);
	}
}