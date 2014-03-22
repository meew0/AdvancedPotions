package meew0.ap.item;

import meew0.ap.AdvancedPotions;
import meew0.ap.backend.EffectWrapper;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

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
}
