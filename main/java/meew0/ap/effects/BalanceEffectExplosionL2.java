package meew0.ap.effects;

import meew0.ap.backend.IBalanceEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by meew0 on 05.04.14.
 */
public class BalanceEffectExplosionL2 implements IBalanceEffect {

    @Override
    public boolean appliesForBalance(int bal) {
        return bal > 50;
    }

    @Override
    public float getProbability(int bal) {
        return .2f;
    }

    @Override
    public void doEffect(ItemStack potionStack, World world, EntityLivingBase entity) {
        world.createExplosion(entity, entity.posX, entity.posY, entity.posZ, 4.0f, true);
    }

    @Override
    public String getUnlocalizedEffectMessage() {
        return "ap.balanceMessage.explosion.2.name";
    }
}
