package meew0.ap.item;

import meew0.ap.backend.EffectWrapper;
import meew0.ap.backend.IPotionEffectContainer;
import meew0.ap.backend.PotionRegistry;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * Created by meew0 on 21.03.14.
 */
public class ItemPotionAnalyzer extends Item {
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int m, float dx, float dy, float dz) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityAdvancedCauldron) {
            TileEntityAdvancedCauldron te2 = (TileEntityAdvancedCauldron) te;
            player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.GRAY.toString() + "Balance: " + te2.balance));
            player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.GRAY.toString() + "Balance modifier: " + te2.balMod));
            player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.GRAY.toString() + "Effects: "));
            for (EffectWrapper effect : te2.effects) {
                IPotionEffectContainer e = effect.getEffect();
                player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.BLUE + e.effectPrefix() + e.effectName() +
                        ((e.displayAmplifier()) ? (" " + PotionRegistry.getRomanNumeral(e.getAmplifierForDisplay())) : "") + "(" +
                        ((e.displayDuration()) ? (" " + PotionRegistry.getReadableDuration(e.getDurationForDisplay())) : "") + ")"));
            }
            return true;
        }
        return false;
    }
}
