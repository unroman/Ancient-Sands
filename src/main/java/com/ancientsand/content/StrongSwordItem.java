package com.ancientsand.content;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class StrongSwordItem extends SwordItem {
    public StrongSwordItem(Tier p_43269_, Properties p_43272_) {
        super(p_43269_, p_43272_);
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int p_40670_) {
        if (entity instanceof Player player) {
            int i = this.getUseDuration(stack, entity) - p_40670_;
            if (i >= 22) {
                LivingEntity target = level.getNearestEntity(LivingEntity.class, TargetingConditions.forCombat().range(3.5D), player, player.getX(), player.getY(), player.getZ(), player.getBoundingBox().inflate(4.0D, 2.0D, 4.0D));
                if (target != null) {
                    target.hurt(player.damageSources().mobAttack(player), 11);
                    stack.hurtAndBreak(3, player, EquipmentSlot.MAINHAND);
                }
                player.swing(player.getUsedItemHand(), false);
                player.getCooldowns().addCooldown(stack.getItem(), 40);
                level.playSound(player, player.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP, player.getSoundSource(),1.0f, 1.0f);
            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level p_43405_, Player p_43406_, InteractionHand p_43407_) {
        ItemStack itemstack = p_43406_.getItemInHand(p_43407_);
        p_43406_.startUsingItem(p_43407_);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public int getUseDuration(ItemStack p_43419_, LivingEntity p_344216_) {
        return 72000;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack p_43417_) {
        return UseAnim.SPEAR;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext text, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.ancient_sword").withStyle(ChatFormatting.GRAY));
    }
}
