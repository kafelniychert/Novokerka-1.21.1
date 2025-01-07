package net.chert.novokerka;

import net.chert.novokerka.entity.ModEntities;
import net.chert.novokerka.entity.client.Zis3Model;
import net.chert.novokerka.entity.client.Zis3Renderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class NovokerkaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(Zis3Model.ZIS3, Zis3Model::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ZIS3, Zis3Renderer::new);
    }
}
