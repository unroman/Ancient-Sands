package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.content.ChamberBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static RegistryObject<Block> REMNANT_BLOCK;
    public static RegistryObject<Block> REMNANT_STAIRS;
    public static RegistryObject<Block> REMNANT_SLAB;
    public static RegistryObject<Block> ANCIENT_BLOCK;
    public static RegistryObject<Block> COMMON_CHAMBER;
    public static RegistryObject<Block> ROYAL_CHAMBER;
    public static RegistryObject<Block> ROYAL_CARPET;

    public static void setup() {
        REMNANT_BLOCK = register(() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(3.0f, 1200.0f).sound(SoundType.SAND)), "remnant_block");
        REMNANT_STAIRS = register(() -> new StairBlock(REMNANT_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.copy(REMNANT_BLOCK.get())), "remnant_stairs");
        REMNANT_SLAB = register(() -> new SlabBlock(BlockBehaviour.Properties.copy(REMNANT_BLOCK.get())), "remnant_slab");
        ANCIENT_BLOCK = register(() -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)), "ancient_block");
        COMMON_CHAMBER = register(() -> new ChamberBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(3.0f, 1200.0f).sound(SoundType.METAL), ModLootTables.CHAMBER_COMMON_LOOT, false), "chamber");
        ROYAL_CHAMBER = register(() -> new ChamberBlock(BlockBehaviour.Properties.copy(COMMON_CHAMBER.get()), ModLootTables.CHAMBER_ROYAL_LOOT, true), "royal_chamber");
        ROYAL_CARPET = register(() -> new CarpetBlock(BlockBehaviour.Properties.copy(Blocks.RED_CARPET)), "royal_carpet");
    }
    public static RegistryObject<Block> register(Supplier<Block> builder, String name) {
        RegistryObject<Block> blockRegistryObject = AncientMod.BLOCK.register(name, builder);
        AncientMod.ITEM.register(name, () -> new BlockItem(blockRegistryObject.get(), new Item.Properties()));
        return blockRegistryObject;
    }
}
