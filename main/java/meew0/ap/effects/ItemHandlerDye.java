package meew0.ap.effects;

import meew0.ap.backend.Color;
import meew0.ap.backend.EffectWrapper;
import meew0.ap.backend.IPotionItemHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

/**
 * Created by meew0 on 28.05.14.
 */
public class ItemHandlerDye implements IPotionItemHandler {
    @Override
    public float getBalance(float oldBalance) {
        return oldBalance;
    }

    @Override
    public float getBalMod(float oldBalMod) {
        return oldBalMod;
    }

    @Override
    public EffectWrapper[] getNewEffects() {
        return new EffectWrapper[0];
    }

    @Override
    public Color getModifiedColor(Color oldColor, ItemStack item) {
        int rgb = ItemDye.field_150922_c[item.getItemDamage()];
        java.awt.Color c = new java.awt.Color(rgb);
        return new Color(c.getRed(), c.getGreen(), c.getBlue());
    }

    @Override
    public boolean canHandleItem(ItemStack item) {
        return item.getItem() == Items.dye;
    }
}
