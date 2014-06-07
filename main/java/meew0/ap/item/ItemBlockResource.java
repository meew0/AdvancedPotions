package meew0.ap.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * Created by meew0 on 07.06.14.
 */
public class ItemBlockResource extends ItemBlock {

    public static final String[] names = {
            "blockFireCharge", "blockArcaneMetal", "arcaneGlass"
    };

    public ItemBlockResource(Block block) {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return "tile." + names[stack.getItemDamage()];
    }
}
