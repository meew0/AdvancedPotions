package meew0.ap.te;

import meew0.ap.item.ItemAdvancedPotion;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.stats.AchievementList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

/**
 * Created by meew0 on 30.04.14.
 */
public class TileEntityAdvancedBeacon extends TileEntity implements IInventory {
    public ItemStack potionStack;
    public int levels;
    public boolean active = false;

    public TileEntityAdvancedBeacon() {
        potionStack = null;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        potionStack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("PotionStack"));
        levels = nbt.getInteger("Levels");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (potionStack != null) {
            NBTTagCompound tag = new NBTTagCompound();
            potionStack.writeToNBT(tag);
            nbt.setTag("PotionStack", tag);
        }

        nbt.setInteger("Levels", levels);
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int par1) {
        return (par1 != 0) ? null : potionStack;
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (par1 == 0 && this.potionStack != null) {
            if (par2 >= this.potionStack.stackSize) {
                ItemStack itemstack = this.potionStack;
                this.potionStack = null;
                return itemstack;
            } else {
                this.potionStack.stackSize -= par2;
                return new ItemStack(this.potionStack.getItem(), par2, this.potionStack.getItemDamage());
            }
        } else return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        if (par1 == 0) potionStack = par2ItemStack;
    }

    @Override
    public String getInventoryName() {
        return "container.advancedBeacon";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return par1 == 0 && par2ItemStack.getItem() instanceof ItemAdvancedPotion;
    }

    @Override
    public void updateEntity() {
        if (worldObj.getTotalWorldTime() % 80l == 0l) {
            update();
        }
    }

    public void update() {
        this.updateBlock();
        this.applyEffects();
    }

    public void updateBlock() {

        int i = this.levels;

        if (!this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord)) {
            active = false;
            levels = 0;
        } else {
            active = true;
            levels = 0;

            for (int j = 1; j <= 4; this.levels = j++) {
                int k = this.yCoord - j;

                if (k < 0) {
                    break;
                }

                boolean flag = true;

                for (int l = this.xCoord - j; l <= this.xCoord + j && flag; ++l) {
                    for (int i1 = this.zCoord - j; i1 <= this.zCoord + j; ++i1) {
                        Block block = this.worldObj.getBlock(l, k, i1);

                        if (!block.isBeaconBase(this.worldObj, l, k, i1, xCoord, yCoord, zCoord)) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (!flag) {
                    break;
                }
            }

            if (this.levels == 0) {
                active = false;
            }
        }

        if (!this.worldObj.isRemote && this.levels == 4 && i < this.levels) {
            // trigger beacon achievement

            for (Object player : worldObj.getEntitiesWithinAABB(EntityPlayer.class,
                    AxisAlignedBB.getAABBPool().getAABB((double) this.xCoord, (double) this.yCoord,
                            (double) this.zCoord, (double) this.xCoord, (double) (this.yCoord - 4),
                            (double) this.zCoord).expand(10.0D, 5.0D, 10.0D)
            )) {
                EntityPlayer entityplayer = (EntityPlayer) player;
                entityplayer.triggerAchievement(AchievementList.field_150965_K);
            }
        }
    }

    public void applyEffects() {
        if (this.active && this.levels > 0 && !this.worldObj.isRemote && potionStack != null) {
            double range = (double) (this.levels * 10 + 10);

            AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB((double) this.xCoord, (double) this.yCoord,
                    (double) this.zCoord, (double) (this.xCoord + 1), (double) (this.yCoord + 1),
                    (double) (this.zCoord + 1)).expand(range, range, range);

            axisalignedbb.maxY = (double) this.worldObj.getHeight();

            for (Object player : worldObj.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb)) {
                ItemAdvancedPotion.applyPotion(potionStack, worldObj,
                        (EntityPlayer) player);
            }
        }
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.func_148857_g());

        /*if (worldObj.isRemote) {
            // rerender the block please
            worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        }*/
    }
}
