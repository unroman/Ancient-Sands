package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.world.TombPieces;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPieces {
    public static final DeferredRegister<StructurePieceType> PIECE = DeferredRegister.create(Registries.STRUCTURE_PIECE, AncientMod.MODID);

    public static final RegistryObject<StructurePieceType> TOMB_PIECE = PIECE.register("tomb", ()-> TombPieces.Piece::new);
}
