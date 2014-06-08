package meew0.ap.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import meew0.ap.AdvancedPotions;
import meew0.ap.backend.Color;
import meew0.ap.block.BlockAdvancedCauldron;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

// thanks to whoever made the tile entity rendering tutorial, much appreciated!
// :P

public class RenderTEAdvancedCauldron implements ISimpleBlockRenderingHandler {
    public static int renderId;

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {

    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        // mostly copied from RenderBlocks.renderCauldron

        AdvancedPotions.debug("Rendering AC...");

        if (block instanceof BlockAdvancedCauldron) {
            BlockAdvancedCauldron cauldron = (BlockAdvancedCauldron) block;

            renderer.renderStandardBlock(cauldron, x, y, z);
            Tessellator tessellator = Tessellator.instance;
            tessellator.setBrightness(cauldron.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
            int l = cauldron.colorMultiplier(renderer.blockAccess, x, y, z);
            float f = (float) (l >> 16 & 255) / 255.0F;
            float f1 = (float) (l >> 8 & 255) / 255.0F;
            float f2 = (float) (l & 255) / 255.0F;
            float f4;

            if (EntityRenderer.anaglyphEnable) {
                float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
                f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
                float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
                f = f3;
                f1 = f4;
                f2 = f5;
            }

            tessellator.setColorOpaque_F(f, f1, f2);
            IIcon iicon1 = cauldron.getBlockTextureFromSide(2);
            f4 = 0.125F;
            renderer.renderFaceXPos(cauldron, (double) ((float) x - 1.0F + f4), (double) y, (double) z, iicon1);
            renderer.renderFaceXNeg(cauldron, (double) ((float) x + 1.0F - f4), (double) y, (double) z, iicon1);
            renderer.renderFaceZPos(cauldron, (double) x, (double) y, (double) ((float) z - 1.0F + f4), iicon1);
            renderer.renderFaceZNeg(cauldron, (double) x, (double) y, (double) ((float) z + 1.0F - f4), iicon1);
            IIcon iicon2 = BlockAdvancedCauldron.innerIcon;
            renderer.renderFaceYPos(cauldron, (double) x, (double) ((float) y - 1.0F + 0.25F), (double) z, iicon2);
            renderer.renderFaceYNeg(cauldron, (double) x, (double) ((float) y + 1.0F - 0.75F), (double) z, iicon2);

            TileEntity te = world.getTileEntity(x, y, z);
            if (te instanceof TileEntityAdvancedCauldron) {
                TileEntityAdvancedCauldron te2 = (TileEntityAdvancedCauldron) te;
                int i1 = te2.getWaterLevel();
                if (i1 > 0) {
                    IIcon iicon = BlockAdvancedCauldron.potionIcon;
                    Color c = te2.color;
                    tessellator.setColorOpaque(c.getRed(), c.getGreen(), c.getBlue());
                    renderer.renderFaceYPos(cauldron, (double) x, (double) ((float) y - 1.0F + BlockAdvancedCauldron.getRenderLiquidLevel(i1)), (double) z, iicon);
                }
            }
            AdvancedPotions.debug("Rendering finished");
            return true;
        }

        return false;
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
