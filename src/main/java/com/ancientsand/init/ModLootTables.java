package com.ancientsand.init;

import com.ancientsand.AncientMod;
import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class ModLootTables {
    private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();
    public static final ResourceLocation CHAMBER_COMMON_LOOT = register("chamber/common");
    public static final ResourceLocation CHAMBER_ROYAL_LOOT = register("chamber/royal");

    private static ResourceLocation register(String p_78768_) {
        return register(new ResourceLocation(AncientMod.MODID, p_78768_));
    }

    private static ResourceLocation register(ResourceLocation p_78770_) {
        if (LOCATIONS.add(p_78770_)) {
            return p_78770_;
        } else {
            throw new IllegalArgumentException(p_78770_ + " is already a registered built-in loot table");
        }
    }
}
