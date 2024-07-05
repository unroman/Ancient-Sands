package com.ancientsand;

import com.ancientsand.init.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("ancientsand")
public class AncientMod {
    public static final String MODID = "ancientsand";

    public AncientMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ModEntities::attributes);
        bus.addListener(ModEntities::renderers);
        bus.addListener(ModEntities::layers);
        ModTypes.TYPE.register(bus);
        ModPieces.PIECE.register(bus);
        ModItems.ITEM.register(bus);
        ModBlocks.BLOCK.register(bus);
        ModEntities.ENTITY.register(bus);
        ModParticles.PARTICLE.register(bus);
        ModSounds.SOUND.register(bus);
        ModTab.CREATIVE_TAB.register(bus);
    }
}
