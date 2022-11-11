package net.tismatis.tismatisroads.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.util.SelectionManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.UpdateSignC2SPacket;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;
import net.tismatis.tismatisroads.TismatisRoadsShared;
import net.tismatis.tismatisroads.blocks.MessageBoard;
import net.tismatis.tismatisroads.blocks.MessageBoardBlockEntity;
import net.tismatis.tismatisroads.client.MessageBoardRenderer;

import java.util.stream.IntStream;

@Environment(EnvType.CLIENT)
public class MessageBoardEditScreen extends Screen {
	private final MessageBoardBlockEntity sign;
	private int ticksSinceOpened;
	private int currentRow;
	private SelectionManager selectionManager;
	private final String[] text;

	public MessageBoardEditScreen(MessageBoardBlockEntity sign, boolean filtered) {
		super(Text.translatable("sign.edit"));
		this.text = IntStream.range(0, 3).mapToObj(
				(row) -> sign.getTextOnRow(row, filtered)
		).map(Text::getString).toArray(String[]::new);
		this.sign = sign;
	}

	@Override
	protected void init() {
		if (this.client != null) {
			this.client.keyboard.setRepeatEvents(true);

			this.addDrawableChild(new ButtonWidget(
					this.width / 2 - 100,
					this.height / 4 + 120,
					200,
					20,
					ScreenTexts.DONE, (button) -> this.finishEditing()
			));

			this.selectionManager = new SelectionManager(() -> this.text[this.currentRow], (text) -> {
				this.text[this.currentRow] = text;
//				this.sign.setTextOnRow(this.currentRow, Text.literal(text));
			}, SelectionManager.makeClipboardGetter(this.client), SelectionManager.makeClipboardSetter(this.client), (text) -> this.client.textRenderer.getWidth(text) <= 30);
		}
	}

	@Override
	public void removed() {
		if (this.client != null) {
			this.client.keyboard.setRepeatEvents(false);
			ClientPlayNetworkHandler clientPlayNetworkHandler = this.client.getNetworkHandler();
			if (clientPlayNetworkHandler != null) {
//				clientPlayNetworkHandler.sendPacket(new UpdateSignC2SPacket(this.sign.getPos(), this.text[0], this.text[1], this.text[2], this.text[3]));
				//TODO: use message board packets
			}
		}
	}

	@Override
	public void tick() {
		++this.ticksSinceOpened;
		if (!this.sign.getType().supports(this.sign.getCachedState())) {
			this.finishEditing();
		}

	}

	private void finishEditing() {
		if (this.client != null) {
			this.sign.markDirty();
			this.client.setScreen(null);
		}
	}

	@Override
	public boolean charTyped(char chr, int modifiers) {
		this.selectionManager.insert(chr);
		return true;
	}

	@Override
	public void close() {
		this.finishEditing();
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (keyCode == 265) {
			this.currentRow = this.currentRow - 1;
			if (this.currentRow < 0) {this.currentRow = 0;}
			this.selectionManager.putCursorAtEnd();
			return true;
		} else if (keyCode != 264 && keyCode != 257 && keyCode != 335) {
			return this.selectionManager.handleSpecialKey(keyCode) || super.keyPressed(keyCode, scanCode, modifiers);
		} else {
			this.currentRow = this.currentRow + 1;
			if (this.currentRow > 2) {this.currentRow = 2;}
			this.selectionManager.putCursorAtEnd();
			return true;
		}
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		if (client != null) {
			DiffuseLighting.disableGuiDepthLighting();
			this.renderBackground(matrices);
			drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 16777215);
			matrices.push();


			int color = MessageBoardRenderer.getColor(sign);

			int renderColor;
			if (sign.isGlowingText()) {
				renderColor = sign.getTextColor().getSignColor();
			} else {
				renderColor = color;
			}

			float y = this.height / 2f;
			for(int row = 0; row < 3; ++row) {
				OrderedText orderedText = Text.of(text[row]).asOrderedText();
				float x = (float)(-this.textRenderer.getWidth(orderedText) / 2) + (this.width/2f);
				this.client.textRenderer.draw(matrices, orderedText, x, (float)(row * 10 - 10)+y, renderColor);
			}

			DiffuseLighting.enableGuiDepthLighting();
			super.render(matrices, mouseX, mouseY, delta);
		}
	}
}
