package meew0.ap.effects;

import meew0.ap.backend.Color;
import meew0.ap.backend.EffectWrapper;
import meew0.ap.backend.IPotionItemHandler;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by meew0 on 28.05.14.
 */
public class ItemHandlerBalance implements IPotionItemHandler {
    private float balC, bmC;
    private Color color;
    private ItemStack item;

    public ItemHandlerBalance(ItemStack item, float balC, float bmC, Color color) {
        this.balC = balC;
        this.bmC = bmC;
        this.color = color;
        this.item = item;
    }

    public ItemHandlerBalance(Item item, float balC, float bmC, Color color) {
        this(new ItemStack(item, 0, 0), balC, bmC, color);
    }

    @Override
    public float getBalance(float oldBalance) {
        return oldBalance + balC;
    }

    @Override
    public float getBalMod(float oldBalMod) {
        return oldBalMod + bmC;
    }

    @Override
    public EffectWrapper[] getNewEffects() {
        return new EffectWrapper[0];
    }

    @Override
    public Color getModifiedColor(Color oldColor, ItemStack item) {
        return TileEntityAdvancedCauldron.mixColor(oldColor, color);
    }

    @Override
    public boolean canHandleItem(ItemStack item) {
        return this.item.isItemEqual(item);
    }
}
