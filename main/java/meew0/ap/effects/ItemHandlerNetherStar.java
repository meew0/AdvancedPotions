package meew0.ap.effects;

import meew0.ap.backend.Color;
import meew0.ap.backend.EffectWrapper;
import meew0.ap.backend.IPotionItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by meew0 on 28.05.14.
 */
public class ItemHandlerNetherStar implements IPotionItemHandler {
    @Override
    public float getBalance(float oldBalance) {
        return 0;
    }

    @Override
    public float getBalMod(float oldBalMod) {
        return 0;
    }

    @Override
    public EffectWrapper[] getNewEffects() {
        return new EffectWrapper[0];
    }

    @Override
    public Color getModifiedColor(Color oldColor, ItemStack item) {
        return new Color(255, 255, 255);
    }

    @Override
    public boolean canHandleItem(ItemStack item) {
        return item.getItem() == Items.nether_star;
    }
}
