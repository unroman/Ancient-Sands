package com.ancientsand.content;

import com.ancientsand.init.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.EnumSet;

public class Mourner extends Monster {
    protected static final EntityDataAccessor<Boolean> BLINDING = SynchedEntityData.defineId(Mourner.class, EntityDataSerializers.BOOLEAN);
    public Mourner(EntityType<? extends Monster> p_32133_, Level p_32134_) {
        super(p_32133_, p_32134_);
    }
    public boolean isCasting() {
        return this.entityData.get(BLINDING);
    }

    public void setCasting(boolean blinding) {
        this.entityData.set(BLINDING, blinding);
    }

    protected void defineSynchedData(SynchedEntityData.Builder p_335149_) {
        super.defineSynchedData(p_335149_);
        p_335149_.define(BLINDING, false);
    }

    public void tick() {
        super.tick();
        if (this.isCasting()) {
            float f = this.yBodyRot * ((float) Math.PI / 180F);
            float f1 = Mth.cos(f);
            float f2 = Mth.sin(f);
            this.level().addParticle(ModParticles.FALL.get(), this.getX() + (double)f1 * 0.7D, this.getY() + 1.8D, this.getZ() + (double)f2 * 0.7D, 0, 0, 0);
            this.level().addParticle(ModParticles.FALL.get(), this.getX() - (double)f1 * 0.7D, this.getY() + 1.8D, this.getZ() - (double)f2 * 0.7D, 0, 0, 0);
        }
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new Mourner.SpellcasterUseSpellGoal());
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.SKELETON_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_33579_) {
        return SoundEvents.SKELETON_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.SKELETON_DEATH;
    }

    protected void playStepSound(BlockPos p_32159_, BlockState p_32160_) {
        this.playSound(SoundEvents.SKELETON_STEP, 0.15F, 1.0F);
    }

    @Override
    protected float getEquipmentDropChance(EquipmentSlot p_21520_) {
        return 0.0f;
    }

    protected class SpellcasterUseSpellGoal extends Goal {
        public SpellcasterUseSpellGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }
        protected int attackWarmupDelay;
        protected int nextAttackTickCount;

        public boolean canUse() {
            LivingEntity livingentity = Mourner.this.getTarget();
            if (livingentity != null && livingentity.isAlive()) {
                return Mourner.this.tickCount >= this.nextAttackTickCount;
            } else {
                return false;
            }
        }

        public boolean canContinueToUse() {
            LivingEntity livingentity = Mourner.this.getTarget();
            return livingentity != null && livingentity.isAlive() && this.attackWarmupDelay > 0;
        }

        public void start() {
            this.attackWarmupDelay = this.adjustedTickDelay(this.getCastWarmupTime());
            this.nextAttackTickCount = Mourner.this.tickCount + this.getCastingInterval();
            SoundEvent soundevent = this.getSpellPrepareSound();
            if (soundevent != null) {
                Mourner.this.playSound(soundevent, 1.0F, 1.0F);
            }
            Mourner.this.navigation.stop();
        }

        public void tick() {
            --this.attackWarmupDelay;
            Mourner.this.setCasting(true);
            if (this.attackWarmupDelay == 0 && Mourner.this.getTarget() != null) {
                Mourner.this.getTarget().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 300, 0, true, false), Mourner.this);
                Mourner.this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 280, 0, true, false), Mourner.this);
                Mourner.this.getTarget().hurt( Mourner.this.damageSources().magic(), 1);
                Mourner.this.setCasting(false);
            }
            if (Mourner.this.getTarget() != null) {
                Mourner.this.getLookControl().setLookAt(Mourner.this.getTarget(), (float)Mourner.this.getMaxHeadYRot(), (float)Mourner.this.getMaxHeadXRot());
            }
        }

        protected int getCastWarmupTime() {
            return 30;
        }

        protected int getCastingInterval() {
            return 700;
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.ILLUSIONER_PREPARE_BLINDNESS;
        }
    }
}
