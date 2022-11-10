package net.tismatis.tismatisroads.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Function;

import static net.tismatis.tismatisroads.TismatisRoadsShared.MESSAGE_BOARD_BLOCK_ENTITY;

public class MessageBoardBlockEntity extends BlockEntity {
	@Nullable
	private OrderedText[] textsBeingEdited;
	private boolean filterText;
	private final Text[] texts;
	private final Text[] filteredTexts;

	public MessageBoardBlockEntity(BlockPos pos, BlockState state) {
		super(MESSAGE_BOARD_BLOCK_ENTITY, pos, state);
		this.texts = new Text[]{
				Text.of("????"),
				Text.of("????"),
				Text.of("????"),
				Text.of("????")
		};
		this.filteredTexts = new Text[]{ScreenTexts.EMPTY, ScreenTexts.EMPTY, ScreenTexts.EMPTY, ScreenTexts.EMPTY};
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
	}

	public boolean isGlowingText() {
		return true;
	}

	public DyeColor getTextColor() {
		return DyeColor.WHITE;
	}

	public OrderedText[] updateSign(boolean filterText, Function<Text, OrderedText> textOrderingFunction) {
		if (this.textsBeingEdited == null || this.filterText != filterText) {
			this.filterText = filterText;
			this.textsBeingEdited = new OrderedText[4];

			for(int i = 0; i < 4; ++i) {
				this.textsBeingEdited[i] = (OrderedText)textOrderingFunction.apply(this.getTextOnRow(i, filterText));
			}
		}

		return this.textsBeingEdited;
	}

	public Text getTextOnRow(int row, boolean filtered) {
		return this.getTexts(filtered)[row];
	}

	private Text[] getTexts(boolean filtered) {
		return filtered ? this.filteredTexts : this.texts;
	}
}
