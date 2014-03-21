package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import meew0.ap.backend.IPotionItemHandler;
import net.minecraft.item.Item;
import org.lwjgl.util.Color;

/**
 * Created by meew0 on 21.03.14.
 */
public class ItemHandlerSugar implements IPotionItemHandler {
    @Override
    public float getBalance(float oldBalance) {
        return oldBalance + 1;
    }

    @Override
    public float getBalMod(float oldBalMod) {
        return oldBalMod;
    }

    @Override
    public int[] getNewEffects() {
        return new int[]{};
    }

    @Override
    public Color getModifiedColor(Color oldColor) {
        return null;
    }

    @Override
    public boolean canHandleItem(Item item) {
        return false;
    }
}
