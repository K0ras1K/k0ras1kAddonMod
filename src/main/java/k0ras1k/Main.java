package k0ras1k;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import k0ras1k.network.K0ras1KPacketHandler;
import k0ras1k.proxy.CommonProxy;
import k0ras1k.reg.blocks.BlockList;
import k0ras1k.reg.blocks.BlocksSolarPanelRegister;
import k0ras1k.reg.blocks.MainBlocksRegister;
import k0ras1k.reg.items.UpgradeRegister;
import k0ras1k.gui.GuiHandler;
import k0ras1k.utils.MTRecipeConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;


@Mod(modid = "k0ras1k", dependencies = "required-after:IC2")

public class Main {

    @Mod.Instance
    public static Main instance = new Main();

    @SidedProxy(
            clientSide = "k0ras1k.proxy.ClientProxy",
            serverSide = "k0ras1k.proxy.CommonProxy"
    )
    public static CommonProxy proxy;
    public static final String MODID = "k0ras1k";
    public static final String VERSION = "1.0";

    public static final CreativeTabs lightBlocks = new CreativeTabs("lightBlocks") {
        @Override
        public Item getTabIconItem() {
            return Item.getItemFromBlock(BlockList.candy_panel_yellow);
        }
    };


    public static String configFileName;
    public static boolean isSimulating() {
        return !FMLCommonHandler.instance().getEffectiveSide().isClient();
    }
    public static void addLog(String logLine) {
        System.out.println("[AdvancedSolarPanel] " + logLine);
    }

    public static int blockTransformerRenderID;




    @Mod.EventHandler
    public void pre(FMLPreInitializationEvent e) {
        Configuration config = new Configuration(e.getSuggestedConfigurationFile());
        try {
            config.load();
            configFileName = e.getSuggestedConfigurationFile().getAbsolutePath();
        } finally {
            config.save();
        }

        MainBlocksRegister.init();
        BlocksSolarPanelRegister.init();
        UpgradeRegister.init();
        MTRecipeConfig.doDebug();



    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        proxy.initRecipes();
        K0ras1KPacketHandler.load();

    }

    @Mod.EventHandler
    public void post(FMLPostInitializationEvent e) {

    }

}

