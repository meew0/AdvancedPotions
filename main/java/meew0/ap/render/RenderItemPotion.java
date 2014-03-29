package meew0.ap.render;

import meew0.ap.item.ItemAdvancedPotion;
import meew0.ap.item.ItemPotionBottle;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;

/**
 * Created by meew0 on 22.03.14.
 */
public class RenderItemPotion implements IItemRenderer {
    private static RenderItem renderItem = new RenderItem();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type != ItemRenderType.ENTITY;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    public void renderIcon(int par1, int par2, IIcon par3Icon, int par4, int par5, float zLevel, int r, int g, int b, int a, boolean flipped) {
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

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        IIcon water = ItemAdvancedPotion.textures[item.getItemDamage()];

        int minX = 0, minZ = 0;
        int maxX = 1, maxZ = 1;
        int r = 255, g = 255, b = 255, a = 127;
        //int normalZ = 1;

        if (item.stackTagCompound != null) {
            r = item.stackTagCompound.getInteger("red");
            b = item.stackTagCompound.getInteger("green");
            g = item.stackTagCompound.getInteger("blue");
            a = 127;
        }
        IIcon bottle = ItemPotionBottle.textures[item.getItemDamage()];

        //Tessellator.instance.setNormal(0, 0, normalZ);
        //


        //Tessellator.instance.setNormal(0, 0, normalZ);
        //

        if (type == ItemRenderType.INVENTORY) {
            minX = minZ = 0;
            maxX = maxZ = 16;
        }

        renderIcon(minX, minZ, bottle, maxX, maxZ, 0, 255, 255, 255, 255, type != ItemRenderType.INVENTORY);
        renderIcon(minX, minZ, water, maxX, maxZ, 0, r, g, b, a, type != ItemRenderType.INVENTORY);
    }
}
