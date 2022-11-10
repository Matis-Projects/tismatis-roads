package net.tismatis.tismatisroads.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class MessageBoardBlockEntity extends BlockEntity {
	public static BlockEntityType<?> type;

	public MessageBoardBlockEntity(BlockPos pos, BlockState state) {
		super(type, pos, state);
	}
}
