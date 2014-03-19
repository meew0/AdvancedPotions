package meew0.ap.backend;

/**
 * Created by meew0 on 19.03.14.
 */
public interface IPotionIDHandler {
    public IPotionEffectContainer getEffectContainer(int id, int duration, int amplifier);

    public boolean canHandleEffect(int id);
}
