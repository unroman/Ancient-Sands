package com.ancientsand.init;

import com.ancientsand.AncientMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND = DeferredRegister.create(Registries.SOUND_EVENT, AncientMod.MODID);
    public static RegistryObject<SoundEvent> LYRE = SOUND.register( "lyre_play", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(AncientMod.MODID, "lyre_play")));
}
