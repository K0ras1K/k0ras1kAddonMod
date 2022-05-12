package k0ras1k.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import k0ras1k.Main;
import k0ras1k.tiles.machines.TileEntityWaterGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockWaterGenerator extends BlockContainer {

    private IIcon top, sides, front;
    private Random randy = new Random();

    public BlockWaterGenerator() {
        super(Material.iron);
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(20.0F);
        this.setBlockName("water_generator");
        this.setHarvestLevel("pickaxe", 3);
        this.setCreativeTab(Main.lightBlocks);
        this.setLightLevel(1F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.top = iconRegister.registerIcon("k0ras1k:collector_top");
        this.sides = iconRegister.registerIcon("k0ras1k:collector_side");
        this.front = iconRegister.registerIcon("k0ras1k:collector_front");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        if (side == 1)
            return top;
        int facing = 2;
        TileEntityWaterGenerator machine = (TileEntityWaterGenerator) world.getTileEntity(x, y, z);
        if (machine != null)
            facing = machine.getFacing();
        if (side == facing)
            return front;
        else
            return sides;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata) {
        if (side == 1)
            return top;
        if (side == 3)
            return front;
        return sides;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (world.isRemote) {
            return true;
        } else {
            player.openGui(Main.instance, 2, world, x, y, z);
            return true;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityWaterGenerator();
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityWaterGenerator) {
            TileEntityWaterGenerator machine = (TileEntityWaterGenerator) tile;
            int l = MathHelper.floor_double((double) (player.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;


        }

    }

    public void breakBlock(World world, int x, int y, int z, Block block, int wut) {
        TileEntityWaterGenerator generator = (TileEntityWaterGenerator) world.getTileEntity(x, y, z);

        if (generator != null) {
            ItemStack itemstack = generator.getStackInSlot(0);

            if (itemstack != null) {
                float f = this.randy.nextFloat() * 0.8F + 0.1F;
                float f1 = this.randy.nextFloat() * 0.8F + 0.1F;
                float f2 = this.randy.nextFloat() * 0.8F + 0.1F;

                while (itemstack.stackSize > 0) {
                    int j1 = this.randy.nextInt(21) + 10;

                    if (j1 > itemstack.stackSize) {
                        j1 = itemstack.stackSize;
                    }

                    itemstack.stackSize -= j1;
                    EntityItem entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));

                    if (itemstack.hasTagCompound()) {
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                    }

                    float f3 = 0.05F;
                    entityitem.motionX = (double) ((float) this.randy.nextGaussian() * f3);
                    entityitem.motionY = (double) ((float) this.randy.nextGaussian() * f3 + 0.2F);
                    entityitem.motionZ = (double) ((float) this.randy.nextGaussian() * f3);
                    world.spawnEntityInWorld(entityitem);
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, wut);
    }

}
