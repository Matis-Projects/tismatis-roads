package net.tismatis.tismatisroads;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.tismatis.tismatisroads.TismatisRoadsShared.InitializeElementsShared;

public class TismatisRoadsClient implements ClientModInitializer {

    public static String MODID = TismatisRoadsShared.MODID;
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitializeClient() {
        InitializeElementsShared();

        /*BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L1w, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L2w, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L3w, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L4w, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L5w, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L6w, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L7w, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L1o, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L2o, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L3o, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L4o, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L5o, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L6o, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_L7o, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_RCp, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_RCg, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(TismatisRoadsShared.BLKS_RCgp, RenderLayer.getCutout());*/

        LOGGER.info("[TismatisRoads-FABRIC] The client part has loaded!");
    }
}
