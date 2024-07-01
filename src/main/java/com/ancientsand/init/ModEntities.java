package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.content.Lost;
import com.ancientsand.content.LostModel;
import com.ancientsand.content.LostRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static RegistryObject<EntityType<Lost>> LOST;
    public static void setup() {
        LOST = AncientMod.ENTITY.register("lost", () -> EntityType.Builder.of(Lost::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build("lost"));

    }

    public static void attributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.LOST.get(), Lost.create().build());
    }

    public static void renderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.LOST.get(), LostRenderer::new);
    }

    public static void layers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModLayers.LOST, LostModel::createBodyLayer);
    }

}
