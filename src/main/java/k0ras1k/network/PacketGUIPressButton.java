package k0ras1k.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class PacketGUIPressButton extends IPacket{
    public int dimID;
    public int x;
    public int y;
    public int z;
    public int buttonID;

    public PacketGUIPressButton() {
    }

    public void read(DataInputStream bytes) throws Throwable {
        this.dimID = bytes.readInt();
        this.x = bytes.readInt();
        this.y = bytes.readInt();
        this.z = bytes.readInt();
        this.buttonID = bytes.readInt();
    }

    public void write(DataOutputStream bytes) throws Throwable {
        bytes.writeInt(this.dimID);
        bytes.writeInt(this.x);
        bytes.writeInt(this.y);
        bytes.writeInt(this.z);
        bytes.writeInt(this.buttonID);
    }

    public static void issue(TileEntity te, int buttonID) {
        PacketGUIPressButton pgpb = new PacketGUIPressButton();
        pgpb.x = te.xCoord;
        pgpb.y = te.yCoord;
        pgpb.z = te.zCoord;
        pgpb.dimID = te.getWorldObj().provider.dimensionId;
        pgpb.buttonID = buttonID;
        K0ras1KPacketHandler.sendToServer(pgpb);
    }

    public static void issue(ChunkCoordinates te, int dimID, int buttonID) {
        PacketGUIPressButton pgpb = new PacketGUIPressButton();
        pgpb.x = te.posX;
        pgpb.y = te.posY;
        pgpb.z = te.posZ;
        pgpb.dimID = dimID;
        pgpb.buttonID = buttonID;
        K0ras1KPacketHandler.sendToServer(pgpb);
    }

    public void execute() {
        try {
            WorldServer ws = DimensionManager.getWorld(this.dimID);
            if (ws != null) {
                TileEntity te = ws.getTileEntity(this.x, this.y, this.z);
                if (te != null && te instanceof IHasButton) {
                    ((IHasButton)te).handleButtonClick(this.buttonID);
                }
            }
        } catch (Throwable var3) {
            var3.printStackTrace();
        }

    }
}
