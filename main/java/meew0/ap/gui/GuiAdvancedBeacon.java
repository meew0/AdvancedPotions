package meew0.ap.gui;

import meew0.ap.te.TileEntityAdvancedBeacon;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by meew0 on 30.04.14.
 */
public class GuiAdvancedBeacon extends GuiBeacon {
    public GuiAdvancedBeacon(InventoryPlayer par1InventoryPlayer, TileEntityAdvancedBeacon par2TileEntityBeacon) {
        super(par1InventoryPlayer, par2TileEntityBeacon);
    }
}
