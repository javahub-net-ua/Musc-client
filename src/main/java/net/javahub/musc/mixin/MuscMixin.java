package net.javahub.musc.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.javahub.musc.resources.MuscResourcesProvider;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
@Mixin(ResourcePackManager.class)
public abstract class MuscMixin {

    @Mutable
    @Final
    @Shadow
    private Set<ResourcePackProvider> providers;

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void registerLoader(CallbackInfo info) {
        this.providers = new HashSet<>(this.providers);
        this.providers.add(new MuscResourcesProvider());
    }
}
