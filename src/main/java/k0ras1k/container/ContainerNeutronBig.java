package k0ras1k.container;

import k0ras1k.slots.UpgradeSlot;
import k0ras1k.tiles.TileEntityNeutronBig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerNeutronBig extends Container {
    private TileEntityNeutronBig tileNeutronBig;


    public ContainerNeutronBig(InventoryPlayer player, TileEntityNeutronBig machine)
    {
        this.tileNeutronBig = machine;
        this.addSlotToContainer(new SlotFurnace(player.player, this.tileNeutronBig, 2, 80, 35));
        this.addSlotToContainer(new UpgradeSlot(this.tileNeutronBig, 0, 152, 22));
        this.addSlotToContainer(new UpgradeSlot(this.tileNeutronBig, 1, 152, 43));
        this.addSlotToContainer(new UpgradeSlot(this.tileNeutronBig, 3, 152, 64));


        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }

    }

    public boolean canInteractWith(EntityPlayer player)
    {
        return this.tileNeutronBig.isUseableByPlayer(player);
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNumber);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotNumber == 0)
            {
                if (!this.mergeItemStack(itemstack1, 1, 37, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else
            {
                if (slotNumber >= 1 && slotNumber < 28)
                {
                    if (!this.mergeItemStack(itemstack1, 28, 37, false))
                    {
                        return null;
                    }
                }
                else if (slotNumber >= 28 && slotNumber < 37 && !this.mergeItemStack(itemstack1, 1, 28, false))
                {
                    return null;
                }
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
}
