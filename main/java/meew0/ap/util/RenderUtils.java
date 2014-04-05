package meew0.ap.util;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import static net.minecraft.client.Minecraft.getMinecraft;
import static net.minecraft.client.Minecraft.getSystemTime;

/**
 * Created by meew0 on 29.03.14.
 */
public class RenderUtils {
    public static final ResourceLocation GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public static void renderIconWithColor(int par1, int par2, IIcon par3Icon, int par4, int par5, float zLevel, int r, int g, int b, int a, boolean flipped) {
        float u2 = (flipped) ? par3Icon.getMinU() : par3Icon.getMaxU(), u1 = (flipped) ? par3Icon.getMaxU() : par3Icon.getMinU();
        float v2 = (flipped) ? par3Icon.getMinV() : par3Icon.getMaxV(), v1 = (flipped) ? par3Icon.getMaxV() : par3Icon.getMinV();
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(r, g, b, a);
        tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + par5), (double) zLevel, (double) u1, (double) v2);
        tessellator.addVertexWithUV((double) (par1 + par4), (double) (par2 + par5), (double) zLevel, (double) u2, (double) v2);
        tessellator.addVertexWithUV((double) (par1 + par4), (double) (par2 + 0), (double) zLevel, (double) u2, (double) v1);
        tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + 0), (double) zLevel, (double) u1, (double) v1);
        tessellator.draw();
    }

    public static void renderIconIn2DWithColor(Tessellator par0Tessellator, IIcon icon, float depth, boolean flipped, int r, int g, int b, int a) {
        float u2 = (flipped) ? icon.getMinU() : icon.getMaxU(), u1 = (flipped) ? icon.getMaxU() : icon.getMinU();
        float v2 = (flipped) ? icon.getMinV() : icon.getMaxV(), v1 = (flipped) ? icon.getMaxV() : icon.getMinV();
        int par5 = icon.getIconWidth(), par6 = icon.getIconHeight();

        renderIconIn2DWithColor(par0Tessellator, u1, u2, v1, v2, par5, par6, depth, r, g, b, a);
    }

    public static void renderIconIn2DWithColor(Tessellator par0Tessellator, float u1, float u2, float v1, float v2, int width, int height, float depth, int r, int b, int g, int a) {
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 0.0F, 1.0F);
        par0Tessellator.setColorRGBA(r, g, b, a);
        par0Tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, (double) u1, (double) v2);
        par0Tessellator.addVertexWithUV(1.0D, 0.0D, 0.0D, (double) u2, (double) v2);
        par0Tessellator.addVertexWithUV(1.0D, 1.0D, 0.0D, (double) u2, (double) v1);
        par0Tessellator.addVertexWithUV(0.0D, 1.0D, 0.0D, (double) u1, (double) v1);
        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 0.0F, -1.0F);
        par0Tessellator.setColorRGBA(r, g, b, a);
        par0Tessellator.addVertexWithUV(0.0D, 1.0D, (double) (0.0F - depth), (double) u1, (double) v1);
        par0Tessellator.addVertexWithUV(1.0D, 1.0D, (double) (0.0F - depth), (double) u2, (double) v1);
        par0Tessellator.addVertexWithUV(1.0D, 0.0D, (double) (0.0F - depth), (double) u2, (double) v2);
        par0Tessellator.addVertexWithUV(0.0D, 0.0D, (double) (0.0F - depth), (double) u1, (double) v2);
        par0Tessellator.draw();
        float f5 = 0.5F * (u1 - u2) / (float) width;
        float f6 = 0.5F * (v2 - v1) / (float) height;
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        par0Tessellator.setColorRGBA(r, g, b, a);
        int k;
        float f7;
        float f8;

        for (k = 0; k < width; ++k) {
            f7 = (float) k / (float) width;
            f8 = u1 + (u2 - u1) * f7 - f5;
            par0Tessellator.addVertexWithUV((double) f7, 0.0D, (double) (0.0F - depth), (double) f8, (double) v2);
            par0Tessellator.addVertexWithUV((double) f7, 0.0D, 0.0D, (double) f8, (double) v2);
            par0Tessellator.addVertexWithUV((double) f7, 1.0D, 0.0D, (double) f8, (double) v1);
            par0Tessellator.addVertexWithUV((double) f7, 1.0D, (double) (0.0F - depth), (double) f8, (double) v1);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(1.0F, 0.0F, 0.0F);
        par0Tessellator.setColorRGBA(r, g, b, a);
        float f9;

        for (k = 0; k < width; ++k) {
            f7 = (float) k / (float) width;
            f8 = u1 + (u2 - u1) * f7 - f5;
            f9 = f7 + 1.0F / (float) width;
            par0Tessellator.addVertexWithUV((double) f9, 1.0D, (double) (0.0F - depth), (double) f8, (double) v1);
            par0Tessellator.addVertexWithUV((double) f9, 1.0D, 0.0D, (double) f8, (double) v1);
            par0Tessellator.addVertexWithUV((double) f9, 0.0D, 0.0D, (double) f8, (double) v2);
            par0Tessellator.addVertexWithUV((double) f9, 0.0D, (double) (0.0F - depth), (double) f8, (double) v2);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, 1.0F, 0.0F);
        par0Tessellator.setColorRGBA(r, g, b, a);

        for (k = 0; k < height; ++k) {
            f7 = (float) k / (float) height;
            f8 = v2 + (v1 - v2) * f7 - f6;
            f9 = f7 + 1.0F / (float) height;
            par0Tessellator.addVertexWithUV(0.0D, (double) f9, 0.0D, (double) u1, (double) f8);
            par0Tessellator.addVertexWithUV(1.0D, (double) f9, 0.0D, (double) u2, (double) f8);
            par0Tessellator.addVertexWithUV(1.0D, (double) f9, (double) (0.0F - depth), (double) u2, (double) f8);
            par0Tessellator.addVertexWithUV(0.0D, (double) f9, (double) (0.0F - depth), (double) u1, (double) f8);
        }

        par0Tessellator.draw();
        par0Tessellator.startDrawingQuads();
        par0Tessellator.setNormal(0.0F, -1.0F, 0.0F);
        par0Tessellator.setColorRGBA(r, g, b, a);

        for (k = 0; k < height; ++k) {
            f7 = (float) k / (float) height;
            f8 = v2 + (v1 - v2) * f7 - f6;
            par0Tessellator.addVertexWithUV(1.0D, (double) f7, 0.0D, (double) u2, (double) f8);
            par0Tessellator.addVertexWithUV(0.0D, (double) f7, 0.0D, (double) u1, (double) f8);
            par0Tessellator.addVertexWithUV(0.0D, (double) f7, (double) (0.0F - depth), (double) u1, (double) f8);
            par0Tessellator.addVertexWithUV(1.0D, (double) f7, (double) (0.0F - depth), (double) u2, (double) f8);
        }

        par0Tessellator.draw();
    }

    // From RenderSnowball
    public static void renderIconTowardsPlayer(Tessellator tessellator, IIcon icon, RenderManager manager) {
        float f = icon.getMinU();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMaxV();
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        GL11.glRotatef(180.0F - manager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-manager.playerViewX, 1.0F, 0.0F, 0.0F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV((double) (0.0F - f5), (double) (0.0F - f6), 0.0D, (double) f, (double) f3);
        tessellator.addVertexWithUV((double) (f4 - f5), (double) (0.0F - f6), 0.0D, (double) f1, (double) f3);
        tessellator.addVertexWithUV((double) (f4 - f5), (double) (f4 - f6), 0.0D, (double) f1, (double) f2);
        tessellator.addVertexWithUV((double) (0.0F - f5), (double) (f4 - f6), 0.0D, (double) f, (double) f2);
        tessellator.draw();
    }

    public static void renderIconInPlayerHandWithColor(IIcon icon, int itemSpriteNumber, int r, int g, int b, int a, boolean glint) {

        GL11.glPushMatrix();

        if (icon == null) {
            GL11.glPopMatrix();
            return;
        }


        getMinecraft().getTextureManager().bindTexture(getMinecraft().getTextureManager().
                getResourceLocation(itemSpriteNumber));
        TextureUtil.func_147950_a(false, false);
        Tessellator tessellator = Tessellator.instance;
//        float f = iicon.getMinU();
//        float f1 = iicon.getMaxU();
//        float f2 = iicon.getMinV();
//        float f3 = iicon.getMaxV();
        float f4 = 0.0F;
        float f5 = 0.3F;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
//        GL11.glTranslatef(-f4, -f5, 0.0F);
        float f6 = 1.5F;
//        GL11.glScalef(f6, f6, f6);
//        GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
//        GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
////        GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
//        GL11.glTranslatef(-0.6375f, -0.0625F, 0.0F);
        //renderItemIn2D(tessellator, f1, f2, f, f3, iicon.getIconWidth(), iicon.getIconHeight(), 0.0625F);
        renderIconIn2DWithColor(tessellator, icon, 0.0625f, false, r, g, b, a);

        if (glint) {
            GL11.glDepthFunc(GL11.GL_EQUAL);
            GL11.glDisable(GL11.GL_LIGHTING);
            getMinecraft().getTextureManager().bindTexture(GLINT);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(768, 1, 1, 0);
            float f7 = 0.76F;
            GL11.glColor4f(0.5F * f7, 0.25F * f7, 0.8F * f7, 1.0F);
            GL11.glMatrixMode(GL11.GL_TEXTURE);
            GL11.glPushMatrix();
            float f8 = 0.125F;
            GL11.glScalef(f8, f8, f8);
            float f9 = (float) (getSystemTime() % 3000L) / 3000.0F * 8.0F;
            GL11.glTranslatef(f9, 0.0F, 0.0F);
            GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
            renderIconIn2DWithColor(tessellator, 0.0f, 0.0f, 1.0f, 1.0f, 256, 256, 0.0625f, r, g, b, a);
//            renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(f8, f8, f8);
            f9 = (float) (getSystemTime() % 4873L) / 4873.0F * 8.0F;
            GL11.glTranslatef(-f9, 0.0F, 0.0F);
            GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
            renderIconIn2DWithColor(tessellator, 0.0f, 0.0f, 1.0f, 1.0f, 256, 256, 0.0625f, r, g, b, a);
//            renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 256, 256, 0.0625F);
            GL11.glPopMatrix();
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDepthFunc(GL11.GL_LEQUAL);
        }

        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        getMinecraft().getTextureManager().bindTexture(getMinecraft().getTextureManager().
                getResourceLocation(itemSpriteNumber));
        TextureUtil.func_147945_b();

        GL11.glPopMatrix();
    }
}
