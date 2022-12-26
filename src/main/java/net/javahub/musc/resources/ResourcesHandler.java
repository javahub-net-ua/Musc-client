package net.javahub.musc.resources;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static net.javahub.musc.Musc.LOGGER;
import static net.javahub.musc.config.MuscConfig.CONFIG;

public class ResourcesHandler {

    private static final Path PATH = Path.of(FabricLoader.getInstance().getGameDir().toString(), "resources");
    private static volatile Set<Path> resources = null;

    private static Path downloadResources(String address) {
        Path path = PATH.resolve(Path.of(Integer.toHexString(address.hashCode()) + ".zip"));
        try {
            if (!Files.exists(PATH)) Files.createDirectory(PATH);
            Files.deleteIfExists(path);
            URLConnection connection = new URL(address).openConnection();
            connection.setConnectTimeout(10 * 1000);
            LOGGER.info("trying to connect to " + address);
            try (InputStream in = connection.getInputStream()) {
                Files.copy(in, path);
            } catch (ConnectException e) {
                LOGGER.warn(address + " - Connect refused");
            } catch (SocketTimeoutException e) {
                LOGGER.warn(address + " - Connect time out! (10s)");
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return path;
    }

    private static Set<Path> getResources() {
        return CONFIG.servers.stream().map(ResourcesHandler::downloadResources).collect(Collectors.toSet());
    }

    public static Set<Path> getResourcePacks() {
        if (Objects.nonNull(resources)) return resources;
        synchronized(ResourcesHandler.class) {
            if (Objects.isNull(resources)) resources = getResources();
            return resources;
        }
    }
}
