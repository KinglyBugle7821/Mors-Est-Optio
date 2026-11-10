    package net.numericalk.meo.mixin;

    import net.minecraft.world.attribute.BedRule;
    import net.minecraft.world.level.Level;
    import org.spongepowered.asm.mixin.Mixin;
    import org.spongepowered.asm.mixin.injection.At;
    import org.spongepowered.asm.mixin.injection.Inject;
    import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

    @Mixin(BedRule.class)
    public class SleepingMixin {
        @Inject(method = "canSleep", at = @At("HEAD"), cancellable = true)
        private void meo$alwaysAllowSleeping(Level level, CallbackInfoReturnable<Boolean> cir) {
            cir.setReturnValue(true);
        }
    }
