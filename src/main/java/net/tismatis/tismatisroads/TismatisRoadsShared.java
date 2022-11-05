package net.tismatis.tismatisroads;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.tismatis.tismatisroads.blockentities.CraftingMachineBlock;
import net.tismatis.tismatisroads.blockentities.CraftingMachineEntity;
import net.tismatis.tismatisroads.blockentities.CraftingMachineScreenHandler;
import net.tismatis.tismatisroads.blocks.*;
import net.tismatis.tismatisroads.items.PaintItem;
import net.tismatis.tismatisroads.items.SignTool1;
import net.tismatis.tismatisroads.items.SignTool2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TismatisRoadsShared {

    public static String MODID = "tismatis-roads";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    /* CREATIVE TABS */
    public static final ItemGroup CT_BASE = FabricItemGroupBuilder.create(
                    new Identifier(MODID, "creative-tab_base"))
            .icon(() -> new ItemStack(Items.STONE))
            .build();
    public static final ItemGroup CT_ROADS_MARKS = FabricItemGroupBuilder.create(
                    new Identifier(MODID, "creative-tab_roads-marks"))
            .icon(() -> new ItemStack(Items.STONE))
            .build();
    public static final ItemGroup CT_TRAFFICS = FabricItemGroupBuilder.create(
                    new Identifier(MODID, "creative-tab_traffics"))
            .icon(() -> new ItemStack(Items.STONE))
            .build();
    public static final ItemGroup CT_SIGNS = FabricItemGroupBuilder.create(
                    new Identifier(MODID, "creative-tab_signs"))
            .icon(() -> new ItemStack(Items.STONE))
            .build();

    private static final CraftingMachineBlock CRAFTING_MACHINE_BLOCK = new CraftingMachineBlock(FabricBlockSettings.of(Material.STONE));
    public static final BlockEntityType<?> CRAFTING_MACHINE_ENTITY = RegisterABlockEntity(
            FabricBlockEntityTypeBuilder.create(CraftingMachineEntity::new, CRAFTING_MACHINE_BLOCK).build(),
            CRAFTING_MACHINE_BLOCK,
            "crafting_machine"
    );
    public static final ScreenHandlerType<CraftingMachineScreenHandler> CRAFTING_MACHINE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(
            new Identifier(MODID, "crafting_machine"),
            CraftingMachineScreenHandler::new
    );

    public static void InitializeElementsShared()
    {
        /* BLOCKS WITH ITEMS */
            /* WHITE */
                RegisterWithClass("Block","standard_line_white", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_white_overtaking", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_white_end", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_white_stop_left", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_white_stop_center", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_white_stop_right", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_white_stop", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_white_rotate_soft", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_white_rotate_direct_a", CT_ROADS_MARKS, "LineBlockSpecial");
                RegisterWithClass("Block","standard_line_white_rotate_direct_b", CT_ROADS_MARKS, "LineBlockSpecial");
                RegisterWithClass("Block","standard_line_white_rotate_direct_c", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_white_multiple_two", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_white_multiple_three", CT_ROADS_MARKS, "LineBlock");
            /* ORANGE */
                RegisterWithClass("Block","standard_line_orange", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_orange_overtaking", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_orange_end", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_orange_stop_left", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_orange_stop_center", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_orange_stop_right", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_orange_stop", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_orange_rotate_soft", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_orange_rotate_direct_a", CT_ROADS_MARKS, "LineBlockSpecial");
                RegisterWithClass("Block","standard_line_orange_rotate_direct_b", CT_ROADS_MARKS, "LineBlockSpecial");
                RegisterWithClass("Block","standard_line_orange_rotate_direct_c", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_orange_multiple_two", CT_ROADS_MARKS, "LineBlock");
                RegisterWithClass("Block","standard_line_orange_multiple_three", CT_ROADS_MARKS, "LineBlock");
            /* Rail Crossing */
                RegisterWithClass("Block","rail_crossing_pot", CT_TRAFFICS, "RailCrossingPot");
                RegisterWithClass("Block","rail_crossing_gate", CT_TRAFFICS, "RailCrossingGate");
                RegisterWithClass("Block","rail_crossing_gate_pot", CT_TRAFFICS, "RailCrossingGatePot");
            /* Sign Block */
                RegisterWithClass("Block", "stone_pole", CT_SIGNS, "SignPoleBlock");
                RegisterWithClass("Block", "signblock_1", CT_SIGNS, "SignBlock1");
                RegisterWithClass("Block", "signblock_2", CT_SIGNS, "SignBlock2");
            /* Traffic Light */
                /*RegisterWithClass("Block", "traffic_light_c1_little", CT_TRAFFICS, "TrafficLight");*/
        /* ITEMS ONLY */
            /* PAINT-TOOL */
                RegisterWithClass("Item", "paint_tool", CT_ROADS_MARKS, "PaintItem");
            /* SIGN-TOOL */
                for(int i = 1; i < 29; ++i)
                {
                    RegisterWithClass("Item", "signitem_1_" + i, CT_SIGNS, "SignTool1");
                }
                for(int i = 1; i < 12; ++i)
                {
                    RegisterWithClass("Item", "signitem_2_" + i, CT_SIGNS, "SignTool2");
                }
        /* CREATIVE TAB */


        LOGGER.info("[TismatisRoads-FABRIC] The shared part has loaded!");
    }

    public static void RegisterWithClass(String what, String path, ItemGroup it)
    {
        if(what == "Block")
        {
            RegisterWithClass(what, path, it, "Block");
        }else{
            RegisterWithClass(what, path, it, "Items");
        }
    }
    public static void RegisterWithClass(String what, String path, ItemGroup it,String type)
    {
        if(what == "Block")
        {
            if(type == "LineBlock")
            {
                RegisterABlock(new LineBlock(FabricBlockSettings.of(Material.STONE)), path, it);
            }else if(type == "LineBlockSpecial") {
                RegisterABlock(new LineBlockSpecial(FabricBlockSettings.of(Material.STONE)), path, it);
            }else if(type == "RailCrossingPot"){
                RegisterABlock(new RailCrossingPot(FabricBlockSettings.of(Material.STONE)), path, it);
            }else if(type == "RailCrossingGate"){
                RegisterABlock(new RailCrossingGate(FabricBlockSettings.of(Material.STONE)), path, it);
            }else if(type == "RailCrossingGatePot"){
                RegisterABlock(new RailCrossingGatePot(FabricBlockSettings.of(Material.STONE)), path, it);
            }else if(type == "SignBlock1") {
                RegisterABlock(new SignBlock1(FabricBlockSettings.of(Material.STONE)), path, it);
            }else if(type == "SignBlock2"){
                RegisterABlock(new SignBlock2(FabricBlockSettings.of(Material.STONE)), path, it);
            }else if(type == "SignPoleBlock"){
                RegisterABlock(new SignPoleBlock(FabricBlockSettings.of(Material.STONE)), path, it);
            }else if(type == "TrafficLight"){
                RegisterABlock(new TrafficLight(FabricBlockSettings.of(Material.STONE)), path, it);
            }else if(type == "BaseRotateBlock"){
                RegisterABlock(new BaseRotateBlock(FabricBlockSettings.of(Material.STONE)), path, it);
            }else if(type == "Block"){
                RegisterABlock(new Block(FabricBlockSettings.of(Material.STONE)), path, it);
            }else{
                LOGGER.error("Can't handle this object: name: '" + path + "', type: '" + what + "' !");
            }
        }else{
            if(type == "PaintItem")
            {
                Registry.register(Registry.ITEM, new Identifier(MODID, path), new PaintItem(new FabricItemSettings().group(it)));
            }else if(type == "SignTool1")
            {
                Registry.register(Registry.ITEM, new Identifier(MODID, path), new SignTool1(new FabricItemSettings().group(it)));
            }else if(type == "SignTool2")
            {
                Registry.register(Registry.ITEM, new Identifier(MODID, path), new SignTool2(new FabricItemSettings().group(it)));
            }else if(type == "Items")
            {
                Registry.register(Registry.ITEM, new Identifier(MODID, path), new Item(new FabricItemSettings().group(it)));

            }else{
                LOGGER.error("Can't handle this object: name: '" + path + "', type: '" + what + "' !");
            }
        }
    }

    public static void RegisterABlock(Block blk, String path, ItemGroup it)
    {
        Registry.register(Registry.BLOCK, new Identifier(MODID, path), blk);
        RegisterAItem(new BlockItem(blk, new FabricItemSettings().group(it)), path);
    }
    public static BlockEntityType<?> RegisterABlockEntity(BlockEntityType<?> blkEnt, Block blk, String path)
    {
        Registry.register(Registry.BLOCK, new Identifier(MODID, path), blk);
        RegisterAItem(new BlockItem(blk, new FabricItemSettings().group(CT_BASE)), path);

        return Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier(MODID, path),
            blkEnt
        );
    }
    public static void RegisterAItem(Item itm, String path)
    {
        Registry.register(Registry.ITEM, new Identifier(MODID, path), itm);
    }
}
