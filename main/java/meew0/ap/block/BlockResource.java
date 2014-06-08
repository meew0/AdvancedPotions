package meew0.ap.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by meew0 on 24.03.14.
 */
public class BlockResource extends Block {

    public static IIcon[] textures = new IIcon[3];
    private static String[] textureNames = {
            "advancedpotions:fire_charge_block",
            "advancedpotions:arcane_metal_block",
            "advancedpotions:arcane_glass"
    };

    public BlockResource(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    @Override
    public void registerBlockIcons(IIconRegister p_149651_1_) {
        for (int i = 0; i < textureNames.length; i++) {
            textures[i] = p_149651_1_.registerIcon(textureNames[i]);
        }
    }

    @Override
    public IIcon getIcon(int side, int meta) {
        return textures[meta];
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 2 /* arcane_glass doesn't need to be added to subtype list */; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
