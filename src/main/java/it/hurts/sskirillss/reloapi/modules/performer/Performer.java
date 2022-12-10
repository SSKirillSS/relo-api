package it.hurts.sskirillss.reloapi.modules.performer;

import it.hurts.sskirillss.reloapi.modules.performer.actions.base.PerformerAction;
import it.hurts.sskirillss.reloapi.modules.performer.data.base.PerformerData;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Performer<T extends PerformerData> {
    @Getter
    private final String id;
    @Getter
    private final T data;

    @Getter
    private final List<PerformerAction> actions = new ArrayList<>();
    @Getter
    @Setter
    private int stage;
    @Getter
    @Setter
    private boolean isConstructed;

    @Getter
    @Setter
    private Side side = Side.NONE;

    public Performer(String id, T data) {
        this.id = id;
        this.data = data;
        this.isConstructed = false;
    }

    public void tick() {
        if (!isConstructed)
            return;

        if (stage < actions.size()) {
            PerformerAction action = actions.get(stage);

            if (action.isDone()) {
                ++stage;
            } else
                action.tick();
        } else {
            stage = 0;

            Performers.getPerformers().remove(id);
        }
    }

    public enum Side {
        SERVER,
        CLIENT,
        COMMON,
        NONE
    }
}