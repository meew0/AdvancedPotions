package meew0.ap.entity;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;

/**
 * Created by meew0 on 22.03.14.
 */
public class EntityHostilePig extends EntityZombie {

    public EntityHostilePig(World par1World) {
        super(par1World);
    }


    /**
     * Returns the sound this mob makes while it's alive.
     */
    protected String getLivingSound() {
        return "mob.pig.say";
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
    protected String getHurtSound() {
        return "mob.pig.say";
    }

    /**
     * Returns the sound this mob makes on death.
     */
    protected String getDeathSound() {
        return "mob.pig.death";
    }

    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_) {
        this.playSound("mob.pig.step", 0.15F, 1.0F);
    }

    // overwrite so I can use my own DamageSource

    @Override
    public boolean attackEntityAsMob(Entity par1Entity) {
        float f = (float) this.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
        int i = 0;

        if (par1Entity instanceof EntityLivingBase) {
            f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) par1Entity);
            i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) par1Entity);
        }

        boolean flag = par1Entity.attackEntityFrom(new EntityDamageSource("eatenByPig", this), f);

        if (flag) {
            if (i > 0) {
                par1Entity.addVelocity((double) (-MathHelper.sin(this.rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F), 0.1D, (double) (MathHelper.cos(this.rotationYaw * (float) Math.PI / 180.0F) * (float) i * 0.5F));
                this.motionX *= 0.6D;
                this.motionZ *= 0.6D;
            }

            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0) {
                par1Entity.setFire(j * 4);
            }

            if (par1Entity instanceof EntityLivingBase) {
                EnchantmentHelper.func_151384_a((EntityLivingBase) par1Entity, this);
            }

            EnchantmentHelper.func_151385_b(this, par1Entity);
        }

        return flag;
    }
}
