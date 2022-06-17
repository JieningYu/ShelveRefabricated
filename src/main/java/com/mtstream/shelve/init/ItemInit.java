package com.mtstream.shelve.init;

import com.mtstream.shelve.Shelve;
import com.mtstream.shelve.item.*;
import com.mtstream.shelve.item.boxitem.InfernalBoxItem;
import com.mtstream.shelve.item.boxitem.MarineBoxItem;
import com.mtstream.shelve.item.boxitem.MysteriousBoxItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ItemInit {
    public static final Item CHEESE = new Item(new FabricItemSettings().group(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(5).saturationMod(4).build()));
    public static final TurfItem TURF = new TurfItem(new FabricItemSettings().group(CreativeModeTab.TAB_MISC));
    public static final Item MARINIDE_INGOT = new Item(new FabricItemSettings().group(CreativeModeTab.TAB_MISC));
    public static final HandheldDispenserItem HANDHELD_DISPENSER = new HandheldDispenserItem(new FabricItemSettings().group(CreativeModeTab.TAB_TOOLS).stacksTo(1));
    public static final Item AMETHYST_BALL = new Item(new FabricItemSettings().group(CreativeModeTab.TAB_MISC));
    // public static final TransmutationMagnetItem TRANSMUTATION_MAGNET = new TransmutationMagnetItem(new FabricItemSettings().group(CreativeModeTab.TAB_TOOLS).stacksTo(1));
    public static final Item EMPTY_BOX = new Item(new FabricItemSettings());
    public static final MysteriousBoxItem MYSTERIOUS_BOX = new MysteriousBoxItem(new FabricItemSettings());
    public static final InfernalBoxItem INFERNAL_BOX = new InfernalBoxItem(new FabricItemSettings());
    public static final MarineBoxItem MARINE_BOX = new MarineBoxItem(new FabricItemSettings());

    public static void registerItems() {
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "cheese"), CHEESE);
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "turf"), TURF);
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "marinide_ingot"), MARINIDE_INGOT);
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "handheld_dispenser"), HANDHELD_DISPENSER);
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "amethyst_ball"), AMETHYST_BALL);
        // Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "transmutation_magnet"), TRANSMUTATION_MAGNET);
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "empty_box"), EMPTY_BOX);
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "mysterious_box"), MYSTERIOUS_BOX);
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "infernal_box"), INFERNAL_BOX);
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "marine_box"), MARINE_BOX);
    }
}
