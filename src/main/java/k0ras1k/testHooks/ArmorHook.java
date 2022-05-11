package k0ras1k.testHooks;

import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.ReturnCondition;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeHooks;

public class ArmorHook {

    @Hook(injectOnExit = true, returnCondition = ReturnCondition.ALWAYS)
    public static int getTotalArmorValue(ForgeHooks fh, EntityPlayer player, @Hook.ReturnValue int returnValue) {
        return returnValue/2;
    }

}
