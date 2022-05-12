package k0ras1k.proxy;


import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import k0ras1k.Main;
import k0ras1k.api.MTAPI;
import k0ras1k.renders.BlockTransformerRender;
import k0ras1k.renders.TileTransformerRender;
import k0ras1k.tiles.TileEntityTransformer;
import k0ras1k.utils.MTRecipeManager;


public class ClientProxy extends CommonProxy {

    public void initRecipes() {
        MTAPI.manager = MTRecipeManager.instance;
        MTRecipeManager.instance.initRecipes();
    }

    public void registerRenderers() {
        Main.blockTransformerRenderID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(new BlockTransformerRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTransformer.class, new TileTransformerRender());
    }


}
