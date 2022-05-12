package k0ras1k.items.electric;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import k0ras1k.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class BaseElectricSword extends ItemSword implements IElectricItem {

    // IElectricItem
    protected int energyTier;
    protected double maxCharge;
    protected double transferLimit;
    protected boolean providesEnergy;

    public BaseElectricSword(ToolMaterial toolMaterial, int energyTier, double maxCharge, double transferLimit, boolean providesEnergy) {
        super(toolMaterial);
        this.energyTier = energyTier;
        this.maxCharge = maxCharge;
        this.transferLimit = transferLimit;
        this.providesEnergy = providesEnergy;

        this.setMaxDamage(27);
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.setCreativeTab(Main.lightBlocks);
    }

    public void useEnergy(ItemStack itemStack, double energyAmount) {
        ElectricItem.manager.discharge(itemStack, energyAmount, this.energyTier, true, false, false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tabs, List itemList) {
        ItemStack chargedItem = new ItemStack(this, 1);
        ElectricItem.manager.charge(chargedItem, Double.POSITIVE_INFINITY, Integer.MAX_VALUE, true, false);
        itemList.add(chargedItem);

        ItemStack depletedItem = new ItemStack(this, 1, getMaxDamage());
        itemList.add(depletedItem);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean p_77624_4_) {
        NBTTagCompound tags = itemStack.getTagCompound();
        if (tags != null) {
            list.add("EU: " + itemStack.stackTagCompound.getInteger("charge") + " / " + this.getMaxCharge(itemStack));
        } else {
            list.add("EU: " + "0" + " / " + this.getMaxCharge(itemStack));
        }
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return this.providesEnergy;
    }

    @Override
    public Item getChargedItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public Item getEmptyItem(ItemStack itemStack) {
        return this;
    }

    @Override
    public double getMaxCharge(ItemStack itemStack) {
        return this.maxCharge;
    }

    @Override
    public int getTier(ItemStack itemStack) {
        return this.energyTier;
    }

    @Override
    public double getTransferLimit(ItemStack itemStack) {
        return this.transferLimit;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemStack) {
        return EnumRarity.epic;
    }
}
