package k0ras1k.tiles;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityBigFurnance extends TileEntityFurnace implements IInventory {
    protected static final int[] slotsTop = new int[] {0, 1};
    protected static final int[] slotsBottom = new int[] {2};
    protected static final int[] slotsSides = new int[] {3, 4};
    protected ItemStack[] furnaceItemStacks = new ItemStack[5];
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;
    private String furnaceCustomName;
    public int speed;
    private String name;



    public TileEntityBigFurnance(){
    }


    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        NBTTagList nbtTagList = tag.getTagList("Items", 10);
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbtTagList.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbtTagList.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.furnaceItemStacks.length) this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
        }

        this.furnaceBurnTime = tag.getShort("BurnTime");
        this.furnaceCookTime = tag.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

        if (tag.hasKey("CustomName", 8)) this.furnaceCustomName = tag.getString("CustomName");
    }

    public boolean isCustomInventoryName() {
        return this.furnaceCustomName != null && this.furnaceCustomName.length() > 0;
    }


    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);

        tag.setShort("BurnTime", (short)this.furnaceBurnTime);
        tag.setShort("CookTime", (short)this.furnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
            if (this.furnaceItemStacks[i] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        tag.setTag("Items", nbttaglist);

        if (this.isCustomInventoryName()) tag.setString("CustomName", this.furnaceCustomName);
    }


    @Override
    public int getSizeInventory() {
        return this.furnaceItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.furnaceItemStacks[slot];
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (this.furnaceItemStacks[index] != null){
            ItemStack itemStack;

            if (this.furnaceItemStacks[index].stackSize >= count){
                itemStack = this.furnaceItemStacks[index];
                this.furnaceItemStacks[index] = null;
                return itemStack;
            }
            else{
                itemStack = this.furnaceItemStacks[index].splitStack((count));

                if (this.furnaceItemStacks[index].stackSize == 0){
                    this.furnaceItemStacks[index] = null;
                }
                return itemStack;
            }
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        if (this.furnaceItemStacks[slot] != null){
            ItemStack itemStack = this.furnaceItemStacks[slot];
            this.furnaceItemStacks[slot] = null;
            return itemStack;
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.furnaceItemStacks[slot] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) stack.stackSize = this.getInventoryStackLimit();
    }

    @Override
    public String getInventoryName() {
        return this.isCustomInventoryName() ? this.furnaceCustomName : "container.big_furnace";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return this.furnaceCustomName != null && this.furnaceCustomName.length() > 0;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }


    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int p_145953_1_)
    {
        return this.furnaceCookTime * p_145953_1_ / this.speed;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int p_145955_1_) {
        if (this.currentItemBurnTime == 0) this.currentItemBurnTime = this.speed;

        return this.furnaceBurnTime * p_145955_1_ / this.currentItemBurnTime;
    }

    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }

    public void updateEntity() {
        boolean flag = this.furnaceBurnTime > 0;
        boolean isDirty = false;

        if (this.furnaceBurnTime > 0)  --this.furnaceBurnTime;

        if (!this.worldObj.isRemote) {
            if (this.furnaceBurnTime != 0 || this.furnaceItemStacks[1] != null || this.furnaceItemStacks[0] != null) {
                if (this.furnaceBurnTime == 0 && this.canSmelt()) {
                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[0]);

                    if (this.furnaceBurnTime > 0) {
                        isDirty = true;

                        if (this.furnaceItemStacks[2] != null) {
                            --this.furnaceItemStacks[2].stackSize;

                            if (this.furnaceItemStacks[2].stackSize == 0) this.furnaceItemStacks[2] = furnaceItemStacks[2].getItem().getContainerItem(furnaceItemStacks[2]);
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt()) {
                    ++this.furnaceCookTime;

                    if (this.furnaceCookTime == this.speed) {
                        this.furnaceCookTime = 0;
                        this.smeltItem();
                        isDirty = true;
                    }
                }
                else this.furnaceCookTime = 0;
            }

            if (flag != this.furnaceBurnTime > 0) {
                isDirty = true;
                updateBlock();
            }
        }

        if (isDirty) this.markDirty();
    }

    private boolean canSmelt() {
        if (this.furnaceItemStacks[0] == null || this.furnaceItemStacks[1] == null) return false;
        else {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
            if (itemstack == null) return false;
            if (this.furnaceItemStacks[3] == null || this.furnaceItemStacks[4] == null) return true;
            if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) return false;
            int result = furnaceItemStacks[3].stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[3].getMaxStackSize();
        }
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);

            if (this.furnaceItemStacks[2] == null) this.furnaceItemStacks[2] = itemstack.copy();
            else if (this.furnaceItemStacks[2].getItem() == itemstack.getItem()) this.furnaceItemStacks[2].stackSize += itemstack.stackSize;

            --this.furnaceItemStacks[0].stackSize;

            if (this.furnaceItemStacks[0].stackSize <= 0) this.furnaceItemStacks[0] = null;
        }
    }

    public static int getItemBurnTime(ItemStack stack) {
        if (stack == null) return 0;
        else {
            Item item = stack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.wooden_slab)
                {
                    return 150;
                }

                if (block.getMaterial() == Material.wood)
                {
                    return 300;
                }

                if (block == Blocks.coal_block)
                {
                    return 16000;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200;
            if (item == Items.stick) return 100;
            if (item == Items.coal) return 1600;
            if (item == Items.lava_bucket) return 20000;
            if (item == Item.getItemFromBlock(Blocks.sapling)) return 100;
            if (item == Items.blaze_rod) return 2400;
            return GameRegistry.getFuelValue(stack);
        }
    }

    public static boolean isItemFuel(ItemStack stack) {
        return getItemBurnTime(stack) > 0;
    }


    public void openChest() {}

    public void closeChest() {}

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack stack) {
        if (slot == 3 || slot == 4){
            return false;
        }
        else if (slot == 2 && isItemFuel(stack)){
            return true;
        }
        return true;
    }

    public int[] getSlotsForFace(int face) {
        return face == 0 ? slotsBottom : (face == 1 ? slotsTop : slotsSides);
    }

    @Override
    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return this.isItemValidForSlot(slot, stack);
    }

    @Override
    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return side != 0 || slot != 1 || stack.getItem() == Items.bucket;
    }

    public void updateBlock() {}

    }
