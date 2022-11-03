package net.javahub.musc.discs;

import net.javahub.musc.Musc;
import net.javahub.musc.records.Record;
import net.minecraft.item.Item;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class MuscItems {

    static final Item COCONUT = registerItem("coconut_jpg", registerSoundEvent("coconut_jpg"));

    private static Item registerItem(String name, SoundEvent sound) {
        Item.Settings settings = new Item.Settings()
                .rarity(Rarity.RARE).maxCount(1).group(MuscItemsGroup.MUSC_GROUP);
        Identifier id = new Identifier(Musc.MOD_ID, name);
        MuscDiscItem disc = new MuscDiscItem(14, sound, settings);
        return Registry.register(Registry.ITEM, id, disc);
    }

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(Musc.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

    public static Item registerRecord(Record record) {
        SoundEvent soundEvent = registerSoundEvent(record.getSoundEventID());
        return registerItem(record.getItemID(), soundEvent);
    }
}
