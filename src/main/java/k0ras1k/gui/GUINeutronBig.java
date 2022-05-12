package k0ras1k.gui;


import k0ras1k.container.ContainerNeutronBig;
import k0ras1k.tiles.TileEntityNeutronBig;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;


public class GUINeutronBig extends GuiContainer {
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("k0ras1k", "textures/gui/neutron_collector_gui_big.png");
    public TileEntityNeutronBig tileentity;

    public GUINeutronBig(InventoryPlayer player, TileEntityNeutronBig machine) {
        super(new ContainerNeutronBig(player, machine));
        this.tileentity = machine;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = StatCollector.translateToLocal("container.neutron_collector_big");
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 13487565);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 13487565);
    }

    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        if (this.tileentity.gaugeProgressScaled() > 0) {
            int h = this.tileentity.gaugeProgressScaled();
            this.drawTexturedModalRect(k + 8, l + 16, 176, 0, 16, h);
        }


    }

}
