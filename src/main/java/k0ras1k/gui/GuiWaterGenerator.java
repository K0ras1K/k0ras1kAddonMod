package k0ras1k.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.GuiIC2;
import ic2.core.IC2;
import ic2.core.block.machine.container.ContainerMatter;
import ic2.core.block.machine.tileentity.TileEntityMatter;
import ic2.core.util.DrawUtil;
import ic2.core.util.GuiTooltipHelper;
import k0ras1k.container.ContainerNeutronBig;
import k0ras1k.container.ContainerWaterGenerator;
import k0ras1k.tiles.TileEntityNeutronBig;
import k0ras1k.tiles.machines.TileEntityWaterGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.opengl.GL11;


@SideOnly(Side.CLIENT)
public class GuiWaterGenerator extends GuiContainer {
    public ContainerWaterGenerator container;
    public TileEntityWaterGenerator tileentity;
    public String progressLabel;
    public String amplifierLabel;

    private static final ResourceLocation tex = new ResourceLocation("k0ras1k", "textures/gui/GUIMatter.png");


    public GuiWaterGenerator(InventoryPlayer player, TileEntityWaterGenerator machine) {
        super(new ContainerWaterGenerator(player.player, machine));
        this.progressLabel = StatCollector.translateToLocal("ic2.Matter.gui.info.progress");
        this.amplifierLabel = StatCollector.translateToLocal("ic2.Matter.gui.info.amplifier");
        this.tileentity = machine;
        this.ySize = ySize;
        this.xSize = xSize;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(this.progressLabel, 8, 22, 4210752);
        super.drawGuiContainerForegroundLayer(par1, par2);
    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(tex);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
    }

    public String getName() {
        return StatCollector.translateToLocal("ic2.Matter.gui.name");
    }
}
