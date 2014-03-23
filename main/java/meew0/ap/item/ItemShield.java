package meew0.ap.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by meew0 on 23.03.14.
 */
public class ItemShield extends Item {
    public static String[] textureNames = new String[]{
            "ingredient_arcane_shield",
            "ingredient_force_field"
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
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int x, boolean y) {
        if (entity instanceof EntityLivingBase && ((EntityLivingBase) entity).getHeldItem() == stack) {
            ((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.resistance.getId(), 2,
                    (stack.getItemDamage() > 0) ? 3 : 1));
            if (stack.getItemDamage() < 1) ((EntityLivingBase) entity).addPotionEffect(
                    new PotionEffect(Potion.moveSlowdown.getId(), 2, 0));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return "item.shield." + par1ItemStack.getItemDamage();
    }


}
