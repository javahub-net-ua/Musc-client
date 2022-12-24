package net.javahub.musc.records;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static net.javahub.musc.Musc.LOGGER;

public class RecordUtils {

    private static LinkedHashSet<Record> parseToRecords(String json) {
        LinkedHashSet<Record> records = new Gson().fromJson(json, new TypeToken<LinkedHashSet<Record>>(){}.getType());
        return Objects.nonNull(records) ? records : new LinkedHashSet<>();
    }

    private static String getMuscJson(Path pack) {
        String json = "";
        try (ZipFile zip = new ZipFile(pack.toFile())) {
            ZipEntry entry = zip.stream().filter(e -> e.getName().equals("musc.json")).findAny().orElse(null);
            if (entry != null) json = new String(zip.getInputStream(entry).readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException ignored) {
            LOGGER.warn(pack.getFileName().toString());
        }
        return json;
    }

    public static LinkedHashSet<Record> getRecords(Set<Path> packs) {
        return packs.stream()
                .map(RecordUtils::getMuscJson)
                .map(RecordUtils::parseToRecords)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
