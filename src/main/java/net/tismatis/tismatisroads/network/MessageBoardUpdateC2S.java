package net.tismatis.tismatisroads.network;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.tismatis.tismatisroads.blocks.MessageBoardBlockEntity;

import java.util.Arrays;

public class MessageBoardUpdateC2S implements ServerPlayNetworking.PlayChannelHandler {
	@Override
	public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
		BlockPos pos = buf.readBlockPos();
		Text line1 = buf.readText();
		Text line2 = buf.readText();
		Text line3 = buf.readText();
		server.execute(() -> {
			ServerWorld world = (ServerWorld) player.world;
			if (world != null) {
				BlockEntity entity = world.getBlockEntity(pos);
				if (entity instanceof MessageBoardBlockEntity sign) {
					Text[] texts = sign.getTexts(false);
					texts[0] = line1.copyContentOnly().setStyle(texts[0].getStyle());
					texts[1] = line2.copyContentOnly().setStyle(texts[1].getStyle());
					texts[2] = line3.copyContentOnly().setStyle(texts[2].getStyle());
					responseSender.sendPacket(sign.toUpdatePacket());
				}
			}
		});
	}
}
