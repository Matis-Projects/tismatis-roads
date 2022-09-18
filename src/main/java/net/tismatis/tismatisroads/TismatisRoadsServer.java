package net.tismatis.tismatisroads;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.tismatis.tismatisroads.TismatisRoadsShared.InitializeElementsShared;

public class TismatisRoadsServer implements ModInitializer {

	public static String MODID = TismatisRoadsShared.MODID;
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		InitializeElementsShared();

		LOGGER.info("[TismatisRoads-FABRIC] The server part has loaded!");
	}
}