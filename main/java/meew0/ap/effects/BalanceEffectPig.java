package meew0.ap.effects;

import meew0.ap.backend.IBalanceEffect;
import meew0.ap.entity.EntityHostilePig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by meew0 on 22.03.14.
 */
public class BalanceEffectPig implements IBalanceEffect {
    @Override
    public boolean appliesForAbsoluteBalance(int bal) {
        return bal > 100;
    }

    @Override
    public float getProbability(int bal) {
        return 1.0f;
    }

    @Override
    public void doEffect(ItemStack potionStack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            EntityHostilePig entity = new EntityHostilePig(world);
            entity.setPosition(player.posX, player.posY, player.posZ);
            world.spawnEntityInWorld(entity);
        }
    }

    @Override
    public String getUnlocalizedEffectMessage() {
        return "ap.balanceMessage.pig.name";
    }
}
