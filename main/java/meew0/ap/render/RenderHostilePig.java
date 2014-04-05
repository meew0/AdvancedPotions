package meew0.ap.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

/**
 * Created by meew0 on 22.03.14.
 */
public class RenderHostilePig extends RenderPig {

    public static ResourceLocation texture = new ResourceLocation("advancedpotions:textures/entities/hostilepig.png");

    public RenderHostilePig(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
        super(par1ModelBase, par2ModelBase, par3);
    }

    @Override
    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return -1;
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return texture;
    }
}
