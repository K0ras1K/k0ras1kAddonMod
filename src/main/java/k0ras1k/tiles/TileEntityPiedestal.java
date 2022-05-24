package k0ras1k.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPiedestal extends TileEntity implements IInventory {


    private int id;
    private int damage;

    private boolean isActive;
    protected ItemStack[] inv;

    public TileEntityPiedestal() {
        this.id = 0;
        this.damage = 0;
        this.isActive = false;
        inv = new ItemStack[1];
    }

    public void updateEntity() {
        super.updateEntity();
    }

    public void setActive() {
        isActive = false;
    }






    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        id = nbtTagCompound.getInteger("id");
        damage = nbtTagCompound.getInteger("damage");
        isActive = nbtTagCompound.getBoolean("isActive");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("resultID", id);
        nbtTagCompound.setInteger("resultDamage", damage);
        nbtTagCompound.setBoolean("isActive", isActive);
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inv[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int decr) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null)
        {
            if (stack.stackSize <= decr)
                setInventorySlotContents(slot, null);
            else
            {
                stack = stack.splitStack(decr);
                if (stack.stackSize == 0)
                    setInventorySlotContents(slot, null);
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        ItemStack stack = getStackInSlot(slot);
        if (stack != null)
            setInventorySlotContents(slot, null);
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        inv[slot] = stack;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        if (stack != null && stack.stackSize > getInventoryStackLimit())
            stack.stackSize = getInventoryStackLimit();
    }

    public String getInventoryName() {
        return "TileEntityPiedestal";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this
                && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

}
