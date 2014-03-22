package meew0.ap.effects;

import meew0.ap.backend.IBalanceEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by meew0 on 22.03.14.
 */
public class BalanceEffectNull implements IBalanceEffect {
    @Override
    public boolean appliesForAbsoluteBalance(int bal) {
        return true;
    }

    @Override
    public void doEffect(ItemStack potionStack, World world, EntityPlayer player) {
        // nothing
    }

    @Override
    public String getUnlocalizedEffectMessage() {
        return "ap.balanceMessage.null.name";
    }
}
