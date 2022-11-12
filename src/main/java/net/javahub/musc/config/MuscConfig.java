package net.javahub.musc.config;

import draylar.omegaconfig.OmegaConfig;
import draylar.omegaconfig.api.Config;

import java.util.ArrayList;
import java.util.List;

public class MuscConfig implements Config {

    public List<Listening> servers = new ArrayList<>();

    public static class Listening {
        public String hostname;
        public int port;
    }

    public String getName() {
        return  "Musc";
    }

    public String getDirectory() {
        return "/musc/";
    }

    public static MuscConfig init() {
        return OmegaConfig.register(MuscConfig.class);
    }
}
