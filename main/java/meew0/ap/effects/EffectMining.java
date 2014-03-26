package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * Created by meew0 on 24.03.14.
 */
public class EffectMining implements IPotionEffectContainer {
    int a;

    public EffectMining(int amplifier) {
        a = amplifier;
    }

    @Override
    public void onApply(EntityLivingBase player) {

    }

    @Override
    public void onSplash(World world, int x, int y, int z) {
        for (int i = -a; i <= a; i++) {
            for (int j = -a; j <= a; j++) {
                for (int k = -a; k <= a; k++) {
                    if (world.getBlock(x + i, y + j, k + z) == Blocks.stone) {
                        world.setBlock(x + i, y + j, k + z, Blocks.air);
                    }
                }
            }
        }
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
        return 0;
    }

    @Override
    public String effectPrefix() {
        return EnumChatFormatting.DARK_GRAY.toString();
    }

    @Override
    public String effectName() {
        return "ap.effect.mining.name";
    }
}
