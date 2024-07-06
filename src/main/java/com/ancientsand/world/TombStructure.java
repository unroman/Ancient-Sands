package com.ancientsand.world;

import com.ancientsand.init.ModTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;

import java.util.Optional;

public class TombStructure extends Structure {
    public static final MapCodec<TombStructure> CODEC = simpleCodec(TombStructure::new);
    public TombStructure(Structure.StructureSettings structureSettings) {
        super(structureSettings);
    }

    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    public Optional<GenerationStub> findGenerationPoint(Structure.GenerationContext generationContext) {
        return onTopOfChunkCenter(generationContext, Heightmap.Types.WORLD_SURFACE_WG, (builder) -> {
            this.generatePieces(builder, generationContext);
        });
    }

    public void generatePieces(StructurePiecesBuilder pieceBuilder, Structure.GenerationContext generatorContext) {
        BlockPos chunkPos = new BlockPos(generatorContext.chunkPos().getMinBlockX(), 90, generatorContext.chunkPos().getMinBlockZ());
        int landHeight = generatorContext.chunkGenerator().getFirstOccupiedHeight(chunkPos.getX(), chunkPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, generatorContext.heightAccessor(), generatorContext.randomState());
        if (landHeight < 74) {
            BlockPos position = new BlockPos(chunkPos.getX(), landHeight - 3, chunkPos.getZ());
            TombPieces.addPieces(generatorContext.structureTemplateManager(), position, pieceBuilder, generatorContext.random());
        }
    }

    @Override
    public StructureType<?> type() {
        return ModTypes.TOMB_TYPE.get();
    }
}
