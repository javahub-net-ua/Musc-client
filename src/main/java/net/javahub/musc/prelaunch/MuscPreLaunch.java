package net.javahub.musc.prelaunch;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.javahub.musc.config.MuscConfig;
import net.javahub.musc.records.Record;
import net.javahub.musc.records.RecordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;

public class MuscPreLaunch implements PreLaunchEntrypoint {

    public static final String MOD_ID = "musc";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final MuscConfig CONFIG = MuscConfig.init();
    public static final Set<Record> RECORDS = RecordUtils.getRecords();

    @Override
    public void onPreLaunch() {

    }
}
