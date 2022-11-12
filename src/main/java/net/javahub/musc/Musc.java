package net.javahub.musc;

import draylar.omegaconfiggui.OmegaConfigGui;
import net.fabricmc.api.ModInitializer;
import net.javahub.musc.discs.MuscItemsProvider;
import net.javahub.musc.prelaunch.MuscPreLaunch;

import java.io.IOException;

public class Musc implements ModInitializer {

    @Override
    public void onInitialize() {
        MuscItemsProvider.initItems();
    }
}
