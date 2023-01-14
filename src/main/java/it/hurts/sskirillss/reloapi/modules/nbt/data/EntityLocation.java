package it.hurts.sskirillss.reloapi.modules.nbt.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityLocation {
    private final Vec3 pos;
    private final ResourceLocation level;

    public EntityLocation(Entity entity) {
        this.pos = entity.position();
        this.level = entity.getLevel().dimension().location();
    }

    public EntityLocation(Vec3 pos, Level level) {
        this.pos = pos;
        this.level = level.dimension().location();
    }

    public EntityLocation(Vec3 pos, ResourceLocation level) {
        this.pos = pos;
        this.level = level;
    }

    public Vec3 getPos() {
        return pos;
    }

    public ResourceLocation getLevel() {
        return level;
    }
}