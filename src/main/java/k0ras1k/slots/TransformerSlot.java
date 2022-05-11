package k0ras1k.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class TransformerSlot extends Slot {

    private Boolean canPutInSlot;

    public TransformerSlot(IInventory inventory, int par3, int par4, int par5, Boolean canPut) {
        super(inventory, par3, par4, par5);
        this.canPutInSlot = canPut;
    }

    public boolean isItemValid(ItemStack par1ItemStack) {
        return this.canPutInSlot;
    }

}
