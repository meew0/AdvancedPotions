package meew0.ap.effects;

/**
 * Created by meew0 on 06.04.14.
 */
public class BalanceEffectTeleportL2 extends BalanceEffectTeleportL1 {
    @Override
    protected int getLevel() {
        return 16;
    }

    @Override
    public String getUnlocalizedEffectMessage() {
        return "ap.balanceMessage.teleport.2.name";
    }
}
