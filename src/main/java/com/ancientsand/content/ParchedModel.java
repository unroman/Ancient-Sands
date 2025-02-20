package com.ancientsand.content;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ParchedModel<T extends Mob> extends HumanoidModel<T> {
    public ParchedModel(ModelPart p_170941_) {
        super(p_170941_);
    }
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.offset(0.0F, 0.0F, 0.0F))
                .addOrReplaceChild("beard", CubeListBuilder.create().texOffs(49, 17).addBox(-1.0F, 0.0F, -0.7F, 2.0F, 3.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 0.0F, -3.0F, -0.7854F, 0.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(5.0F, 2.0F, 0.0F));
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(-2.0F, 12.0F, 0.0F));
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F), PartPose.offset(2.0F, 12.0F, 0.0F));
        return LayerDefinition.create(meshdefinition, 64, 32);
    }
    public void prepareMobModel(T p_103793_, float p_103794_, float p_103795_, float p_103796_) {
        this.rightArmPose = HumanoidModel.ArmPose.EMPTY;
        this.leftArmPose = HumanoidModel.ArmPose.EMPTY;
        super.prepareMobModel(p_103793_, p_103794_, p_103795_, p_103796_);
    }

    public void setupAnim(T entity, float p_103799_, float p_103800_, float p_103801_, float p_103802_, float p_103803_) {
        this.head.yRot = p_103802_ * ((float)Math.PI / 180F);
        this.head.xRot = p_103803_ * ((float)Math.PI / 180F);
        this.rightArm.zRot = 0.0F;
        this.leftArm.zRot = 0.0F;
        this.leftArm.yRot = 0.0F;
        this.rightArm.yRot = 0.0F;
        if (this.riding) {
            this.leftArm.xRot = (-(float)Math.PI / 5F);
            this.rightLeg.xRot = -1.4137167F;
            this.rightLeg.yRot = ((float)Math.PI / 10F);
            this.rightLeg.zRot = 0.07853982F;
            this.leftLeg.xRot = -1.4137167F;
            this.leftLeg.yRot = (-(float)Math.PI / 10F);
            this.leftLeg.zRot = -0.07853982F;
        } else {
            AnimationUtils.bobModelPart(this.rightArm, p_103801_, 1.0F);
            AnimationUtils.bobModelPart(this.leftArm, p_103801_, -1.0F);
            this.leftArm.xRot = Mth.cos(p_103799_ * 0.6662F) * 2.0F * p_103800_ * 0.5F;
            this.rightLeg.xRot = Mth.cos(p_103799_ * 0.6662F) * 1.4F * p_103800_ * 0.5F;
            this.rightLeg.yRot = 0.0F;
            this.rightLeg.zRot = 0.0F;
            this.leftLeg.xRot = Mth.cos(p_103799_ * 0.6662F + (float)Math.PI) * 1.4F * p_103800_ * 0.5F;
            this.leftLeg.yRot = 0.0F;
            this.leftLeg.zRot = 0.0F;
        }
        if (entity.isAggressive()) {
            AnimationUtils.swingWeaponDown(this.rightArm, this.leftArm, entity, this.attackTime, p_103801_);
        } else {
            this.rightArm.xRot = 0.0F;
            this.head.xRot = Mth.cos(p_103799_ * 0.6662F) * p_103800_ * 0.4F;
        }
    }
    public void translateToHand(HumanoidArm p_103778_, PoseStack p_103779_) {
        float f = p_103778_ == HumanoidArm.RIGHT ? 1.0F : -1.0F;
        ModelPart modelpart = this.getArm(p_103778_);
        modelpart.x += f;
        modelpart.translateAndRotate(p_103779_);
        modelpart.x -= f;
    }
}
