package meew0.ap.effects;

import meew0.ap.backend.IBalanceEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by meew0 on 06.04.14.
 */
public class BalanceEffectOverdoseL1 implements IBalanceEffect {
    @Override
    public boolean appliesForBalance(int bal) {
        return bal > 8;
    }

    @Override
    public float getProbability(int bal) {
        return 0.4f;
    }

    @Override
    public void doEffect(ItemStack potionStack, World world, EntityLivingBase entity) {
        entity.addPotionEffect(new PotionEffect(Potion.poison.getId(), 200, 0));
    }

    @Override
    public String getUnlocalizedEffectMessage() {
        return "ap.balanceMessage.overdose.1.name";
    }
}
