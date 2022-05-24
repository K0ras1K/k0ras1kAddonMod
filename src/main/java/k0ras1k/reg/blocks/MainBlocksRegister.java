package k0ras1k.reg.blocks;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import static k0ras1k.reg.blocks.BlockList.*;

import k0ras1k.renders.TilePilonRender;
import k0ras1k.tiles.TileEntityBigFurnance;
import k0ras1k.tiles.TileEntityNeutronBig;
import k0ras1k.tiles.TileEntityPiedestal;
import k0ras1k.tiles.TileEntityTransformer;
import k0ras1k.tiles.machines.TileEntityWaterGenerator;


public class MainBlocksRegister {


    public static void init() {
        MainBlocksRegister();
    }

    public static void MainBlocksRegister() {

        GameRegistry.registerTileEntity(TileEntityNeutronBig.class, "Neutron Collector Big");
        GameRegistry.registerBlock(neutron_collector_big, "neutron_collector_big");
        GameRegistry.registerTileEntity(TileEntityBigFurnance.class, "Big Furnance");
        GameRegistry.registerBlock(big_furnance, "big_furnance");
        GameRegistry.registerBlock(water_generator, "water_generator");
        GameRegistry.registerTileEntity(TileEntityWaterGenerator.class, "Water Generator");
        GameRegistry.registerTileEntity(TileEntityTransformer.class, "Transformer");
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPiedestal.class, new TilePilonRender());
        GameRegistry.registerTileEntity(TileEntityPiedestal.class, "TileEntityPiedestal");
        GameRegistry.registerBlock(piedestal, "piedestal");


    }

}
