package k0ras1k.container;

import k0ras1k.tiles.panels.TileEntitySolarPanel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerSolarPanel extends Container {
    private TileEntitySolarPanel tileentity;
    private int storage = 0;
    private int fuel = 0;
    private boolean sunIsUp;
    private boolean skyIsVisible;
    private int generating;

    public ContainerSolarPanel(InventoryPlayer inventoryplayer, TileEntitySolarPanel tileentitysolarpanel) {
        this.tileentity = tileentitysolarpanel;

        int j;
        for (j = 0; j < 4; ++j) {
            this.addSlotToContainer(new Slot(this.tileentity, j, 152, 9 + 18 * j));
        }

        for (j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(inventoryplayer, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for (j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }

    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.tileentity.sunIsUp ? 1 : 0);
        icrafting.sendProgressBarUpdate(this, 1, this.tileentity.skyIsVisible ? 1 : 0);
        icrafting.sendProgressBarUpdate(this, 2, this.tileentity.generating);
        icrafting.sendProgressBarUpdate(this, 3, this.tileentity.storage & '\uffff');
        icrafting.sendProgressBarUpdate(this, 4, this.tileentity.storage >>> 16);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.sunIsUp ? 1 : 0);
            icrafting.sendProgressBarUpdate(this, 1, this.tileentity.skyIsVisible ? 1 : 0);
            icrafting.sendProgressBarUpdate(this, 2, this.tileentity.generating);
            icrafting.sendProgressBarUpdate(this, 3, this.tileentity.storage & '\uffff');
            icrafting.sendProgressBarUpdate(this, 4, this.tileentity.storage >>> 16);
        }

        this.sunIsUp = this.tileentity.sunIsUp;
        this.skyIsVisible = this.tileentity.skyIsVisible;
        this.generating = this.tileentity.generating;
        this.storage = this.tileentity.storage;
    }

    public void updateProgressBar(int i, int j) {
        if (i == 0) {
            this.tileentity.sunIsUp = j != 0;
        }

        if (i == 1) {
            this.tileentity.skyIsVisible = j != 0;
        }

        if (i == 2) {
            this.tileentity.generating = j;
        }

        if (i == 3) {
            this.tileentity.storage = this.tileentity.storage & -65536 | j;
        }

        if (i == 4) {
            this.tileentity.storage = this.tileentity.storage & '\uffff' | j << 16;
        }

    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack stack = null;
        Slot slotObject = (Slot) this.inventorySlots.get(par2);
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            if (par2 >= 0 && par2 <= 3) {
                if (!this.mergeItemStack(stackInSlot, 4, 40, true)) {
                    return null;
                }
            } else if (par2 >= 4 && par2 < 31) {
                if (!this.mergeItemStack(stackInSlot, 0, 4, false) && !this.mergeItemStack(stackInSlot, 31, 40, false)) {
                    return null;
                }
            } else if (par2 >= 31 && par2 < 39) {
                if (!this.mergeItemStack(stackInSlot, 0, 30, false)) {
                    return null;
                }
            } else if (!this.mergeItemStack(stackInSlot, 0, 30, false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack((ItemStack) null);
            } else {
                slotObject.onSlotChanged();
            }

            if (stack.stackSize == stackInSlot.stackSize) {
                return null;
            }

            slotObject.onPickupFromSlot(par1EntityPlayer, stackInSlot);
        }

        return stack;
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return this.tileentity.isUseableByPlayer(entityplayer);
    }
}
