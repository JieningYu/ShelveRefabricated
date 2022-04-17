package com.mtstream.shelve;

import com.mtstream.shelve.init.BlockInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;

public class ShelveClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.WATER_CAGE, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.HARVESTER, RenderType.cutout());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockInit.CRYSTAL_BALL, RenderType.translucent());
		Shelve.LOGGER.info("Client initialized");
	}
}
