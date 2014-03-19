package meew0.ap.block;

import java.util.List;

import meew0.ap.AdvancedPotions;
import meew0.ap.render.RenderTEAdvancedCauldron;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AABBPool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAdvancedCauldron extends BlockCauldron implements ITileEntityProvider {
	// public static final double x1 = -1.0, x2 = 2.0, y1 = 0.0, y2 = 0.75, z1 =
	// -1.0, z2 = 2.0;
	
	public BlockAdvancedCauldron() {
		super();
	}
	
	@Override public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityAdvancedCauldron();
	}
	
	@Override protected String getTextureName() {
		return "AdvancedPotions:advc";
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int m, float dx, float dy, float dz) {
		if (world.isRemote) return true;
		else {
			ItemStack itemstack = player.inventory.getCurrentItem();
			
			if (itemstack == null) return true;
			else {
//				int i1 = world.getBlockMetadata(x, y, z);
//				int j1 = func_150027_b(i1);
				int meta = ((TileEntityAdvancedCauldron) world.getTileEntity(x, y, z)).waterLevel;
				
				if (itemstack.getItem() == Items.water_bucket) {
					if (meta < 3) {
						if (!player.capabilities.isCreativeMode) {
							player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.bucket));
						}
						
						this.func_150024_a(world, x, y, z, 3);
					}
					
					return true;
				} else {
					if (itemstack.getItem() == Items.glass_bottle) {
						if (meta > 0) {
							if (!player.capabilities.isCreativeMode) {
								ItemStack itemstack1 = new ItemStack(Items.potionitem, 1, 0);
								
								if (!player.inventory.addItemStackToInventory(itemstack1)) {
									world.spawnEntityInWorld(new EntityItem(world, (double) x + 0.5D, (double) y + 1.5D, (double) z + 0.5D, itemstack1));
								} else if (player instanceof EntityPlayerMP) {
									((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
								}
								
								--itemstack.stackSize;
								
								if (itemstack.stackSize <= 0) {
									player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack) null);
								}
							}
							
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
	@Override public void func_150024_a(World world, int x, int y, int z, int meta) {
		// world.setBlockMetadataWithNotify(x, y, z, MathHelper.clamp_int(meta,
		// 0, 3), 2);

        AdvancedPotions.debug("150024: " + meta);

		TileEntityAdvancedCauldron te = (TileEntityAdvancedCauldron) world.getTileEntity(x, y, z);
		te.waterLevel = MathHelper.clamp_int(meta, 0, 3);
		
		world.func_147453_f(x, y, z, this);
	}
	// @Override public boolean renderAsNormalBlock() {
	// return false;
	// }
	//
	// @Override public boolean isOpaqueCube() {
	// return false;
	// }
	//
	// @Override public int getRenderType() {
	// return RenderTEAdvancedCauldron.renderId;
	// }
	//
	// @Override public AxisAlignedBB getSelectedBoundingBoxFromPool(World
	// world, int x, int y, int z) {
	// int d = world.getBlockMetadata(x, y, z) - 1;
	// int oX = (d % 3) - 1;
	// int oZ = MathHelper.floor_double((double) d / 3.0) - 1;
	// return AxisAlignedBB.getAABBPool().getAABB(x + x1 + oX, y + y1, z + z1 +
	// oZ, x + x2 + oX, y + y2, z + z2 + oZ);
	// }
	//
	// @Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World
	// world, int x, int y, int z) {
	// int d = world.getBlockMetadata(x, y, z) - 1;
	// int oX = (d % 3) - 1;
	// int oZ = MathHelper.floor_double((double) d / 3.0) - 1;
	// return AxisAlignedBB.getAABBPool().getAABB(x + x1 - oX, y + y1, z + z1 -
	// oZ, x + x2 + oX, y + y2, z + z2 + oZ);
	// }
	//
	// @Override public int onBlockPlaced(World world, int x, int y, int z, int
	// side, float a, float b, float c, int meta) {
	//
	// for (int i = -1; i <= 1; i++) {
	// for (int j = -1; j <= 1; j++) {
	// if(i == 0 && j == 0) continue;
	// world.setBlock(x+i, y, z+j, this, (i+1)+((j+1)*3)+1, 3);
	// }
	// }
	// return meta;
	// }
	//
	// @Override public boolean canPlaceBlockAt(World world, int x, int y, int
	// z) {
	// boolean canPlace = super.canPlaceBlockAt(world, x, y, z);
	// for (int i = -1; i <= 1; i++) {
	// for (int j = 0; j <= 2; j++) {
	// for (int k = -1; k <= 1; k++) {
	// canPlace = canPlace
	// && (world.getBlock(x + i, y + j, z + k) == Blocks.air || world.getBlock(x
	// + i, y + j, z + k).isAir(world, x + i, y + j, k + z));
	// }
	// }
	// }
	// return canPlace;
	// }
	//
	// @Override public void onBlockDestroyedByPlayer(World world, int x, int y,
	// int z, int meta) {
	// int d = meta;
	// int oX = (d % 3) - 1;
	// int oZ = MathHelper.floor_double((double) d / 3.0) - 1;
	// if(d == 5) oX = oZ = 0;
	// for (int i = -1; i <= 1; i++) {
	// for (int j = 0; j <= 2; j++) {
	// for (int k = -1; k <= 1; k++) {
	// world.setBlock(x + i + oX, y + j, z + k + oZ, Blocks.air);
	// }
	// }
	// }
	// }
	//
	//
	// @Override public void onBlockDestroyedByExplosion(World world, int x, int
	// y, int z, Explosion explosion) {
	// onBlockDestroyedByPlayer(world, x, y, z, world.getBlockMetadata(x, y,
	// z));
	// }
}
