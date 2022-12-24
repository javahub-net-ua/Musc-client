package net.javahub.musc.resources;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resource.*;
import net.minecraft.text.Text;

import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class MuscResourcesProvider implements ResourcePackProvider {

    public static void forEachProfile(Path path, BiConsumer<Path, ResourcePackProfile.PackFactory> consumer) {
        ResourcePackProfile.PackFactory packFactory = FileResourcePackProvider.getFactory(path, true);
        if (Objects.nonNull(packFactory)) consumer.accept(path, packFactory);
    }

    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder) {
        Set<Path> paths = ResourcesHandler.getResourcePacks();
        paths.forEach(p -> forEachProfile(p, (path, packFactory) -> {
            String name = path.getFileName().toString();
            ResourcePackProfile resourcePackProfile = ResourcePackProfile.create(name,
                    Text.literal(name), true, packFactory, ResourceType.CLIENT_RESOURCES,
                    ResourcePackProfile.InsertionPosition.TOP, ResourcePackSource.SERVER);
            if (Objects.nonNull(profileAdder)) profileAdder.accept(resourcePackProfile);
        }));
    }
}
