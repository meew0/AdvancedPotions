package meew0.ap.te;

import meew0.ap.item.ItemAdvancedPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityBeacon;

/**
 * Created by meew0 on 30.04.14.
 */
public class TileEntityAdvancedBeacon extends TileEntityBeacon {
    public ItemStack potionStack;

    public TileEntityAdvancedBeacon() {
        potionStack = null;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        potionStack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("PotionStack"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);

        if (potionStack != null) {
            NBTTagCompound tag = new NBTTagCompound();
            potionStack.writeToNBT(tag);
            nbt.setTag("PotionStack", tag);
        }
    }

    @Override
    public int getSizeInventory() {
        return 2;
    }

    @Override
    public ItemStack getStackInSlot(int par1) {
        return (par1 != 1) ? super.getStackInSlot(par1) : potionStack;
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2) {
        if (par1 == 1 && this.potionStack != null) {
            if (par2 >= this.potionStack.stackSize) {
                ItemStack itemstack = this.potionStack;
                this.potionStack = null;
                return itemstack;
            } else {
                this.potionStack.stackSize -= par2;
                return new ItemStack(this.potionStack.getItem(), par2, this.potionStack.getItemDamage());
            }
        } else return super.decrStackSize(par1, par2);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1) {
        if (par1 == 1 && this.potionStack != null) {
            ItemStack itemstack = this.potionStack;
            this.potionStack = null;
            return itemstack;
        } else return super.getStackInSlotOnClosing(par1);
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
        if (par1 == 1) potionStack = par2ItemStack;
        else super.setInventorySlotContents(par1, par2ItemStack);
    }

    @Override
    public String getInventoryName() {
        return hasCustomInventoryName() ? super.getInventoryName() : "container.advancedBeacon";
    }

    @Override
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        if (par1 == 1) return par2ItemStack.getItem() instanceof ItemAdvancedPotion;
        else return super.isItemValidForSlot(par1, par2ItemStack);
    }
}
