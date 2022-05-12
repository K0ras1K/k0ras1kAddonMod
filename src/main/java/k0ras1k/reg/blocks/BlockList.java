package k0ras1k.reg.blocks;

import k0ras1k.blocks.BlockBigFurnance;
import k0ras1k.blocks.BlockNeutronBig;
import k0ras1k.blocks.BlockTransformer;
import k0ras1k.blocks.BlockWaterGenerator;
import k0ras1k.blocks.solarPanels.candyPanels.BlockCandyPanel;
import net.minecraft.block.Block;

public class BlockList {

    public static Block candy_panel_blue = new BlockCandyPanel("candy_panel_blue", 100000, 50000, 1000000, 100000, "blue").setBlockName("candy_panel_blue");
    public static Block candy_panel_yellow = new BlockCandyPanel("candy_panel_yellow", 300000, 150000, 10000000, 1000000, "yellow").setBlockName("candy_panel_yellow");
    public static Block candy_panel_red = new BlockCandyPanel("candy_panel_red", 1000000, 500000, 100000000, 10000000, "red").setBlockName("candy_panel_red");
    public static Block candy_panel_green = new BlockCandyPanel("candy_panel_green", 10000000, 5000000, 1000000000, 100000000, "green").setBlockName("candy_panel_green");
    public static Block candy_panel_cloud = new BlockCandyPanel("candy_panel_cloud", 100000000, 50000000, 1000000000, 1000000000, "cloud").setBlockName("candy_panel_cloud");
    public static Block transformer = new BlockTransformer().setBlockName("transformer");
    public static Block neutron_collector_big = new BlockNeutronBig().setBlockName("neutron_collector_big");
    public static Block big_furnance = new BlockBigFurnance().setBlockName("big_furnance");
    public static Block water_generator = new BlockWaterGenerator().setBlockName("water_generator");


}
