package meew0.ap.block;

import meew0.ap.AdvancedPotions;
import meew0.ap.item.ItemAdvancedPotion;
import meew0.ap.render.RenderAdvancedBeacon;
import meew0.ap.te.TileEntityAdvancedBeacon;
import net.minecraft.block.BlockBeacon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by meew0 on 30.04.14.
 */
public class BlockAdvancedBeacon extends BlockBeacon {

    public static void spawnBlockParticles(String type, World world, int x, int y, int z, double vy) {
        for (int i = 0; i < 100; i++) {
            double d0 = (double) ((float) x + 0.4F + AdvancedPotions.rng.nextFloat() * 0.2F);
            double d1 = (double) ((float) y + 0.7F + AdvancedPotions.rng.nextFloat() * 0.3F);
            double d2 = (double) ((float) z + 0.4F + AdvancedPotions.rng.nextFloat() * 0.2F);
            if (world.isRemote) world.spawnParticle(type, d0, d1, d2, 0.d, vy, 0.d);
        }
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int eventId, int eventParam) {
        if (eventId == 1) {
            spawnBlockParticles((eventParam == 2) ? "smoke" : "happyVillager", world, x, y, z,
                    (eventParam == 2) ? .05d : 1.d);
            return true;
        }

        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityAdvancedBeacon();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int m, float dx, float dy, float dz) {
        if (!world.isRemote) {
            ItemStack stack = player.getCurrentEquippedItem();
            if (stack != null) {
                if (stack.getItem() instanceof ItemAdvancedPotion && (((ItemAdvancedPotion) stack.getItem()).isArcane(stack))) {
                    TileEntityAdvancedBeacon te = (TileEntityAdvancedBeacon) world.getTileEntity(x, y, z);
                    te.potionStack = stack;
                    te.update();
                    world.markBlockForUpdate(x, y, z);
                    world.addBlockEvent(x, y, z, this, 1, 1); // spawn happyVillager particle
                } else {
                    world.addBlockEvent(x, y, z, this, 1, 2); // spawn smoke particle
                }
            }
        }
        return true;
    }

    @Override
    public int getRenderType() {
        return RenderAdvancedBeacon.renderId;
    }
}
