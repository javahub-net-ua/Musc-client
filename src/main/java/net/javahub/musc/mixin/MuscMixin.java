package net.javahub.musc.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.javahub.musc.resources.MuscResourcesProvider;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Environment(EnvType.CLIENT)
@Mixin(ResourcePackManager.class)
public abstract class MuscMixin {
    @Shadow
    private Set<ResourcePackProvider> providers;

    @Inject(method = "<init>*", at = @At("TAIL"))
    private void registerLoader(CallbackInfo info) throws IOException {
        this.providers = new HashSet(this.providers);
        this.providers.add(new MuscResourcesProvider());
    }
}