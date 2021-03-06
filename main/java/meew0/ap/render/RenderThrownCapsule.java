package meew0.ap.render;

import meew0.ap.AdvancedPotions;
import meew0.ap.entity.EntityThrownCapsule;
import meew0.ap.item.ItemAdvancedPotion;
import meew0.ap.item.ItemPotionBottle;
import meew0.ap.util.RenderUtils;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

/**
 * Created by meew0 on 05.04.14.
 */
public class RenderThrownCapsule extends Render {

    @Override
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        if (par1Entity instanceof EntityThrownCapsule) {
            EntityThrownCapsule capsule = (EntityThrownCapsule) par1Entity;
            ItemStack stack = capsule.potionStack;

            if (stack == null) return;

            IIcon bottleIcon = ItemPotionBottle.textures[stack.getItemDamage()], waterIcon = ItemAdvancedPotion.texturesWater[stack.getItemDamage()];

            if (bottleIcon != null && waterIcon != null) {
                AdvancedPotions.debug("rendering");
                GL11.glPushMatrix();
                GL11.glTranslatef((float) par2, (float) par4, (float) par6);
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                GL11.glScalef(0.5F, 0.5F, 0.5F);
                this.bindEntityTexture(par1Entity);
                Tessellator tessellator = Tessellator.instance;

                int r, g, b;
                r = g = b = 0;

                if (stack.stackTagCompound != null) {
                    NBTTagCompound nbt = stack.stackTagCompound;
                    r = nbt.getInteger("red");
                    g = nbt.getInteger("green");
                    b = nbt.getInteger("blue");
                }

                GL11.glPushMatrix();
                RenderUtils.renderIconTowardsPlayerWithColor(tessellator, waterIcon, renderManager, r, g, b, 128);
                GL11.glPopMatrix();

                GL11.glPushMatrix();
                RenderUtils.renderIconTowardsPlayer(tessellator, bottleIcon, renderManager);
                GL11.glPopMatrix();

                GL11.glDisable(GL12.GL_RESCALE_NORMAL);
                GL11.glPopMatrix();
            }
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity var1) {
        return TextureMap.locationItemsTexture;
    }
}
