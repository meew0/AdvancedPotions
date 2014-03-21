package meew0.ap.backend;

import net.minecraft.item.Item;
import org.lwjgl.util.Color;

/**
 * Created by meew0 on 19.03.14.
 */
public interface IPotionItemHandler {
    public float getBalance(float oldBalance);

    public float getBalMod(float oldBalMod);

    public int[] getNewEffects();

    public Color getModifiedColor(Color oldColor);

    public boolean canHandleItem(Item item);
}
