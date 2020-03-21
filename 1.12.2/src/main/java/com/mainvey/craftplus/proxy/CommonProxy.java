package com.mainvey.craftplus.proxy;

import com.mainvey.craftplus.register.CraftingRegister;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class CommonProxy {
    public void init(FMLInitializationEvent event){
        new CraftingRegister();
    }
}
