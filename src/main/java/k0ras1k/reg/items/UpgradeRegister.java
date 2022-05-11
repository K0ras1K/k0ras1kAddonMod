package k0ras1k.reg.items;

import cpw.mods.fml.common.registry.GameRegistry;
import k0ras1k.items.ItemNeutronUpgrade;
import net.minecraft.item.Item;

public class UpgradeRegister {


    public static Item ItemNeutronUpgradeSpeed = new ItemNeutronUpgrade("ItemsNeutronUpgrade", "ItemsNeutronUpgrade");
    public static Item ItemNeutronUpgradeProd = new ItemNeutronUpgrade("ItemNeutronUpgradeProd", "ItemNeutronUpgradeProd");
    public static Item ItemNeutronUpgradeIng = new ItemNeutronUpgrade("ItemNeutronUpgradeIng", "ItemNeutronUpgradeIng");


    public static void init() {
        UpgradeRegister();
    }

    public static void UpgradeRegister() {

        GameRegistry.registerItem(ItemNeutronUpgradeSpeed, "ItemNeutronUpgrade");
        GameRegistry.registerItem(ItemNeutronUpgradeProd, "ItemNeutronUpgradeProd");
        GameRegistry.registerItem(ItemNeutronUpgradeIng, "ItemNeutronUpgradeIng");

    }

}
