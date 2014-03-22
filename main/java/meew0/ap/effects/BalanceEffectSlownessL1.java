package meew0.ap.effects;

import meew0.ap.backend.IBalanceEffect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * Created by meew0 on 22.03.14.
 */
public class BalanceEffectSlownessL1 implements IBalanceEffect {
    @Override
    public boolean appliesForAbsoluteBalance(int bal) {
        return bal > 7;
    }

    @Override
    public float getProbability(int bal) {
        return 1f; //TODO
    }

    @Override
    public void doEffect(ItemStack potionStack, World world, EntityPlayer player) {
        player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.getId(), 200, 0));
    }

    @Override
    public String getUnlocalizedEffectMessage() {
        return "ap.balanceMessage.slowness.1.name";
    }
}
