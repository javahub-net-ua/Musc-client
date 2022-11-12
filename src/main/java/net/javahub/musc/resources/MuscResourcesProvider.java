package net.javahub.musc.resources;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ZipResourcePack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;

import static net.javahub.musc.prelaunch.MuscPreLaunch.CONFIG;
import static net.javahub.musc.prelaunch.MuscPreLaunch.LOGGER;

@Environment(EnvType.CLIENT)
public class MuscResourcesProvider implements ResourcePackProvider {

    private final Path DIR;
    private final Set<File> PACKS = new LinkedHashSet<>();

    public MuscResourcesProvider() throws IOException {
        DIR = Path.of(FabricLoader.getInstance().getGameDir() + File.separator + "resources");
        if (!Files.exists(DIR)) Files.createDirectory(DIR);
    }

//    public Set<File> downloadResources() {
//        for (MuscConfig.Listening server: CONFIG.servers) {
//            Path dst = Path.of(DIR + File.separator + String.format("%s:%s.zip", server.hostname, server.port));
//            File resources = MuscTCPClient.download();
//            PACKS.add(resources);
//        }
//        return null;
//    }

    @Override
    public void register(Consumer<ResourcePackProfile> profileAdder, ResourcePackProfile.Factory factory) {
        for (File pack: PACKS) {
            ResourcePackProfile container = ResourcePackProfile.of(
                    "musc", true, () -> new ZipResourcePack(pack),
                    factory, ResourcePackProfile.InsertionPosition.TOP, ResourcePackSource.PACK_SOURCE_SERVER
            );
            if (container != null) profileAdder.accept(container);
            else LOGGER.warn("Error loading resources");
        }
    }
}
