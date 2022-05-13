package k0ras1k.tiles.machines;

import ic2.core.*;
import ic2.core.audio.AudioSource;
import ic2.core.audio.PositionSpec;
import ic2.core.block.TileEntityLiquidTankElectricMachine;
import ic2.core.block.comp.Redstone;
import ic2.core.block.invslot.*;
import ic2.core.init.MainConfig;
import ic2.core.network.NetworkManager;
import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import ic2.core.upgrade.UpgradableProperty;
import ic2.core.util.ConfigUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class TileEntityWaterGenerator extends TileEntityLiquidTankElectricMachine implements IHasGui, IUpgradableBlock {

    public final int defaultTier;
    public int soundTicker;
    public int scrap = 0;
    private final int StateIdle = 0;
    private final int StateRunning = 1;
    private final int StateRunningScrap = 2;
    private int state = 0;
    private int prevState = 0;
    public boolean redstonePowered = false;
    private AudioSource audioSource;
    private AudioSource audioSourceScrap;
    public final InvSlotOutput outputSlot;
    public final InvSlotUpgrade upgradeSlot;
    public final InvSlotConsumableLiquid containerslot;
    protected final Redstone redstone;

    public TileEntityWaterGenerator() {
        super(Math.round(1000000.0F * ConfigUtil.getFloat(MainConfig.get(), "balance/uuEnergyFactor")), 3, -1, 8);
        this.soundTicker = IC2.random.nextInt(32);
        this.outputSlot = new InvSlotOutput(this, "output", 1, 1);
        this.containerslot = new InvSlotConsumableLiquidByList(this, "containerslot", 2, InvSlot.Access.I, 1, InvSlot.InvSide.TOP, InvSlotConsumableLiquid.OpType.Fill, new Fluid[]{FluidRegistry.getFluid("water")});
        this.defaultTier = 3;
        this.redstone = (Redstone) this.addComponent(new Redstone(this));
        this.upgradeSlot = new InvSlotUpgrade(this, "upgrade", 3, 4);
    }


    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);

        try {
            this.scrap = nbttagcompound.getInteger("scrap");
        } catch (Throwable var3) {
            this.scrap = nbttagcompound.getShort("scrap");
        }

    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("scrap", this.scrap);
    }

    public String getInventoryName() {
        return "Mass Fabricator";
    }

    protected void updateEntityServer() {
        super.updateEntityServer();
        this.redstonePowered = false;
        boolean needsInvUpdate = false;

        for (int i = 0; i < this.upgradeSlot.size(); ++i) {
            ItemStack stack = this.upgradeSlot.get(i);
            if (stack != null && stack.getItem() instanceof IUpgradeItem && ((IUpgradeItem) stack.getItem()).onTick(stack, this)) {
                needsInvUpdate = true;
            }
        }


        if (!this.redstone.hasRedstoneInput() && !(this.energy <= 0.0D)) {
            this.setState(this.scrap > 0 ? 2 : 1);
            this.setActive(true);


            if (this.energy >= (double) this.maxEnergy) {
                needsInvUpdate = this.attemptGeneration();
            }

            MutableObject<ItemStack> output = new MutableObject();
            if (this.containerslot.transferFromTank(this.fluidTank, output, true) && (output.getValue() == null || this.outputSlot.canAdd((ItemStack) output.getValue()))) {
                this.containerslot.transferFromTank(this.fluidTank, output, false);
                if (output.getValue() != null) {
                    this.outputSlot.add((ItemStack) output.getValue());
                }
            }

            if (needsInvUpdate) {
                this.markDirty();
            }
        } else {
            this.setState(0);
            this.setActive(false);
        }

    }

    public void onUnloaded() {
        if (IC2.platform.isRendering() && this.audioSource != null) {
            IC2.audioManager.removeSources(this);
            this.audioSource = null;
            this.audioSourceScrap = null;
        }

        super.onUnloaded();
    }

    public boolean attemptGeneration() {
        if (this.fluidTank.getFluidAmount() + 1 > this.fluidTank.getCapacity()) {
            return false;
        } else {
            this.fill((ForgeDirection) null, new FluidStack(FluidRegistry.getFluid("water"), 100), true);
            this.energy -= (double) this.maxEnergy;
            return true;
        }
    }

    public double getDemandedEnergy() {
        return this.redstone.hasRedstoneInput() ? 0.0D : (double) this.maxEnergy - this.energy;
    }

    public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
        if (!(this.energy >= (double) this.maxEnergy) && !this.redstone.hasRedstoneInput()) {
            int bonus = Math.min((int) amount, this.scrap);
            this.scrap -= bonus;
            this.energy += amount + (double) (5 * bonus);
            return 0.0D;
        } else {
            return amount;
        }
    }

    public String getProgressAsString() {
        int p = Math.min((int) (this.energy * 100.0D / (double) this.maxEnergy), 100);
        return "" + p + "%";
    }


    @Override
    public ContainerBase<?> getGuiContainer(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean b) {
        return null;
    }

    public void onGuiClosed(EntityPlayer entityPlayer) {
    }

    private void setState(int aState) {
        this.state = aState;
        if (this.prevState != this.state) {
            ((NetworkManager) IC2.network.get()).updateTileEntityField(this, "state");
        }

        this.prevState = this.state;
    }

    public List<String> getNetworkedFields() {
        List<String> ret = new Vector(1);
        ret.add("state");
        ret.addAll(super.getNetworkedFields());
        return ret;
    }

    public void onNetworkUpdate(String field) {
        super.onNetworkUpdate(field);
    }

    public float getWrenchDropRate() {
        return 0.7F;
    }


    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid == FluidRegistry.getFluid("water");
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return true;
    }

    public void onLoaded() {
        super.onLoaded();
        if (IC2.platform.isSimulating()) {
            this.setUpgradestat();
        }
    }

    public void markDirty() {
        super.markDirty();
        if (IC2.platform.isSimulating()) {
            this.setUpgradestat();
        }
    }

    public void setUpgradestat() {
        this.upgradeSlot.onChanged();
        this.setTier(applyModifier(this.defaultTier, this.upgradeSlot.extraTier, 1.0D));
    }


    private static int applyModifier(int base, int extra, double multiplier) {
        double ret = (double) Math.round(((double) base + (double) extra) * multiplier);
        return ret > 2.147483647E9D ? 2147483647 : (int) ret;
    }

    public double getEnergy() {
        return this.energy;
    }

    public boolean useEnergy(double amount) {
        if (this.energy >= amount) {
            this.energy -= amount;
            return true;
        } else {
            return false;
        }
    }

    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.RedstoneSensitive, UpgradableProperty.Transformer, UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing, UpgradableProperty.FluidProducing);
    }

    public int getMatterValue() {
        if (this.fluidTank != null){
            return (int) (this.fluidTank.getFluidAmount() * 0.0075);
        }
        return 1;
    }

}
