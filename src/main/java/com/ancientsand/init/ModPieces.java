package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.world.TombPieces;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

public class ModPieces {
    public static StructurePieceType TOMB_PIECE = TombPieces.Piece::new;

    public static void setup() {
        Registry.register(BuiltInRegistries.STRUCTURE_PIECE, new ResourceLocation(AncientMod.MODID, "tomb"), TOMB_PIECE);
    }
}
