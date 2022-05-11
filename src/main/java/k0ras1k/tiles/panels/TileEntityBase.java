package k0ras1k.tiles.panels;

import ic2.api.network.INetworkDataProvider;
import ic2.api.network.INetworkTileEntityEventListener;
import ic2.api.network.NetworkHelper;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

public class TileEntityBase extends TileEntity implements IWrenchable, INetworkDataProvider, INetworkTileEntityEventListener {

    public boolean active = false;
    public short facing = 5;
    public boolean prevActive = false;
    public short prevFacing = 0;

    public TileEntityBase() {
    }

    public boolean getActive() {
        return this.active;
    }

    public short getFacing() {
        return this.facing;
    }

    public boolean wrenchCanSetFacing(EntityPlayer var1, int facingToSet) {
        return facingToSet >= 2 && facingToSet != this.facing;
    }

    public void setFacing(short var1) {
        this.facing = var1;
        NetworkHelper.updateTileEntityField(this, "facing");
        this.prevFacing = var1;
    }

    public void onNetworkEvent(int event) {
    }

    public List<String> getNetworkedFields() {
        return null;
    }

    public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
        return true;
    }

    public float getWrenchDropRate() {
        return 1.0F;
    }

    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 1, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
    }

}
