package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.content.ChamberBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(Registries.BLOCK, AncientMod.MODID);
    public static RegistryObject<Block> REMNANT_BLOCK = register(() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).requiresCorrectToolForDrops().strength(3.0f, 1200.0f).sound(SoundType.SAND)), "remnant_block");
    public static RegistryObject<Block> REMNANT_STAIRS = register(() -> new StairBlock(REMNANT_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(REMNANT_BLOCK.get())), "remnant_stairs");
    public static RegistryObject<Block> REMNANT_SLAB = register(() -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(REMNANT_BLOCK.get())), "remnant_slab");
    public static RegistryObject<Block> ANCIENT_BLOCK = register(() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK)), "ancient_block");
    public static RegistryObject<Block> COMMON_CHAMBER = register(() -> new ChamberBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(3.0f, 1200.0f).sound(SoundType.METAL), ModLootTables.CHAMBER_COMMON_LOOT, false), "chamber");
    public static RegistryObject<Block> ROYAL_CHAMBER = register(() -> new ChamberBlock(BlockBehaviour.Properties.ofFullCopy(COMMON_CHAMBER.get()), ModLootTables.CHAMBER_ROYAL_LOOT, true), "royal_chamber");
    public static RegistryObject<Block> ROYAL_CARPET = register(() -> new CarpetBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_CARPET)), "royal_carpet");

    public static RegistryObject<Block> register(Supplier<Block> builder, String name) {
        RegistryObject<Block> blockRegistryObject = BLOCK.register(name, builder);
        ModItems.ITEM.register(name, () -> new BlockItem(blockRegistryObject.get(), new Item.Properties()));
        return blockRegistryObject;
    }
}
