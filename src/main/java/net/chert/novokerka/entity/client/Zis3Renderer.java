package net.chert.novokerka.entity.client;

import net.chert.novokerka.Novokerka;
import net.chert.novokerka.entity.custom.Zis3Entity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class Zis3Renderer extends MobEntityRenderer<Zis3Entity, Zis3Model<Zis3Entity>> {

    public Zis3Renderer(EntityRendererFactory.Context context) {
        super(context, new Zis3Model<>(context.getPart(Zis3Model.ZIS3)), 1.3f);
    }

    @Override
    public Identifier getTexture(Zis3Entity entity) {
        return Identifier.of(Novokerka.MOD_ID, "textures/entity/zis3/texture_zis3.png");
    }

    @Override
    public void render(Zis3Entity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i){
        if(livingEntity.isBaby())
        {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }
        else if(livingEntity.getIsGoal()){
            matrixStack.scale(15f, 10f, 7f);
        }
        else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
