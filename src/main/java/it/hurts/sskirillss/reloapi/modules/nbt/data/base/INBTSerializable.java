package it.hurts.sskirillss.reloapi.modules.nbt.data.base;

import net.minecraft.nbt.CompoundTag;

public interface INBTSerializable<T> {
    CompoundTag toNBT();

    T fromNBT(CompoundTag nbt);
}