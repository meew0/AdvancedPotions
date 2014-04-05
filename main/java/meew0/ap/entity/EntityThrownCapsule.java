package meew0.ap.entity;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import meew0.ap.backend.EffectWrapper;
import meew0.ap.item.ItemAdvancedPotion;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.util.ArrayList;

/**
 * Created by meew0 on 23.03.14.
 */
public class EntityThrownCapsule extends EntityThrowable implements IEntityAdditionalSpawnData {
    public ItemStack potionStack;

    public EntityThrownCapsule(World world) {
        super(world);
    }

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
        if (potionStack == null) return;
        ArrayList<EffectWrapper> effects = new ArrayList<EffectWrapper>();
        effects.addAll(TileEntityAdvancedCauldron.getEffectWrappersForTileNBT(potionStack.stackTagCompound));
        for (EffectWrapper effect : effects) {
            effect.getEffect().onSplash(worldObj, pos.blockX, pos.blockY, pos.blockZ);
        }

        for (Object entity : worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
                AxisAlignedBB.getBoundingBox(pos.blockX - 1.0, pos.blockY - 1.0, pos.blockZ - 1.0,
                        pos.blockX + 2.0, pos.blockY + 2.0, pos.blockZ + 2.0).expand(1.0, 1.0, 1.0)
        )) {
            if (entity instanceof EntityLivingBase) {
                ItemAdvancedPotion.applyPotion(potionStack, worldObj, (EntityLivingBase) entity);
            }
        }
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        ByteBufUtils.writeItemStack(buffer, potionStack);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        potionStack = ByteBufUtils.readItemStack(additionalData);
    }
}
