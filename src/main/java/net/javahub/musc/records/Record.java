package net.javahub.musc.records;

import net.minecraft.util.Identifier;

public class Record {

    private final String id;

    private Record(String id) {
        this.id = id;
    }

    public Identifier getSoundEventID() {
        return new Identifier("musc", String.format("music_disc.%s", id.replace("@", ".")));
    }

    public Identifier getItemID() {
        return new Identifier("musc", String.format("music_disc_%s", id.replace("@", "_")));
    }
}
