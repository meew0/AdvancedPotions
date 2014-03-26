package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import meew0.ap.backend.IPotionIDHandler;

/**
 * Created by meew0 on 21.03.14.
 */
public class AdvancedPotionsIDHandler implements IPotionIDHandler {
    public static final int idEntityExplosion = 40;
    public static final int idBlockExplosion = 41;
    public static final int idEntityFire = 42;
    public static final int idBlockFire = 43;
    public static final int idFieryExplosion = 44;
    public static final int idMining = 45;

    @Override
    public IPotionEffectContainer getEffectContainer(int id, int duration, int amplifier) {
        switch (id) {
            case idEntityExplosion:
                return new EffectEntityExplosion(amplifier);
            case idBlockExplosion:
                return new EffectBlockExplosion(amplifier);
            case idEntityFire:
                return new EffectEntityFire(amplifier);
            case idBlockFire:
                return new EffectBlockFire();
            case idFieryExplosion:
                return new EffectFieryExplosion(amplifier);
            case idMining:
                return new EffectMining(amplifier);
        }
        return null;
    }

    @Override
    public boolean canHandleEffect(int id) {
        return id > 0 && id < 100 && (id < 1 || id > 39);
    }

    @Override
    public int[] getHandledIDs() {
        return new int[]{
                0, 40,
                41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
                51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
                61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
                71, 72, 73, 74, 75, 76, 77, 78, 79, 80,
                81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
                91, 92, 93, 94, 95, 96, 97, 98, 99
        };
    }
}
