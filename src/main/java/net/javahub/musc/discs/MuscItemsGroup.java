package net.javahub.musc.discs;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.javahub.musc.Musc;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

abstract class MuscItemsGroup implements ModInitializer {
    public static final ItemGroup MUSC_GROUP = FabricItemGroupBuilder.create(
            new Identifier(Musc.MOD_ID, "group"))
            .icon(() -> new ItemStack(MuscItems.COCONUT)).build();
}
