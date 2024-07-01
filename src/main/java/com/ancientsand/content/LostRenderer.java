package com.ancientsand.content;

import com.ancientsand.AncientMod;
import com.ancientsand.init.ModLayers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LostRenderer extends HumanoidMobRenderer<AbstractSkeleton, LostModel<AbstractSkeleton>> {
    private static final ResourceLocation SKELETON_LOCATION = new ResourceLocation(AncientMod.MODID, "textures/entity/lost.png");

    public LostRenderer(EntityRendererProvider.Context p_174380_) {
        this(p_174380_, ModLayers.LOST, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }

    public LostRenderer(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
        super(p_174382_, new LostModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);

    }

    public ResourceLocation getTextureLocation(AbstractSkeleton p_115941_) {
        return SKELETON_LOCATION;
    }
}