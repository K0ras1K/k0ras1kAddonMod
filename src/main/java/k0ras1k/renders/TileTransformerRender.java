package k0ras1k.renders;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import k0ras1k.tiles.TileEntityTransformer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

public class TileTransformerRender extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
    private static ResourceLocation texture;
    private static  ResourceLocation objModelLocation;
    private static IModelCustom model;

    public static int renderId = RenderingRegistry.getNextAvailableRenderId();

    public TileTransformerRender() {

        texture = new ResourceLocation("k0ras1k", "textures/models/pilon.png");
        objModelLocation = new ResourceLocation("k0ras1k", "textures/models/pilon.obj");
        model = AdvancedModelLoader.loadModel(objModelLocation);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTransformer.class, this);
        RenderingRegistry.registerBlockHandler(this);

    }

    @Override
    public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float timeSinceLastTick) {
        TileEntityTransformer te2 = (TileEntityTransformer) te;
        bindTexture(texture);

        GL11.glPushMatrix();
        GL11.glTranslated(posX + 0.5, posY + 0.5, posZ + 0.5);
        GL11.glPushMatrix();
        model.renderAll();
        GL11.glPopMatrix();
        GL11.glPopMatrix();

    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        bindTexture(texture);
        GL11.glPushMatrix();
        GL11.glPushMatrix();
        model.renderAll();
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }
}
