package meew0.ap.effects;

import meew0.ap.backend.IBalanceEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by meew0 on 06.04.14.
 */
public class BalanceEffectTeleportL1 implements IBalanceEffect {
    EffectEntityTeleport effect;

    public BalanceEffectTeleportL1() {
        effect = new EffectEntityTeleport(getLevel());
    }

    protected int getLevel() {
        return 3;
    }

    @Override
    public boolean appliesForBalance(int bal) {
        return bal > 18;
    }

    @Override
    public float getProbability(int bal) {
        return .05f;
    }

    @Override
    public void doEffect(ItemStack potionStack, World world, EntityLivingBase entity) {
        effect.onApply(entity);
    }

    @Override
    public String getUnlocalizedEffectMessage() {
        return "ap.balanceMessage.teleport.1.name";
    }
}
