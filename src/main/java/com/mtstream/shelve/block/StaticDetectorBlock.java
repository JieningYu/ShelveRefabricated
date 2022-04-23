package com.mtstream.shelve.block;

import javax.annotation.Nullable;

import com.mtstream.shelve.blockEntity.StaticDetectorBlockEntity;
import com.mtstream.shelve.init.BlockEntityInit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class StaticDetectorBlock extends BaseEntityBlock{
	
	public static final IntegerProperty POWER = IntegerProperty.create("power", 0, 15);
	
	public static final VoxelShape BASE = Shapes.box(0, 0, 0, 1, 0.375, 1);
	public static final VoxelShape ROD = Shapes.box(0.4375, 0.375, 0.4375, 0.5625, 0.75, 0.5625);
	public static final VoxelShape TOP = Shapes.box(0.375, 0.75, 0.375, 0.625, 1, 0.625);
	public static final VoxelShape ROD_BASE = Shapes.box(0.375, 0.1875, 0.375, 0.625, 0.4375, 0.625);
	public static final VoxelShape AABB = Shapes.or(BASE, ROD, TOP, ROD_BASE);
	
	@Override
	public VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
		return AABB;
	}
	@Override
	public RenderShape getRenderShape(@NotNull BlockState state) {
		return RenderShape.MODEL;
	}

	public StaticDetectorBlock(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(POWER, 0));
	}
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(POWER);
	}
	@Override
	public boolean useShapeForLightOcclusion(@NotNull BlockState state) {
		return true;
	}
	@Override
	public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		return BlockEntityInit.STATIC_DETECTOR.create(pos, state);
	}
	@Override
	public int getSignal(BlockState state, @NotNull BlockGetter get, @NotNull BlockPos pos, @NotNull Direction dir) {
		return state.getValue(POWER);
	}
	@Override
	public void neighborChanged(@NotNull BlockState state, @NotNull Level lev, @NotNull BlockPos pos, @NotNull Block blo,
								@NotNull BlockPos bpos, boolean bln) {
		updateSignal(lev, pos, state);
	}
	public static void updateSignal(Level lev,BlockPos pos,BlockState state) {
		int signal = 0;
		if(lev.isThundering()) {
			signal = 12;
		}
		lev.setBlock(pos, state.setValue(POWER, signal), 3);
	}
	@Override
	@Nullable
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level lev, @NotNull BlockState state, @NotNull BlockEntityType<T> type) {
		return lev.isClientSide ? null : createTickerHelper(type, BlockEntityInit.STATIC_DETECTOR, StaticDetectorBlock::tickEntity);
	}
	public static void tickEntity(Level lev,BlockPos pos,BlockState state,StaticDetectorBlockEntity entity) {
		if(lev.getGameTime()%20L == 0L) {
			updateSignal(lev, pos, state);
		}
	}
	@Override
	public boolean isSignalSource(@NotNull BlockState state) {
		return true;
	}
}
