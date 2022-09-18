package net.tismatis.tismatisroads;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tismatis.tismatisroads.blocks.*;
import net.tismatis.tismatisroads.items.PaintItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class TismatisRoadsShared {

    public static String MODID = "tismatis-roads";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    /* CREATIVE TABS */
    public static final ItemGroup CT_ROADS_MARKS = FabricItemGroupBuilder.create(
                    new Identifier(MODID, "roads_marks"))
            .icon(() -> new ItemStack(Items.STONE))
            .build();
    public static final ItemGroup CT_TRAFFICS = FabricItemGroupBuilder.create(
                    new Identifier(MODID, "traffics"))
            .icon(() -> new ItemStack(Items.STONE))
            .build();

    /* BLOCKS */
    public static final LineBlock BLKS_L1w = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L2w = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L3w = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L4w = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L5w = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L6w = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L7w = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L8w = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L9w = new LineBlockSpecial(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L10w = new LineBlockSpecial(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L11w = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L12w = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L13w = new LineBlock(FabricBlockSettings.of(Material.STONE));

    public static final LineBlock BLKS_L1o = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L2o = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L3o = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L4o = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L5o = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L6o = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L7o = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L8o = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L9o = new LineBlockSpecial(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L10o = new LineBlockSpecial(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L11o = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L12o = new LineBlock(FabricBlockSettings.of(Material.STONE));
    public static final LineBlock BLKS_L13o = new LineBlock(FabricBlockSettings.of(Material.STONE));

    public static final RailCrossingPot BLKS_RCp = new RailCrossingPot(FabricBlockSettings.of(Material.STONE));
    public static final RailCrossingGate BLKS_RCg = new RailCrossingGate(FabricBlockSettings.of(Material.STONE));
    public static final RailCrossingGatePot BLKS_RCgp = new RailCrossingGatePot(FabricBlockSettings.of(Material.STONE));

    /* ITEMS */
    public static final Item PAINT_TOOL = new PaintItem(new FabricItemSettings().group(CT_ROADS_MARKS));


    public static void InitializeElementsShared()
    {
        /* BLOCKS WITH ITEMS */
            /* WHITE */
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white"), BLKS_L1w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white"), new BlockItem(BLKS_L1w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_overtaking"), BLKS_L2w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_overtaking"), new BlockItem(BLKS_L2w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_end"), BLKS_L3w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_end"), new BlockItem(BLKS_L3w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_stop_left"), BLKS_L4w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_stop_left"), new BlockItem(BLKS_L4w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_stop_center"), BLKS_L5w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_stop_center"), new BlockItem(BLKS_L5w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_stop_right"), BLKS_L6w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_stop_right"), new BlockItem(BLKS_L6w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_stop"), BLKS_L7w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_stop"), new BlockItem(BLKS_L7w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_rotate_soft"), BLKS_L8w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_rotate_soft"), new BlockItem(BLKS_L8w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_rotate_direct_a"), BLKS_L9w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_rotate_direct_a"), new BlockItem(BLKS_L9w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_rotate_direct_b"), BLKS_L10w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_rotate_direct_b"), new BlockItem(BLKS_L10w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_rotate_direct_c"), BLKS_L11w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_rotate_direct_c"), new BlockItem(BLKS_L11w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_multiple_two"), BLKS_L12w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_multiple_two"), new BlockItem(BLKS_L12w, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_white_multiple_three"), BLKS_L13w);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_white_multiple_three"), new BlockItem(BLKS_L13w, new FabricItemSettings().group(CT_ROADS_MARKS)));

            /* ORANGE */
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange"), BLKS_L1o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange"), new BlockItem(BLKS_L1o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_overtaking"), BLKS_L2o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_overtaking"), new BlockItem(BLKS_L2o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_end"), BLKS_L3o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_end"), new BlockItem(BLKS_L3o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_stop_left"), BLKS_L4o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_stop_left"), new BlockItem(BLKS_L4o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_stop_center"), BLKS_L5o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_stop_center"), new BlockItem(BLKS_L5o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_stop_right"), BLKS_L6o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_stop_right"), new BlockItem(BLKS_L6o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_stop"), BLKS_L7o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_stop"), new BlockItem(BLKS_L7o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_rotate_soft"), BLKS_L8o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_rotate_soft"), new BlockItem(BLKS_L8o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_rotate_direct_a"), BLKS_L9o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_rotate_direct_a"), new BlockItem(BLKS_L9o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_rotate_direct_b"), BLKS_L10o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_rotate_direct_b"), new BlockItem(BLKS_L10o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_rotate_direct_c"), BLKS_L11o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_rotate_direct_c"), new BlockItem(BLKS_L11o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_multiple_two"), BLKS_L12o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_multiple_two"), new BlockItem(BLKS_L12o, new FabricItemSettings().group(CT_ROADS_MARKS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "standard_line_orange_multiple_three"), BLKS_L13o);
            Registry.register(Registry.ITEM, new Identifier(MODID, "standard_line_orange_multiple_three"), new BlockItem(BLKS_L13o, new FabricItemSettings().group(CT_ROADS_MARKS)));

        /* Rail Crossing */
            Registry.register(Registry.BLOCK, new Identifier(MODID, "rail_crossing_pot"), BLKS_RCp);
            Registry.register(Registry.ITEM, new Identifier(MODID, "rail_crossing_pot"), new BlockItem(BLKS_RCp, new FabricItemSettings().group(CT_TRAFFICS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "rail_crossing_gate"), BLKS_RCg);
            Registry.register(Registry.ITEM, new Identifier(MODID, "rail_crossing_gate"), new BlockItem(BLKS_RCg, new FabricItemSettings().group(CT_TRAFFICS)));
            Registry.register(Registry.BLOCK, new Identifier(MODID, "rail_crossing_gate_pot"), BLKS_RCgp);
            Registry.register(Registry.ITEM, new Identifier(MODID, "rail_crossing_gate_pot"), new BlockItem(BLKS_RCgp, new FabricItemSettings().group(CT_TRAFFICS)));
        /* ITEMS ONLY */
            Registry.register(Registry.ITEM, new Identifier(MODID, "paint_tool"), PAINT_TOOL);
        /* CREATIVE TAB */


        LOGGER.info("[TismatisRoads-FABRIC] The shared part has loaded!");
    }
}
