package meew0.ap.render;

import meew0.ap.block.BlockAdvancedCauldron;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

// thanks to whoever made the tile entity rendering tutorial, much appreciated!
// :P

public class RenderTEAdvancedCauldron extends TileEntitySpecialRenderer {
    public static int renderId;

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

        double dy = BlockAdvancedCauldron.getRenderLiquidLevel(te.waterLevel);
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
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha());
        tessellator.addVertexWithUV(0.0, par5, (double) zLevel, (double) par3Icon.getMinU(), (double) par3Icon.getMaxV());
        tessellator.addVertexWithUV(1.0, par5, (double) zLevel, (double) par3Icon.getMaxU(), (double) par3Icon.getMaxV());
        tessellator.addVertexWithUV(1.0, par5, (double) zLevel - 1.0, (double) par3Icon.getMaxU(), (double) par3Icon.getMinV());
        tessellator.addVertexWithUV(0.0, par5, (double) zLevel - 1.0, (double) par3Icon.getMinU(), (double) par3Icon.getMinV());
        tessellator.draw();


        GL11.glPopMatrix();
    }

}
