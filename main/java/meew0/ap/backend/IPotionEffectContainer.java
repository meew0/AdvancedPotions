package meew0.ap.backend;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

/**
 * Created by meew0 on 19.03.14.
 * <p/>
 * <p/>
 * This class is meant to wrap vanilla potion effects.
 * Apply the potion effect to the player in onApply().
 * This is so I can make potions that set things on fire and stuff.
 */
public interface IPotionEffectContainer {
    public void onApply(EntityPlayer player);

    public boolean displayDuration();

    public boolean displayColor();

    public String effectPrefix();

    public String effectName();
}
