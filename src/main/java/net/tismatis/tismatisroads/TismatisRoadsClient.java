package net.tismatis.tismatisroads;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.tismatis.tismatisroads.blockentities.CraftingMachineScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.tismatis.tismatisroads.TismatisRoadsShared.InitializeElementsShared;

public class TismatisRoadsClient implements ClientModInitializer {

    public static String MODID = TismatisRoadsShared.MODID;
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitializeClient() {
        InitializeElementsShared();

        ScreenRegistry.register(TismatisRoadsShared.CRAFTING_MACHINE_SCREEN_HANDLER, CraftingMachineScreen::new);

        LOGGER.info("[TismatisRoads-FABRIC] The client part has loaded!");
    }
}
