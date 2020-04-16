package com.mainvey.craftplus;

import com.mainvey.craftplus.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = CraftPlus.ID)
public class CraftPlus {
    public static final String ID = "craftplus";

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