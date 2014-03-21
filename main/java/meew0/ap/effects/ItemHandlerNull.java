package meew0.ap.effects;

import meew0.ap.backend.IPotionEffectContainer;
import meew0.ap.backend.IPotionIDHandler;
import meew0.ap.backend.IPotionItemHandler;
import net.minecraft.item.Item;
import org.lwjgl.util.Color;

/**
 * Created by meew0 on 21.03.14.
 */
public class ItemHandlerNull implements IPotionItemHandler {
    @Override
    public int getBalance(int oldBalance) {
        return oldBalance;
    }

    @Override
    public int getBalMod(int oldBalMod) {
        return oldBalMod;
    }

    @Override
    public IPotionEffectContainer[] getNewEffects() {
        return new IPotionEffectContainer[0];
    }

    @Override
    public Color getModifiedColor(Color oldColor) {
        return oldColor;
    }

    @Override
    public boolean canHandleItem(Item item) {
        return true;
    }
}
