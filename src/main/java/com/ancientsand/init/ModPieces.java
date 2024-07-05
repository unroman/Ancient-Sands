package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.world.TombPieces;
import com.ancientsand.world.TombStructure;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPieces {
    public static final DeferredRegister<StructurePieceType> PIECE = DeferredRegister.create(Registries.STRUCTURE_PIECE, AncientMod.MODID);

    public static final RegistryObject<StructurePieceType> TOMB_PIECE = PIECE.register("tomb", ()-> TombPieces.Piece::new);

}
