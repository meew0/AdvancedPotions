package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;

/**
 * Created by meew0 on 19.03.14.
 */
public class EffectNull implements IPotionEffectContainer {
    @Override
    public void onApply(EntityLivingBase player) {
        // do nothing.
    }

    @Override
    public boolean displayDuration() {
        return false;
    }

    @Override
    public boolean displayAmplifier() {
        return false;
    }

    @Override
    public int getDurationForDisplay() {
        return 0;
    }

    @Override
    public int getAmplifierForDisplay() {
        return 1;
    }

    @Override
    public String effectPrefix() {
        return EnumChatFormatting.GRAY.toString();
    }

    @Override
    public String effectName() {
        return "None";
    }
}
