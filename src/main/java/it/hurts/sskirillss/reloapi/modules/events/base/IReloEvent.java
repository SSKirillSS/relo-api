package it.hurts.sskirillss.reloapi.modules.events.base;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public interface IReloEvent<T extends Event> {
    default T fire() {
        MinecraftForge.EVENT_BUS.post((T) this);

        return (T) this;
    }
}