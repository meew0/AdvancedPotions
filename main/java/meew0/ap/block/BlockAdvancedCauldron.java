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
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AABBPool;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockAdvancedCauldron extends BlockCauldron implements ITileEntityProvider {
//	public static final double	x1	= -1.0, x2 = 2.0, y1 = 0.0, y2 = 0.75, z1 = -1.0, z2 = 2.0;
	
	public BlockAdvancedCauldron() {
		super();
	}
	
	@Override public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityAdvancedCauldron();
	}
	
	@Override public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override public boolean isOpaqueCube() {
		return false;
	}
	
//	@Override public int getRenderType() {
//		return RenderTEAdvancedCauldron.renderId;
//	}
//	
//	@Override public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
//		int d = world.getBlockMetadata(x, y, z) - 1;
//		int oX = (d % 3) - 1;
//		int oZ = MathHelper.floor_double((double) d / 3.0) - 1;
//		return AxisAlignedBB.getAABBPool().getAABB(x + x1 + oX, y + y1, z + z1 + oZ, x + x2 + oX, y + y2, z + z2 + oZ);
//	}
//	
//	@Override public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
//		int d = world.getBlockMetadata(x, y, z) - 1;
//		int oX = (d % 3) - 1;
//		int oZ = MathHelper.floor_double((double) d / 3.0) - 1;
//		return AxisAlignedBB.getAABBPool().getAABB(x + x1 - oX, y + y1, z + z1 - oZ, x + x2 + oX, y + y2, z + z2 + oZ);
//	}
//	
//	@Override public int onBlockPlaced(World world, int x, int y, int z, int side, float a, float b, float c, int meta) {
//		
//		for (int i = -1; i <= 1; i++) {
//			for (int j = -1; j <= 1; j++) {
//				if(i == 0 && j == 0) continue;
//				world.setBlock(x+i, y, z+j, this, (i+1)+((j+1)*3)+1, 3);
//			}
//		}
//		return meta;
//	}
//	
//	@Override public boolean canPlaceBlockAt(World world, int x, int y, int z) {
//		boolean canPlace = super.canPlaceBlockAt(world, x, y, z);
//		for (int i = -1; i <= 1; i++) {
//			for (int j = 0; j <= 2; j++) {
//				for (int k = -1; k <= 1; k++) {
//					canPlace = canPlace
//							&& (world.getBlock(x + i, y + j, z + k) == Blocks.air || world.getBlock(x + i, y + j, z + k).isAir(world, x + i, y + j, k + z));
//				}
//			}
//		}
//		return canPlace;
//	}
//	
//	@Override public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
//		int d = meta;
//		int oX = (d % 3) - 1;
//		int oZ = MathHelper.floor_double((double) d / 3.0) - 1;
//		if(d == 5) oX = oZ = 0;
//		for (int i = -1; i <= 1; i++) {
//			for (int j = 0; j <= 2; j++) {
//				for (int k = -1; k <= 1; k++) {
//					world.setBlock(x + i + oX, y + j, z + k + oZ, Blocks.air);
//				}
//			}
//		}
//	}
//	
//	
//	@Override public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion) {
//		onBlockDestroyedByPlayer(world, x, y, z, world.getBlockMetadata(x, y, z));
//	}
}
