package com.ancientsand.content;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ConnectedCarpetBlock extends CarpetBlock {
    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty SOUTH = BooleanProperty.create("south");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty WEST = BooleanProperty.create("west");
    public static final Map<Direction, BooleanProperty> PROPERTY_BY_DIRECTION = ImmutableMap.copyOf(Util.make(Maps.newEnumMap(Direction.class), (p_55164_) -> {
        p_55164_.put(Direction.NORTH, NORTH);
        p_55164_.put(Direction.EAST, EAST);
        p_55164_.put(Direction.SOUTH, SOUTH);
        p_55164_.put(Direction.WEST, WEST);
    }));
    public ConnectedCarpetBlock(Properties p_152915_) {
        super(p_152915_);
        this.registerDefaultState(this.getStateDefinition().any().setValue(NORTH, Boolean.TRUE).setValue(SOUTH, Boolean.TRUE).setValue(EAST, Boolean.TRUE).setValue(WEST, Boolean.TRUE));
    }

    public BlockState getStateForPlacement(BlockPlaceContext p_54138_) {
        BlockGetter blockgetter = p_54138_.getLevel();
        BlockPos blockpos = p_54138_.getClickedPos();
        return this.defaultBlockState().setValue(NORTH, !blockgetter.getBlockState(blockpos.north()).is(this)).setValue(EAST, !blockgetter.getBlockState(blockpos.east()).is(this)).setValue(SOUTH, !blockgetter.getBlockState(blockpos.south()).is(this)).setValue(WEST, !blockgetter.getBlockState(blockpos.west()).is(this));
    }

    public @NotNull BlockState updateShape(BlockState p_54146_, Direction p_54147_, BlockState p_54148_, LevelAccessor p_54149_, BlockPos p_54150_, BlockPos p_54151_) {
        return p_54148_.is(this) && !(p_54147_ == Direction.UP || p_54147_ == Direction.DOWN) ? p_54146_.setValue(PROPERTY_BY_DIRECTION.get(p_54147_), Boolean.FALSE) : super.updateShape(p_54146_, p_54147_, p_54148_, p_54149_, p_54150_, p_54151_);
    }

    public @NotNull BlockState rotate(BlockState p_54143_, Rotation p_54144_) {
        return p_54143_.setValue(PROPERTY_BY_DIRECTION.get(p_54144_.rotate(Direction.NORTH)), p_54143_.getValue(NORTH)).setValue(PROPERTY_BY_DIRECTION.get(p_54144_.rotate(Direction.SOUTH)), p_54143_.getValue(SOUTH)).setValue(PROPERTY_BY_DIRECTION.get(p_54144_.rotate(Direction.EAST)), p_54143_.getValue(EAST)).setValue(PROPERTY_BY_DIRECTION.get(p_54144_.rotate(Direction.WEST)), p_54143_.getValue(WEST));
    }

    public @NotNull BlockState mirror(BlockState p_54140_, Mirror p_54141_) {
        return p_54140_.setValue(PROPERTY_BY_DIRECTION.get(p_54141_.mirror(Direction.NORTH)), p_54140_.getValue(NORTH)).setValue(PROPERTY_BY_DIRECTION.get(p_54141_.mirror(Direction.SOUTH)), p_54140_.getValue(SOUTH)).setValue(PROPERTY_BY_DIRECTION.get(p_54141_.mirror(Direction.EAST)), p_54140_.getValue(EAST)).setValue(PROPERTY_BY_DIRECTION.get(p_54141_.mirror(Direction.WEST)), p_54140_.getValue(WEST));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_153490_) {
        p_153490_.add(NORTH, EAST, SOUTH, WEST);
    }
}
