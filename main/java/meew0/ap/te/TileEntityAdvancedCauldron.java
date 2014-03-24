package meew0.ap.te;

import meew0.ap.AdvancedPotions;
import meew0.ap.backend.Color;
import meew0.ap.backend.EffectWrapper;
import meew0.ap.backend.IPotionItemHandler;
import meew0.ap.backend.PotionRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

import java.util.ArrayList;
import java.util.List;

public class TileEntityAdvancedCauldron extends TileEntity {
    public int waterLevel;

    public float balance, balMod;
    public ArrayList<EffectWrapper> effects;

    public Color color;

    public TileEntityAdvancedCauldron() {
        effects = new ArrayList<EffectWrapper>();
        color = new Color(0, 0, 255);

        balance = 0.0f;
        balMod = 1.0f;
    }

    public static ArrayList<EffectWrapper> getEffectWrappersForTileNBT(NBTTagCompound nbt) {
        ArrayList<EffectWrapper> ret = new ArrayList<EffectWrapper>();
        NBTTagList tagList = nbt.getTagList("effects", nbt.getId());
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound c = tagList.getCompoundTagAt(i);
            EffectWrapper ew = new EffectWrapper();
            ew.fromNBT(c);
            ret.add(ew);
        }
        return ret;
    }

    public static Color mixColor(Color oldColor, Color newColor) {
        // this uses weighted averages to mix RGB colors.
        // The result is not always intuitive and I don't know of an algorithm that does so realistically.
        // I don't want to get into very advanced calculations either to avoid tons of lag.
        // If anyone knows of a realistic and lag-free way to mix RGB colors, please tell me.

        oldColor.set((oldColor.getRed() + newColor.getRed()) / 2, (oldColor.getGreen() + newColor.getGreen()) / 2, (oldColor.getBlue() + newColor.getBlue()) / 2);
        return oldColor;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
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
        effects.addAll(getEffectWrappersForTileNBT(nbt));

    }

    public void handleAddedItem(ItemStack stack) {
        IPotionItemHandler itemHandler = PotionRegistry.getItemHandler(stack);

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
            boolean addEffect = true;
            int i = 0;
            for (EffectWrapper effect : effects) {
                if (effect.id == newEffect.id) {
                    newEffect.amplifier = Math.max(Math.max(effect.amplifier, newEffect.amplifier), effect.amplifier + newEffect.amplifier);
                    newEffect.duration = MathHelper.floor_double(((double) (effect.duration + newEffect.duration)) / 3.0);
                    effects.set(i, newEffect);
                    addEffect = false;
                }
                i++;
            }
            if (addEffect) effects.add(newEffect);
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
                    ItemStack stack = item.getEntityItem();

                    ItemStack handledStack = stack.copy();
                    handledStack.stackSize = 1;
                    for (int i = 0; i < stack.stackSize; i++) {
                        handleAddedItem(handledStack);
                    }

                    item.setDead();
                }
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
    }

    public ItemStack createPotionStack(int meta) {
        if (waterLevel < 1) {
            AdvancedPotions.debug("waterlevel < 1");
            return new ItemStack(AdvancedPotions.potionBottle);
        }
        ItemStack stack = new ItemStack(AdvancedPotions.potion);

        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        tag.removeTag("waterLevel"); // so potions are stackable properly
        stack.stackTagCompound = tag;

        stack.setItemDamage(meta);

        return stack;
    }
}
