package meew0.ap.effects;

import meew0.ap.backend.IBalanceEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by meew0 on 05.04.14.
 */
public class BalanceEffectExplosion implements IBalanceEffect {

    @Override
    public boolean appliesForBalance(int bal) {
        return bal > 50;
    }

    @Override
    public float getProbability(int bal) {
        return 0;
    }

    @Override
    public void doEffect(ItemStack potionStack, World world, EntityLivingBase entity) {

    }

    @Override
    public String getUnlocalizedEffectMessage() {
        return null;
    }
}
