package meew0.ap.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import meew0.ap.block.BlockResource;
import meew0.ap.te.TileEntityAdvancedBeacon;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;

/**
 * Created by meew0 on 25.05.14.
 */
public class RenderAdvancedBeacon implements ISimpleBlockRenderingHandler {
    public static int renderId;

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
                                    RenderBlocks renderer) {
        float f = 0.1875F;

        renderer.setOverrideBlockTexture(BlockResource.textures[2]);
        renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.renderAllFaces = true;

        renderer.setOverrideBlockTexture(BlockResource.textures[1]);
        renderer.setRenderBounds(0.125D, 0.0062500000931322575D, 0.125D, 0.875D, (double) f, 0.875D);
        renderer.renderStandardBlock(block, x, y, z);

        renderer.setOverrideBlockTexture(renderer.getBlockIcon(Blocks.beacon));
        renderer.setRenderBounds(0.1875D, (double) f, 0.1875D, 0.8125D, 0.875D, 0.8125D);

        if (world.getTileEntity(x, y, z) instanceof TileEntityAdvancedBeacon) {
            TileEntityAdvancedBeacon te = (TileEntityAdvancedBeacon) world.getTileEntity(x, y, z);
            if (te.potionStack != null && te.potionStack.stackTagCompound != null) {
                NBTTagCompound c = te.potionStack.stackTagCompound;
                renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, c.getInteger("red"),
                        c.getInteger("green"), c.getInteger("blue"));
            }
        }

        renderer.renderStandardBlock(block, x, y, z);

        renderer.renderAllFaces = false;
        renderer.clearOverrideBlockTexture();

        return true;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public int getRenderId() {
        return renderId;
    }
}
