package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * Created by meew0 on 26.03.14.
 */
public class EffectAnimalBreed implements IPotionEffectContainer {
    @Override
    public void onApply(EntityLivingBase player) {
        if (player instanceof EntityAnimal) {
            EntityAnimal animal = (EntityAnimal) player;
            animal.func_146082_f(null);
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
        return false;
    }

    @Override
    public int getDurationForDisplay() {
        return 0;
    }

    @Override
    public int getAmplifierForDisplay() {
        return 0;
    }

    @Override
    public String effectPrefix() {
        return EnumChatFormatting.GREEN.toString();
    }

    @Override
    public String effectName() {
        return "ap.effect.animalBreed.name";
    }
}
