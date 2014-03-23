package meew0.ap.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by meew0 on 22.03.14.
 */
public class ItemPotionIngredient extends Item {
    public static String[] textureNames = new String[]{
            "potion_water_vial",
            "ingredient_corrupted_pickaxe",
            "ingredient_corrupted_sugar",
            "potion_water_vial",
            "ingredient_frozen_carrot",
            "ingredient_frozen_tear",
            "ingredient_invisibility_carrot",
            "ingredient_spring",
            "ingredient_mouldy_melon",
            "arcane_stone",
            "arcane_nugget",
            "arcane_ingot"
    };

    public static IIcon[] textures = new IIcon[textureNames.length];

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < textures.length; i++) {
            textures[i] = par1IconRegister.registerIcon("advancedpotions:" + textureNames[i]);
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return textures[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < textures.length; i++) {
            if (i != 0 && i != 3) list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "item.ingredient." + par1ItemStack.getItemDamage();
    }
}
