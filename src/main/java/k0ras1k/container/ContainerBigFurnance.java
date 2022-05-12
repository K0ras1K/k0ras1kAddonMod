package k0ras1k.container;

import k0ras1k.tiles.TileEntityBigFurnance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.tileentity.TileEntityFurnace;


public class ContainerBigFurnance extends Container {
    private TileEntityFurnace tileEntityFurnace;


    public ContainerBigFurnance(InventoryPlayer player, TileEntityFurnace machine) {
        this.tileEntityFurnace = machine;


        this.addSlotToContainer(new Slot(machine, 0, 46, 16));
        this.addSlotToContainer(new Slot(machine, 1, 68, 16));
        this.addSlotToContainer(new Slot(machine, 2, 56, 53));
        this.addSlotToContainer(new SlotFurnace(player.player, machine, 3, 113, 22));
        this.addSlotToContainer(new SlotFurnace(player.player, machine, 4, 113, 44));


        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }

    }


    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }
}

