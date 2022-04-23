package com.mtstream.shelve.init;

import com.mtstream.shelve.Shelve;
import com.mtstream.shelve.block.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;

public class BlockInit {
    // Temporarily removed trash cans
    public static final FireCrackerBlock FIRECRACKER = new FireCrackerBlock(FabricBlockSettings.of(Material.EXPLOSIVE).instabreak().dynamicShape().sound(SoundType.GRASS));
    public static final ChannelerBlock CHANNELER = new ChannelerBlock(FabricBlockSettings.copy(Blocks.COPPER_BLOCK).dynamicShape().sound(SoundType.COPPER).strength(3.0f).requiresCorrectToolForDrops());
    public static final WaterCageBlock WATER_CAGE = new WaterCageBlock(FabricBlockSettings.of(Material.METAL, MaterialColor.COLOR_BLUE).dynamicShape().sound(SoundType.LANTERN).strength(2.8f).requiresCorrectToolForDrops());
    public static final IgniterBlock IGNITER = new IgniterBlock(FabricBlockSettings.copy(Blocks.BLACKSTONE).sound(SoundType.STONE).strength(1.5f).requiresCorrectToolForDrops());
    public static final HarvesterBlock HARVESTER = new HarvesterBlock(FabricBlockSettings.copy(Blocks.PISTON).dynamicShape().sound(SoundType.STONE).strength(1.5f).requiresCorrectToolForDrops());
    public static final HumidityDetectorBlock HUMIDITY_DETECTOR = new HumidityDetectorBlock(FabricBlockSettings.copy(Blocks.DAYLIGHT_DETECTOR).dynamicShape().sound(SoundType.STONE).strength(1.5f).requiresCorrectToolForDrops());
    public static final StaticDetectorBlock STATIC_DETECTOR = new StaticDetectorBlock(FabricBlockSettings.copy(Blocks.DAYLIGHT_DETECTOR).dynamicShape().sound(SoundType.COPPER).strength(1.5f).requiresCorrectToolForDrops());
    public static final MilkCauldron MILK_CAULDRON = new MilkCauldron(FabricBlockSettings.copy(Blocks.CAULDRON).dynamicShape().sound(SoundType.METAL).strength(2.0f).requiresCorrectToolForDrops());
    public static final CrystalBallBlock CRYSTAL_BALL = new CrystalBallBlock(FabricBlockSettings.copy(Blocks.AMETHYST_BLOCK).dynamicShape().sound(SoundType.AMETHYST).lightLevel($->12).strength(1.5f).requiresCorrectToolForDrops());
    public static final InstantTntBlock INSTANT_TNT = new InstantTntBlock(FabricBlockSettings.of(Material.EXPLOSIVE).instabreak().sound(SoundType.GRASS));
    public static final CheeseCakeBlock CHEESE_CAKE = new CheeseCakeBlock(FabricBlockSettings.copy(Blocks.CAKE).dynamicShape().sound(SoundType.METAL).strength(2.0f));

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "firecracker"), FIRECRACKER);
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "channeler"), CHANNELER);
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "water_cage"), WATER_CAGE);
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "igniter"), IGNITER);
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "harvester"), HARVESTER);
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "humidity_detector"), HUMIDITY_DETECTOR);
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "static_detector"),STATIC_DETECTOR);
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "milk_cauldron"), MILK_CAULDRON);
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "crystal_ball"), CRYSTAL_BALL);
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "instant_tnt"), INSTANT_TNT);
        Registry.register(Registry.BLOCK, new ResourceLocation(Shelve.MOD_ID, "cheese_cake"), CHEESE_CAKE);

        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "firecracker"), new BlockItem(FIRECRACKER, new FabricItemSettings().group(CreativeModeTab.TAB_MISC)));
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "channeler"), new BlockItem(CHANNELER, new FabricItemSettings().group(CreativeModeTab.TAB_REDSTONE)));
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "water_cage"), new BlockItem(WATER_CAGE, new FabricItemSettings().group(CreativeModeTab.TAB_REDSTONE)));
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "igniter"), new BlockItem(IGNITER, new FabricItemSettings().group(CreativeModeTab.TAB_REDSTONE)));
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "harvester"), new BlockItem(HARVESTER, new FabricItemSettings().group(CreativeModeTab.TAB_REDSTONE)));
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "humidity_detector"), new BlockItem(HUMIDITY_DETECTOR, new FabricItemSettings().group(CreativeModeTab.TAB_REDSTONE)));
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "static_detector"), new BlockItem(STATIC_DETECTOR, new FabricItemSettings().group(CreativeModeTab.TAB_REDSTONE)));
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "milk_cauldron"), new BlockItem(MILK_CAULDRON, new FabricItemSettings()));
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "crystal_ball"), new BlockItem(CRYSTAL_BALL, new FabricItemSettings().group(CreativeModeTab.TAB_DECORATIONS)));
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "instant_tnt"), new BlockItem(INSTANT_TNT, new FabricItemSettings().group(CreativeModeTab.TAB_REDSTONE)));
        Registry.register(Registry.ITEM, new ResourceLocation(Shelve.MOD_ID, "cheese_cake"), new BlockItem(CHEESE_CAKE, new FabricItemSettings().group(CreativeModeTab.TAB_FOOD)));
    }
}
