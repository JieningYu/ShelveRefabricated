package com.mtstream.shelve.block;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class InstantTntBlock extends TntBlock{
	public InstantTntBlock(Properties prop) {
		super(prop);
	}

	public InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
		ItemStack itemStack = player.getItemInHand(hand);
		if (!itemStack.is(Items.FLINT_AND_STEEL) && !itemStack.is(Items.FIRE_CHARGE)) {
			return super.use(state, level, pos, player, hand, hit);
		} else {
			explode(level, pos, player);
			level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
			Item item = itemStack.getItem();
			if (!player.isCreative()) {
				if (itemStack.is(Items.FLINT_AND_STEEL)) {
					itemStack.hurtAndBreak(1, player, (playerx) -> {
						playerx.broadcastBreakEvent(hand);
					});
				} else {
					itemStack.shrink(1);
				}
			}
			player.awardStat(Stats.ITEM_USED.get(item));
			return InteractionResult.sidedSuccess(level.isClientSide);
		}
	}

	public static void explode(Level lev,BlockPos pos,@Nullable LivingEntity ign) {
		PrimedTnt tnt = new PrimedTnt(lev, pos.getX(), pos.getY(), pos.getZ(), ign);
		tnt.setFuse(0);
		lev.addFreshEntity(tnt);
		lev.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
        lev.gameEvent(ign, GameEvent.PRIME_FUSE, pos);
	}
}
