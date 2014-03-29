package meew0.ap.item;

import meew0.ap.AdvancedPotions;
import meew0.ap.backend.EffectWrapper;
import meew0.ap.backend.IBalanceEffect;
import meew0.ap.backend.IPotionEffectContainer;
import meew0.ap.backend.PotionRegistry;
import meew0.ap.entity.EntityThrownCapsule;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by meew0 on 21.03.14.
 */
public class ItemAdvancedPotion extends Item {
    public static String[] textureNames = new String[]{
            "potion_regular",
            "potion_heart",
            "potion_vial",
            "potion_square",
            "potion_capsule",
            "potion_reinforced"
    };

    public static String[] textureNamesWater = new String[]{
            "potion_water_regular",
            "potion_water_heart",
            "potion_water_vial",
            "potion_water_square",
            "potion_water_capsule",
            "potion_water_reinforced"
    };

    public static IIcon[] textures = new IIcon[6];
    public static IIcon[] texturesWater = new IIcon[6];

    public static void applyPotion(ItemStack stack, World world, EntityLivingBase entity) {
        ArrayList<EffectWrapper> effects = new ArrayList<EffectWrapper>();
        effects.addAll(TileEntityAdvancedCauldron.getEffectWrappersForTileNBT(stack.stackTagCompound));
        for (EffectWrapper effect : effects) {
            AdvancedPotions.debug("Effect applied " + effect.id + " " + effect.duration + " " + effect.amplifier);
            effect.getEffect().onApply(entity);
        }

        ArrayList<IBalanceEffect> bals = PotionRegistry.getBalanceEffects(Math.abs(
                stack.stackTagCompound.getInteger("bal")));
        String potionMessage = " ";
        if (bals.size() < 2) potionMessage += StatCollector.translateToLocal(bals.get(0).
                getUnlocalizedEffectMessage());
        else potionMessage += StatCollector.translateToLocal("ap.balanceMessage.multiple.name");
        if (entity instanceof EntityPlayer) {
            ((EntityPlayer) entity).addChatComponentMessage(new ChatComponentText("" + EnumChatFormatting.RED +
                    StatCollector.translateToLocal("ap.balanceMessage.badlyCooked.name") +
                    EnumChatFormatting.RESET + potionMessage));
        }
        for (IBalanceEffect bal : bals) {
            if (bals.size() > 1 && entity instanceof EntityPlayer) {
                ((EntityPlayer) entity).addChatComponentMessage(new ChatComponentText(" " +
                        StatCollector.translateToLocal(bal.getUnlocalizedEffectMessage())));
            }
            bal.doEffect(stack, world, entity);
        }
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        for (int i = 0; i < 6; i++) {
            textures[i] = par1IconRegister.registerIcon("advancedpotions:" + textureNames[i]);
            texturesWater[i] = par1IconRegister.registerIcon("advancedpotions:" + textureNamesWater[i]);
        }
    }

    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!player.capabilities.isCreativeMode) --stack.stackSize;

        if (stack.stackTagCompound != null && !world.isRemote) {
            applyPotion(stack, world, player);
        }

        if (stack.stackSize > 0) return stack;
        return null;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        if (stack.getItemDamage() != 4) return EnumAction.drink;
        return EnumAction.none;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        // TODO
        return Items.potionitem.getMaxItemUseDuration(stack);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (stack.getItemDamage() != 4) {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        } else {

            if (!player.capabilities.isCreativeMode) --stack.stackSize;
            world.playSoundAtEntity(player, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if (!world.isRemote) world.spawnEntityInWorld(new EntityThrownCapsule(world, stack, player));
        }
        return stack;
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        return textures[meta];
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        if (stack.stackTagCompound != null) {

            list.add(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.GRAY.toString() + "Balance: " +
                    stack.stackTagCompound.getInteger("bal"));
            list.add(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.GRAY.toString() + "Balance modifier: " +
                    stack.stackTagCompound.getInteger("balmod"));
            list.add(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.GRAY.toString() + "Effects: ");

            ArrayList<EffectWrapper> effects = new ArrayList<EffectWrapper>();
            effects.addAll(TileEntityAdvancedCauldron.getEffectWrappersForTileNBT(stack.stackTagCompound));
            for (EffectWrapper effect : effects) {
                IPotionEffectContainer e = effect.getEffect();
                list.add(EnumChatFormatting.ITALIC.toString() + EnumChatFormatting.BLUE + e.effectPrefix() +
                        StatCollector.translateToLocal(e.effectName()) + ((e.displayAmplifier()) ?
                        (" " + PotionRegistry.getRomanNumeral(e.getAmplifierForDisplay())) : "") + " (" +
                        ((e.displayDuration()) ? ("" + PotionRegistry.getReadableDuration(e.getDurationForDisplay()))
                                : "") + ")");
            }
        }
    }
}
