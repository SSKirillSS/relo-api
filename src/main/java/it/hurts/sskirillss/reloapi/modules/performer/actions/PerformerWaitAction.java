package it.hurts.sskirillss.reloapi.modules.performer.actions;

import it.hurts.sskirillss.reloapi.modules.performer.Performer;
import it.hurts.sskirillss.reloapi.modules.performer.actions.base.PerformerAction;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class PerformerWaitAction extends PerformerAction {
    private final Supplier<Boolean> predicate;

    public PerformerWaitAction(Supplier<Boolean> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void tick() {

    }

    @Override
    public boolean isDone() {
        return predicate.get();
    }
}