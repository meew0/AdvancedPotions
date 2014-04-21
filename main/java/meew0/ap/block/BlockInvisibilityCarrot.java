package meew0.ap.block;

import meew0.ap.AdvancedPotions;
import net.minecraft.block.BlockCarrot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by meew0 on 21.04.14.
 */
public class BlockInvisibilityCarrot extends BlockCarrot {

    @Override
    protected Item func_149865_P() {
        return AdvancedPotions.ingredient;
    }

    @Override
    protected Item func_149866_i() {
        return AdvancedPotions.ingredient;
    }

    @Override
    public int damageDropped(int p_149692_1_) {
        return 6;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        //copied from BlockCrops.getDrops
        ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

        if (metadata >= 7) {
            for (int i = 0; i < 3 + fortune; ++i) {
                if (world.rand.nextInt(15) <= metadata) {
                    ret.add(new ItemStack(this.func_149866_i(), 1, damageDropped(metadata)));
                }
            }
        }

        return ret;
    }
}
