package net.tismatis.tismatisroads.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.*;
import net.tismatis.tismatisroads.blocks.MessageBoard;
import net.tismatis.tismatisroads.blocks.MessageBoardBlockEntity;

import java.util.List;

@Environment(EnvType.CLIENT)
public class MessageBoardRenderer implements BlockEntityRenderer<MessageBoardBlockEntity> {
	private final TextRenderer textRenderer;
	private static final int RENDER_DISTANCE = MathHelper.square(16);

	public MessageBoardRenderer(BlockEntityRendererFactory.Context ctx) {
		this.textRenderer = ctx.getTextRenderer();
	}

	@Override
	public void render(MessageBoardBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		BlockState blockState = entity.getCachedState();

		matrices.translate(0.5D, 0.5D, 0.5D);
		double signWidth = 1;
		float rotation = -blockState.get(MessageBoard.FACING).asRotation();
		matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(rotation));
		matrices.translate(0.0D, (-0.3125D)+0.10, (-0.3375D)-((signWidth/10)-0.02));

		float fontSize = 0.010416667F*3;
		matrices.translate(0.0D, 0.3333333432674408D, 0.046666666865348816D);
		matrices.scale(fontSize, -fontSize, fontSize);

		OrderedText[] orderedTexts = entity.updateSign(MinecraftClient.getInstance().shouldFilterText(), (text) -> {
			List<OrderedText> list = this.textRenderer.wrapLines(text, 90);
			return list.isEmpty() ? OrderedText.EMPTY : list.get(0);
		});

		int color = getColor(entity);

		int renderColor;
		boolean shouldRender;
		int l;
		if (entity.isGlowingText()) {
			renderColor = entity.getTextColor().getSignColor();
			shouldRender = shouldRender(entity, renderColor);
			l = 15728880;
		} else {
			renderColor = color;
			shouldRender = false;
			l = light;
		}

		for(int row = 0; row < 3; ++row) {
			OrderedText orderedText = orderedTexts[row];
			float y = (float)(-this.textRenderer.getWidth(orderedText) / 2);
			if (shouldRender) {
				this.textRenderer.drawWithOutline(orderedText, y, (float)(row * 10 - 10), renderColor, color, matrices.peek().getPositionMatrix(), vertexConsumers, l);
			} else {
				this.textRenderer.draw(orderedText, y, (float)(row * 10 - 10), renderColor, false, matrices.peek().getPositionMatrix(), vertexConsumers, false, 0, l);
			}
		}
	}

	private static int getColor(MessageBoardBlockEntity sign) {
		int i = sign.getTextColor().getSignColor();
		double d = 0.4D;
		int j = (int)((double) NativeImage.getRed(i) * 0.4D);
		int k = (int)((double)NativeImage.getGreen(i) * 0.4D);
		int l = (int)((double)NativeImage.getBlue(i) * 0.4D);
		return i == DyeColor.BLACK.getSignColor() && sign.isGlowingText() ? -988212 : NativeImage.packColor(0, l, k, j);
	}

	private static boolean shouldRender(MessageBoardBlockEntity sign, int signColor) {
		if (signColor == DyeColor.BLACK.getSignColor()) {
			return true;
		} else {
			MinecraftClient minecraftClient = MinecraftClient.getInstance();
			ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
			if (clientPlayerEntity != null && minecraftClient.options.getPerspective().isFirstPerson() && clientPlayerEntity.isUsingSpyglass()) {
				return true;
			} else {
				Entity entity = minecraftClient.getCameraEntity();
				return entity != null && entity.squaredDistanceTo(Vec3d.ofCenter(sign.getPos())) < (double)RENDER_DISTANCE;
			}
		}
	}
}
