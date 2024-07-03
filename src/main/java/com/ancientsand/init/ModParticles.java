package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.content.FallParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AncientMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModParticles {
    public static RegistryObject<SimpleParticleType> FALL;
    public static void setup() {
        FALL = AncientMod.PARTICLE.register("fall", () -> new SimpleParticleType(false));
    }

    @SubscribeEvent
    public static void particles(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(FALL.get(), FallParticle.Factory::new);
    }
}
