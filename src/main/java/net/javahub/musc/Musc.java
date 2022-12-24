package net.javahub.musc;

import net.fabricmc.api.ModInitializer;
import net.javahub.musc.discs.MuscItems;
import net.javahub.musc.records.Record;
import net.javahub.musc.records.RecordUtils;
import net.javahub.musc.resources.ResourcesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.Set;

public class Musc implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("Musc");

    @Override
    public void onInitialize() {
        Set<Path> resources = ResourcesHandler.getResourcePacks();
        Set<Record> records = RecordUtils.getRecords(resources);
        records.forEach(MuscItems::registerRecord);
    }
}
