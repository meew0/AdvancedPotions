package meew0.ap.effects;

import meew0.ap.backend.Color;
import meew0.ap.backend.EffectWrapper;
import meew0.ap.backend.IPotionItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

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
    public EffectWrapper[] getNewEffects() {
        return new EffectWrapper[]{new EffectWrapper(1, 9600, 1)};
    }

    @Override
    public Color getModifiedColor(Color oldColor, ItemStack item) {
        return oldColor;
    }

    @Override
    public boolean canHandleItem(ItemStack item) {
        return item.getItem() == Items.sugar;
    }
}
