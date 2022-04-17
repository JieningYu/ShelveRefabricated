package com.mtstream.shelve;

import com.mtstream.shelve.init.BlockEntityInit;
import com.mtstream.shelve.init.BlockInit;
import com.mtstream.shelve.init.ItemInit;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Shelve implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Shelve");
	public static final String MOD_ID = "shelve";

	@Override
	public void onInitialize() {
		BlockInit.registerBlocks();
		BlockEntityInit.registerBlockEntities();
		ItemInit.registerItems();
		LOGGER.info("Initialized");
	}
}
