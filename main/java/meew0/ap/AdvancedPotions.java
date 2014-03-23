package meew0.ap;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import meew0.ap.backend.PotionRegistry;
import meew0.ap.block.BlockAdvancedCauldron;
import meew0.ap.effects.*;
import meew0.ap.entity.EntityHostilePig;
import meew0.ap.item.*;
import meew0.ap.render.RenderHostilePig;
import meew0.ap.render.RenderItemPotion;
import meew0.ap.render.RenderItemShield;
import meew0.ap.render.RenderTEAdvancedCauldron;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelPig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.MinecraftForgeClient;
import org.apache.logging.log4j.Logger;
import org.lwjgl.util.Color;

import java.util.Random;

@Mod(modid = AdvancedPotions.MODID, name = AdvancedPotions.NAME, version = AdvancedPotions.VERSION)
public class AdvancedPotions {
    public static final String MODID = "advancedpotions";
    public static final String NAME = "Advanced Potions";
    public static final String VERSION = "0.01";

    public static Logger advpLogger;

    public static Block advancedCauldron;

    public static Item potionAnalyzer;
    public static Item potionBottle;
    public static Item potion;
    public static Item pigSpawner;
    public static Item ingredient;
    public static Item shield;

    public static Random rng;

    public static void debug(String d) {
        advpLogger.info(d); //TODO this going to debug sometime
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // all the stuff goes here

        advpLogger = event.getModLog();
        rng = new Random();


        advancedCauldron = new BlockAdvancedCauldron().setBlockName("advancedCauldron").setHardness(5.0f).setCreativeTab(CreativeTabs.tabBrewing);
        GameRegistry.registerBlock(advancedCauldron, "advancedCauldron");
        GameRegistry.registerTileEntity(TileEntityAdvancedCauldron.class, "advancedCauldron");

        RenderTEAdvancedCauldron.renderId = RenderingRegistry.getNextAvailableRenderId();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAdvancedCauldron.class, new RenderTEAdvancedCauldron());

        potionAnalyzer = new ItemPotionAnalyzer().setUnlocalizedName("potionAnalyzer").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabBrewing).setTextureName("advancedpotions:potion_analyzer");
        potionBottle = new ItemPotionBottle().setUnlocalizedName("potionBottle").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabBrewing).setTextureName("advancedpotions:potion_bottle");
        potion = new ItemAdvancedPotion().setUnlocalizedName("potion").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabBrewing).setTextureName("advancedpotions:potion");
        pigSpawner = new ItemHostilePigSpawner().setUnlocalizedName("pigSpawner").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabAllSearch).setTextureName("advancedpotions:pig_spawner");
        ingredient = new ItemPotionIngredient().setUnlocalizedName("ingredient").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabBrewing).setTextureName("advancedpotions:ingredient");
        shield = new ItemShield().setUnlocalizedName("shield").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabCombat).setTextureName("advancedpotions:shield");

        GameRegistry.registerItem(potionAnalyzer, "potionAnalyzer");
        GameRegistry.registerItem(potionBottle, "potionBottle");
        GameRegistry.registerItem(potion, "potion");
        GameRegistry.registerItem(pigSpawner, "pigSpawner");
        GameRegistry.registerItem(ingredient, "ingredient");
        GameRegistry.registerItem(shield, "shield");

        EntityRegistry.registerModEntity(EntityHostilePig.class, "hostilePig", EntityRegistry.findGlobalUniqueEntityId(), this, 80, 3, true);
        RenderingRegistry.registerEntityRenderingHandler(EntityHostilePig.class, new RenderHostilePig(new ModelPig(), new ModelPig(), 0.5f));

        MinecraftForgeClient.registerItemRenderer(potion, new RenderItemPotion());
        MinecraftForgeClient.registerItemRenderer(shield, new RenderItemShield());


        PotionRegistry.init();

        PotionRegistry.registerHandler(new AdvancedPotionsIDHandler());
        PotionRegistry.registerHandler(new VanillaIDHandler());

        PotionRegistry.registerBalanceHandler(new BalanceEffectNull());
        PotionRegistry.registerBalanceHandler(new BalanceEffectNauseaL1());
        PotionRegistry.registerBalanceHandler(new BalanceEffectSlownessL1());
        PotionRegistry.registerBalanceHandler(new BalanceEffectPig());

        PotionRegistry.registerItemHandler(new ItemHandlerSugar());

        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.heal.id, Items.speckled_melon, 5.0f, 0.2f, 1, 1, new Color(240, 208, 58)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.fireResistance.id, Items.magma_cream, 2.0f, 0.2f, 4800, 1, new Color(171, 36, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.regeneration.id, Items.ghast_tear, 6.0f, 0.3f, 1200, 1, new Color(190, 221, 197)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.damageBoost.id, Items.blaze_powder, 7.0f, 0.2f, 2400, 1, new Color(112, 0, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.nightVision.id, Items.golden_carrot, 2.0f, 0.1f, 4800, 1, new Color(0, 30, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.waterBreathing.id, new ItemStack(Items.fish, 1, 3), 2.0f, 0.1f, 4800, 1, new Color(0, 10, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.weakness.id, Items.fermented_spider_eye, -4.0f, 0.1f, 2400, 1, new Color(200, 0, 100)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.digSpeed.id, Items.iron_pickaxe, 5.0f, 0.3f, 2400, 1, new Color(200, 200, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.digSpeed.id, Items.golden_pickaxe, 6.0f, 0.2f, 4800, 2, new Color(200, 180, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.digSpeed.id, Items.golden_pickaxe, 6.0f, 0.2f, 4800, 2, new Color(200, 180, 0)));

        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.resistance.id, new ItemStack(ingredient, 1, 0), 2.0f, 2.0f, 900, 1, new Color(128, 128, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.digSlowdown.id, new ItemStack(ingredient, 1, 1), -3.0f, 0.1f, 2400, 1, new Color(0, 30, 180)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.moveSlowdown.id, new ItemStack(ingredient, 1, 2), -1.0f, 0.0f, 4800, 1, new Color(0, 0, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.resistance.id, new ItemStack(ingredient, 1, 3), 4.0f, 3.0f, 2400, 2, new Color(30, 80, 255)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.blindness.id, new ItemStack(ingredient, 1, 4), -4.0f, 0.2f, 600, 1, new Color(10, 40, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.poison.id, new ItemStack(ingredient, 1, 5), 2.0f, 2.0f, 1200, 2, new Color(0, 30, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.invisibility.id, new ItemStack(ingredient, 1, 6), 2.0f, 0.1f, 9600, 1, new Color(255, 255, 255)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.jump.id, new ItemStack(ingredient, 1, 7), 3.0f, 0.1f, 2400, 1, new Color(128, 128, 128)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.harm.id, new ItemStack(ingredient, 1, 8), 3.0f, 0.1f, 1, 1, new Color(128, 128, 128)));


    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}
