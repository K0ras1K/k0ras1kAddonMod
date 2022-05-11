package k0ras1k.reg.blocks;


import cpw.mods.fml.common.registry.GameRegistry;

import k0ras1k.tiles.TileEntityTransformer;
import k0ras1k.tiles.panels.TileEntitySolarPanel;

import static k0ras1k.reg.blocks.BlockList.*;

public class BlocksSolarPanelRegister {




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
