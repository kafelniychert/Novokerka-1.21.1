package net.chert.novokerka.entity.client;

import net.chert.novokerka.Novokerka;
import net.chert.novokerka.entity.custom.Zis3Entity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class Zis3Model<T extends Zis3Entity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer ZIS3 = new EntityModelLayer(Identifier.of(Novokerka.MOD_ID, "zis3"), "main");
    private final ModelPart zis3;
    private final ModelPart body;
    private final ModelPart wheels;
    private final ModelPart head;
    private final ModelPart cannon;
    private final ModelPart springs;
    public Zis3Model(ModelPart root) {
        this.zis3 = root.getChild("zis3");
        this.body = this.zis3.getChild("body");
        this.wheels = this.zis3.getChild("wheels");
        this.head = this.zis3.getChild("head");
        this.cannon = this.head.getChild("cannon");
        this.springs = this.head.getChild("springs");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData zis3 = modelPartData.addChild("zis3", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData body = zis3.addChild("body", ModelPartBuilder.create().uv(132, 66).cuboid(-17.003F, -0.145F, -19.4637F, 34.0F, 4.0F, 10.0F, new Dilation(0.0F))
                .uv(132, 80).cuboid(-22.003F, 2.855F, -13.4637F, 44.0F, 2.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.003F, -9.855F, 9.4637F));

        ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(0, 66).cuboid(-2.0F, -2.0F, -31.0F, 4.0F, 4.0F, 62.0F, new Dilation(0.0F)), ModelTransform.of(16.2216F, 6.855F, 20.2542F, -0.1309F, 0.1745F, 0.0F));

        ModelPartData cube_r2 = body.addChild("cube_r2", ModelPartBuilder.create().uv(132, 84).cuboid(-16.0F, -19.0F, -1.0F, 32.0F, 19.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-0.003F, -0.145F, -15.4637F, -0.2182F, 0.0F, 0.0F));

        ModelPartData cube_r3 = body.addChild("cube_r3", ModelPartBuilder.create().uv(0, 132).cuboid(-2.0F, -2.0F, -31.0F, 4.0F, 4.0F, 62.0F, new Dilation(0.0F)), ModelTransform.of(-16.2125F, 6.855F, 20.0806F, -0.1309F, -0.1745F, 0.0F));

        ModelPartData wheels = zis3.addChild("wheels", ModelPartBuilder.create().uv(134, 42).cuboid(-24.0F, -6.0F, -6.0F, 2.0F, 12.0F, 12.0F, new Dilation(0.0F))
                .uv(134, 18).cuboid(22.0F, -6.0F, -6.0F, 2.0F, 12.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -6.0F, -3.0F));

        ModelPartData head = zis3.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -15.5F, -6.0F));

        ModelPartData cannon = head.addChild("cannon", ModelPartBuilder.create().uv(132, 149).cuboid(-3.0F, -3.0F, -69.0F, 6.0F, 5.0F, 7.0F, new Dilation(0.0F))
                .uv(132, 105).cuboid(-4.0F, -4.0F, 1.0F, 8.0F, 7.0F, 22.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-2.0F, -2.0F, -62.0F, 4.0F, 3.0F, 63.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.5F, 1.0F));

        ModelPartData springs = head.addChild("springs", ModelPartBuilder.create().uv(132, 134).cuboid(-3.0F, -5.25F, -5.0F, 6.0F, 4.0F, 11.0F, new Dilation(0.0F))
                .uv(134, 0).cuboid(-3.0F, 0.75F, -7.0F, 6.0F, 5.0F, 13.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.25F, -5.0F));
        return TexturedModelData.of(modelData, 256, 256);
    }
    @Override
    public void setAngles(Zis3Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);

        this.animateMovement(Zis3Animations.walk, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.updateAnimation(entity.idleAnimationState, Zis3Animations.shoot, ageInTicks, 1f);
    }
    private void setHeadAngles(float headYaw, float headPitch){
        headYaw = MathHelper.clamp(headYaw, -20.0f, 20.0f);
        headPitch = MathHelper.clamp(headPitch, -10.0f, 45.0f);

        this.head.yaw = headYaw * 0.017453292F;
        this.head.pitch = headPitch * 0.017453292F;
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        zis3.render(matrices, vertexConsumer, light, overlay, color);
    }
    public ModelPart getPart(){
        return zis3;
    }
}
