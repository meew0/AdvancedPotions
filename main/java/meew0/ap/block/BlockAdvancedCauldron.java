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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockAdvancedCauldron extends BlockCauldron implements ITileEntityProvider {
    public BlockAdvancedCauldron() {
        super();
    }

    public static IIcon potionIcon, innerIcon;

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

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int m, float dx, float dy, float dz) {
        if (world.isRemote) return true;
        else {
            ItemStack itemstack = player.inventory.getCurrentItem();

            if (itemstack == null) return true;
            else {
                TileEntityAdvancedCauldron te = (TileEntityAdvancedCauldron) world.getTileEntity(x, y, z);
                int meta = te.waterLevel;

                if (itemstack.getItem() == Items.water_bucket) {
                    if (meta < 3) {
                        if (!player.capabilities.isCreativeMode)
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));

                        this.func_150024_a(world, x, y, z, 3);
                    }

                    return true;
                } else {
                    if (itemstack.getItem() == AdvancedPotions.potionBottle) {
                        if (meta > 0) {
                            ItemStack itemstack1 = te.createPotionStack(itemstack.getItemDamage());

                            if (!player.inventory.addItemStackToInventory(itemstack1))
                                world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY + 1.0, player.posZ, itemstack1));
                            else if (player instanceof EntityPlayerMP)
                                ((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);

                            --itemstack.stackSize;

                            if (itemstack.stackSize <= 0)
                                player.inventory.setInventorySlotContents(player.inventory.currentItem, null);

                            this.func_150024_a(world, x, y, z, meta - 1);
                        }
                    } else if (meta > 0 && itemstack.getItem() instanceof ItemArmor
                            && ((ItemArmor) itemstack.getItem()).getArmorMaterial() == ItemArmor.ArmorMaterial.CLOTH) {
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

    // Make sure to always suppress metadata changes so the metadata always
    // stays 0 so I can use my custom liquid rendering code
    @Override
    public void func_150024_a(World world, int x, int y, int z, int meta) {
        TileEntityAdvancedCauldron te = (TileEntityAdvancedCauldron) world.getTileEntity(x, y, z);
        te.waterLevel = MathHelper.clamp_int(meta, 0, 3);

        world.markBlockForUpdate(x, y, z);

        world.func_147453_f(x, y, z, this);
    }
}
