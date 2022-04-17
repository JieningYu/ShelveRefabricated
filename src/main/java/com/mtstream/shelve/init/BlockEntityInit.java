package com.mtstream.shelve.init;

import com.mtstream.shelve.blockEntity.HumidityDetectorBlockEntity;
import com.mtstream.shelve.blockEntity.StaticDetectorBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntityInit {
    public static BlockEntityType<HumidityDetectorBlockEntity> HUMIDITY_DETECTOR;
    public static BlockEntityType<StaticDetectorBlockEntity> STATIC_DETECTOR;

    public static void registerBlockEntities() {
        HUMIDITY_DETECTOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "shelve:humidity_detector_block_entity", FabricBlockEntityTypeBuilder.create(HumidityDetectorBlockEntity::new, BlockInit.HUMIDITY_DETECTOR).build(null));
        STATIC_DETECTOR = Registry.register(Registry.BLOCK_ENTITY_TYPE, "shelve:static_detector_block_entity", FabricBlockEntityTypeBuilder.create(StaticDetectorBlockEntity::new, BlockInit.STATIC_DETECTOR).build(null));
    }
}
