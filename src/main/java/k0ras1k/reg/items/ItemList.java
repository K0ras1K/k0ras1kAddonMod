package k0ras1k.reg.items;

import k0ras1k.items.ItemNeutronUpgrade;
import k0ras1k.items.ItemStormPickaxe;
import k0ras1k.items.ItemStormSword;
import k0ras1k.materials.ItemToolMaterial;
import net.minecraft.item.Item;

public class ItemList {

    public static Item ItemNeutronUpgradeSpeed = new ItemNeutronUpgrade("ItemsNeutronUpgrade", "ItemsNeutronUpgrade");
    public static Item ItemNeutronUpgradeProd = new ItemNeutronUpgrade("ItemNeutronUpgradeProd", "ItemNeutronUpgradeProd");
    public static Item ItemNeutronUpgradeIng = new ItemNeutronUpgrade("ItemNeutronUpgradeIng", "ItemNeutronUpgradeIng");
    public static Item ItemStormSword = new ItemStormSword("stormSkySword", "k0ras1k:stormSkySword0");
    public static Item ItemStormPickaxe = new ItemStormPickaxe(ItemToolMaterial.stormPickaxe);
}
