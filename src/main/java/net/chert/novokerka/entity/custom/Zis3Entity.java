package net.chert.novokerka.entity.custom;

import net.chert.novokerka.entity.ModEntities;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Zis3Entity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public int idleAnimationTimeout = 0;
    public int shootTimeout = 0;
    public Zis3Entity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }
    private boolean isGoal = false;
    public float aimYaw = 0f;
    public float aimPitch = 0f;
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.IRON_BLOCK);
    }


    protected void initGoals(){

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
                .add(EntityAttributes.GENERIC_STEP_HEIGHT, 0.6f)
                .add(EntityAttributes.GENERIC_OXYGEN_BONUS, 500)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1f)
                .add(EntityAttributes.GENERIC_JUMP_STRENGTH, 1f);
    }

    private void setupAnimationStates(){
        if(this.idleAnimationTimeout <= 0){
            this.idleAnimationTimeout = 10;
            this.idleAnimationState.start(this.age);

        }
        else{
            --this.idleAnimationTimeout;
        }
        /*if(this.goalTimeout <= 0){
            this.goalTimeout = 40;
            if(this.isGoal){
                this.isGoal = false;
            }
            else{
                this.isGoal = true;
            }
        }
        else{
            --this.goalTimeout;
        }*/
    }
    @Override
    public void tick() {
        super.tick();
        if(this.getWorld().isClient()){
            this.setupAnimationStates();
        }
        if(!this.getWorld().isClient()){
            if(this.getFirstPassenger() != null){
                this.headYaw = this.getFirstPassenger().getHeadYaw();
                this.setPitch(this.getFirstPassenger().getPitch());
                this.setBodyYaw(this.getFirstPassenger().getHeadYaw());
                aimYaw = this.getFirstPassenger().getHeadYaw();
                aimPitch = this.getFirstPassenger().getPitch();
            }
            else{
                this.headYaw = aimYaw;
                this.setBodyYaw(aimYaw);
                this.setPitch(aimPitch);
            }
            if(shootTimeout <= 0){
                shootTimeout = 10;
                shootArrow(3.0f);
                this.getWorld().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.NEUTRAL, 0.5F, 1.0F);
            }
            else{
                shootTimeout--;
            }
        }
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.ZIS3.create(world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if(!this.isBreedingItem(itemStack)){
            player.startRiding((Entity) this);
            return ActionResult.SUCCESS;
        }
        if (this.isBreedingItem(itemStack)) {
            int i = this.getBreedingAge();
            if (!this.getWorld().isClient && i == 0 && this.canEat()) {
                this.eat(player, hand, itemStack);
                this.lovePlayer(player);
                return ActionResult.SUCCESS;
            }

            if (this.isBaby()) {
                this.eat(player, hand, itemStack);
                this.growUp(toGrowUpAge(-i), true);
                return ActionResult.success(this.getWorld().isClient);
            }

            if (this.getWorld().isClient) {
                return ActionResult.CONSUME;
            }
        }

        return super.interactMob(player, hand);
    }
    public boolean getIsGoal(){
        return isGoal;
    }

    private void shootArrow(float power) {
        ArrowEntity arrow = new ArrowEntity(EntityType.ARROW, this.getWorld());
        Vec3d entityPos = this.getPos();
        arrow.setPos(entityPos.x, entityPos.y + this.getEyeHeight(this.getPose())-0.81f, entityPos.z);
        Vec3d lookDirection = this.getRotationVec(1.0F);
        arrow.setVelocity(lookDirection.x, lookDirection.y, lookDirection.z, power, 1.0F);
        this.getWorld().spawnEntity(arrow);

        //this.setVelocity(lookDirection.multiply(-0.1f));
    }
}
