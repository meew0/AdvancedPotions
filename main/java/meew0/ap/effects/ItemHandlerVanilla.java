package meew0.ap.effects;

import meew0.ap.backend.Color;
import meew0.ap.backend.EffectWrapper;
import meew0.ap.backend.IPotionItemHandler;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by meew0 on 22.03.14.
 */
public class ItemHandlerVanilla implements IPotionItemHandler {
    private float balC, bmC;
    private int id, duration, amplifier;
    private Color color;
    private ItemStack item;

    public ItemHandlerVanilla(int id, ItemStack item, float balC, float bmC, int duration, int amplifier, Color color) {
        this.balC = balC;
        this.bmC = bmC;
        this.id = id;
        this.duration = duration;
        this.amplifier = amplifier;
        this.color = color;
        this.item = item;
    }

    public ItemHandlerVanilla(int id, Item item, float balC, float bmC, int duration, int amplifier, Color color) {
        this(id, new ItemStack(item, 0, 0), balC, bmC, duration, amplifier, color);
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
        return new EffectWrapper[]{new EffectWrapper(id, duration, amplifier)};
    }

    @Override
    public Color getModifiedColor(Color oldColor) {
        return TileEntityAdvancedCauldron.mixColor(oldColor, color);
    }

    @Override
    public boolean canHandleItem(ItemStack item) {
        return this.item.isItemEqual(item);
    }
}
