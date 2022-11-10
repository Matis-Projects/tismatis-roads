package net.tismatis.tismatisroads.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

import static net.tismatis.tismatisroads.TismatisRoadsShared.MESSAGE_BOARD_BLOCK_ENTITY;

public class MessageBoardBlockEntity extends BlockEntity {

	public MessageBoardBlockEntity(BlockPos pos, BlockState state) {
		super(MESSAGE_BOARD_BLOCK_ENTITY, pos, state);
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
	}
}
