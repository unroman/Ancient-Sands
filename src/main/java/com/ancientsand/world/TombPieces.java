package com.ancientsand.world;

import com.ancientsand.AncientMod;
import com.ancientsand.init.ModBlocks;
import com.ancientsand.init.ModEntities;
import com.ancientsand.init.ModPieces;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class TombPieces {
    private static final ResourceLocation ENTRY_1 = ResourceLocation.fromNamespaceAndPath(AncientMod.MODID, "entry_1");
    private static final ResourceLocation ENTRY_2 = ResourceLocation.fromNamespaceAndPath(AncientMod.MODID, "entry_2");
    private static final ResourceLocation TUBE = ResourceLocation.fromNamespaceAndPath(AncientMod.MODID, "tube");
    private static final ResourceLocation ROOM = ResourceLocation.fromNamespaceAndPath(AncientMod.MODID, "room");
    private static final ResourceLocation HALL_1 = ResourceLocation.fromNamespaceAndPath(AncientMod.MODID, "hall_1");
    private static final ResourceLocation HALL_2 = ResourceLocation.fromNamespaceAndPath(AncientMod.MODID, "hall_2");
    private static final ResourceLocation DOOR = ResourceLocation.fromNamespaceAndPath(AncientMod.MODID, "door");
    private static final ResourceLocation STORAGE = ResourceLocation.fromNamespaceAndPath(AncientMod.MODID, "storage");
    private static final ResourceLocation THRONE = ResourceLocation.fromNamespaceAndPath(AncientMod.MODID, "throne");
    public static void addPieces(StructureTemplateManager manager, BlockPos pos, StructurePieceAccessor pieceList, RandomSource random) {
        Rotation rotation = Rotation.NONE;
        if (random.nextInt(10) < 6) {
            pieceList.addPiece(new TombPieces.Piece(manager, ENTRY_1, pos, rotation));
        } else {
            pieceList.addPiece(new TombPieces.Piece(manager, ENTRY_2, pos.offset(0, 0, -2), rotation));
        }
        pieceList.addPiece(new TombPieces.Piece(manager, TUBE, pos.offset(4, -5, 2), rotation));
        pieceList.addPiece(new TombPieces.Piece(manager, TUBE, pos.offset(4, -10, 2), rotation));
        BlockPos entry = pos.offset(2, -16, 0);
        pieceList.addPiece(new TombPieces.Piece(manager, ROOM, entry, rotation));
        pieceList.addPiece(new TombPieces.Piece(manager, random.nextInt(10) < 5 ? HALL_1 : HALL_2, entry.offset(-8, 0, 0), rotation));
        pieceList.addPiece(new TombPieces.Piece(manager, random.nextInt(10) < 5 ? HALL_1 : HALL_2, entry.offset(8, 0, -8), rotation.getRotated(Rotation.CLOCKWISE_90)));
        if (random.nextInt(10) < 3 ) {
            pieceList.addPiece(new TombPieces.Piece(manager, HALL_1, entry.offset(-16, 0, 0), rotation));
            if (random.nextInt(10) < 6 ) {
                pieceList.addPiece(new TombPieces.Piece(manager, random.nextInt(10) < 5 ? HALL_1 : HALL_2, entry.offset(-24, 0, 0), rotation));
            }
        }
        if (random.nextInt(10) < 3 ) {
            pieceList.addPiece(new TombPieces.Piece(manager, HALL_1, entry.offset(8, 0, -16), rotation.getRotated(Rotation.CLOCKWISE_90)));
        }
        if (random.nextInt(10) < 4 ) {
            pieceList.addPiece(new TombPieces.Piece(manager, HALL_1, entry.offset(9, 0, 0), rotation));
            pieceList.addPiece(new TombPieces.Piece(manager, DOOR, entry.offset(17, 0, 0), rotation));
            if (random.nextInt(10) < 5 ) {
                pieceList.addPiece(new TombPieces.Piece(manager, STORAGE, entry.offset(21, 0, 0), rotation));
            } else {
                pieceList.addPiece(new TombPieces.Piece(manager, THRONE, entry.offset(21, 0, -2), rotation));
            }
        } else {
            pieceList.addPiece(new TombPieces.Piece(manager, HALL_1, entry.offset(8, 0, 9), rotation.getRotated(Rotation.CLOCKWISE_90)));
            pieceList.addPiece(new TombPieces.Piece(manager, DOOR, entry.offset(8, 0, 17), rotation.getRotated(Rotation.CLOCKWISE_90)));
            if (random.nextInt(10) < 6) {
                pieceList.addPiece(new TombPieces.Piece(manager, STORAGE, entry.offset(8, 0, 21), rotation.getRotated(Rotation.CLOCKWISE_90)));
            } else {
                pieceList.addPiece(new TombPieces.Piece(manager, THRONE, entry.offset(10, 0, 21), rotation.getRotated(Rotation.CLOCKWISE_90)));
            }
        }
    }

    public static class Piece extends TemplateStructurePiece {
        public Piece(StructureTemplateManager p_228540_, ResourceLocation p_228541_, BlockPos p_228542_, Rotation p_228543_) {
            super(ModPieces.TOMB_PIECE.get(), 0, p_228540_, p_228541_, p_228541_.toString(), makeSettings(p_228543_), p_228542_);
        }
        public Piece(StructurePieceSerializationContext p_228545_, CompoundTag p_228546_) {
            super(ModPieces.TOMB_PIECE.get(), p_228546_, p_228545_.structureTemplateManager(), (p_228568_) -> makeSettings(Rotation.valueOf(p_228546_.getString("Rot"))));
        }

        public void postProcess(WorldGenLevel worldIn, StructureManager p_229138_, ChunkGenerator p_229139_, RandomSource random, BoundingBox p_229141_, ChunkPos p_229142_, BlockPos pos) {
            super.postProcess(worldIn, p_229138_, p_229139_, random, p_229141_, p_229142_, pos);
            for (int d = 0; d < 5; ++d) {
                BlockPos blockpos = pos.offset(random.nextInt(-9, 7), random.nextInt(-16, -10), random.nextInt(-9, 7));
                if (worldIn.getBlockState(blockpos).getBlock() == ModBlocks.REMNANT_BLOCK.get()) {
                    if (!worldIn.isEmptyBlock(blockpos.below()) && worldIn.isEmptyBlock(blockpos.above())) {
                        worldIn.setBlock(blockpos, Blocks.SAND.defaultBlockState(), 2);
                        if (random.nextInt(4) == 1) {
                            worldIn.setBlock(blockpos.above(), Blocks.DEAD_BUSH.defaultBlockState(), 2);
                        }
                    } else if (worldIn.isEmptyBlock(blockpos.above())) {
                        worldIn.setBlock(blockpos, ModBlocks.REMNANT_SLAB.get().defaultBlockState(), 2);
                    }
                }
            }
        }

        private static StructurePlaceSettings makeSettings(Rotation p_228556_) {
            return (new StructurePlaceSettings()).setRotation(p_228556_).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }

        protected void addAdditionalSaveData(StructurePieceSerializationContext p_228558_, CompoundTag p_228559_) {
            super.addAdditionalSaveData(p_228558_, p_228559_);
            p_228559_.putString("Rot", this.placeSettings.getRotation().name());
        }

        protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor worldIn, RandomSource random, BoundingBox sbb) {
            if (function.startsWith("Chamber")) {
                if (random.nextInt(6) == 1) {
                    worldIn.setBlock(pos, ModBlocks.ROYAL_CHAMBER.get().defaultBlockState(), 2);
                } else {
                    worldIn.setBlock(pos, ModBlocks.COMMON_CHAMBER.get().defaultBlockState(), 2);
                }
            } if (function.startsWith("Spawner")) {
                worldIn.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2);
                BlockEntity blockentity = worldIn.getBlockEntity(pos);
                if (blockentity instanceof SpawnerBlockEntity spawnerblockentity) {
                    if (random.nextInt(6) == 1) {
                        spawnerblockentity.setEntityId(ModEntities.MOURNER.get(), random);
                    } else {
                        spawnerblockentity.setEntityId(ModEntities.PARCHED.get(), random);
                    }
                }
            }
        }
    }
}