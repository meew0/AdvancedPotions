package meew0.ap.backend;

import net.minecraft.item.ItemStack;

/**
 * Created by meew0 on 19.03.14.
 */
public interface IPotionItemHandler {
    public float getBalance(float oldBalance);

    public float getBalMod(float oldBalMod);

    public EffectWrapper[] getNewEffects();

    public Color getModifiedColor(Color oldColor, ItemStack item);

    public boolean canHandleItem(ItemStack item);
}
