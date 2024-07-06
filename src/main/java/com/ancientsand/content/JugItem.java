package com.ancientsand.content;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class JugItem extends Item {
    public JugItem(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        if (entity instanceof Player player) {
            int i = this.getUseDuration(stack, entity) - count;
            if (i == 40) {
                player.getFoodData().eat(4, 0.2f);
                player.onEquippedItemBroken(stack.getItem(), EquipmentSlot.MAINHAND);
                if (!player.isCreative()) {stack.shrink(1);}
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
        return 40;
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.DRINK;
    }
}
