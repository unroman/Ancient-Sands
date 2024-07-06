package com.ancientsand.content;

import com.ancientsand.AncientMod;
import com.ancientsand.init.ModLayers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MournerRenderer extends HumanoidMobRenderer<Mourner, MournerModel<Mourner>> {
    private static final ResourceLocation MOURNER_LOCATION = ResourceLocation.fromNamespaceAndPath(AncientMod.MODID, "textures/entity/mourner.png");

    public MournerRenderer(EntityRendererProvider.Context p_174380_) {
        this(p_174380_, ModLayers.MOURNER, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }
    public MournerRenderer(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
        super(p_174382_, new MournerModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);

    }

    public ResourceLocation getTextureLocation(Mourner p_115941_) {
        return MOURNER_LOCATION;
    }
}