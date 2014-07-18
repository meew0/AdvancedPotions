package meew0.ap;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import meew0.ap.backend.Color;
import meew0.ap.backend.PotionRegistry;
import meew0.ap.block.*;
import meew0.ap.effects.*;
import meew0.ap.entity.EntityHostilePig;
import meew0.ap.entity.EntityThrownCapsule;
import meew0.ap.item.*;
import meew0.ap.te.TileEntityAdvancedBeacon;
import meew0.ap.te.TileEntityAdvancedCauldron;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemReed;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = AdvancedPotions.MODID, name = AdvancedPotions.NAME, version = AdvancedPotions.VERSION)
public class AdvancedPotions {
    public static final String MODID = "advancedpotions";
    public static final String NAME = "Advanced Potions";
    public static final String VERSION = "0.2";

    @Mod.Instance("advancedpotions")
    public static AdvancedPotions instance;

    @SidedProxy(clientSide = "meew0.ap.APClientProxy", serverSide = "meew0.ap.APCommonProxy")
    public static APCommonProxy proxy;

    public static Configuration config;

    public static boolean debugMode;
    public static boolean imcDebug;
    public static int cauldronParticleAmount;

    public static Logger advpLogger;

    public static Block advancedCauldron;
    public static Block resource;
    public static Block arcaneGlass;

    public static Block invisibilityCarrotBlock;
    public static Block frozenCarrotBlock;

    public static Block advancedBeacon;

    public static Item itemAdvCauldron;

    public static Item potionAnalyzer;
    public static Item potionBottle;
    public static Item potion;

    public static Item ingredient;
    public static Item shield;

    public static int entityIDCounter = 0;

    public static Random rng;

