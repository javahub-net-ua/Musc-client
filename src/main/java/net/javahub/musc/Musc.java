package net.javahub.musc;

import draylar.omegaconfig.OmegaConfig;
import net.fabricmc.api.ModInitializer;
import net.javahub.musc.config.MuscConfig;
import net.javahub.musc.logging.MuscLogger;

public class Musc implements ModInitializer {

    public static final String MOD_ID = "musc";
    public static final MuscConfig CONFIG = MuscConfig.init();
    public static final MuscLogger LOGGER = new MuscLogger();

    @Override
    public void onInitialize() {

    }
}