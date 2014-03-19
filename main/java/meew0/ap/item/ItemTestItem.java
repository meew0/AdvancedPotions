package meew0.ap.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

/**
 * Created by meew0 on 19.03.14.
 * <p/>
 * This is a test item I need for something. Please ignore this.
 */
public class ItemTestItem extends Item {
    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        itemIcon = iconRegister.registerIcon("advancedpotions:testItem");
    }
}
