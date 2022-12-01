package net.tismatis.tismatisroads.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.tismatis.tismatisroads.blocks.MessageBoardBlockEntity;
import net.tismatis.tismatisroads.network.MessageBoardUpdateC2S;
import net.tismatis.tismatisroads.network.NetworkConstants;

import java.util.List;

@Environment(EnvType.CLIENT)
public class MessageBoardEditScreen extends Screen {
	private final MessageBoardBlockEntity sign;
	private TextFieldWidget line1;
	private TextFieldWidget line2;
	private TextFieldWidget line3;
	private final List<Text> lines;

	public MessageBoardEditScreen(MessageBoardBlockEntity sign, List<Text> lines) {
		super(Text.translatable("sign.edit"));
		this.sign = sign;
		this.lines = lines;
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

			int x = (this.width / 2) - (16 * (4/2));
			int y = (this.height / 2) - (16 * (3));

			this.line1 = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, x, y, 16*4, 16, Text.of(""));
			this.line1.setMaxLength(5);
			this.line1.setText(lines.get(0).getString());
			this.line1.setEditableColor(sign.getTextColor().getSignColor());

			this.line2 = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, x, y+22, 16*4, 16, Text.of(""));
			this.line2.setMaxLength(5);
			this.line2.setText(lines.get(1).getString());
			this.line2.setEditableColor(sign.getTextColor().getSignColor());

			this.line3 = new TextFieldWidget(MinecraftClient.getInstance().textRenderer, x, y+22*2, 16*4, 16, Text.of(""));
			this.line3.setMaxLength(5);
			this.line3.setText(lines.get(2).getString());
			this.line3.setEditableColor(sign.getTextColor().getSignColor());

			this.addDrawableChild(this.line1);
			this.addDrawableChild(this.line2);
			this.addDrawableChild(this.line3);
		}
	}

	@Override
	public void removed() {
		if (this.client != null) {
			this.client.keyboard.setRepeatEvents(false);
			PacketByteBuf buf = PacketByteBufs.create();
			System.out.println(sign.getPos());
			buf.writeBlockPos(sign.getPos().mutableCopy());
			buf.writeText(Text.of(this.line1.getText()));
			buf.writeText(Text.of(this.line2.getText()));
			buf.writeText(Text.of(this.line3.getText()));
			ClientPlayNetworking.send(NetworkConstants.UPDATE_MESSAGE_BOARD_C2S, buf);
		}
	}

	@Override
	public void tick() {
		this.line1.tick();
		this.line2.tick();
		this.line3.tick();
	}

	private void finishEditing() {
		if (this.client != null) {
			this.sign.markDirty();
			this.client.setScreen(null);
		}
	}

	@Override
	public void close() {
		this.finishEditing();
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		if (client != null) {
			DiffuseLighting.disableGuiDepthLighting();
			this.renderBackground(matrices);
			drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 16777215);
			DiffuseLighting.enableGuiDepthLighting();
			super.render(matrices, mouseX, mouseY, delta);
		}
	}
}
