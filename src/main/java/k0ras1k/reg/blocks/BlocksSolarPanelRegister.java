package k0ras1k.reg.blocks;


import cpw.mods.fml.common.registry.GameRegistry;
import k0ras1k.blocks.BlockTransformer;
import k0ras1k.blocks.solarPanels.candyPanels.*;
import k0ras1k.tiles.TileEntityTransformer;
import k0ras1k.tiles.panels.TileEntitySolarPanel;
import net.minecraft.block.Block;

public class BlocksSolarPanelRegister {

    public static Block candy_panel_blue = new BlockCandyPanel("candy_panel_blue", 100000, 50000, 1000000, 100000, "blue").setBlockName("candy_panel_blue");
    public static Block candy_panel_yellow = new BlockCandyPanel("candy_panel_yellow", 300000, 150000, 10000000, 1000000, "yellow").setBlockName("candy_panel_yellow");
    public static Block candy_panel_red = new BlockCandyPanel("candy_panel_red", 1000000, 500000, 100000000, 10000000, "red").setBlockName("candy_panel_red");
    public static Block candy_panel_green = new BlockCandyPanel("candy_panel_green", 10000000, 5000000, 1000000000, 100000000, "green").setBlockName("candy_panel_green");
    public static Block candy_panel_cloud = new BlockCandyPanel("candy_panel_cloud", 100000000, 50000000, 1000000000, 1000000000, "cloud").setBlockName("candy_panel_cloud");
    public static Block transformer = new BlockTransformer().setBlockName("transformer");


    public static void init(){
        BlocksSolarPanelRegister();
    }

    public static void BlocksSolarPanelRegister(){
        GameRegistry.registerTileEntity(TileEntitySolarPanel.class, "solar_panel");
        GameRegistry.registerBlock(candy_panel_blue, "candy_panel_blue");
        GameRegistry.registerBlock(candy_panel_yellow, "candy_panel_yellow");
        GameRegistry.registerBlock(candy_panel_red, "candy_panel_red");
        GameRegistry.registerBlock(candy_panel_green, "candy_panel_green");
        GameRegistry.registerBlock(candy_panel_cloud, "candy_panel_cloud");
        GameRegistry.registerBlock(transformer, "transformer");
        GameRegistry.registerTileEntity(TileEntityTransformer.class, "tile_entity_transformer");


    }

}
