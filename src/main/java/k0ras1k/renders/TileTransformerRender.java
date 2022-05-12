package k0ras1k.renders;

import cpw.mods.fml.client.FMLClientHandler;
import k0ras1k.tiles.TileEntityTransformer;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TileTransformerRender extends TileEntitySpecialRenderer {
    private static final ResourceLocation transfTextloc = new ResourceLocation("k0ras1k", "textures/models/textureMolecularTransformer.png");
    private static final ResourceLocation plazmaTextloc = new ResourceLocation("k0ras1k", "textures/models/plazma.png");
    private static final ResourceLocation particlesTextloc = new ResourceLocation("k0ras1k", "textures/models/particles.png");
    public final ModelTransformer model = new ModelTransformer();
    private static Map textureSizeCache = new HashMap();
    public int ticker;

    public TileTransformerRender() {
    }

    public static int getTextureSize(String s, int dv) {
        if (textureSizeCache.get(Arrays.asList(s, dv)) != null) {
            return (Integer) textureSizeCache.get(Arrays.asList(s, dv));
        } else {
            try {
                InputStream inputstream = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("k0ras1k", s)).getInputStream();
                if (inputstream == null) {
                    throw new Exception("Image not found: " + s);
                } else {
                    BufferedImage bi = ImageIO.read(inputstream);
                    int size = bi.getWidth() / dv;
                    textureSizeCache.put(Arrays.asList(s, dv), size);
                    return size;
                }
            } catch (Exception var5) {
                var5.printStackTrace();
                return 16;
            }
        }
    }

    public void renderCore(TileEntity te, double x, double y, double z, float scale) {
        int size1;
        int size2;
        ++this.ticker;
        if (this.ticker > 161) {
            this.ticker = 1;
        }

        size1 = getTextureSize("textures/models/plazma.png", 64);
        size2 = getTextureSize("textures/models/particles.png", 32);
        float f1 = ActiveRenderInfo.rotationX;
        float f2 = ActiveRenderInfo.rotationXZ;
        float f3 = ActiveRenderInfo.rotationZ;
        float f4 = ActiveRenderInfo.rotationYZ;
        float f5 = ActiveRenderInfo.rotationXY;
        float scaleCore = 0.35F;
        float posX = (float) x + 0.5F;
        float posY = (float) y + 0.5F;
        float posZ = (float) z + 0.5F;
        Tessellator tessellator = Tessellator.instance;
        Color color = new Color(12648447);
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(plazmaTextloc);
        int i = this.ticker % 16;
        float size4 = (float) (size1 * 4);
        float float_sizeMinus0_01 = (float) size1 - 0.01F;
        float float_texNudge = 1.0F / ((float) (size1 * size1) * 2.0F);
        float float_reciprocal = 1.0F / (float) size1;
        float x0 = ((float) (i % 4 * size1) + 0.0F) / size4;
        float x1 = ((float) (i % 4 * size1) + float_sizeMinus0_01) / size4;
        float x2 = ((float) (i / 4 * size1) + 0.0F) / size4;
        float x3 = ((float) (i / 4 * size1) + float_sizeMinus0_01) / size4;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_F((float) color.getRed() / 255.0F, (float) color.getGreen() / 255.0F, (float) color.getBlue() / 255.0F, 1.0F);
        tessellator.addVertexWithUV((double) (posX - f1 * scaleCore - f4 * scaleCore), (double) (posY - f2 * scaleCore), (double) (posZ - f3 * scaleCore - f5 * scaleCore), (double) x1, (double) x3);
        tessellator.addVertexWithUV((double) (posX - f1 * scaleCore + f4 * scaleCore), (double) (posY + f2 * scaleCore), (double) (posZ - f3 * scaleCore + f5 * scaleCore), (double) x1, (double) x2);
        tessellator.addVertexWithUV((double) (posX + f1 * scaleCore + f4 * scaleCore), (double) (posY + f2 * scaleCore), (double) (posZ + f3 * scaleCore + f5 * scaleCore), (double) x0, (double) x2);
        tessellator.addVertexWithUV((double) (posX + f1 * scaleCore - f4 * scaleCore), (double) (posY - f2 * scaleCore), (double) (posZ + f3 * scaleCore - f5 * scaleCore), (double) x0, (double) x3);
        tessellator.draw();
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(particlesTextloc);
        int qq = this.ticker % 16;
        i = 24 + qq;
        float size8 = (float) (size2 * 8);
        float_sizeMinus0_01 = (float) size2 - 0.01F;
        float_texNudge = 1.0F / ((float) (size2 * size2) * 2.0F);
        float_reciprocal = 1.0F / (float) size2;
        x0 = ((float) (i % 8 * size2) + 0.0F) / size8;
        x1 = ((float) (i % 8 * size2) + float_sizeMinus0_01) / size8;
        x2 = ((float) (i / 8 * size2) + 0.0F) / size8;
        x3 = ((float) (i / 8 * size2) + float_sizeMinus0_01) / size8;
        float var11 = MathHelper.sin((float) this.ticker / 10.0F) * 0.1F;
        scaleCore = 0.4F + var11;
        tessellator.startDrawingQuads();
        tessellator.setBrightness(240);
        tessellator.setColorRGBA_F(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.addVertexWithUV((double) (posX - f1 * scaleCore - f4 * scaleCore), (double) (posY - f2 * scaleCore), (double) (posZ - f3 * scaleCore - f5 * scaleCore), (double) x1, (double) x3);
        tessellator.addVertexWithUV((double) (posX - f1 * scaleCore + f4 * scaleCore), (double) (posY + f2 * scaleCore), (double) (posZ - f3 * scaleCore + f5 * scaleCore), (double) x1, (double) x2);
        tessellator.addVertexWithUV((double) (posX + f1 * scaleCore + f4 * scaleCore), (double) (posY + f2 * scaleCore), (double) (posZ + f3 * scaleCore + f5 * scaleCore), (double) x0, (double) x2);
        tessellator.addVertexWithUV((double) (posX + f1 * scaleCore - f4 * scaleCore), (double) (posY - f2 * scaleCore), (double) (posZ + f3 * scaleCore - f5 * scaleCore), (double) x0, (double) x3);
        tessellator.draw();
        GL11.glDisable(3042);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
    }

    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        this.renderTileEntityAt((TileEntityTransformer) te, x, y, z, scale);
    }

    public void renderTileEntityAt(TileEntityTransformer tileTransformer, double x, double y, double z, float scale) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glPushMatrix();
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(transfTextloc);
        this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        if (tileTransformer.isActive()) {
            GL11.glPushMatrix();
            this.renderCore(tileTransformer, x, y, z, scale);
            GL11.glPopMatrix();
        }

    }

    private void rotateBlock(World world, int x, int y, int z, Block block) {
        if (world != null) {
            int dir = world.getBlockMetadata(x, y, z);
            GL11.glPushMatrix();
            GL11.glRotatef((float) (dir * 90), 0.0F, 1.0F, 0.0F);
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(transfTextloc);
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(transfTextloc);
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }

    }
}
