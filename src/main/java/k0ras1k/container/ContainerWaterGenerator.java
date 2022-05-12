package k0ras1k.container;

import ic2.core.ContainerFullInv;
import ic2.core.slot.SlotInvSlot;
import k0ras1k.tiles.machines.TileEntityWaterGenerator;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class ContainerWaterGenerator extends ContainerFullInv<TileEntityWaterGenerator> {
    public ContainerWaterGenerator(EntityPlayer entityPlayer, TileEntityWaterGenerator tileEntity1) {
        super(entityPlayer, tileEntity1, 166);
        this.addSlotToContainer(new SlotInvSlot(tileEntity1.outputSlot, 0, 121, 63));
        this.addSlotToContainer(new SlotInvSlot(tileEntity1.containerslot, 0, 121, 23));

        for(int i = 0; i < 4; ++i) {
            this.addSlotToContainer(new SlotInvSlot(tileEntity1.upgradeSlot, i, 152, 8 + i * 18));
        }
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("energy");
        ret.add("scrap");
        ret.add("fluidTank");
        return ret;
    }
}