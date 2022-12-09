package it.hurts.sskirillss.reloapi.modules.performer;

import it.hurts.sskirillss.reloapi.modules.performer.actions.*;
import it.hurts.sskirillss.reloapi.modules.performer.actions.base.PerformerAction;

import java.util.function.Function;

public class PerformerBuilder {
    protected final Performer performer;

    public PerformerBuilder(String id) {
        this.performer = new Performer(id);
    }

    public PerformerBuilder thenRun(Runnable action) {
        return append(performer -> new PerformerRunAction(performer, action));
    }

    public PerformerBuilder thenDelay(int ticks) {
        return append(performer -> new PerformerDelayAction(performer, () -> ticks));
    }

    public Performer build(Performer.Side side) {
        performer.setSide(side);

        Performers.getPerformers().putIfAbsent(performer.getId(), performer);

        return performer;
    }

    public Performer build() {
        return build(Performer.Side.NONE);
    }

    protected PerformerBuilder append(Function<Performer, PerformerAction> factory) {
        performer.getActions().add(factory.apply(performer));

        return this;
    }
}