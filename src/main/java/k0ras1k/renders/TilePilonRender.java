package k0ras1k.renders;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import k0ras1k.tiles.TileEntityPiedestal;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

public class TilePilonRender extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler {
    private static ResourceLocation texture;
    private static ResourceLocation objModelLocation;
    private static IModelCustom model;
    private final RenderItem customRenderItem;

    public static int renderId = RenderingRegistry.getNextAvailableRenderId();

    public TilePilonRender() {

        texture = new ResourceLocation("k0ras1k", "textures/models/pilon.png");
        objModelLocation = new ResourceLocation("k0ras1k", "textures/models/pilon.obj");
        model = AdvancedModelLoader.loadModel(objModelLocation);
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPiedestal.class, this);
        RenderingRegistry.registerBlockHandler(this);
        customRenderItem = new RenderItem() {
            @Override
            public boolean shouldBob() {
                return false;
            }
        };
        customRenderItem.setRenderManager(RenderManager.instance);

    }

    @Override
    public void renderTileEntityAt(TileEntity te, double posX, double posY, double posZ, float timeSinceLastTick) {
        TileEntityPiedestal tileAltar = (TileEntityPiedestal) te;
        bindTexture(texture);

        GL11.glPushMatrix();
        GL11.glTranslated(posX + 0.5, posY + 0.5, posZ + 0.5);
        GL11.glPushMatrix();
        model.renderAll();
        GL11.glPopMatrix();
        GL11.glPopMatrix();

        if (tileAltar.getStackInSlot(0) != null) {
            float scaleFactor = getGhostItemScaleFactor(tileAltar.getStackInSlot(0));
            float rotationAngle = Minecraft.isFancyGraphicsEnabled() ? (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) : 0;

            EntityItem ghostEntityItem = new EntityItem(tileAltar.getWorldObj());
            ghostEntityItem.hoverStart = 0.0F;
            ghostEntityItem.setEntityItemStack(tileAltar.getStackInSlot(0));
            float displacement = 0.3F;

            if (ghostEntityItem.getEntityItem().getItem() instanceof ItemBlock) {
                GL11.glTranslatef((float) posX + 0.5F, (float) posY + displacement + 0.7F, (float) posZ + 0.5F);
            } else {
                GL11.glTranslatef((float) posX + 0.5F, (float) posY + displacement + 0.6F, (float) posZ + 0.5F);
            }
            GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
            GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);
            customRenderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
        }

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

    private float getGhostItemScaleFactor(ItemStack itemStack) {
        float scaleFactor = 1.0F;

        if (itemStack != null) {
            if (itemStack.getItem() instanceof ItemBlock) {
                switch (customRenderItem.getMiniBlockCount(itemStack, (byte) 1)) {
                    case 1:
                        return 0.90F;

                    case 2:
                        return 0.90F;

                    case 3:
                        return 0.90F;

                    case 4:
                        return 0.90F;

                    case 5:
                        return 0.80F;

                    default:
                        return 0.90F;
                }
            } else {
                switch (customRenderItem.getMiniItemCount(itemStack, (byte) 1)) {
                    case 1:
                        return 0.65F;

                    case 2:
                        return 0.65F;

                    case 3:
                        return 0.65F;

                    case 4:
                        return 0.65F;

                    default:
                        return 0.65F;
                }
            }
        }

        return scaleFactor;
    }

}
