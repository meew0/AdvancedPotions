package meew0.ap.util;

import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by meew0 on 29.03.14.
 */
public abstract class BehaviorAdvancedProjectileDispense extends BehaviorDefaultDispenseItem {
    /**
     * Dispense the specified stack, play the dispense sound and spawn particles.
     */
    public ItemStack dispenseStack(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
        World world = par1IBlockSource.getWorld();
        IPosition iposition = BlockDispenser.func_149939_a(par1IBlockSource);
        EnumFacing enumfacing = BlockDispenser.func_149937_b(par1IBlockSource.getBlockMetadata());
        ItemStack stack = par2ItemStack.splitStack(1);
        if (matchesItem(par2ItemStack)) {
            // dispense item as projectile because the item matches
            IProjectile iprojectile = this.getProjectileEntity(world, iposition, par2ItemStack);
            iprojectile.setThrowableHeading((double) enumfacing.getFrontOffsetX(), (double) ((float) enumfacing.getFrontOffsetY() + 0.1F), (double) enumfacing.getFrontOffsetZ(), this.func_82500_b(), this.func_82498_a());
            world.spawnEntityInWorld((Entity) iprojectile);
        } else {
            // dispense item as item because it doesn't match
            BehaviorDefaultDispenseItem.doDispense(world, stack, 6, enumfacing, iposition);
        }
        return par2ItemStack;
    }

    /**
     * Play the dispense sound from the specified block.
     */
    protected void playDispenseSound(IBlockSource par1IBlockSource) {
        par1IBlockSource.getWorld().playAuxSFX(1002, par1IBlockSource.getXInt(), par1IBlockSource.getYInt(), par1IBlockSource.getZInt(), 0);
    }

    /**
     * whether or not the item should be dispensed as a projectile
     *
     * @param stack the stack to test for
     * @return whether or not to dispense as a projectile
     */

    protected abstract boolean matchesItem(ItemStack stack);

    /**
     * Return the projectile entity spawned by this dispense behavior.
     */
    protected abstract IProjectile getProjectileEntity(World var1, IPosition var2, ItemStack stack);

    protected float func_82498_a() {
        return 6.0F;
    }

    protected float func_82500_b() {
        return 1.1F;
    }
}
