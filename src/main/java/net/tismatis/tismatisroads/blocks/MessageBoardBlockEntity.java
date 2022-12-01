package net.tismatis.tismatisroads.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

import static net.tismatis.tismatisroads.TismatisRoadsShared.MESSAGE_BOARD_BLOCK_ENTITY;

public class MessageBoardBlockEntity extends BlockEntity {
	@Nullable
	private OrderedText[] textsBeingEdited;
	private boolean filterText;
	private final Text[] texts;
	private final Text[] filteredTexts;
	private DyeColor textColor = DyeColor.WHITE;
	private boolean glowingText = false;

	public MessageBoardBlockEntity(BlockPos pos, BlockState state) {
		super(MESSAGE_BOARD_BLOCK_ENTITY, pos, state);
		this.filteredTexts = new Text[]{ScreenTexts.EMPTY, ScreenTexts.EMPTY, ScreenTexts.EMPTY};
		this.texts = new Text[]{ScreenTexts.EMPTY, ScreenTexts.EMPTY, ScreenTexts.EMPTY};
	}

	@Override
	public void readNbt(NbtCompound nbt) {
		super.readNbt(nbt);
		this.textColor = DyeColor.byName(nbt.getString("color"), DyeColor.WHITE);
		NbtCompound textNbt = (NbtCompound) nbt.get("text");
		if (textNbt != null) {
			for (int i = 0; i < 3; ++i) {
				String data = textNbt.getString(String.valueOf(i));
				Text text = Text.of(data);
				this.texts[i] = text;
				String key = String.valueOf(i);
				if (textNbt.contains(key, 8)) {
					this.filteredTexts[i] = Text.of(textNbt.getString(key));
				} else {
					this.filteredTexts[i] = text;
				}
			}
		}

		this.textsBeingEdited = null;
		this.glowingText = nbt.getBoolean("glowing");
	}

	@Override
	protected void writeNbt(NbtCompound nbt) {
		super.writeNbt(nbt);
		NbtCompound text = new NbtCompound();
		text.putString("0", texts[0].getString());
		text.putString("1", texts[1].getString());
		text.putString("2", texts[2].getString());
		nbt.put("text", text);
		nbt.putString("color", this.textColor.getName());
		nbt.putBoolean("glowing", this.glowingText);
	}

	@Override
	public BlockEntityUpdateS2CPacket toUpdatePacket() {
		return BlockEntityUpdateS2CPacket.create(this);
	}

	@Override
	public NbtCompound toInitialChunkDataNbt() {
		return this.createNbt();
	}

	@Override
	public boolean copyItemDataRequiresOperator() {
		return true;
	}

	public OrderedText[] updateSign(boolean filterText, Function<Text, OrderedText> textOrderingFunction) {
		if (this.textsBeingEdited == null || this.filterText != filterText) {
			this.filterText = filterText;
			this.textsBeingEdited = new OrderedText[4];

			for(int i = 0; i < 3; ++i) {
				this.textsBeingEdited[i] = textOrderingFunction.apply(this.getTextOnRow(i, filterText));
			}
		}

		return this.textsBeingEdited;
	}

	public final Text getTextOnRow(int row, boolean filtered) {
		if (row < getTextCount()) {
			return this.getTexts(filtered)[row];
		} else {
			return Text.empty();
		}
	}

	public Text[] getTexts(boolean filtered) {
		return filtered ? this.filteredTexts : this.texts;
	}

	public int getTextCount() {
		return this.texts.length;
	}

	public DyeColor getTextColor() {
		return this.textColor;
	}

	public boolean setTextColor(DyeColor value) {
		if (value != this.getTextColor()) {
			this.textColor = value;
			this.updateListeners();
			return true;
		} else {
			return false;
		}
	}

	public boolean isGlowingText() {
		return this.glowingText;
	}

	public boolean setGlowingText(boolean glowingText) {
		if (this.glowingText != glowingText) {
			this.glowingText = glowingText;
			this.updateListeners();
			return true;
		} else {
			return false;
		}
	}

	private void updateListeners() {
		this.markDirty();
		if (this.world != null) {
			this.world.updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), 3);
		}
	}
}
