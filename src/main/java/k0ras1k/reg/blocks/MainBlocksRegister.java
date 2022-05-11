package k0ras1k.reg.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import k0ras1k.Main;
import k0ras1k.blocks.*;
import k0ras1k.tiles.TileEntityBigFurnance;
import k0ras1k.tiles.TileEntityNeutronBig;
import k0ras1k.tiles.panels.TileEntitySolarPanel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class MainBlocksRegister {



    public static Block neutron_collector_big = new BlockNeutronBig().setBlockName("neutron_collector_big");
    public static Block big_furnance = new BlockBigFurnance().setBlockName("big_furnance");





    public static void init(){
        lightBlocksRegister();
    }

    public static void lightBlocksRegister(){

        GameRegistry.registerTileEntity(TileEntityNeutronBig.class, "Neutron Collector Big");
        GameRegistry.registerBlock(neutron_collector_big, "neutron_collector_big");
        GameRegistry.registerTileEntity(TileEntityBigFurnance.class, "Big Furnance");
        GameRegistry.registerBlock(big_furnance, "big_furnance");

    }

}
