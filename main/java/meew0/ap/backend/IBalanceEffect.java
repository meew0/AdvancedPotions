package meew0.ap.backend;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by meew0 on 22.03.14.
 */
public interface IBalanceEffect {
    public boolean appliesForBalance(int bal);

    // I had a hard time naming this method. Please don't yell at me for the name being totally wrong.
    public float getProbability(int bal);

    public void doEffect(ItemStack potionStack, World world, EntityLivingBase entity);

    public String getUnlocalizedEffectMessage();
}
