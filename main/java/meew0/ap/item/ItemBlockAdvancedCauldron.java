package meew0.ap.item;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;

/**
 * Created by meew0 on 23.03.14.
 */
public class ItemBlockAdvancedCauldron extends ItemBlock {

    public ItemBlockAdvancedCauldron(Block block) {
        super(block);
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon("advancedpotions:advanced_cauldron");
    }
}
