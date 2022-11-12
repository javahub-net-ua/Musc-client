package net.javahub.musc.records;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class RecordUtils {

    public static Set<Record> getRecords() {
        return new LinkedHashSet<>(parseMuscJson());
    }

    private static Set<Record> parseMuscJson() {
        String json = null;
        try (ZipFile zip = new ZipFile(".musc.zip")) {
            Set<ZipEntry> entry = zip.stream()
                    .filter(e -> e.getName().equals("musc.json")).collect(Collectors.toSet());
            for (ZipEntry e: entry) {
                json = new String(zip.getInputStream(e).readAllBytes(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(json, new TypeToken<LinkedHashSet<Record>>(){}.getType());
    }
}
