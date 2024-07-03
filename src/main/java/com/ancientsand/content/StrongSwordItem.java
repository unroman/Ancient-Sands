package com.ancientsand.content;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class StrongSwordItem extends SwordItem {

    public StrongSwordItem(Tier p_43269_, int p_43270_, float p_43271_, Properties p_43272_) {
        super(p_43269_, p_43270_, p_43271_, p_43272_);
    }

    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int p_40670_) {
        if (entity instanceof Player player) {
            int i = this.getUseDuration(stack) - p_40670_;
            if (i >= 22) {
                if (!level.isClientSide) {
                    LivingEntity target = level.getNearestEntity(LivingEntity.class, TargetingConditions.forCombat().range(3.0D), player, player.getX(), player.getY(), player.getZ(), player.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
                    if (target != null) {
                        target.hurt(player.damageSources().mobAttack(player), 11);
                        stack.hurtAndBreak(7, player, (p_43388_) -> {
                            p_43388_.broadcastBreakEvent(entity.getUsedItemHand());
                        });
                    }
                    player.getCooldowns().addCooldown(stack.getItem(), 40);
                    player.stopUsingItem();
                }
            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level p_43405_, Player p_43406_, InteractionHand p_43407_) {
        ItemStack itemstack = p_43406_.getItemInHand(p_43407_);
        p_43406_.startUsingItem(p_43407_);
        return InteractionResultHolder.consume(itemstack);
    }

    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.SPEAR;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.ancient_sword").withStyle(ChatFormatting.GRAY));
    }
}
