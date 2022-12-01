package net.tismatis.tismatisroads;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.tismatis.tismatisroads.network.MessageBoardEditS2C;
import net.tismatis.tismatisroads.network.MessageBoardUpdateC2S;
import net.tismatis.tismatisroads.network.NetworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.tismatis.tismatisroads.TismatisRoadsShared.InitializeElementsShared;

public class TismatisRoadsServer implements DedicatedServerModInitializer {

	public static String MODID = TismatisRoadsShared.MODID;
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitializeServer() {
		InitializeElementsShared();

		LOGGER.info("[TismatisRoads-FABRIC] The server part has loaded!");
	}
}
