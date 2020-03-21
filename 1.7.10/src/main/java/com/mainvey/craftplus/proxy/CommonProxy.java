package com.mainvey.craftplus.proxy;

import com.mainvey.craftplus.register.ConfigRegister;
import com.mainvey.craftplus.register.CraftingRegister;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


public class CommonProxy {
    protected ConfigRegister configRegister;

    public void preInit(FMLPreInitializationEvent event){
        configRegister = new ConfigRegister(event);
    }

    public void init(FMLInitializationEvent event){
        new CraftingRegister(configRegister);
    }
}
