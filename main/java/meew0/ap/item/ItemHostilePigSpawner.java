package meew0.ap.item;

import meew0.ap.entity.EntityHostilePig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by meew0 on 22.03.14.
 */
public class ItemHostilePigSpawner extends Item {
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par2World.isRemote) {
            EntityHostilePig pig = new EntityHostilePig(par2World);
            pig.setPosition(par3EntityPlayer.posX, par3EntityPlayer.posY, par3EntityPlayer.posZ);
            par2World.spawnEntityInWorld(pig);
        }
        return par1ItemStack;
    }
}
