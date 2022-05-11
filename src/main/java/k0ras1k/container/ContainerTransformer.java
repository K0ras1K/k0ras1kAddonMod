package k0ras1k.container;

import k0ras1k.slots.TransformerSlot;
import k0ras1k.tiles.TileEntityTransformer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerTransformer extends Container {
    private TileEntityTransformer tileentity;
    private short lastProgress = -1;
    private int energyUsed;
    private int energyPerOperartion;
    private int inputEU;
    private int doWork;
    private int lastRecipeNumber;

    public ContainerTransformer(InventoryPlayer inventoryplayer, TileEntityTransformer tileentitymoleculartransformer) {
        this.tileentity = tileentitymoleculartransformer;
        this.addSlotToContainer(new Slot(this.tileentity, 0, 94, 43));
        this.addSlotToContainer(new TransformerSlot(this.tileentity, 1, 147, 43, false));
        this.addSlotToContainer(new Slot(this.tileentity, 2, 120, 16));

        int j;
        for(j = 0; j < 3; ++j) {
            for(int k = 0; k < 9; ++k) {
                this.addSlotToContainer(new Slot(inventoryplayer, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
            }
        }

        for(j = 0; j < 9; ++j) {
            this.addSlotToContainer(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }

    public void addCraftingToCrafters(ICrafting icrafting) {
        super.addCraftingToCrafters(icrafting);
        icrafting.sendProgressBarUpdate(this, 0, this.tileentity.lastRecipeEnergyUsed & '\uffff');
        icrafting.sendProgressBarUpdate(this, 1, this.tileentity.lastRecipeEnergyUsed >>> 16);
        icrafting.sendProgressBarUpdate(this, 2, this.tileentity.lastRecipeEnergyPerOperation & '\uffff');
        icrafting.sendProgressBarUpdate(this, 3, this.tileentity.lastRecipeEnergyPerOperation >>> 16);
        icrafting.sendProgressBarUpdate(this, 4, this.tileentity.doWork ? 1 : 0);
        icrafting.sendProgressBarUpdate(this, 5, this.tileentity.lastProgress);
        icrafting.sendProgressBarUpdate(this, 6, this.tileentity.lastRecipeNumber);
        icrafting.sendProgressBarUpdate(this, 7, this.tileentity.inputEU & '\uffff');
        icrafting.sendProgressBarUpdate(this, 8, this.tileentity.inputEU >>> 16);
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for(int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.lastRecipeEnergyUsed & '\uffff');
            icrafting.sendProgressBarUpdate(this, 1, this.tileentity.lastRecipeEnergyUsed >>> 16);
            icrafting.sendProgressBarUpdate(this, 2, this.tileentity.lastRecipeEnergyPerOperation & '\uffff');
            icrafting.sendProgressBarUpdate(this, 3, this.tileentity.lastRecipeEnergyPerOperation >>> 16);
            if (this.doWork != (this.tileentity.doWork ? 1 : 0)) {
                icrafting.sendProgressBarUpdate(this, 4, this.tileentity.doWork ? 1 : 0);
            }

            if (this.lastProgress != this.tileentity.lastProgress) {
                icrafting.sendProgressBarUpdate(this, 5, this.tileentity.lastProgress);
            }

            if (this.lastRecipeNumber != this.tileentity.lastRecipeNumber) {
                icrafting.sendProgressBarUpdate(this, 6, this.tileentity.lastRecipeNumber);
            }

            icrafting.sendProgressBarUpdate(this, 7, this.tileentity.inputEU & '\uffff');
            icrafting.sendProgressBarUpdate(this, 8, this.tileentity.inputEU >>> 16);
        }

        this.energyUsed = this.tileentity.lastRecipeEnergyUsed;
        this.energyPerOperartion = this.tileentity.lastRecipeEnergyPerOperation;
        this.doWork = this.tileentity.doWork ? 1 : 0;
        this.lastProgress = this.tileentity.lastProgress;
        this.lastRecipeNumber = this.tileentity.lastRecipeNumber;
        this.inputEU = this.tileentity.inputEU;
    }

    public void updateProgressBar(int index, int value) {
        if (index == 0) {
            this.tileentity.lastRecipeEnergyUsed = this.tileentity.lastRecipeEnergyUsed & -65536 | value;
        }

        if (index == 1) {
            this.tileentity.lastRecipeEnergyUsed = this.tileentity.lastRecipeEnergyUsed & '\uffff' | value << 16;
        }

        if (index == 2) {
            this.tileentity.lastRecipeEnergyPerOperation = this.tileentity.lastRecipeEnergyPerOperation & -65536 | value;
        }

        if (index == 3) {
            this.tileentity.lastRecipeEnergyPerOperation = this.tileentity.lastRecipeEnergyPerOperation & '\uffff' | value << 16;
        }

        if (index == 4) {
            this.tileentity.doWork = value == 1;
        }

        if (index == 5) {
            this.tileentity.lastProgress = (short)value;
        }

        if (index == 6) {
            this.tileentity.lastRecipeNumber = value;
        }

        if (index == 7) {
            this.tileentity.inputEU = this.tileentity.inputEU & -65536 | value;
        }

        if (index == 8) {
            this.tileentity.inputEU = this.tileentity.inputEU & '\uffff' | value << 16;
        }

    }

    public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack stack = null;
        int mySlotsCount = 2;
        int mainInventorySlotsEnd = 27 + mySlotsCount;
        int hotBarslotsEnd = this.inventorySlots.size();
        Slot slotObject = (Slot)this.inventorySlots.get(par2);
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();
            if (par2 <= 1) {
                if (!this.mergeItemStack(stackInSlot, mainInventorySlotsEnd, hotBarslotsEnd, false) && !this.mergeItemStack(stackInSlot, mySlotsCount, mainInventorySlotsEnd, false)) {
                    return null;
                }
            } else if (par2 >= mySlotsCount && par2 < mainInventorySlotsEnd) {
                if (!this.mergeItemStack(stackInSlot, 0, 1, false) && !this.mergeItemStack(stackInSlot, mainInventorySlotsEnd, hotBarslotsEnd, false)) {
                    return null;
                }
            } else if (par2 >= mainInventorySlotsEnd && par2 < hotBarslotsEnd && !this.mergeItemStack(stackInSlot, 0, 1, false) && !this.mergeItemStack(stackInSlot, mySlotsCount, mainInventorySlotsEnd, false)) {
                return null;
            }

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack((ItemStack)null);
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
