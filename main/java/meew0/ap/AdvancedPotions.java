package meew0.ap;

import meew0.ap.block.BlockAdvancedCauldron;
import meew0.ap.item.ItemPotionAnalyzer;
import meew0.ap.item.ItemTestItem;
import meew0.ap.render.RenderTEAdvancedCauldron;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import org.apache.logging.log4j.Logger;

@Mod(modid = AdvancedPotions.MODID, name = AdvancedPotions.NAME, version = AdvancedPotions.VERSION)
public class AdvancedPotions {
    public static final String MODID = "advancedpotions";
    public static final String NAME = "Advanced Potions";
    public static final String VERSION = "0.01";

    public static Logger advpLogger;

    public static Block advancedCauldron;

    public static Item testItem; // !!!
    public static Item potionAnalyzer;

    public static void debug(String d) {
        advpLogger.info(d); //TODO this going to debug sometime
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // all the stuff goes here

        advpLogger = event.getModLog();

        advancedCauldron = new BlockAdvancedCauldron().setBlockName("advancedCauldron").setHardness(5.0f).setCreativeTab(CreativeTabs.tabBrewing);
        GameRegistry.registerBlock(advancedCauldron, "advancedCauldron");
        GameRegistry.registerTileEntity(TileEntityAdvancedCauldron.class, "advancedCauldron");

        RenderTEAdvancedCauldron.renderId = RenderingRegistry.getNextAvailableRenderId();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdvancedCauldron.class, new RenderTEAdvancedCauldron());

        // !!!

        testItem = new ItemTestItem().setUnlocalizedName("testItem").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabAllSearch).setMaxDamage(601);
        GameRegistry.registerItem(testItem, "testItem");

        // !!!

        potionAnalyzer = new ItemPotionAnalyzer().setUnlocalizedName("potionAnalyzer").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabBrewing).setTextureName("advancedpotions:potion_analyzer");
        GameRegistry.registerItem(potionAnalyzer, "potionAnalyzer");

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
