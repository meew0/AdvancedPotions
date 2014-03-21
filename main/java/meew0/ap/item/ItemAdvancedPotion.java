package meew0.ap.item;

import meew0.ap.AdvancedPotions;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by meew0 on 21.03.14.
 */
public class ItemAdvancedPotion extends Item {
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) --stack.stackSize;


        if (stack.stackSize > 0) return stack;
        return null;
    }
}
