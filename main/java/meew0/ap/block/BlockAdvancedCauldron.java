package meew0.ap.block;

import meew0.ap.AdvancedPotions;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;

public class BlockAdvancedCauldron extends BlockCauldron implements ITileEntityProvider {
    public static IIcon potionIcon, innerIcon;

    public BlockAdvancedCauldron() {
        super();
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityAdvancedCauldron();
    }

    @Override
    protected String getTextureName() {
        return "AdvancedPotions:advc";
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        super.registerBlockIcons(iconRegister);

        potionIcon = iconRegister.registerIcon("advancedpotions:potion_base");
        innerIcon = iconRegister.registerIcon("advancedpotions:advc_inner"); // stupid BlockCauldron makes their textures private
    }


    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_) {
        super.setBlockBoundsBasedOnState(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int m, float dx, float dy, float dz) {
        if (world.isRemote) return true; // do nothing for client worlds
        else {
            ItemStack itemstack = player.inventory.getCurrentItem();

            if (itemstack == null) return true; // do nothing if the player's hand is empty
            else {
                // There's something in the player's hand
                TileEntityAdvancedCauldron te = (TileEntityAdvancedCauldron) world.getTileEntity(x, y, z);
                int meta = te.getWaterLevel();

                if (itemstack.getItem() == Items.water_bucket) {
                    // It's a water bucket, so fill with water
                    if (meta < 3) {
                        if (!player.capabilities.isCreativeMode) // don't consume the bucket in creative
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));

                        this.func_150024_a(world, x, y, z, 3);
                    }

                    return true;
                } else {
                    if (itemstack.getItem() == AdvancedPotions.potionBottle) {
                        // It's a potion bottle
                        if (meta > 0) {
                            // There's something in the cauldron, so make a potion
                            ItemStack itemstack1 = te.createPotionStack(itemstack.getItemDamage());

                            if (!player.inventory.addItemStackToInventory(itemstack1))
                                // if the player's inventory is full, throw the item on the ground
                                world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY + 1.0, player.posZ, itemstack1));
                            else if (player instanceof EntityPlayerMP) // update the inventory
                                ((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);

                            if (!player.capabilities.isCreativeMode) // don't consume the bottle in creative
                                --itemstack.stackSize;

                            if (itemstack.stackSize <= 0) // if the bottle stack is empty, delete the stack
                                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

                            // update the TE
                            this.func_150024_a(world, x, y, z, meta - 1);
                        }
                    } else if (meta > 0 && itemstack.getItem() instanceof ItemArmor
                            && ((ItemArmor) itemstack.getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.CLOTH) {
                        // It's a piece of leather armor, so undye it
                        ItemArmor itemarmor = (ItemArmor) itemstack.getItem();
                        itemarmor.removeColor(itemstack);
                        this.func_150024_a(world, x, y, z, meta - 1);
                        return true;
                    }

                    return false;
                }
            }
        }
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(AdvancedPotions.itemAdvCauldron)));
    }

    // Make sure to always suppress metadata changes so the metadata always
    // stays 0 so I can use my custom liquid rendering code
    @Override
    public void func_150024_a(World world, int x, int y, int z, int meta) {
        TileEntityAdvancedCauldron te = (TileEntityAdvancedCauldron) world.getTileEntity(x, y, z);
        te.setWaterLevel(meta);

        world.markBlockForUpdate(x, y, z);
        world.func_147453_f(x, y, z, this);
    }
}
