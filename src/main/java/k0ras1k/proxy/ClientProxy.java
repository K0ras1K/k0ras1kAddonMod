package k0ras1k.proxy;


import k0ras1k.api.MTAPI;
import k0ras1k.utils.MTRecipeManager;


public class ClientProxy extends CommonProxy {

    public void initRecipes() {
        MTAPI.manager = MTRecipeManager.instance;
        MTRecipeManager.instance.initRecipes();
    }



}
