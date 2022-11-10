package net.tismatis.tismatisroads;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.tismatis.tismatisroads.TismatisRoadsShared.InitializeElementsShared;

public class TismatisRoadsClient implements ClientModInitializer {

    public static String MODID = TismatisRoadsShared.MODID;
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitializeClient() {
        InitializeElementsShared();

        LOGGER.info("[TismatisRoads-FABRIC] The client part has loaded!");
    }
}
