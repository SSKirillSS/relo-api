package it.hurts.sskirillss.reloapi.mixin;

import it.hurts.sskirillss.reloapi.modules.events.LivingSlippingEvent;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @ModifyVariable(method = "travel", index = 8, ordinal = 0, at = @At("STORE"))
    protected float setBlockFriction(float original) {
        LivingEntity entity = (LivingEntity) (Object) this;

        return new LivingSlippingEvent(entity, entity.level.getBlockState(entity.getBlockPosBelowThatAffectsMyMovement()), original).fire().getFriction();
    }
}