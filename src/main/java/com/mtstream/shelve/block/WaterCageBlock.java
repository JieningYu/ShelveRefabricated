package com.mtstream.shelve.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class WaterCageBlock extends WaterLoggableBlock{
	public static VoxelShape SHAPE = Shapes.box(0, 0, 0, 1, 1, 1);

	public WaterCageBlock(Properties properties) {
		super(properties);
	}

	@Override
	public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter get, @NotNull BlockPos pos, @NotNull CollisionContext con) {
		return SHAPE;
	}

	@Override
	public RenderShape getRenderShape(@NotNull BlockState state) {
		return RenderShape.MODEL;
	}
}
