package net.javahub.musc.discs;

import net.minecraft.item.MusicDiscItem;
import net.minecraft.sound.SoundEvent;

class MuscDiscItem extends MusicDiscItem {

    public MuscDiscItem(int comparatorOutput, SoundEvent sound, Settings settings) {
        super(comparatorOutput, sound, settings, 0);
    }
}
