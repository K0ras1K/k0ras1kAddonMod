package k0ras1k.tiles;

import fox.spiteful.avaritia.items.LudicrousItems;
import fox.spiteful.avaritia.tile.TileEntityNeutron;
import k0ras1k.reg.items.ItemList;
import k0ras1k.reg.items.UpgradeRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityNeutronBig extends TileEntityNeutron {

    private ItemStack neutrons;
    private ItemStack upgrade;
    private ItemStack upgrade1;
    private ItemStack upgrade2;
    private int progress;
    private int facing = 2;



    private int getProductionTime() {
        for (int i = 0; i < 4; ++i) {
            if (i != 2) {
                if (this.getStackInSlot(i) != null) {
                    if (this.getStackInSlot(i).getItem() == ItemList.ItemNeutronUpgradeSpeed) {
                        return 20;
                    }
                }
            }
        }
        return 80;
    }

    private int getProductionStackSize() {
        for (int i = 0; i < 4; ++i) {
            if (i != 2) {
                if (this.getStackInSlot(i) != null) {
                    if (this.getStackInSlot(i).getItem() == ItemList.ItemNeutronUpgradeProd) {
                        return 4;
                    }
                }
            }
        }
        return 1;
    }

    private int getProductionId() {
        for (int i = 0; i < 4; ++i) {
            if (i != 2) {
                if (this.getStackInSlot(i) != null) {
                    if (this.getStackInSlot(i).getItem() == ItemList.ItemNeutronUpgradeIng) {
                        return 3;
                    }
                }
            }
        }
        return 2;
    }

    private void addNeutronsStackSize() {
        if (this.neutrons.getItemDamage() != this.getProductionId()) {
            this.neutrons = new ItemStack(LudicrousItems.resource, this.getProductionStackSize(), this.getProductionId());
        } else {
            this.neutrons.stackSize = this.neutrons.stackSize + this.getProductionStackSize();
        }

    }

    @Override
    public void updateEntity() {
        if (++this.progress >= this.getProductionTime()) {
            if (this.neutrons == null) {
                this.neutrons = new ItemStack(LudicrousItems.resource, this.getProductionStackSize(), this.getProductionId());
            } else if (this.neutrons.getItem() == LudicrousItems.resource && this.neutrons.stackSize < 64) {
                addNeutronsStackSize();
            }
            this.progress = 0;
            this.markDirty();
        }

    }


    public void readCustomNBT(NBTTagCompound tag) {
        this.neutrons = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Neutrons"));
        this.progress = tag.getInteger("Progress");
        this.facing = tag.getShort("Facing");
        this.upgrade = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Upgrade"));
        this.upgrade1 = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Upgrade1"));
        this.upgrade2 = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Upgrade2"));
    }

    public void writeCustomNBT(NBTTagCompound tag) {
        tag.setInteger("Progress", this.progress);
        tag.setShort("Facing", (short) this.facing);
        if (this.neutrons != null) {
            NBTTagCompound produce = new NBTTagCompound();
            this.neutrons.writeToNBT(produce);
            tag.setTag("Neutrons", produce);
        } else {
            tag.removeTag("Neutrons");
        }


        if (this.upgrade != null) {
            NBTTagCompound upgradeSlot = new NBTTagCompound();
            this.upgrade.writeToNBT(upgradeSlot);
            tag.setTag("Upgrade", upgradeSlot);
        }
        else{
            tag.removeTag("Upgrade");
        }

        if (this.upgrade1 != null) {
            NBTTagCompound upgradeSlot1 = new NBTTagCompound();
            this.upgrade1.writeToNBT(upgradeSlot1);
            tag.setTag("Upgrade1", upgradeSlot1);
        }
        else{
            tag.removeTag("Upgrade1");
        }

        if (this.upgrade2 != null) {
            NBTTagCompound upgradeSlot2 = new NBTTagCompound();
            this.upgrade2.writeToNBT(upgradeSlot2);
            tag.setTag("Upgrade2", upgradeSlot2);
        }
        else{
            tag.removeTag("Upgrade2");
        }



    }

    public int getFacing() {
        return this.facing;
    }

    public void setFacing(int dir) {
        this.facing = dir;
    }

    public int getSizeInventory() {
        return 1;
    }

    public ItemStack getStackInSlot(int slot) {
        if (slot == 2) {
            return this.neutrons;
        } else if (slot == 0) {
            return this.upgrade;
        } else if (slot == 1) {
            return this.upgrade1;
        } else if (slot == 3) {
            return this.upgrade2;
        } else {
            return null;
        }


    }

    public ItemStack decrStackSize(int slot, int decrement) {
        if (slot == 2) {
            if (this.neutrons == null) {
                return null;
            } else {
                ItemStack take;
                if (decrement < this.neutrons.stackSize) {
                    take = this.neutrons.splitStack(decrement);
                    if (this.neutrons.stackSize <= 0) {
                        this.neutrons = null;
                    }

                    return take;
                } else {
                    take = this.neutrons;
                    this.neutrons = null;
                    return take;
                }
            }
        } else if (slot == 0) {
            if (this.upgrade == null) {
                return null;
            } else {
                ItemStack take;
                if (decrement < this.upgrade.stackSize) {
                    take = this.upgrade.splitStack(decrement);
                    if (this.upgrade.stackSize <= 0) {
                        this.upgrade = null;
                    }
                    return take;
                } else {
                    take = this.upgrade;
                    this.upgrade = null;
                    return take;
                }
            }
        } else if (slot == 1) {
            if (this.upgrade1 == null) {
                return null;
            } else {
                ItemStack take;
                if (decrement < this.upgrade1.stackSize) {
                    take = this.upgrade1.splitStack(decrement);
                    if (this.upgrade1.stackSize <= 0) {
                        this.upgrade1 = null;
                    }
                    return take;
                } else {
                    take = this.upgrade1;
                    this.upgrade1 = null;
                    return take;
                }
            }
        } else {
            if (this.upgrade2 == null) {
                return null;
            } else {
                ItemStack take;
                if (decrement < this.upgrade2.stackSize) {
                    take = this.upgrade2.splitStack(decrement);
                    if (this.upgrade2.stackSize <= 0) {
                        this.upgrade2 = null;
                    }
                    return take;
                } else {
                    take = this.upgrade2;
                    this.upgrade2 = null;
                    return take;
                }
            }
        }
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
    }

    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        return false;

    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public void setInventorySlotContents(int slot, ItemStack stack) {
        if (slot == 2) {
            this.neutrons = stack;
        } else if (slot == 0) {
            this.upgrade = stack;
        } else if (slot == 1) {
            this.upgrade1 = stack;
        } else {
            this.upgrade2 = stack;
        }
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    public String getInventoryName() {
        return "container.neutron";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int gaugeProgressScaled() {
        for (int i = 0; i < 4; ++i) {
            if (i != 2) {
                if (this.getStackInSlot(i) != null) {
                    if (this.getStackInSlot(i).getItem() == ItemList.ItemNeutronUpgradeSpeed) {
                        return (int) (this.progress * 2.6);
                    }
                }
            }
        }
        return (int) (this.progress * 0.65);
    }
}
