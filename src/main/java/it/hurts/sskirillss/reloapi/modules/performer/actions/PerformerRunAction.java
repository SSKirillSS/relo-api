package it.hurts.sskirillss.reloapi.modules.performer.actions;

import it.hurts.sskirillss.reloapi.modules.performer.Performer;
import it.hurts.sskirillss.reloapi.modules.performer.actions.base.PerformerAction;

public class PerformerRunAction extends PerformerAction {
    private final Runnable action;
    private boolean isDone;

    public PerformerRunAction(Runnable action) {
        this.action = action;

        isDone = false;
    }

    @Override
    public void tick() {
        action.run();

        isDone = true;
    }

    @Override
    public boolean isDone() {
        return isDone;
    }
}