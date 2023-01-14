package it.hurts.sskirillss.reloapi.modules.nbt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.hurts.sskirillss.reloapi.modules.nbt.base.INBTContainer;
import lombok.Getter;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public record NBTProcessor(@Getter CompoundTag tag) {
    private static final Gson SERIALIZER = new GsonBuilder()
            .disableHtmlEscaping()
            .create();

    public static NBTProcessor createProcessor(Entity entity) {
        return new NBTProcessor(entity.getPersistentData());
    }

    public static NBTProcessor createProcessor(ItemStack stack) {
        return new NBTProcessor(stack.getOrCreateTag());
    }

    public static NBTProcessor createProcessor(INBTContainer container) {
        return new NBTProcessor(container.getNBT());
    }

    public static NBTProcessor createProcessor(CompoundTag tag) {
        return new NBTProcessor(tag);
    }

    public CompoundTag getCompound(String key, CompoundTag defaultValue) {
        return tag.contains(key) ? tag.getCompound(key) : defaultValue;
    }

    public NBTProcessor setCompound(String key, CompoundTag value) {
        tag.put(key, value);

        return this;
    }

    public int getInt(String key, int defaultValue) {
        return tag.contains(key) ? tag.getInt(key) : defaultValue;
    }

    public NBTProcessor setInt(String key, int value) {
        tag.putInt(key, value);

        return this;
    }

    public String getString(String key, String defaultValue) {
        return tag.contains(key) ? tag.getString(key) : defaultValue;
    }

    public NBTProcessor setString(String key, String value) {
        tag.putString(key, value);

        return this;
    }

    public double getDouble(String key, double defaultValue) {
        return tag.contains(key) ? tag.getDouble(key) : defaultValue;
    }

    public NBTProcessor setDouble(String key, double value) {
        tag.putDouble(key, value);

        return this;
    }

    public Object getObject(String key, Class<?> type, Object defaultValue) {
        return tag.contains(key) ? SERIALIZER.fromJson(tag.getString(key), type) : defaultValue;
    }

    public NBTProcessor setObject(String key, Object value) {
        tag.putString(key, SERIALIZER.toJson(value, value.getClass()));

        return this;
    }
}