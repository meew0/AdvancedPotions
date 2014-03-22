package meew0.ap.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by meew0 on 21.03.14.
 */
public class ItemPotionBottle extends Item {
    public static String[] textureNames = new String[]{
            "potion_bottle_regular",
            "potion_bottle_heart",
            "potion_bottle_vial",
            "potion_bottle_square",
            "potion_bottle_capsule",
            "potion_bottle_reinforced"
    };

    public static IIcon[] textures = new IIcon[6];

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < 6; i++) {
            textures[i] = par1IconRegister.registerIcon("advancedpotions:" + textureNames[i]);
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return textures[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < 6; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "item.potionBottle." + par1ItemStack.getItemDamage();
    }
}
