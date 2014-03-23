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
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        IIcon water = ItemAdvancedPotion.textures[item.getItemDamage()];

        int minX = 0, minZ = 0;
        int maxX = 1, maxZ = 1;
        //int normalZ = 1;

        if (type == ItemRenderType.INVENTORY) {
            minX = minZ = 0;
            maxX = maxZ = 16;
            //normalZ = -1;
        }

        if (item.stackTagCompound != null) {
            Tessellator.instance.setColorRGBA(item.stackTagCompound.getInteger("red"), item.stackTagCompound.getInteger("green"), item.stackTagCompound.getInteger("blue"), 127);
        }
        //Tessellator.instance.setNormal(0, 0, normalZ);
        renderItem.renderIcon(minX, minZ, water, maxX, maxZ);

        IIcon bottle = ItemPotionBottle.textures[item.getItemDamage()];
        //Tessellator.instance.setNormal(0, 0, normalZ);
        renderItem.renderIcon(minX, minZ, bottle, maxX, maxZ);
    }
}
