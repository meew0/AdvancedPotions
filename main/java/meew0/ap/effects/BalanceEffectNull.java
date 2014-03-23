package meew0.ap.effects;

import meew0.ap.backend.IBalanceEffect;
import net.minecraft.entity.EntityLivingBase;
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
    public float getProbability(int bal) {
        return 0f;
    }

    @Override
    public void doEffect(ItemStack potionStack, World world, EntityLivingBase player) {
        // nothing
    }

    @Override
    public String getUnlocalizedEffectMessage() {
        return "ap.balanceMessage.null.name";
    }
}
