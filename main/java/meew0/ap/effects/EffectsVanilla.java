package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

/**
 * Created by meew0 on 21.03.14.
 */
public class EffectsVanilla implements IPotionEffectContainer {
    public int id, duration, amplifier;

    public EffectsVanilla(int id, int duration, int amplifier) {
        this.id = id;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public Potion getPotion() {
        return Potion.potionTypes[id];
    }

    @Override
    public void onApply(EntityPlayer player) {
        // the -1 is because vanilla uses 0 as level 1...
        player.addPotionEffect(new PotionEffect(id, duration, amplifier - 1));
    }

    @Override
    public boolean displayDuration() {
        return !getPotion().isInstant();
    }

    @Override
    public boolean displayAmplifier() {
        return !getPotion().isInstant();
    }

    @Override
    public int getDurationForDisplay() {
        return duration;
    }

    @Override
    public int getAmplifierForDisplay() {
        return amplifier;
    }

    @Override
    public String effectPrefix() {
        return getPotion().isBadEffect() ? EnumChatFormatting.RED.toString() : EnumChatFormatting.GREEN.toString();
    }

    @Override
    public String effectName() {
        return getPotion().getName();
    }
}
