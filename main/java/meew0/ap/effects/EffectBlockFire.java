package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * Created by meew0 on 24.03.14.
 */
public class EffectBlockFire implements IPotionEffectContainer {

    @Override
    public void onApply(EntityLivingBase player) {
    }

    @Override
    public void onSplash(World world, int x, int y, int z) {
        world.setBlock(x, y, z, Blocks.fire);
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
        return EnumChatFormatting.RED.toString();
    }

    @Override
    public String effectName() {
        return "ap.effect.blockFire.name";
    }
}
