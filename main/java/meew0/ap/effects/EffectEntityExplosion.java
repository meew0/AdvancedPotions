package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * Created by meew0 on 24.03.14.
 */
public class EffectEntityExplosion implements IPotionEffectContainer {

    int a;

    public EffectEntityExplosion(int amplifier) {
        a = amplifier;
    }

    @Override
    public void onApply(EntityLivingBase player) {
        if (!player.worldObj.isRemote) {
            player.worldObj.newExplosion(player, player.posX, player.posY, player.posZ, a * 2.0f, false, true);
        }
    }

    @Override
    public void onSplash(World world, int x, int y, int z) {

    }

    @Override
    public boolean displayDuration() {
        return false;
    }

    @Override
    public boolean displayAmplifier() {
        return true;
    }

    @Override
    public int getDurationForDisplay() {
        return 0;
    }

    @Override
    public int getAmplifierForDisplay() {
        return a;
    }

    @Override
    public String effectPrefix() {
        return EnumChatFormatting.RED.toString();
    }

    @Override
    public String effectName() {
        return "ap.effect.entityExplosion.name";
    }
}
