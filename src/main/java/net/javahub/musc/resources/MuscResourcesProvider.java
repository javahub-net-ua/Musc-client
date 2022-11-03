package net.javahub.musc.resources;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.resource.ResourcePackSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import static net.javahub.musc.Musc.LOGGER;

@Environment(EnvType.CLIENT)
public class MuscResourcesProvider implements ResourcePackProvider {
    private final Path packFolder;

    public MuscResourcesProvider() throws IOException {
        packFolder = Path.of(FabricLoader.getInstance().getGameDir() + "/musc-resources");
        if (!Files.exists(packFolder)) {
            Files.createDirectory(packFolder);
        }
    }

    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        ResourcePackProfile container = ResourcePackProfile.of(
                "musc", true, () -> new DirectoryResourcePack(packFolder.toFile()),
                factory, ResourcePackProfile.InsertionPosition.TOP, ResourcePackSource.PACK_SOURCE_SERVER
        );
        if (container != null) profileAdder.accept(container);
        else LOGGER.info("Error loading resources");
    }
}
