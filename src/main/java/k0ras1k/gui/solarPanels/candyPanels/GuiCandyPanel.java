package k0ras1k.gui.solarPanels.candyPanels;

import k0ras1k.blocks.solarPanels.candyPanels.BlockCandyPanel;
import k0ras1k.container.ContainerSolarPanel;
import k0ras1k.tiles.panels.TileEntitySolarPanel;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GuiCandyPanel extends GuiContainer {


    public TileEntitySolarPanel tileentity;
    private static ResourceLocation tex = new ResourceLocation("k0ras1k", "textures/gui/GuiBluePanel.png");

    public GuiCandyPanel(InventoryPlayer inventoryplayer, TileEntitySolarPanel tileentitysolarpanel) {
        super(new ContainerSolarPanel(inventoryplayer, tileentitysolarpanel));
        this.tileentity = tileentitysolarpanel;
        this.allowUserInput = false;
        this.xSize = 175;
        this.ySize = 165;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        String formatPanelName = I18n.format(this.tileentity.panelName);
        int nmPos = (this.xSize - this.fontRendererObj.getStringWidth(formatPanelName)) / 2;
        this.fontRendererObj.drawString(formatPanelName, nmPos, 73, 13487565);
        String storageString = I18n.format("gui.candypanels.storage");
        String maxOutputString = I18n.format("gui.candypanels.maxOutput");
        String generatingString = I18n.format("gui.candypanels.generating");
        String energyPerTickString = I18n.format("gui.candypanels.eut");
        String maxStorageString = I18n.format("gui.candypanels.maxstorage");
        this.fontRendererObj.drawString(storageString, 12, 31, 13487565);
        this.fontRendererObj.drawString(String.valueOf(this.tileentity.storage), 12, 41, 13487565);
        this.fontRendererObj.drawString(maxStorageString, 12, 51, 13487565);
        this.fontRendererObj.drawString(String.valueOf(this.tileentity.maxStorage), 12, 61, 13487565);
        this.fontRendererObj.drawString(maxOutputString, 58, 31, 13487565);
        this.fontRendererObj.drawString(String.valueOf(this.tileentity.production), 58, 41, 13487565);
        this.fontRendererObj.drawString(energyPerTickString, 58, 51, 13487565);
        this.fontRendererObj.drawString(generatingString, 104, 31, 13487565);
        this.fontRendererObj.drawString(String.valueOf(this.tileentity.generating), 104, 41, 13487565);
        this.fontRendererObj.drawString(energyPerTickString, 104, 51, 13487565);
        }

    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        changeGuiLocation();
        this.mc.renderEngine.bindTexture(tex);
        int h = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(h, k, 0, 0, this.xSize, this.ySize);
        if (this.tileentity.storage > 0) {
            double l = this.tileentity.gaugeEnergyScaled(137);
            this.drawTexturedModalRect(h + 9, k + 8, 119, 166, (int) (l + 1), 8);
        }

        if (this.tileentity.skyIsVisible) {
            if (this.tileentity.sunIsUp) {
                this.drawTexturedModalRect(h + 133, k + 30, 176, 10, 9, 9);
            } else if (!this.tileentity.sunIsUp) {
                this.drawTexturedModalRect(h + 133, k + 30, 176, 0, 9, 9);
            }
        }

    }
    public void changeGuiLocation(){
        tex = new ResourceLocation("k0ras1k", "textures/gui/Gui_" + this.tileentity.panelName + ".png");
    }


}
