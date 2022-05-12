package k0ras1k.blocks;

import k0ras1k.Main;
import k0ras1k.tiles.TileEntityBigFurnance;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBigFurnance extends BlockContainer {


    public BlockBigFurnance() {
        super(Material.iron);
        setStepSound(Block.soundTypeMetal);
        setHardness(20.0F);
        setBlockName("Big_Furnance");
        setHarvestLevel("pickaxe", 3);
        setCreativeTab(Main.lightBlocks);
        setLightLevel(1F);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityBigFurnance();
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
}
