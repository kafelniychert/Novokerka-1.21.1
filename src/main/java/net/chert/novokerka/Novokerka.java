package net.chert.novokerka;

import net.chert.novokerka.entity.ModEntities;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Novokerka implements ModInitializer {
	public static final String MOD_ID = "novokerka";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntities.registerModEntities();
	}
}