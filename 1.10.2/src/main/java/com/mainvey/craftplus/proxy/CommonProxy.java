package com.mainvey.craftplus.proxy;

import com.mainvey.craftplus.command.GetNBTCommand;
import com.mainvey.craftplus.register.ConfigRegister;
import com.mainvey.craftplus.register.CraftingRegister;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {
    protected ConfigRegister config;

    public void preInit(FMLPreInitializationEvent event){
        config = new ConfigRegister(event);
    }

    public void init(FMLInitializationEvent event){
        new CraftingRegister(config);
    }

    public void serverStart(FMLServerStartingEvent event) {
        if(config.GetNBTCommand)
            event.registerServerCommand(new GetNBTCommand());
    }
}
