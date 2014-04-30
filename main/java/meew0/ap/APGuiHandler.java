package meew0.ap;

import cpw.mods.fml.common.network.IGuiHandler;
import meew0.ap.gui.ContainerAdvancedBeacon;
import meew0.ap.gui.GuiAdvancedBeacon;
import meew0.ap.te.TileEntityAdvancedBeacon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by meew0 on 30.04.14.
 */
public class APGuiHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (te instanceof TileEntityAdvancedBeacon) {
            return new ContainerAdvancedBeacon(player.inventory, (TileEntityAdvancedBeacon) te);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);

        if (te instanceof TileEntityAdvancedBeacon) {
            return new GuiAdvancedBeacon(player.inventory, (TileEntityAdvancedBeacon) te);
        }

        return null;
    }
}