    public static void debug(String d) {
        advpLogger.info(d); //TODO this going to debug sometime
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        // all the stuff goes here

        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();

        cauldronParticleAmount = config.get("general", "cauldronParticleAmount", 40, "The amount of particles an advanced cauldron generates when an item is thrown in").getInt();
        debugMode = config.get("general", "debugMode", false, "Enables debug messages, spams your console").getBoolean(false);
        imcDebug = config.get("general", "imcDebug", false, "Causes AP to send an version update IMC message to itself, for debug purposes only").getBoolean(false);

        config.save();

        advpLogger = event.getModLog();
        rng = new Random();


        advancedCauldron = new BlockAdvancedCauldron().setBlockName("advancedCauldron").setHardness(5.0f);
        resource = new BlockResource(Material.rock).setBlockName("resource").setBlockTextureName("advancedpotions:fire_charge_block").setHardness(5.0f).setLightLevel(1.0f).setCreativeTab(CreativeTabs.tabBlock);
        invisibilityCarrotBlock = new BlockInvisibilityCarrot().setBlockName("invisibilityCarrots").setBlockTextureName("advancedpotions:invisibility_carrots");
        frozenCarrotBlock = new BlockFrozenCarrot().setBlockName("frozenCarrots").setBlockTextureName("advancedpotions:frozen_carrots");
        advancedBeacon = new BlockAdvancedBeacon().setBlockName("advancedBeacon").setBlockTextureName("minecraft:beacon").setCreativeTab(CreativeTabs.tabBlock);
        arcaneGlass = new BlockArcaneGlass().setBlockName("arcaneGlass");

        GameRegistry.registerBlock(resource, ItemBlockResource.class, "resource");
        GameRegistry.registerBlock(advancedCauldron, "advancedCauldron");
        GameRegistry.registerBlock(invisibilityCarrotBlock, "invisibilityCarrots");
        GameRegistry.registerBlock(frozenCarrotBlock, "frozenCarrots");
        GameRegistry.registerBlock(advancedBeacon, "advancedBeacon");
        GameRegistry.registerBlock(arcaneGlass, "arcaneGlass");

        GameRegistry.registerTileEntity(TileEntityAdvancedCauldron.class, "advancedCauldron");
        GameRegistry.registerTileEntity(TileEntityAdvancedBeacon.class, "advancedBeacon");

        potionAnalyzer = new ItemPotionAnalyzer().setUnlocalizedName("potionAnalyzer").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabBrewing).setTextureName("advancedpotions:potion_analyzer");
        potionBottle = new ItemPotionBottle().setUnlocalizedName("potionBottle").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabBrewing).setTextureName("advancedpotions:potion_bottle");
        potion = new ItemAdvancedPotion().setUnlocalizedName("potion").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabBrewing).setTextureName("advancedpotions:potion");
        ingredient = new ItemPotionIngredient().setUnlocalizedName("ingredient").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabBrewing).setTextureName("advancedpotions:ingredient");
        shield = new ItemShield().setUnlocalizedName("shield").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabCombat).setTextureName("advancedpotions:shield");
        itemAdvCauldron = new ItemReed(advancedCauldron).setUnlocalizedName("advancedCauldronItem").setMaxStackSize(64).setCreativeTab(CreativeTabs.tabBrewing).setTextureName("advancedpotions:advanced_cauldron");

        GameRegistry.registerItem(potionAnalyzer, "potionAnalyzer");
        GameRegistry.registerItem(potionBottle, "potionBottle");
        GameRegistry.registerItem(potion, "potion");
        GameRegistry.registerItem(ingredient, "ingredient");
        GameRegistry.registerItem(shield, "shield");
        GameRegistry.registerItem(itemAdvCauldron, "advancedCauldronItem");

        EntityRegistry.registerModEntity(EntityHostilePig.class, "hostilePig", entityIDCounter++, this, 80, 3, true);
        EntityRegistry.registerModEntity(EntityThrownCapsule.class, "thrownCapsule", entityIDCounter++, this, 80, 3, true);


        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);


        PotionRegistry.init();

        PotionRegistry.registerHandler(new AdvancedPotionsIDHandler());
        PotionRegistry.registerHandler(new VanillaIDHandler());

        PotionRegistry.registerBalanceHandler(new BalanceEffectNull());

        PotionRegistry.registerBalanceHandler(new BalanceEffectNauseaL1());
        PotionRegistry.registerBalanceHandler(new BalanceEffectNauseaL2());
        PotionRegistry.registerBalanceHandler(new BalanceEffectNauseaL3());
        PotionRegistry.registerBalanceHandler(new BalanceEffectSlownessL1());
        PotionRegistry.registerBalanceHandler(new BalanceEffectSlownessL2());
        PotionRegistry.registerBalanceHandler(new BalanceEffectExplosionL1());
        PotionRegistry.registerBalanceHandler(new BalanceEffectExplosionL2());
        PotionRegistry.registerBalanceHandler(new BalanceEffectTeleportL1());
        PotionRegistry.registerBalanceHandler(new BalanceEffectTeleportL2());
        PotionRegistry.registerBalanceHandler(new BalanceEffectOverdoseL1());
        PotionRegistry.registerBalanceHandler(new BalanceEffectOverdoseL2());
        PotionRegistry.registerBalanceHandler(new BalanceEffectOverdoseL3());
        PotionRegistry.registerBalanceHandler(new BalanceEffectOverdoseL4());
        PotionRegistry.registerBalanceHandler(new BalanceEffectPig());

        PotionRegistry.registerBalanceHandler(new BalanceEffectHealingL1());
        PotionRegistry.registerBalanceHandler(new BalanceEffectHealingL2());
        PotionRegistry.registerBalanceHandler(new BalanceEffectRegenL1());
        PotionRegistry.registerBalanceHandler(new BalanceEffectRegenL2());
        PotionRegistry.registerBalanceHandler(new BalanceEffectRegenL3());
        PotionRegistry.registerBalanceHandler(new BalanceEffectStrengthL1());
        PotionRegistry.registerBalanceHandler(new BalanceEffectStrengthL2());

        PotionRegistry.registerItemHandler(new ItemHandlerSugar());

        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.heal.id, Items.speckled_melon, 5.0f, 0.2f, 1, 1, new Color(240, 208, 58)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.heal.id, Items.cake, -1.0f, -0.1f, 1, 2, new Color(240, 208, 58)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.fireResistance.id, Items.magma_cream, 2.0f, 0.2f, 4800, 1, new Color(171, 36, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.regeneration.id, Items.ghast_tear, 6.0f, 0.3f, 1200, 1, new Color(190, 221, 197)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.damageBoost.id, Items.blaze_powder, 7.0f, 0.2f, 2400, 1, new Color(112, 0, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.nightVision.id, Items.golden_carrot, 2.0f, 0.1f, 4800, 1, new Color(0, 30, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.waterBreathing.id, new ItemStack(Items.fish, 1, 3), 2.0f, 0.1f, 4800, 1, new Color(0, 10, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.weakness.id, Items.fermented_spider_eye, -4.0f, 0.1f, 2400, 1, new Color(200, 0, 100)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.digSpeed.id, Items.iron_pickaxe, 5.0f, 0.3f, 2400, 1, new Color(200, 200, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.digSpeed.id, Items.golden_pickaxe, 6.0f, 0.2f, 4800, 2, new Color(200, 180, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.hunger.id, Items.rotten_flesh, -5.0f, 0.2f, 1200, 1, new Color(200, 10, 0)));

        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.resistance.id, new ItemStack(shield, 1, 0), 2.0f, 2.0f, 900, 1, new Color(128, 128, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.resistance.id, new ItemStack(shield, 1, 1), 4.0f, 3.0f, 2400, 2, new Color(30, 80, 255)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.digSlowdown.id, new ItemStack(ingredient, 1, 1), -3.0f, 0.1f, 2400, 1, new Color(0, 30, 180)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.moveSlowdown.id, new ItemStack(ingredient, 1, 2), -1.0f, 0.0f, 4800, 1, new Color(0, 0, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.blindness.id, new ItemStack(ingredient, 1, 4), -4.0f, 0.2f, 600, 1, new Color(10, 40, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.poison.id, new ItemStack(ingredient, 1, 5), 2.0f, 2.0f, 1200, 2, new Color(0, 30, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.invisibility.id, new ItemStack(ingredient, 1, 6), 2.0f, 0.1f, 9600, 1, new Color(255, 255, 255)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.jump.id, new ItemStack(ingredient, 1, 7), 3.0f, 0.1f, 2400, 1, new Color(128, 128, 128)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(Potion.harm.id, new ItemStack(ingredient, 1, 8), 3.0f, 0.1f, 1, 1, new Color(128, 128, 128)));

        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(AdvancedPotionsIDHandler.idBlockFire, Items.flint, -3.0f, 0.1f, 1, 1, new Color(50, 50, 60)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(AdvancedPotionsIDHandler.idEntityFire, Items.blaze_rod, -3.0f, 0.2f, 1, 1, new Color(255, 50, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(AdvancedPotionsIDHandler.idBlockExplosion, new ItemStack(Blocks.tnt, 1, 0), -4.0f, 0.2f, 1, 1, new Color(255, 50, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(AdvancedPotionsIDHandler.idEntityExplosion, Items.tnt_minecart, -4.0f, 0.2f, 1, 1, new Color(255, 50, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(AdvancedPotionsIDHandler.idFieryExplosion, new ItemStack(resource, 1, 0), -4.0f, 0.2f, 1, 1, new Color(255, 50, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(AdvancedPotionsIDHandler.idMining, Items.stone_pickaxe, 10.0f, 0.2f, 1, 1, new Color(200, 200, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(AdvancedPotionsIDHandler.idAnimalBreed, new ItemStack(Blocks.hay_block, 1, 0), 10.0f, 0.2f, 1, 1, new Color(200, 130, 10)));
        PotionRegistry.registerItemHandler(new ItemHandlerVanilla(AdvancedPotionsIDHandler.idEntityTeleport, Items.ender_pearl, -1.0f, 0.2f, 1, 1, new Color(10, 60, 210)));

        PotionRegistry.registerItemHandler(new ItemHandlerNetherStar());
        PotionRegistry.registerItemHandler(new ItemHandlerDye());

        PotionRegistry.registerItemHandler(new ItemHandlerBalance(Items.diamond, -2.0f, -0.3f, new Color(50, 125, 255)));
        PotionRegistry.registerItemHandler(new ItemHandlerBalance(Items.coal, 1.5f, -0.2f, new Color(3, 3, 3)));
        PotionRegistry.registerItemHandler(new ItemHandlerBalance(Items.cookie, -0.1f, -0.1f, new Color(50, 125, 255)));
        PotionRegistry.registerItemHandler(new ItemHandlerBalance(Items.emerald, -2.5f, -0.2f, new Color(0, 255, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerBalance(Items.enchanted_book, -3.0f, -0.3f, new Color(255, 0, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerBalance(Items.gold_ingot, -1.0f, -0.3f, new Color(255, 200, 0)));
        PotionRegistry.registerItemHandler(new ItemHandlerBalance(Items.iron_ingot, -0.5f, -0.1f, new Color(127, 127, 127)));
        PotionRegistry.registerItemHandler(new ItemHandlerBalance(Items.quartz, -0.3f, -0.2f, new Color(200, 200, 200)));
        PotionRegistry.registerItemHandler(new ItemHandlerBalance(Items.redstone, -0.1f, 0.3f, new Color(150, 0, 0)));

        GameRegistry.addShapelessRecipe(new ItemStack(ingredient, 1, 1), Items.golden_pickaxe, Items.fermented_spider_eye, new ItemStack(ingredient, 1, 2));
        GameRegistry.addShapelessRecipe(new ItemStack(ingredient, 1, 2), Items.sugar, Items.fermented_spider_eye);
        GameRegistry.addShapelessRecipe(new ItemStack(ingredient, 1, 4), Items.golden_carrot, Items.carrot, Items.fermented_spider_eye, Blocks.ice, new ItemStack(ingredient, 1, 2));
        GameRegistry.addShapelessRecipe(new ItemStack(ingredient, 1, 5), Items.ghast_tear, Blocks.ice, Items.fermented_spider_eye);
        GameRegistry.addShapelessRecipe(new ItemStack(ingredient, 1, 6), Items.golden_carrot, Blocks.ice, Items.fermented_spider_eye);
        GameRegistry.addShapedRecipe(new ItemStack(ingredient, 1, 7), "ii ", "  i", "iii", 'i', Items.iron_ingot);
        GameRegistry.addShapelessRecipe(new ItemStack(ingredient, 1, 8), Items.melon, Items.spider_eye, Items.fermented_spider_eye);
        GameRegistry.addShapelessRecipe(new ItemStack(ingredient, 9, 10), new ItemStack(ingredient, 1, 11));
        GameRegistry.addShapedRecipe(new ItemStack(ingredient, 9, 11), "iii", "iai", "iii", 'i', Items.iron_ingot, 'a', new ItemStack(ingredient, 1, 9));

        GameRegistry.addShapedRecipe(new ItemStack(shield, 1, 0), "i i", "iii", "nin", 'i', Items.iron_ingot, 'n', new ItemStack(ingredient, 1, 10));
        GameRegistry.addShapedRecipe(new ItemStack(shield, 1, 1), "drd", "d d", " d ", 'd', Items.diamond, 'r', Items.redstone);

        GameRegistry.addShapedRecipe(new ItemStack(potionAnalyzer, 1, 0), " r ", "aab", "aaa", 'a', new ItemStack(ingredient, 1, 11), 'r', Items.redstone, 'b', new ItemStack(potionBottle, 1, 0));
        GameRegistry.addShapedRecipe(new ItemStack(itemAdvCauldron, 1, 0), "a a", "aca", "aaa", 'a', new ItemStack(ingredient, 1, 11), 'c', Items.cauldron);
        GameRegistry.addShapedRecipe(new ItemStack(advancedBeacon, 1, 0), "ggg", "gng", "mmm", 'g', Blocks.glass, 'n', Items.nether_star, 'm', new ItemStack(resource, 1, 1));

        GameRegistry.addShapedRecipe(new ItemStack(potionBottle, 8, 0), " p ", "g g", " g ", 'p', Blocks.glass_pane, 'g', Blocks.glass);
        GameRegistry.addShapedRecipe(new ItemStack(potionBottle, 8, 1), "gpg", "g g", " g ", 'p', Blocks.glass_pane, 'g', Blocks.glass);
        GameRegistry.addShapedRecipe(new ItemStack(potionBottle, 8, 2), "p p", "p p", " g ", 'p', Blocks.glass_pane, 'g', Blocks.glass);
        GameRegistry.addShapedRecipe(new ItemStack(potionBottle, 8, 3), " p ", "g g", "ggg", 'p', Blocks.glass_pane, 'g', Blocks.glass);
        GameRegistry.addShapedRecipe(new ItemStack(potionBottle, 16, 4), "pgp", "g g", "pgp", 'p', Blocks.glass_pane, 'g', Blocks.glass);
        GameRegistry.addShapedRecipe(new ItemStack(potionBottle, 8, 5), " n ", "a a", " a ", 'n', new ItemStack(ingredient, 1, 10), 'a', new ItemStack(ingredient, 1, 11));

        GameRegistry.addShapedRecipe(new ItemStack(resource, 1, 0), "fff", "fff", "fff", 'f', Items.fire_charge);
        GameRegistry.addShapedRecipe(new ItemStack(resource, 1, 1), "fff", "fff", "fff", 'f', new ItemStack(ingredient, 1, 11));

        GameRegistry.addShapedRecipe(new ItemStack(arcaneGlass, 8, 0), "fff", "fnf", "fff", 'n', new ItemStack(ingredient, 1, 10), 'f', Blocks.glass);

        BlockDispenser.dispenseBehaviorRegistry.putObject(potion, new BehaviorCapsuleDispense());

        proxy.registerRenderThings();
    }

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        debug("drop");
        float dropChance = 0.0f;
        if (event.entityLiving instanceof EntityMob) {
            debug("drop1");
            dropChance = 0.2f;
            if (event.entityLiving instanceof EntityWitch) {
                debug("drop2");
                dropChance = 1.0f;
            }
        }
        dropChance *= (event.lootingLevel + 1);
        if (rng.nextFloat() < dropChance) {
            debug("dropping");
            event.entityLiving.worldObj.spawnEntityInWorld(new EntityItem(
                    event.entityLiving.worldObj, event.entityLiving.posX,
                    event.entityLiving.posY, event.entityLiving.posZ, new ItemStack(ingredient, 1, 9)));
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        if (imcDebug) {
            NBTTagCompound nbt = new NBTTagCompound();
            nbt.setString("payload", "3.14;6.28;http://google.com/;false;THIS#IS#ONLY#A#DEBUG#UPDATE#DON'T#ACTUALLY#DOWNLOAD#THIS;ap-6.28-IMCDEBUG.jar");
            FMLInterModComms.sendMessage("advancedpotions", "EyeNotification", nbt);
        }
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    @EventHandler
    public void processIMC(FMLInterModComms.IMCEvent event) {
        for (FMLInterModComms.IMCMessage message : event.getMessages()) {
            if (message.key.equals("EyeNotification")) { // OpenEye IMC Payload
                NBTTagCompound eyeTag = message.getNBTValue(),
                        nbt = new NBTTagCompound();

                String[] eyeMsg = eyeTag.getString("payload").replace("#", " ").split(";");
                if (eyeMsg.length != 6) {
                    advpLogger.error("Mod received malformed EyeNotification! This is most likely a bug on my side, don't worry about this");
                }

                nbt.setString("modDisplayName", "Advanced Potions");
                nbt.setString("oldVersion", eyeMsg[0]);
                nbt.setString("newVersion", eyeMsg[1]);
                nbt.setString("updateUrl", eyeMsg[2]);
                nbt.setBoolean("isDirectLink", Boolean.parseBoolean(eyeMsg[3]));
                nbt.setString("changeLog", eyeMsg[4]);
                nbt.setString("newFileName", eyeMsg[5]);

                FMLInterModComms.sendMessage("VersionChecker", "addUpdate", nbt); // Send update notification to Dynious' Version Checker if present


            }

        }
    }
}
