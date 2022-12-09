package it.hurts.sskirillss.reloapi.modules.performer.actions;

import it.hurts.sskirillss.reloapi.modules.performer.Performer;
import it.hurts.sskirillss.reloapi.modules.performer.actions.base.PerformerAction;

import java.util.function.Supplier;

public class PerformerDelayAction extends PerformerAction {
    private int ticks;

    public PerformerDelayAction(Performer performer, Supplier<Integer> ticks) {
        this.ticks = ticks.get();
    }

    @Override
    public void tick() {
        if (ticks > 0)
            ticks--;
    }

    @Override
    public boolean isDone() {
        return ticks <= 0;
    }
}