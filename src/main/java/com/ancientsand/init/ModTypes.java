package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.world.TombStructure;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTypes {
    public static final DeferredRegister<StructureType<?>> TYPE = DeferredRegister.create(Registries.STRUCTURE_TYPE, AncientMod.MODID);
    public static final RegistryObject<StructureType<TombStructure>> TOMB_TYPE = TYPE.register("tomb", () -> stuff(TombStructure.CODEC));

    private static <T extends Structure> StructureType<T> stuff(MapCodec<T> codec) {
        return () -> codec;
    }
}
