package meew0.ap.effects;

import meew0.ap.AdvancedPotions;
import meew0.ap.backend.IPotionEffectContainer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

/**
 * Created by meew0 on 26.03.14.
 */
public class EffectEntityTeleport implements IPotionEffectContainer {

    double a;

    public EffectEntityTeleport(int amplifier) {
        a = amplifier;
    }

    // from EntityEnderPearl.onImpact
    public void teleportParticles(EntityLivingBase player) {
        for (int i = 0; i < 32; ++i) {
            player.worldObj.spawnParticle("portal", player.posX, player.posY + AdvancedPotions.rng.nextDouble() * 2.0D,
                    player.posZ, AdvancedPotions.rng.nextGaussian(), 0.0D, AdvancedPotions.rng.nextGaussian());
        }
    }

    @Override
    public void onApply(EntityLivingBase player) {
        player.playSound("mob.endermen.portal", 1.0F, 1.0F);
        teleportParticles(player);

        boolean found = false;
        int tries = 0;
        double destX = player.posX;
        double destY = player.posY;
        double destZ = player.posZ;

        while (!found && tries < 10) {
            tries++;
            destX = player.posX + ((AdvancedPotions.rng.nextFloat() - 0.5) * a);
            destY = player.posY + ((AdvancedPotions.rng.nextFloat() - 0.5) * a) + 1.0;
            destZ = player.posZ + ((AdvancedPotions.rng.nextFloat() - 0.5) * a);
            if (player.worldObj.getBlock((int) destX, (int) destY, (int) destZ).isAir(player.worldObj, (int) destX, (int) destY, (int) destZ))
                found = true;
        }

        player.setPosition(destX, destY, destZ);
        teleportParticles(player);
        player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "mob.endermen.portal", 1.0F, 1.0F);
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
        return (int) a;
    }

    @Override
    public String effectPrefix() {
        return EnumChatFormatting.BLUE.toString();
    }

    @Override
    public String effectName() {
        return "ap.effect.entityTeleport.name";
    }
}
