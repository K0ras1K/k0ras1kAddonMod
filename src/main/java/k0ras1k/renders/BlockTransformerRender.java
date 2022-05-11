package k0ras1k.renders;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import k0ras1k.Main;
import k0ras1k.tiles.TileEntityTransformer;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;

public class BlockTransformerRender implements ISimpleBlockRenderingHandler {
    public BlockTransformerRender() {
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        TileEntityRendererDispatcher.instance.renderTileEntityAt(new TileEntityTransformer(), 0.0D, 0.0D, 0.0D, 0.0F);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public int getRenderId() {
        return Main.blockTransformerRenderID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

}
