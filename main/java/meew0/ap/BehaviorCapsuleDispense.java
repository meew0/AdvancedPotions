package meew0.ap;

import meew0.ap.entity.EntityThrownCapsule;
import meew0.ap.item.ItemAdvancedPotion;
import meew0.ap.util.BehaviorAdvancedProjectileDispense;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Created by meew0 on 29.03.14.
 */
public class BehaviorCapsuleDispense extends BehaviorAdvancedProjectileDispense {


    @Override
    protected boolean matchesItem(ItemStack stack) {
        if (!(stack.getItem() instanceof ItemAdvancedPotion)) return false;
        return ((ItemAdvancedPotion) stack.getItem()).isCapsule(stack);
    }

    @Override
    protected IProjectile getProjectileEntity(World world, IPosition pos, ItemStack stack) {
        EntityThrownCapsule capsule = new EntityThrownCapsule(world, stack);
        capsule.setPosition(pos.getX(), pos.getY(), pos.getZ());
        return capsule;
    }
}
