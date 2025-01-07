package net.chert.novokerka;

import net.chert.novokerka.entity.ModEntities;
import net.chert.novokerka.entity.client.Zis3Model;
import net.chert.novokerka.entity.custom.Zis3Entity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Novokerka implements ModInitializer {
	public static final String MOD_ID = "novokerka";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntities.registerModEntities();
		FabricDefaultAttributeRegistry.register(ModEntities.ZIS3, Zis3Entity.createAttributes());
	}
}