package k0ras1k.reg.items;

import cpw.mods.fml.common.registry.GameRegistry;

import static k0ras1k.reg.items.ItemList.*;

public class ItemRegister{

    public static void init() {
        ItemRegister();
    }

    public static void ItemRegister() {

        GameRegistry.registerItem(ItemStormSword, "ItemStormSword");
        GameRegistry.registerItem(ItemStormPickaxe, "ItemStormPickaxe");
    }

}



