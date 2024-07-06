package com.ancientsand.content;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class DaggerItem extends TieredItem {

    public DaggerItem(Tier p_43269_, Item.Properties p_43272_) {
        super(p_43269_, p_43272_);
    }

    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }

    public float getDestroySpeed(ItemStack p_43288_, BlockState p_43289_) {
        return p_43289_.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
    }

    public boolean hurtEnemy(ItemStack itemstack, LivingEntity p_43279_, LivingEntity playerIn) {
        itemstack.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);
        return true;
    }

    public boolean mineBlock(ItemStack itemstack, Level p_43283_, BlockState p_43284_, BlockPos p_43285_, LivingEntity playerIn) {
        if (p_43284_.getDestroySpeed(p_43283_, p_43285_) != 0.0F) {
            itemstack.hurtAndBreak(2, playerIn, EquipmentSlot.MAINHAND);
        }
        return true;
    }
}

