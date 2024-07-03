package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.content.DaggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static RegistryObject<Item> ANCIENT_SWORD;
    public static RegistryObject<Item> ANCIENT_DAGGER;
    public static RegistryObject<Item> ANCIENT_INGOT;
    public static RegistryObject<Item> ANCIENT_NUGGET;
    public static RegistryObject<Item> CHAMBER_KEY;
    public static RegistryObject<Item> ROYAL_KEY;
    public static RegistryObject<Item> PARCHED_EGG;
    public static RegistryObject<Item> MOURNER_EGG;

    public static void setup() {
        ANCIENT_SWORD = register(() -> new SwordItem(ModTiers.ANCIENT, 3, -2.4f, new Item.Properties()), "ancient_sword");
        ANCIENT_DAGGER = register(() -> new DaggerItem(ModTiers.DAGGER, 0, 1f, new Item.Properties()), "ancient_dagger");
        ANCIENT_INGOT = register(() -> new Item(new Item.Properties()), "ancient_ingot");
        ANCIENT_NUGGET = register(() -> new Item(new Item.Properties()), "ancient_nugget");
        CHAMBER_KEY = register(() -> new Item(new Item.Properties()), "chamber_key");
        ROYAL_KEY = register(() -> new Item(new Item.Properties()), "royal_key");
        PARCHED_EGG = register(() -> new ForgeSpawnEggItem(ModEntities.PARCHED, 15125652,7958625 , new Item.Properties()), "parched_spawn_egg");
        MOURNER_EGG = register(() -> new ForgeSpawnEggItem(ModEntities.MOURNER, 15125652,8855049 , new Item.Properties()), "mourner_spawn_egg");
    }

    public static RegistryObject<Item> register(Supplier<Item> builder, String name) {
        return AncientMod.ITEM.register(name, builder);
    }
}
