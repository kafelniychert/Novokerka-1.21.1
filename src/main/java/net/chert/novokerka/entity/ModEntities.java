package net.chert.novokerka.entity;

import net.chert.novokerka.Novokerka;
import net.chert.novokerka.entity.custom.Zis3Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<Zis3Entity> ZIS3 = Registry.register(Registries.ENTITY_TYPE, Identifier.of(Novokerka.MOD_ID, "zis3"), EntityType.Builder.create(Zis3Entity::new, SpawnGroup.CREATURE)
            .dimensions(2.7f, 2.1f).build());
    public static void registerModEntities(){
        Novokerka.LOGGER.info("Registering Mod Entities for " + Novokerka.MOD_ID);
    }
}
