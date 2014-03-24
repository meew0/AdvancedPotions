package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * Created by meew0 on 24.03.14.
 */
public class EffectEntityFire implements IPotionEffectContainer {

    int a;

    public EffectEntityFire(int amplifier) {
        a = amplifier;
    }

    @Override
    public void onApply(EntityLivingBase player) {
        player.setFire(a * 5);
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
        return "ap.effect.entityFire.name";
    }
}
