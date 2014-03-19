package meew0.ap.backend;

import meew0.ap.effects.EffectNull;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by meew0 on 19.03.14.
 */
public class PotionRegistry {
    public static ArrayList<IPotionIDHandler> handlers;

    public static IPotionEffectContainer getEffect(int id, int duration, int amplifier) {
        for (IPotionIDHandler handler : handlers) {
            if (handler.canHandleEffect(id)) {
                return handler.getEffectContainer(id, duration, amplifier);
            }
        }
        return new EffectNull();
    }

    public static void registerHandler(IPotionIDHandler handler) {
        handlers.add(handler);
    }
}
