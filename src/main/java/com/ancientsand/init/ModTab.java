package com.ancientsand.init;

import com.ancientsand.AncientMod;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class ModTab {
    public static void setup() {
        AncientMod.CREATIVE_TAB.register("sandgroup", () -> CreativeModeTab.builder()
                .icon(() -> ModItems.ANCIENT_SWORD.get().asItem().getDefaultInstance())
                .title(Component.translatable("itemGroup.sandgroup"))
                .displayItems((parameters, output) -> {
                    output.accept(ModBlocks.ANCIENT_BLOCK.get());
                    output.accept(ModBlocks.COMMON_CHAMBER.get());
                    output.accept(ModBlocks.ROYAL_CHAMBER.get());
                    output.accept(ModBlocks.REMNANT_BLOCK.get());
                    output.accept(ModBlocks.REMNANT_STAIRS.get());
                    output.accept(ModBlocks.REMNANT_SLAB.get());
                    output.accept(ModBlocks.ROYAL_CARPET.get());
                    output.accept(ModItems.CHAMBER_KEY.get());
                    output.accept(ModItems.ROYAL_KEY.get());
                    output.accept(ModItems.ANCIENT_SWORD.get());
                    output.accept(ModItems.ANCIENT_DAGGER.get());
                    output.accept(ModItems.ANCIENT_INGOT.get());
                    output.accept(ModItems.ANCIENT_NUGGET.get());
                    output.accept(ModItems.PARCHED_EGG.get());
                    output.accept(ModItems.MOURNER_EGG.get());
                }).build());
    }
}
