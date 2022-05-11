package k0ras1k.blocks.solarPanels.candyPanels;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import k0ras1k.Main;
import k0ras1k.tiles.panels.TileEntityBase;
import k0ras1k.tiles.panels.TileEntitySolarPanel;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCandyPanel extends BlockContainer {
    public boolean qgActive;
    private IIcon top, sides, bot;
    private int genDay;
    private int genNight;
    private int production;
    private int maxStorage;
    private String tileName;
    private String color;


    public BlockCandyPanel(String tileName, Integer genDay, Integer genNight, Integer maxStorage, Integer production, String color) {
        super(Material.iron);
        this.setHardness(3.0F);
        this.setCreativeTab(Main.lightBlocks);
        this.qgActive = false;
        this.genDay = genDay;
        this.genNight = genNight;
        this.maxStorage = maxStorage;
        this.production = production;
        this.tileName = tileName;
        this.color = color;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.top = iconRegister.registerIcon("k0ras1k:panel_" + this.color + "_top");
        this.sides = iconRegister.registerIcon("k0ras1k:panel_" + this.color + "_side");
        this.bot = iconRegister.registerIcon("k0ras1k:panel_" + this.color + "_side");
    }


    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side){
        if(side == 1)
            return top;
        return sides;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon (int side, int metadata)
    {
        if(side == 1)
            return top;
        return sides;
    }

    public static boolean isActive(IBlockAccess var0, int var1, int var2, int var3) {
        return ((TileEntityBase)var0.getTileEntity(var1, var2, var3)).getActive();
    }

    public void breakBlock(World world, int i, int j, int k, Block par5, int par6) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        world.removeTileEntity(i, j, k);
        super.breakBlock(world, i, j, k, par5, par6);
    }

    public int quantityDropped(Random random) {
        return 1;
    }

    public int damageDropped(int i) {
        return i;
    }

    public TileEntity getBlockEntity(int i) {
        return new TileEntitySolarPanel(this.tileName, this.genDay, this.genNight, this.maxStorage, this.production);
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int s, float f1, float f2, float f3) {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            FMLNetworkHandler.openGui(player, Main.instance, 2, world, i, j, k);
            return true;
        }
    }

    private void dropItems(TileEntitySolarPanel tileentity, World world) {
        Random rand = new Random();
        if (tileentity instanceof IInventory) {
            IInventory inventory = tileentity;

            for(int i = 0; i < inventory.getSizeInventory(); ++i) {
                ItemStack item = inventory.getStackInSlot(i);
                if (item != null && item.stackSize > 0) {
                    float rx = rand.nextFloat() * 0.8F + 0.1F;
                    float ry = rand.nextFloat() * 0.8F + 0.1F;
                    float rz = rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityItem = new EntityItem(world, (double)((float)tileentity.xCoord + rx), (double)((float)tileentity.yCoord + ry), (double)((float)tileentity.zCoord + rz), new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
                    if (item.hasTagCompound()) {
                        entityItem.getEntityItem().setTagCompound((NBTTagCompound)item.getTagCompound().copy());
                    }

                    float factor = 0.05F;
                    entityItem.motionX = rand.nextGaussian() * (double)factor;
                    entityItem.motionY = rand.nextGaussian() * (double)factor + 0.20000000298023224D;
                    entityItem.motionZ = rand.nextGaussian() * (double)factor;
                    world.spawnEntityInWorld(entityItem);
                    item.stackSize = 0;
                }
            }

        }
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntitySolarPanel(this.tileName, this.genDay, this.genNight, this.maxStorage, this.production);
    }

    public String getPanelColor(){
        return this.color;
    }

}

