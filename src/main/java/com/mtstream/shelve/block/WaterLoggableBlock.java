package com.mtstream.shelve.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;
import org.jetbrains.annotations.NotNull;

public class WaterLoggableBlock extends Block implements SimpleWaterloggedBlock{
	
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    
	public WaterLoggableBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
	}

	@SuppressWarnings("deprecation")
	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	@Override
	public BlockState updateShape(BlockState state, @NotNull Direction dir, @NotNull BlockState dirstate, @NotNull LevelAccessor lev, @NotNull BlockPos pos, @NotNull BlockPos dirpos) {
		if (state.getValue(WATERLOGGED)) lev.scheduleTick(pos, Fluids.WATER,Fluids.WATER.getTickDelay(lev));
		return state;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState flate = context.getLevel().getFluidState(context.getClickedPos());
		return this.defaultBlockState().setValue(WATERLOGGED, flate.is(FluidTags.WATER));
	}

	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> bui) {
		bui.add(WATERLOGGED);
	}

	@Override
	public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter blockg, @NotNull BlockPos pos,
                                  @NotNull PathComputationType pathComputationType) {
		return false;
	}
}
