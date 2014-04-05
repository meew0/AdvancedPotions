package meew0.ap;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import meew0.ap.entity.EntityHostilePig;
import meew0.ap.entity.EntityThrownCapsule;
import meew0.ap.render.*;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.client.model.ModelPig;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * Created by meew0 on 23.03.14.
 */
public class APClientProxy extends APCommonProxy {
    @Override
    public void registerRenderThings() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdvancedCauldron.class, new RenderTEAdvancedCauldron());
        RenderTEAdvancedCauldron.renderId = RenderingRegistry.getNextAvailableRenderId();

        RenderingRegistry.registerEntityRenderingHandler(EntityHostilePig.class, new RenderHostilePig(new ModelPig(), new ModelPig(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityThrownCapsule.class, new RenderThrownCapsule());

        MinecraftForgeClient.registerItemRenderer(AdvancedPotions.potion, new RenderItemPotion());
        MinecraftForgeClient.registerItemRenderer(AdvancedPotions.shield, new RenderItemShield());
    }
}
