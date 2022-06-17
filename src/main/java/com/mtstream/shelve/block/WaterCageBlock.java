package com.mtstream.shelve.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class WaterCageBlock extends WaterLoggableBlock {
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	public static final VoxelShape SHAPE = Shapes.box(0, 0, 0, 1, 1, 1);

	public WaterCageBlock(Properties properties) {
		super(properties);
		registerDefaultState(defaultBlockState().setValue(POWERED, false));
	}

	@Override
	public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter get, @NotNull BlockPos pos, @NotNull CollisionContext con) {
		return Shapes.box(0.01, 0.01, 0.01, 0.99, 0.99, 0.99);
	}

	@Override
	public VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return !state.getValue(POWERED) ? SHAPE : Shapes.box(0.01, 0.01, 0.01, 0.99, 0.99, 0.99);
	}

	@Override
	public RenderShape getRenderShape(@NotNull BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> bui) {
		super.createBlockStateDefinition(bui);
		bui.add(POWERED);
	}

	@Override
	public void neighborChanged(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Block block, @NotNull BlockPos blockPos2, boolean bl) {
		super.neighborChanged(blockState, level, blockPos, block, blockPos2, bl);
		if (!level.isClientSide()) {
			if (blockState.getValue(POWERED) && !level.hasNeighborSignal(blockPos)) {
				level.setBlock(blockPos, blockState.setValue(POWERED,false), 2);
			}
			if (!blockState.getValue(POWERED) && level.hasNeighborSignal(blockPos)) {
				level.setBlock(blockPos, blockState.setValue(POWERED,true), 2);
			}
		}
	}
}
