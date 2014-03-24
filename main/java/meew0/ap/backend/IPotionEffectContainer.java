package meew0.ap.backend;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

/**
 * Created by meew0 on 19.03.14.
 * <p/>
 * <p/>
 * This class is meant to wrap vanilla potion effects.
 * Apply the potion effect to the player in onApply().
 * This is so I can make potions that set things on fire and stuff.
 */
public interface IPotionEffectContainer {
    public void onApply(EntityLivingBase player);

    // only use this for special splash effects, e.g. explosions
    // I will take care of causing effects to entities already.
    public void onSplash(World world, int x, int y, int z);

    public boolean displayDuration();

    public boolean displayAmplifier();

    public int getDurationForDisplay();

    public int getAmplifierForDisplay();

    public String effectPrefix();

    public String effectName();
}
