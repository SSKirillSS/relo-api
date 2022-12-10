package it.hurts.sskirillss.reloapi.modules.performer;

import it.hurts.sskirillss.reloapi.modules.performer.actions.PerformerDelayAction;
import it.hurts.sskirillss.reloapi.modules.performer.actions.PerformerRunAction;
import it.hurts.sskirillss.reloapi.modules.performer.actions.PerformerWaitAction;
import it.hurts.sskirillss.reloapi.modules.performer.actions.base.PerformerAction;
import it.hurts.sskirillss.reloapi.modules.performer.data.base.PerformerData;
import lombok.Getter;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PerformerBuilder<T extends PerformerData> {
    @Getter
    protected final Performer<T> performer;

    public static <T extends PerformerData> Performer<? extends PerformerData> builder(String id, T data, BiConsumer<T, PerformerBuilder<T>> builder) {
        return Performers.getPerformers().getOrDefault(id, new PerformerBuilder<>(id, data, builder).getPerformer());
    }

    public static <T extends PerformerData> Performer<? extends PerformerData> builder(String id, BiConsumer<T, PerformerBuilder<T>> builder) {
        return Performers.getPerformers().getOrDefault(id, new PerformerBuilder<>(id, (T) new PerformerData(), builder).getPerformer());
    }

    protected PerformerBuilder(String id, T data, BiConsumer<T, PerformerBuilder<T>> builder) {
        this.performer = new Performer<>(id, data);

        builder.accept(performer.getData(), this);
    }

    public PerformerBuilder<T> thenRun(Runnable action) {
        return addAction(performer -> new PerformerRunAction(action));
    }

    public PerformerBuilder<T> thenDelay(int ticks) {
        return addAction(performer -> new PerformerDelayAction(() -> ticks));
    }

    public PerformerBuilder<T> thenWaitUntil(Supplier<Boolean> predicate) {
        return addAction(performer -> new PerformerWaitAction(predicate));
    }

    public void build(Performer.Side side) {
        performer.setSide(side);
        performer.setConstructed(true);

        Performers.getPerformers().putIfAbsent(performer.getId(), performer);
    }

    public void build() {
        build(Performer.Side.NONE);
    }

    protected PerformerBuilder<T> addAction(Function<Performer<T>, PerformerAction> factory) {
        PerformerAction action = factory.apply(performer);

        if (performer.isConstructed())
            performer.getActions().add(performer.getStage(), action);
        else
            performer.getActions().add(action);

        return this;
    }
}