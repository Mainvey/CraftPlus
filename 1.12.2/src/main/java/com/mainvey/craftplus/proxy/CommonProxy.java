package com.mainvey.craftplus.proxy;

import com.mainvey.craftplus.command.GetNBTCommand;
import com.mainvey.craftplus.register.ConfigRegister;
import com.mainvey.craftplus.register.CraftingRegister;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {
    public void init(FMLInitializationEvent event) {
        new CraftingRegister();
    }

    public void serverStart(FMLServerStartingEvent event) {
        if(ConfigRegister.GetNBTCommand)
            event.registerServerCommand(new GetNBTCommand());
    }
}
