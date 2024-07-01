package com.ancientsand.init;

import com.ancientsand.AncientMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModLayers {
    public static final ModelLayerLocation LOST = register("lost");

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }

    private static ModelLayerLocation register(String name, String type) {
        return register(new ResourceLocation(AncientMod.MODID, name), type);
    }

    private static ModelLayerLocation register(ResourceLocation location, String type) {
        return new ModelLayerLocation(location, type);
    }
}
