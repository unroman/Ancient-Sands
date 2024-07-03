package com.ancientsand;

import com.ancientsand.init.*;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

@Mod("ancientsand")
public class AncientMod {
    public static final String MODID = "ancientsand";
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(Registries.BLOCK, MODID);
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Registries.ITEM, MODID);
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);
    public static final DeferredRegister<ParticleType<?>> PARTICLE = DeferredRegister.create(Registries.PARTICLE_TYPE, MODID);
    public static final DeferredRegister<Feature<?>> FEATURE = DeferredRegister.create(Registries.FEATURE, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public AncientMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        CREATIVE_TAB.register(bus);
        BLOCK.register(bus);
        ITEM.register(bus);
        FEATURE.register(bus);
        ENTITY.register(bus);
        PARTICLE.register(bus);

        ModTab.setup();
        ModItems.setup();
        ModBlocks.setup();
        ModEntities.setup();
        ModParticles.setup();

        bus.addListener(ModEntities::attributes);
        bus.addListener(ModEntities::renderers);
        bus.addListener(ModEntities::layers);
        bus.addListener(this::setup);
        ModTypes.REGISTRY.register(bus);
    }
    private void setup(final FMLCommonSetupEvent event) {
        ModPieces.setup();
    }

}
