package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import meew0.ap.backend.IPotionIDHandler;

/**
 * Created by meew0 on 21.03.14.
 */
public class VanillaIDHandler implements IPotionIDHandler {
    @Override
    public IPotionEffectContainer getEffectContainer(int id, int duration, int amplifier) {
        if (!canHandleEffect(id)) return new EffectNull();
        return new EffectsVanilla(id, duration, amplifier);
    }

    @Override
    public boolean canHandleEffect(int id) {
        return id > 0 && id < 24;
    }

    @Override
    public int[] getHandledIDs() {
        return new int[]{
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                21, 22, 23
        };
    }
}
