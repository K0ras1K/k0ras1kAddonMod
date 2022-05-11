package k0ras1k.items;

import k0ras1k.Main;
import net.minecraft.item.Item;

public class ItemNeutronUpgrade extends Item {
    public ItemNeutronUpgrade(String name, String texture) {
        this.setUnlocalizedName(name);
        this.setCreativeTab(Main.lightBlocks);
        this.setMaxStackSize(2);
        this.setTextureName(Main.MODID + ':' + texture);
    }
}
