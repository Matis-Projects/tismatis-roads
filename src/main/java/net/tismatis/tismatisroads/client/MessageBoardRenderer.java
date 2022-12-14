package net.tismatis.tismatisroads.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.text.OrderedText;
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
		boolean shouldRenderShadow;
		int l = 15728880;
		if (entity.isGlowingText()) {
			renderColor = entity.getTextColor().getSignColor();
			shouldRenderShadow = shouldRender(entity, renderColor);
		} else {
			renderColor = color;
			shouldRenderShadow = false;
		}

		for(int row = 0; row < 3; ++row) {
			OrderedText orderedText = orderedTexts[row];
			float x = (float)(-this.textRenderer.getWidth(orderedText) / 2);
			if (shouldRenderShadow) {
				this.textRenderer.drawWithOutline(orderedText, x, (float)(row * 10 - 10), renderColor, color, matrices.peek().getPositionMatrix(), vertexConsumers, l);
			} else {
				this.textRenderer.draw(orderedText, x, (float)(row * 10 - 10), renderColor, false, matrices.peek().getPositionMatrix(), vertexConsumers, false, 0, l);
			}
		}
	}

	public static int getColor(MessageBoardBlockEntity sign) {
		int color = sign.getTextColor().getSignColor();
		double d = 0.4D;
		int r = (int)((double)NativeImage.getRed(color) * d);
		int g = (int)((double)NativeImage.getGreen(color) * d);
		int b = (int)((double)NativeImage.getBlue(color) * d);
		return color == DyeColor.WHITE.getSignColor() && sign.isGlowingText() ? -988212 : NativeImage.packColor(0, b, g, r);
	}

	public static boolean shouldRender(MessageBoardBlockEntity sign, int signColor) {
		if (signColor == DyeColor.WHITE.getSignColor()) {
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
