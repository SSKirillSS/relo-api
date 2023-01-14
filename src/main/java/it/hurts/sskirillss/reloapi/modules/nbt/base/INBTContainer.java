package it.hurts.sskirillss.reloapi.modules.nbt.base;

import net.minecraft.nbt.CompoundTag;

import javax.annotation.Nonnull;

public interface INBTContainer {
    @Nonnull
    CompoundTag getNBT();
}