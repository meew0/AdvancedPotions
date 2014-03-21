package meew0.ap.te;

import java.util.ArrayList;
import java.util.List;

import meew0.ap.backend.EffectWrapper;
import meew0.ap.backend.IPotionEffectContainer;
import meew0.ap.backend.IPotionItemHandler;
import meew0.ap.backend.PotionRegistry;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MathHelper;
import org.lwjgl.util.Color;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;

public class TileEntityAdvancedCauldron extends TileEntity {
	public int waterLevel;

    public float balance, balMod;
    public ArrayList<EffectWrapper> effects;


    //public int colorR, colorG, colorB;
    public Color color;

    public TileEntityAdvancedCauldron() {
        effects = new ArrayList<EffectWrapper>();
    }

    @Override public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

        nbt.setInteger("waterLevel", waterLevel);

        if (color == null) color = new Color();

        nbt.setInteger("red", color.getRed());
        nbt.setInteger("green", color.getGreen());
        nbt.setInteger("blue", color.getBlue());

        nbt.setFloat("bal", balance);
        nbt.setFloat("balmod", balMod);

        NBTTagList tagList = new NBTTagList();
        for (EffectWrapper effect : effects) {
            tagList.appendTag(effect.toNBT());
        }
        nbt.setTag("effects", tagList);
    }
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		waterLevel = nbt.getInteger("waterLevel");

        if (color == null) color = new Color();

        color.set(nbt.getInteger("red"), nbt.getInteger("green"), nbt.getInteger("blue"));

        balance = nbt.getFloat("bal");
        balMod = nbt.getFloat("balmod");

        effects = new ArrayList<EffectWrapper>();

        NBTTagList tagList = nbt.getTagList("effects", nbt.getId());
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound c = tagList.getCompoundTagAt(i);
            EffectWrapper ew = new EffectWrapper();
            ew.fromNBT(c);
            effects.add(ew);
        }

    }

    public void mixColor(Color newColor) {
        // this uses weighted averages to mix RGB colors.
        // The result is not always intuitive and I don't know of an algorithm that does so realistically.
        // I don't want to get into very advanced calculations either to avoid tons of lag.
        // If anyone knows of a realistic and lag-free way to mix RGB colors, please tell me.
        color.set((color.getRed() + newColor.getRed()) / 2, (color.getGreen() + newColor.getGreen()) / 2, (color.getBlue() + newColor.getBlue()) / 2);
    }

    public void handleAddedItem(ItemStack stack) {
        IPotionItemHandler itemHandler = PotionRegistry.getItemHandler(stack.getItem());

        // sneaky way to determine whether an item handler sets the balance or just changes it slightly
        boolean set = (itemHandler.getBalance(balance) == itemHandler.getBalance(itemHandler.getBalance(balance)));

        balMod = itemHandler.getBalMod(balMod);

        if (set) balance = itemHandler.getBalance(balance);
        else {
            float diff = itemHandler.getBalance(balance) - balance;
            balance += (diff * balMod);
        }

        EffectWrapper[] newEffects = itemHandler.getNewEffects();

        for (EffectWrapper newEffect : newEffects) {
            for (EffectWrapper effect : effects) {
                if (effect.id == newEffect.id) {
                    newEffect.amplifier = Math.max(effect.amplifier, newEffect.amplifier);
                    newEffect.duration = MathHelper.floor_double(((double) (effect.duration + newEffect.duration)) / 3.0);
                }
            }
            effects.add(newEffect);
        }
    }

    @Override
    public void updateEntity() {
        List entities = worldObj.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 1, zCoord + 1));
        for (Object obj : entities) {
            if (obj instanceof Entity) {
                if (obj instanceof EntityLivingBase)
                    ((EntityLivingBase) obj).attackEntityFrom(DamageSource.inFire, 0.5f);
                else if (obj instanceof EntityItem) {
                    EntityItem item = ((EntityItem) obj);
                    handleAddedItem(item.getEntityItem());
                    item.setDead();
                }
            }
        }
    }
	
	@Override public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
	
	@Override public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
		readFromNBT(pkt.func_148857_g());
	}
}
