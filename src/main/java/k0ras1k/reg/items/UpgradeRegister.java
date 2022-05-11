package k0ras1k.reg.items;

import cpw.mods.fml.common.registry.GameRegistry;

import static k0ras1k.reg.items.ItemList.*;

public class UpgradeRegister {





    public static void init() {
        UpgradeRegister();
    }

    public static void UpgradeRegister() {

        GameRegistry.registerItem(ItemNeutronUpgradeSpeed, "ItemNeutronUpgrade");
        GameRegistry.registerItem(ItemNeutronUpgradeProd, "ItemNeutronUpgradeProd");
        GameRegistry.registerItem(ItemNeutronUpgradeIng, "ItemNeutronUpgradeIng");

    }

}
