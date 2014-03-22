package meew0.ap.item;

import meew0.ap.AdvancedPotions;
import meew0.ap.backend.EffectWrapper;
import meew0.ap.backend.IPotionEffectContainer;
import meew0.ap.backend.PotionRegistry;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meew0 on 21.03.14.
 */
public class ItemAdvancedPotion extends Item {
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) --stack.stackSize;

        if (stack.stackTagCompound != null) {
            ArrayList<EffectWrapper> effects = new ArrayList<EffectWrapper>();
            effects.addAll(TileEntityAdvancedCauldron.getEffectWrappersForTileNBT(stack.stackTagCompound));
            for (EffectWrapper effect : effects) {
                AdvancedPotions.debug("Effect applied " + effect.id + " " + effect.duration + " " + effect.amplifier);
                effect.getEffect().onApply(player);
            }

            //TODO balance effects
        }

        if (stack.stackSize > 0) return stack;
        return null;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.drink;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        // TODO
        return Items.potionitem.getMaxItemUseDuration(stack);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        return stack;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        if (stack.stackTagCompound != null) {

            list.add(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.GRAY.toString() + "Balance: " + stack.stackTagCompound.getInteger("bal"));
            list.add(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.GRAY.toString() + "Balance modifier: " + stack.stackTagCompound.getInteger("balmod"));
            list.add(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.GRAY.toString() + "Effects: ");

            ArrayList<EffectWrapper> effects = new ArrayList<EffectWrapper>();
            effects.addAll(TileEntityAdvancedCauldron.getEffectWrappersForTileNBT(stack.stackTagCompound));
            for (EffectWrapper effect : effects) {
                IPotionEffectContainer e = effect.getEffect();
                list.add(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.BLUE + e.effectPrefix() + StatCollector.translateToLocal(e.effectName()) +
                        ((e.displayAmplifier()) ? (" " + PotionRegistry.getRomanNumeral(e.getAmplifierForDisplay())) : "") + " (" +
                        ((e.displayDuration()) ? ("" + PotionRegistry.getReadableDuration(e.getDurationForDisplay())) : "") + ")");
            }
        }
    }
}
