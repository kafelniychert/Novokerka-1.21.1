package net.chert.novokerka.entity.custom;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Zis3Entity extends PathAwareEntity {

    protected Zis3Entity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }


    protected void initGoals(){
        this.goalSelector.add(0, new WanderAroundFarGoal(this, 1.1));
    }
    public static DefaultAttributeContainer.Builder createAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 100)
                .add(EntityAttributes.GENERIC_ARMOR, 15)
                .add(EntityAttributes.GENERIC_BURNING_TIME, 0)
                .add(EntityAttributes.GENERIC_FALL_DAMAGE_MULTIPLIER, 0.5f)
                .add(EntityAttributes.GENERIC_EXPLOSION_KNOCKBACK_RESISTANCE, 20)
                .add(EntityAttributes.GENERIC_GRAVITY, 1.5f)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 75)
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 1)
                .add(EntityAttributes.GENERIC_OXYGEN_BONUS, 500);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return null;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return null;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public Arm getMainArm() {
        return null;
    }

}
