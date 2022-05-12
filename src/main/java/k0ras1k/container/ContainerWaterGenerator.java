package k0ras1k.container;

import ic2.core.ContainerFullInv;
import ic2.core.slot.SlotInvSlot;
import k0ras1k.tiles.machines.TileEntityWaterGenerator;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class ContainerWaterGenerator extends ContainerFullInv<TileEntityWaterGenerator> {
    public ContainerWaterGenerator(EntityPlayer entityPlayer, TileEntityWaterGenerator tileEntity1) {
        super(entityPlayer, tileEntity1, 166);
        this.addSlotToContainer(new SlotInvSlot(tileEntity1.outputSlot, 0, 125, 59));
        this.addSlotToContainer(new SlotInvSlot(tileEntity1.containerslot, 0, 125, 23));
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("energy");
        ret.add("scrap");
        ret.add("fluidTank");
        return ret;
    }
}