package meew0.ap.render;

import meew0.ap.AdvancedPotions;
import meew0.ap.item.ItemAdvancedPotion;
import meew0.ap.item.ItemPotionBottle;
import meew0.ap.util.RenderUtils;
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


    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        IIcon water = ItemAdvancedPotion.texturesWater[item.getItemDamage()];

        if (water == null) AdvancedPotions.debug("NULL!!!");


        int minX = 0, minZ = 0;
        int maxX = 1, maxZ = 1;
        int r = 255, g = 255, b = 255, a = 127;

        if (item.stackTagCompound != null) {
            r = item.stackTagCompound.getInteger("red");
            b = item.stackTagCompound.getInteger("green");
            g = item.stackTagCompound.getInteger("blue");
            a = 127;
        }
        IIcon bottle = ItemPotionBottle.textures[item.getItemDamage()];

        if (type != ItemRenderType.INVENTORY) {
            RenderUtils.renderIconInPlayerHandWithColor(bottle, item.getItemSpriteNumber(), 255, 255, 255, 255, item.hasEffect(0));
            RenderUtils.renderIconInPlayerHandWithColor(water, item.getItemSpriteNumber(), r, g, b, a, item.hasEffect(0));
        } else {
            minX = minZ = 0;
            maxX = maxZ = 16;
            RenderUtils.renderIconWithColor(minX, minZ, bottle, maxX, maxZ, 0, 255, 255, 255, 255, type != ItemRenderType.INVENTORY);
            RenderUtils.renderIconWithColor(minX, minZ, water, maxX, maxZ, 0, r, g, b, a, type != ItemRenderType.INVENTORY);
        }
    }
}
