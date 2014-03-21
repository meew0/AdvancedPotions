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
    public int getBalance(int oldBalance) {
        return oldBalance + 1;
    }

    @Override
    public int getBalMod(int oldBalMod) {
        return oldBalMod;
    }

    @Override
    public IPotionEffectContainer[] getNewEffects() {
        return new IPotionEffectContainer[]{};
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
