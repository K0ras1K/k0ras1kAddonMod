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
    public ContainerMatter container;
    public String progressLabel;
    public String amplifierLabel;

    private static final ResourceLocation tex = new ResourceLocation("k0ras1k", "textures/gui/GUIMatter.png");



    public GuiWaterGenerator(InventoryPlayer player, TileEntityWaterGenerator machine) {
        super(new ContainerWaterGenerator(player.player, machine));
        this.progressLabel = StatCollector.translateToLocal("ic2.Matter.gui.info.progress");
        this.amplifierLabel = StatCollector.translateToLocal("ic2.Matter.gui.info.amplifier");
        this.ySize = ySize;
        this.xSize = xSize;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(this.progressLabel, 8, 22, 4210752);
        this.fontRendererObj.drawString(((TileEntityMatter) this.container.base).getProgressAsString(), 18, 31, 4210752);
        if (((TileEntityMatter) this.container.base).scrap > 0) {
            this.fontRendererObj.drawString(this.amplifierLabel, 8, 46, 4210752);
            this.fontRendererObj.drawString("" + ((TileEntityMatter) this.container.base).scrap, 8, 58, 4210752);
        }

        super.drawGuiContainerForegroundLayer(par1, par2);
        FluidStack fluidstack = ((TileEntityMatter) this.container.base).getFluidStackfromTank();
        if (fluidstack != null) {
            String tooltip = fluidstack.getFluid().getName() + ": " + fluidstack.amount + StatCollector.translateToLocal("ic2.generic.text.mb");
            GuiTooltipHelper.drawAreaTooltip(par1 - this.guiLeft, par2 - this.guiTop, tooltip, 99, 25, 112, 73);
        }

    }

    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(tex);
        if (((TileEntityMatter) this.container.base).getTankAmount() > 0) {
            IIcon fluidIcon = ((TileEntityMatter) this.container.base).getFluidTank().getFluid().getFluid().getIcon();
            if (fluidIcon != null) {
                this.drawTexturedModalRect(this.xSize + 96, this.ySize + 22, 176, 0, 20, 55);
                this.mc.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
                int liquidHeight = ((TileEntityMatter) this.container.base).gaugeLiquidScaled(47);
                DrawUtil.drawRepeated(fluidIcon, (double) (this.xSize + 100), (double) (this.ySize + 26 + 47 - liquidHeight), 12.0D, (double) liquidHeight, (double) this.zLevel);
                this.mc.renderEngine.bindTexture(this.getResourceLocation());
                this.drawTexturedModalRect(this.xSize + 100, this.ySize + 26, 176, 55, 12, 47);
            }
        }

    }

    public String getName() {
        return StatCollector.translateToLocal("ic2.Matter.gui.name");
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(IC2.textureDomain, "textures/gui/GUIMatter.png");
    }
}
