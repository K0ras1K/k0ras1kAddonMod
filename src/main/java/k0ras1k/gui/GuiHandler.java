package k0ras1k.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import k0ras1k.container.ContainerBigFurnance;
import k0ras1k.container.ContainerNeutronBig;
import k0ras1k.container.ContainerSolarPanel;
import k0ras1k.container.ContainerTransformer;
import k0ras1k.gui.solarPanels.candyPanels.*;
import k0ras1k.tiles.TileEntityBigFurnance;
import k0ras1k.tiles.TileEntityNeutronBig;
import k0ras1k.tiles.TileEntityTransformer;
import k0ras1k.tiles.panels.TileEntitySolarPanel;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        Block block = world.getTileEntity(x, y, z).getBlockType();
        if (tileEntity != null) {
            if (tileEntity instanceof TileEntityNeutronBig) {
                return new GUINeutronBig(player.inventory, (TileEntityNeutronBig) world.getTileEntity(x, y, z));
            } else if (tileEntity instanceof TileEntityBigFurnance) {
                return new GuiBigFurnance(player.inventory, new TileEntityBigFurnance());
            } else if (tileEntity instanceof TileEntitySolarPanel) {
                return new GuiCandyPanel(player.inventory, (TileEntitySolarPanel) world.getTileEntity(x, y, z));
            }

        }
        return null;

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        Block block = world.getTileEntity(x, y, z).getBlockType();
        if (tileEntity != null) {
            if (tileEntity instanceof TileEntityNeutronBig) {
                return new ContainerNeutronBig(player.inventory, (TileEntityNeutronBig) world.getTileEntity(x, y, z));
            } else if (tileEntity instanceof TileEntityBigFurnance) {
                return new ContainerBigFurnance(player.inventory, new TileEntityBigFurnance());
            } else if (tileEntity instanceof TileEntitySolarPanel) {
                return new ContainerSolarPanel(player.inventory, (TileEntitySolarPanel) world.getTileEntity(x, y, z));
            } else if (tileEntity instanceof TileEntityTransformer) {
                return new ContainerTransformer(player.inventory, (TileEntityTransformer) world.getTileEntity(x, y, z));
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
