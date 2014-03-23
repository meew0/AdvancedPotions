package meew0.ap.block;


import meew0.ap.AdvancedPotions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by meew0 on 23.03.14.
 */
public class BlockArcaneOre extends Block {
    public BlockArcaneOre(Material mat) {
        super(mat);
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(AdvancedPotions.ingredient,
                AdvancedPotions.rng.nextInt(fortune) + 1, 9)));
    }
}
