package net.tismatis.tismatisroads.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.network.PacketByteBuf;
import net.tismatis.tismatisroads.blocks.MessageBoardBlockEntity;
import net.tismatis.tismatisroads.client.gui.MessageBoardEditScreen;

public class MessageBoardEditS2C implements ClientPlayNetworking.PlayChannelHandler {

	@Override
	public void receive(MinecraftClient client, ClientPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
		ClientWorld world = client.world;
		if (world != null) {
			BlockEntity entity = world.getBlockEntity(buf.readBlockPos());
			if (entity instanceof MessageBoardBlockEntity sign) {
				client.execute(() -> client.setScreen(new MessageBoardEditScreen(sign, false)));
			}
		}
	}
}
