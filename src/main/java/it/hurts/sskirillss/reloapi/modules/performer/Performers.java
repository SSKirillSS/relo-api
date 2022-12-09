package it.hurts.sskirillss.reloapi.modules.performer;

import lombok.Getter;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

public class Performers {
    @Getter
    private static final Map<String, Performer> performers = new HashMap<>();

    @Mod.EventBusSubscriber
    public static class Events {
        @SubscribeEvent
        public static void onTick(TickEvent event) {
            for (Performer performer : performers.values()) {
                TickEvent.Type currentType = event.type;
                Performer.Side requiredType = performer.getSide();

                if ((currentType == TickEvent.Type.SERVER && (requiredType == Performer.Side.SERVER || requiredType == Performer.Side.COMMON))
                        || (currentType == TickEvent.Type.CLIENT && (requiredType == Performer.Side.CLIENT || requiredType == Performer.Side.COMMON)))
                    performer.tick();
            }
        }
    }
}