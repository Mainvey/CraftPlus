package com.mainvey.craftplus.event;

import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class AnvilEventHandler {
    @SubscribeEvent
    public abstract void onAnvilUpdate(AnvilUpdateEvent event);
}
