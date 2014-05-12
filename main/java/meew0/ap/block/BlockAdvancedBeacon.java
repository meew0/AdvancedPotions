package meew0.ap.block;

import meew0.ap.AdvancedPotions;
import meew0.ap.te.TileEntityAdvancedBeacon;
import net.minecraft.block.BlockBeacon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by meew0 on 30.04.14.
 */
public class BlockAdvancedBeacon extends BlockBeacon {

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityAdvancedBeacon();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int m, float dx, float dy, float dz) {
        if (!world.isRemote) {
            TileEntityAdvancedBeacon te = (TileEntityAdvancedBeacon) world.getTileEntity(x, y, z);

            if (te != null) {
                player.openGui(AdvancedPotions.instance, 0, world, x, y, z);
            }
        }
        return true;
    }
}
