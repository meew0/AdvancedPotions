package meew0.ap.item;

import meew0.ap.AdvancedPotions;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

/**
 * Created by meew0 on 22.03.14.
 */
public class ItemPotionIngredient extends Item implements IPlantable {
    public static String[] textureNames = new String[]{
            "potion_water_vial", // TODO: TBR
            "ingredient_corrupted_pickaxe",
            "ingredient_corrupted_sugar",
            "potion_water_vial", // TODO: TBR
            "ingredient_frozen_carrot",
            "ingredient_frozen_tear",
            "ingredient_invisibility_carrot",
            "ingredient_spring",
            "ingredient_mouldy_melon",
            "arcane_stone",
            "arcane_nugget",
            "arcane_metal"
    };

    public static IIcon[] textures = new IIcon[textureNames.length];

    public static boolean isPlantable(int metadata) {
        return metadata == 6 || metadata == 4;
    }

    public static Block getCropBlock(int metadata) {
        return (metadata == 6) ? AdvancedPotions.invisibilityCarrotBlock : AdvancedPotions.frozenCarrotBlock;
    }

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

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {

        if (!isPlantable(par1ItemStack.getItemDamage())) return false;

        if (par7 != 1) {
            return false;
        } else if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack)) {
            if (par3World.getBlock(par4, par5, par6).canSustainPlant(par3World, par4, par5, par6, ForgeDirection.UP, this) && par3World.isAirBlock(par4, par5 + 1, par6)) {
                par3World.setBlock(par4, par5 + 1, par6, getCropBlock(par1ItemStack.getItemDamage()));
                --par1ItemStack.stackSize;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Crop;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return null;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return 0;
    }
}
