package meew0.ap.backend;

import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by meew0 on 21.03.14.
 */
public class EffectWrapper {
    public int id, duration, amplifier;

    public EffectWrapper() {
    }

    public EffectWrapper(int id, int duration, int amplifier) {
        this.id = id;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public IPotionEffectContainer getEffect() {
        return PotionRegistry.getEffect(id, duration, amplifier);
    }

    public NBTTagCompound toNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("id", id);
        nbt.setInteger("duration", duration);
        nbt.setInteger("amplifier", amplifier);
        return nbt;
    }

    public void fromNBT(NBTTagCompound nbt) {
        id = nbt.getInteger("id");
        duration = nbt.getInteger("duration");
        amplifier = nbt.getInteger("amplifier");
    }
}
