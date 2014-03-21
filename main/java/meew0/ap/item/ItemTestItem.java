package meew0.ap.item;

import meew0.ap.AdvancedPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

/**
 * Created by meew0 on 19.03.14.
 * <p/>
 * This is a test item I need for something. Please ignore this.
 */
public class ItemTestItem extends Item {
    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        itemIcon = par1IconRegister.registerIcon("advancedpotions:testItem");
    }

    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        AdvancedPotions.debug("item use");
        if (par1ItemStack.getItemDamage() > 0) {
            par2EntityPlayer.addChatComponentMessage(new ChatComponentText("Cooldown is still active!"));
            return false;
        }
        //par1ItemStack.setItemDamage(600);

        par1ItemStack.damageItem(600, par2EntityPlayer);
        AdvancedPotions.debug("item used" + par1ItemStack.getItemDamage());

        return true;
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        AdvancedPotions.debug("before " + par1ItemStack.getItemDamage());
        if (par1ItemStack.getItemDamage() > 0) par1ItemStack.setItemDamage(par1ItemStack.getItemDamage() - 1);
        AdvancedPotions.debug("after " + par1ItemStack.getItemDamage());
    }
}
