package it.hurts.sskirillss.reloapi.modules.performer.data;

import it.hurts.sskirillss.reloapi.modules.performer.data.base.PerformerData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.world.entity.player.Player;

@AllArgsConstructor
public class PlayerPerformerData extends PerformerData {
    @Getter
    private final Player player;
}