package k0ras1k.reg.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import k0ras1k.reg.blocks.BlockList.*;
import k0ras1k.tiles.TileEntityBigFurnance;
import k0ras1k.tiles.TileEntityNeutronBig;

import static k0ras1k.reg.blocks.BlockList.big_furnance;
import static k0ras1k.reg.blocks.BlockList.neutron_collector_big;

public class MainBlocksRegister {


    public static void init() {
        lightBlocksRegister();
    }

    public static void lightBlocksRegister() {

        GameRegistry.registerTileEntity(TileEntityNeutronBig.class, "Neutron Collector Big");
        GameRegistry.registerBlock(neutron_collector_big, "neutron_collector_big");
        GameRegistry.registerTileEntity(TileEntityBigFurnance.class, "Big Furnance");
        GameRegistry.registerBlock(big_furnance, "big_furnance");

    }

}
