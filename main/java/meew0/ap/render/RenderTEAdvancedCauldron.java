package meew0.ap.render;

import org.lwjgl.opengl.GL11;

import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

// thanks to whoever made the tile entity rendering tutorial, much appreciated!
// :P

public class RenderTEAdvancedCauldron extends TileEntitySpecialRenderer {
//	private IModelCustom	modelStand, modelCauldron;
	
	public static int		renderId;
	
	public RenderTEAdvancedCauldron() {
//		modelStand = AdvancedModelLoader.loadModel(new ResourceLocation("advancedpotions:cauldron_stand.obj"));
//		modelCauldron = AdvancedModelLoader.loadModel(new ResourceLocation("advancedpotions:cauldron.obj"));
		
	}
	
	// This method is called when minecraft renders a tile entity
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		// This will move our renderer so that it will be on proper place in the
		// world
		
		// the +1 is to fix a weird issue
		GL11.glTranslatef((float) x, (float) y, (float) z + 1);
		TileEntityAdvancedCauldron te2 = (TileEntityAdvancedCauldron) te;
		/*
		 * Note that true tile entity coordinates (tileEntity.xCoord, etc) do
		 * not match to render coordinates (d, etc) that are calculated as [true
		 * coordinates] - [player coordinates (camera coordinates)]
		 */
//		if (te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord) == 0) {
//			// (new
//			// RenderBlocks(te.getWorldObj())).renderStandardBlock(te.blockType,
//			// te.xCoord, te.yCoord, te.zCoord);
//			renderCauldron(te2, te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord, te.blockType);
//		}
		
		GL11.glPopMatrix();
	}
	
	// And this method actually renders your tile entity
	public void renderCauldron(TileEntityAdvancedCauldron te, World world, int x, int y, int z, Block block) {
		Tessellator tessellator = Tessellator.instance;
		// This will make your block brightness dependent from surroundings
		// lighting.
		float f = block.getLightValue(world, x, y, z);
		int l = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
		int l1 = l % 65536;
		int l2 = l / 65536;
		tessellator.setColorOpaque_F(f, f, f);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) l1, (float) l2);
		
		/*
		 * This will rotate your model corresponding to player direction that
		 * was when you placed the block. If you want this to work,
		 * add these lines to onBlockPlacedBy method in your block class.
		 * int dir = MathHelper.floor_double((double)((player.rotationYaw * 4F)
		 * / 360F) + 0.5D) & 3;
		 * world.setBlockMetadataWithNotify(x, y, z, dir, 0);
		 */
		
		int dir = world.getBlockMetadata(x, y, z);
		
		GL11.glPushMatrix();
		GL11.glTranslatef(0.5F, 0, 0.5F);
		// This line actually rotates the renderer.
		GL11.glRotatef(dir * (-90F), 0F, 1F, 0F);
		GL11.glTranslatef(-0.5F, 0, -0.5F);
		bindTexture(new ResourceLocation("advancedpotions:cauldron"));
		/*
		 * Place your rendering code here.
		 */
		
		// using objs because I'm LAZY
//		modelStand.renderAll();
//		modelCauldron.renderAll();
		
		// in the future, render the liquid here
		
		GL11.glPopMatrix();
	}
	
}
