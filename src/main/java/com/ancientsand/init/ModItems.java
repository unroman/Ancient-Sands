package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.ancientsand.content.DaggerItem;
import com.ancientsand.content.JugItem;
import com.ancientsand.content.LyreItem;
import com.ancientsand.content.StrongSwordItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Registries.ITEM, AncientMod.MODID);
    public static RegistryObject<Item> ANCIENT_SWORD = register(() -> new StrongSwordItem(ModTiers.ANCIENT, new Item.Properties().attributes(SwordItem.createAttributes(ModTiers.ANCIENT, 3, -2.4f))), "ancient_sword");
    public static RegistryObject<Item> ANCIENT_DAGGER = register(() -> new DaggerItem(ModTiers.DAGGER, new Item.Properties().attributes(SwordItem.createAttributes(ModTiers.DAGGER, 0, 1f))), "ancient_dagger");
    public static RegistryObject<Item> ANCIENT_INGOT = register(() -> new Item(new Item.Properties()), "ancient_ingot");
    public static RegistryObject<Item> ANCIENT_NUGGET = register(() -> new Item(new Item.Properties()), "ancient_nugget");
    public static RegistryObject<Item> CHAMBER_KEY = register(() -> new Item(new Item.Properties()), "chamber_key");
    public static RegistryObject<Item> ROYAL_KEY = register(() -> new Item(new Item.Properties()), "royal_key");
    public static RegistryObject<Item> PARCHED_EGG = register(() -> new ForgeSpawnEggItem(ModEntities.PARCHED, 15125652,7958625 , new Item.Properties()), "parched_spawn_egg");
    public static RegistryObject<Item> MOURNER_EGG = register(() -> new ForgeSpawnEggItem(ModEntities.MOURNER, 15125652,8855049 , new Item.Properties()), "mourner_spawn_egg");
    public static RegistryObject<Item> LYRE = register(() -> new LyreItem(new Item.Properties().rarity(Rarity.RARE)), "lyre");
    public static RegistryObject<Item> JUG = register(() -> new JugItem(new Item.Properties()), "jug");

    public static RegistryObject<Item> register(Supplier<Item> builder, String name) {
        return ITEM.register(name, builder);
    }
}
