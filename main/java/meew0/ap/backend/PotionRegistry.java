package meew0.ap.backend;

import meew0.ap.AdvancedPotions;
import meew0.ap.PotionException;
import meew0.ap.effects.BalanceEffectNull;
import meew0.ap.effects.EffectNull;
import meew0.ap.effects.ItemHandlerNull;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import java.util.ArrayList;

/**
 * Created by meew0 on 19.03.14.
 */
public class PotionRegistry {
    public static ArrayList<IPotionIDHandler> handlers;
    public static ArrayList<Integer> idList;

    public static ArrayList<IPotionItemHandler> itemHandlers;

    public static ArrayList<IBalanceEffect> balanceHandlers;

    public static void init() {
        handlers = new ArrayList<IPotionIDHandler>();
        idList = new ArrayList<Integer>();
        itemHandlers = new ArrayList<IPotionItemHandler>();
        balanceHandlers = new ArrayList<IBalanceEffect>();
    }

    public static IPotionEffectContainer getEffect(int id, int duration, int amplifier) {
        for (IPotionIDHandler handler : handlers) {
            if (handler.canHandleEffect(id)) {
                return handler.getEffectContainer(id, duration, amplifier);
            }
        }
        return new EffectNull();
    }

    public static IPotionItemHandler getItemHandler(ItemStack item) {
        for (IPotionItemHandler itemHandler : itemHandlers) {
            if (itemHandler.canHandleItem(item)) return itemHandler;
        }
        return new ItemHandlerNull();
    }

    public static ArrayList<IBalanceEffect> getBalanceEffects(int bal) {
//        if (bal != Math.abs(bal)) {
//            AdvancedPotions.advpLogger.error("Balance must not be negative! Please use Math.abs(balance) in the future.");
//            bal = Math.abs(bal);
//        }
        ArrayList<IBalanceEffect> effects = new ArrayList<IBalanceEffect>();
        for (IBalanceEffect balanceHandler : balanceHandlers) {
            if (balanceHandler.appliesForBalance(bal) && (AdvancedPotions.rng.nextFloat() <
                    balanceHandler.getProbability(bal))) effects.add(balanceHandler);
        }
        if (effects.size() < 1) effects.add(new BalanceEffectNull());
        return effects;
    }

    public static void registerHandler(IPotionIDHandler handler) {
        for (int i : handler.getHandledIDs()) {
            if (idList.contains(i)) throw new PotionException("Potion ID conflict!");
            idList.add(i);
        }
        handlers.add(handler);
    }

    public static void registerItemHandler(IPotionItemHandler handler) {
        itemHandlers.add(handler);
    }

    public static void registerBalanceHandler(IBalanceEffect handler) {
        balanceHandlers.add(handler);
    }

    public static String getRomanNumeral(int num) {
        if (num >= 1000) return "M+";
        String ret = "";
        String n = String.valueOf(num);
        int l = n.length();

        if (l > 2) {
            switch (Integer.parseInt("" + n.charAt(l - 3))) {
                case 3:
                    ret += "C";
                case 2:
                    ret += "C";
                case 1:
                    ret += "C";
                    break;
                case 4:
                    ret += "C";
                case 5:
                    ret += "D";
                    break;
                case 6:
                    ret += "DC";
                    break;
                case 7:
                    ret += "DCC";
                    break;
                case 8:
                    ret += "DCCC";
                    break;
                case 9:
                    ret += "CM";
                    break;
            }
        }
        if (l > 1) {
            switch (Integer.parseInt("" + n.charAt(l - 2))) {
                case 3:
                    ret += "X";
                case 2:
                    ret += "X";
                case 1:
                    ret += "X";
                    break;
                case 4:
                    ret += "X";
                case 5:
                    ret += "L";
                    break;
                case 6:
                    ret += "LX";
                    break;
                case 7:
                    ret += "LXX";
                    break;
                case 8:
                    ret += "LXXX";
                    break;
                case 9:
                    ret += "XC";
                    break;
            }
        }
        if (l > 0) {
            switch (Integer.parseInt("" + n.charAt(l - 1))) {
                case 3:
                    ret += "I";
                case 2:
                    ret += "I";
                case 1:
                    ret += "I";
                    break;
                case 4:
                    ret += "I";
                case 5:
                    ret += "V";
                    break;
                case 6:
                    ret += "VI";
                    break;
                case 7:
                    ret += "VII";
                    break;
                case 8:
                    ret += "VIII";
                    break;
                case 9:
                    ret += "IX";
                    break;
            }
        }
        return ret;
    }

    public static String getReadableDuration(int ticks) {
        int tsec = MathHelper.floor_double((double) ticks / 20.0);
        int min = MathHelper.floor_double((double) tsec / 60.0);
        int sec = tsec % 60;
        return "" + min + ":" + ((sec < 10) ? ("0" + sec) : sec);
    }

}
