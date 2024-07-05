package com.ancientsand.content;

import com.ancientsand.init.ModSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.concurrent.ThreadLocalRandom;

public class LyreItem extends Item {
    public LyreItem(Properties p_41383_) {
        super(p_41383_.stacksTo(1));
    }

    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        int i = this.getUseDuration(stack) - count;
        if (i > 40) {
            entity.level().playLocalSound(entity.blockPosition(), ModSounds.LYRE.get(), SoundSource.PLAYERS, 1.0f, 0.8f + ThreadLocalRandom.current().nextFloat(), true);
        }
    }

    public InteractionResultHolder<ItemStack> use(Level p_43405_, Player p_43406_, InteractionHand p_43407_) {
        ItemStack itemstack = p_43406_.getItemInHand(p_43407_);
        p_43406_.startUsingItem(p_43407_);
        return InteractionResultHolder.consume(itemstack);
    }

    public int getUseDuration(ItemStack p_40680_) {
        return 45;
    }
}
