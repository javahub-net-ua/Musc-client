package net.javahub.musc.discs;

import static net.javahub.musc.prelaunch.MuscPreLaunch.RECORDS;

public class MuscItemsProvider {

    public static void initItems() {
        RECORDS.forEach(MuscItems::registerRecord);
    }
}
