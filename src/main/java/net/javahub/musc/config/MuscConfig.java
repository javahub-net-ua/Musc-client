package net.javahub.musc.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import java.util.ArrayList;
import java.util.List;

@Config(name = "musc")
@Config.Gui.Background("minecraft:textures/block/sandstone_bottom.png")
public class MuscConfig implements ConfigData, ModMenuApi {

    @ConfigEntry.Gui.Excluded
    public static final MuscConfig CONFIG = AutoConfig.register(MuscConfig.class, GsonConfigSerializer::new).getConfig();

    public final List<String> servers = new ArrayList<>();

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> AutoConfig.getConfigScreen(MuscConfig.class, parent).get();
    }
}
