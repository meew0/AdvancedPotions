package meew0.ap.render;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;

/**
 * Created by meew0 on 23.03.14.
 */
public class RenderItemShield implements IItemRenderer {
    IModelCustom model;

    public RenderItemShield() {
        model = AdvancedModelLoader.loadModel(new ResourceLocation("advancedpotions:models/shield.obj"));
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return false;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        GL11.glPushMatrix();

        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation((item.getItemDamage() > 0) ? "advancedpotions:textures/shield_force.png" : "advancedpotions:textures/shield_arcane.png"));

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        model.renderAll();

        GL11.glDisable(GL11.GL_BLEND);

        GL11.glPopMatrix();
    }
}
