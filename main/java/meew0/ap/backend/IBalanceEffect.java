package meew0.ap.backend;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by meew0 on 22.03.14.
 */
public interface IBalanceEffect {
    public boolean appliesForAbsoluteBalance(int bal);

    public void doEffect(ItemStack potionStack, World world, EntityPlayer player);

    public String getEffectName();
}
