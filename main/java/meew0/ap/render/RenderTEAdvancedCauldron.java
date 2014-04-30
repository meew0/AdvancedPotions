package meew0.ap.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import meew0.ap.AdvancedPotions;
import meew0.ap.block.BlockAdvancedCauldron;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
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

    /*public static RenderBlocks rb;

    public RenderTEAdvancedCauldron() {
    }

    // This method is called when minecraft renders a tile entity
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x, (float) y, (float) z);
        TileEntityAdvancedCauldron te2 = (TileEntityAdvancedCauldron) te;
        renderCauldron(te2, te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord, te.blockType);

        GL11.glPopMatrix();
    }

    // And this method actually renders your tile entity
    public void renderCauldron(TileEntityAdvancedCauldron te, World world, int x, int y, int z, Block block) {
//        Tessellator tess = Tessellator.instance;
//        float f = block.getLightValue(world, x, y, z);
//        int l = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
//        int l1 = l % 65536;
//        int l2 = l / 65536;
//        tess.setColorOpaque_F(f, f, f);
//        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) l1, (float) l2);
//
//        int dir = world.getBlockMetadata(x, y, z);
//
        GL11.glPushMatrix();
//        GL11.glTranslatef(0.5F, 0, 0.5F);
//        GL11.glRotatef(dir * (-90F), 0F, 1F, 0F);
//        GL11.glTranslatef(-0.5F, 0, -0.5F);

        //bindTexture(new ResourceLocation("advancedpotions:textures/blocks/potion_base.png"));

        double dy = BlockAdvancedCauldron.getRenderLiquidLevel(te.getWaterLevel());
        Color c = te.color;

//        tess.startDrawingQuads();
//        tess.setNormal(0, 1, 0);
//        tess.setColorRGBA(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
//        tess.addVertex(0, dy, 0);
//        tess.addVertex(0, dy, 1);
//        tess.addVertex(1, dy, 1);
//        tess.addVertex(1, dy, 0);
//        tess.draw();
        int par1 = 0;
        int par2 = 0;
        int par4 = 1;
        double par5 = dy;
        int zLevel = 1;
        IIcon par3Icon = BlockAdvancedCauldron.potionIcon;
//        RenderBlocks rb = new RenderBlocks(Minecraft.getMinecraft().theWorld);
//
//        rb.renderFaceYPos(block, (double) x, (double) ((float) y - 1.0F + dy), (double) z, par3Icon);

        //rb.renderFaceYPos(block, (double) x, (double) ((float) y - 1.0f + dy), (double) z, par3Icon);


        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());

/*        tessellator.addVertexWithUV(0.0, par5, (double) zLevel, (double) par3Icon.getMinU(), (double) par3Icon.getMaxV());
        tessellator.addVertexWithUV(1.0, par5, (double) zLevel, (double) par3Icon.getMaxU(), (double) par3Icon.getMaxV());
        tessellator.addVertexWithUV(1.0, par5, (double) zLevel - 1.0, (double) par3Icon.getMaxU(), (double) par3Icon.getMinV());
        tessellator.addVertexWithUV(0.0, par5, (double) zLevel - 1.0, (double) par3Icon.getMinU(), (double) par3Icon.getMinV());

        tessellator.setNormal(0.0f, 1.0f, 0.0f);
        tessellator.addVertex(0, dy, 0);
        tessellator.addVertex(0, dy, 0 + 1.0f);
        tessellator.addVertex(0 + 1.0f, dy, 0 + 1.0f);
        tessellator.addVertex(0 + 1.0f, dy, 0);

        tessellator.draw();



        GL11.glPopMatrix();
    }

    @Override
    public void func_147496_a(World world) {
        rb = new RenderBlocks(world);
    }*/

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
                    IIcon iicon = BlockLiquid.getLiquidIcon("water_still");
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
