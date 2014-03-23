package meew0.ap.entity;

import meew0.ap.item.ItemAdvancedPotion;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by meew0 on 23.03.14.
 */
public class EntityThrownCapsule extends EntitySnowball {
    ItemStack potionStack;

    public EntityThrownCapsule(World world, ItemStack potion) {
        super(world);
        potionStack = potion;
    }

    public EntityThrownCapsule(World world, ItemStack potion, EntityPlayer player) {
        super(world, player);
        potionStack = potion;
    }

    @Override
    protected void onImpact(MovingObjectPosition pos) {
        for (Object entity : worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(pos.blockX - 1.0, pos.blockY - 1.0, pos.blockZ - 1.0, pos.blockX + 2.0, pos.blockY + 2.0, pos.blockZ + 2.0))) {
            if (entity instanceof EntityLivingBase) {
                ItemAdvancedPotion.applyPotion(potionStack, worldObj, (EntityLivingBase) entity);
            }
        }
    }
}
