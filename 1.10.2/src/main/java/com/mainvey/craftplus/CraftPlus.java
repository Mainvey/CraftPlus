package com.mainvey.craftplus;

import com.mainvey.craftplus.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = CraftPlus.ID)
public class CraftPlus {
    public static final String ID = "craftplus";

    @Instance(CraftPlus.ID)
    public static CraftPlus Instance;

    @SidedProxy(clientSide = "com.mainvey.craftplus.proxy.ClientProxy",
                serverSide = "com.mainvey.craftplus.proxy.CommonProxy",
                modId = CraftPlus.ID)
    public static CommonProxy Proxy;

    @EventHandler
    public void  preInit(FMLPreInitializationEvent event) {
        Proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        Proxy.init(event);
    }

    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        Proxy.serverStart(event);
    }
}
