package net.javahub.musc;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import draylar.omegaconfiggui.OmegaConfigGui;
import net.javahub.musc.prelaunch.MuscPreLaunch;

public class MuscModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            OmegaConfigGui.registerConfigScreen(MuscPreLaunch.CONFIG);
            return parent;
        };
    }
}
