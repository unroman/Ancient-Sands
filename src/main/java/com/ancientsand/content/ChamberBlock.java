package com.ancientsand.content;

import com.ancientsand.init.ModItems;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class ChamberBlock extends Block {
    ResourceLocation loot;
    boolean royal;
    public static final BooleanProperty OPEN = BarrelBlock.OPEN;
    public ChamberBlock(Properties p_49795_, ResourceLocation loot, boolean royal) {
        super(p_49795_);
        this.loot = loot;
        this.royal = royal;
        this.registerDefaultState(this.getStateDefinition().any().setValue(OPEN, Boolean.valueOf(false)));
    }

    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (state.getValue(OPEN)) {
            return InteractionResult.PASS;
        } else {
            if ((!royal && itemstack.is(ModItems.CHAMBER_KEY.get())) || (royal && itemstack.is(ModItems.ROYAL_KEY.get()))) {
                dropContent(player, level, pos);
                if (!player.isCreative()) {
                    itemstack.shrink(1);
                }
                level.playSound((Player)null, pos, SoundEvents.BARREL_OPEN, SoundSource.BLOCKS, 0.3F, 0.5F);
                this.open(state, level, pos);
                level.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D, 0f, 0.05f, 0f);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
    public void open(BlockState state, Level level, BlockPos pos) {
        state = state.cycle(OPEN);
        level.setBlock(pos, state, 3);
    }
    private void dropContent(Player player, Level level, BlockPos pos) {
        if (level != null && level.getServer() != null) {
            LootTable loottable = level.getServer().getLootData().getLootTable(this.loot);
            ObjectArrayList<ItemStack> objectarraylist = loottable.getRandomItems((new LootParams.Builder((ServerLevel) player.level())).withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos)).withParameter(LootContextParams.THIS_ENTITY, player).create(LootContextParamSets.ARCHAEOLOGY));
            ItemStack item = objectarraylist.get(0);
            ItemEntity itementity = new ItemEntity(level, pos.getX() + 0.5D, pos.getY() + 1D, pos.getZ() + 0.5D, item.split(level.random.nextInt(21) + 10));
            level.addFreshEntity(itementity);
        }
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_55673_) {
        p_55673_.add(OPEN);
    }
}