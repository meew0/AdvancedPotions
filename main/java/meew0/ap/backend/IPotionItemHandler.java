package meew0.ap.backend;

import org.lwjgl.util.Color;

/**
 * Created by meew0 on 19.03.14.
 */
public interface IPotionItemHandler {
    public int getBalance(int oldBalance);

    public int getBalMod(int oldBalMod);

    public void getModifiedColor(Color oldColor);
}
