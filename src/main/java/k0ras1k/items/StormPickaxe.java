package k0ras1k.items;

import k0ras1k.Main;
import net.minecraft.item.ItemPickaxe;

public class StormPickaxe extends ItemPickaxe {


    public StormPickaxe(ToolMaterial toolMaterial) {
        super(toolMaterial);

        this.setUnlocalizedName("StormPickaxe");
        this.setCreativeTab(Main.lightBlocks);
        this.setMaxStackSize(1);
        this.setTextureName("k0ras1k:StormPickaxe");

    }
}
