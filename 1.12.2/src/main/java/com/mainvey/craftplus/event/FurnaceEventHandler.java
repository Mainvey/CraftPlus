package com.mainvey.craftplus.event;

import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class FurnaceEventHandler {
    @SubscribeEvent
    public abstract void getFuelValue(FurnaceFuelBurnTimeEvent event);
}
