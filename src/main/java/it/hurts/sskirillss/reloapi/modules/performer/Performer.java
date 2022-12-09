package it.hurts.sskirillss.reloapi.modules.performer;

import it.hurts.sskirillss.reloapi.modules.performer.actions.base.PerformerAction;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Performer {
    @Getter
    private final String id;

    @Getter
    private final List<PerformerAction> actions = new ArrayList<>();
    @Getter
    @Setter
    private int stage;

    @Getter
    @Setter
    private Side side = Side.NONE;

    public Performer(String id) {
        this.id = id;
    }

    public void tick() {
        if (stage < actions.size()) {
            PerformerAction action = actions.get(stage);

            if (action.isDone()) {
                ++stage;
            } else
                action.tick();
        } else {
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