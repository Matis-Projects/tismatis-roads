package net.tismatis.tismatisroads;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.tismatis.tismatisroads.client.MessageBoardRenderer;
import net.tismatis.tismatisroads.network.MessageBoardEditS2C;
import net.tismatis.tismatisroads.network.NetworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.tismatis.tismatisroads.TismatisRoadsShared.InitializeElementsShared;
import static net.tismatis.tismatisroads.TismatisRoadsShared.MESSAGE_BOARD_BLOCK_ENTITY;

public class TismatisRoadsClient implements ClientModInitializer {

    public static String MODID = TismatisRoadsShared.MODID;
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitializeClient() {
        InitializeElementsShared();
        BlockEntityRendererRegistry.register(MESSAGE_BOARD_BLOCK_ENTITY, MessageBoardRenderer::new);


        ClientPlayNetworking.registerGlobalReceiver(NetworkConstants.OPEN_MESSAGE_BOARD_S2C, (client, handler, buf, responseSender) -> {
           MessageBoardEditS2C packet = new MessageBoardEditS2C();
           packet.receive(client, handler, buf, responseSender);
        });

        LOGGER.info("[TismatisRoads-FABRIC] The client part has loaded!");
    }
}
