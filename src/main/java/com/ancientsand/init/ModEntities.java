package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.content.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(Registries.ENTITY_TYPE, AncientMod.MODID);
    public static RegistryObject<EntityType<Parched>> PARCHED = ENTITY.register("parched", () -> EntityType.Builder.of(Parched::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build("parched"));
    public static RegistryObject<EntityType<Mourner>> MOURNER = ENTITY.register("mourner", () -> EntityType.Builder.of(Mourner::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build("mourner"));

    public static void attributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PARCHED.get(), Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.2D).add(Attributes.KNOCKBACK_RESISTANCE, 0.1).add(Attributes.ATTACK_SPEED, -2).build());
        event.put(ModEntities.MOURNER.get(), Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.2D).build());
    }

    public static void renderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.PARCHED.get(), ParchedRenderer::new);
        event.registerEntityRenderer(ModEntities.MOURNER.get(), MournerRenderer::new);
    }

    public static void layers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModLayers.PARCHED, ParchedModel::createBodyLayer);
        event.registerLayerDefinition(ModLayers.MOURNER, MournerModel::createBodyLayer);
    }
}
