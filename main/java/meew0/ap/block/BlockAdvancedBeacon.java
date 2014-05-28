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

    private void spawnBlockParticles(String type, World world, int x, int y, int z) {
        for (int i = 0; i < 100; i++) {
            world.spawnParticle(type,
                    x + AdvancedPotions.rng.nextDouble(),
                    y + AdvancedPotions.rng.nextDouble(),
                    z + AdvancedPotions.rng.nextDouble(),
                    AdvancedPotions.rng.nextGaussian(),
                    AdvancedPotions.rng.nextGaussian() * 2.d,
                    AdvancedPotions.rng.nextGaussian());
        }
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
                    spawnBlockParticles("happyVillager", world, x, y, z);
                } else {
                    spawnBlockParticles("smoke", world, x, y, z);
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
