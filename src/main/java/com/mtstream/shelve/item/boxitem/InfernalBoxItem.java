package com.mtstream.shelve.item.boxitem;

import java.util.List;
import java.util.Random;

import com.mtstream.shelve.init.ItemInit;
import com.mtstream.shelve.item.ShrinkItemStack;
import com.mtstream.shelve.loot.ShelveLootTable;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;

public class InfernalBoxItem extends Item{

	public InfernalBoxItem(Properties properties) {
		super(properties);
	}
	public InteractionResultHolder<ItemStack> use(Level lev, Player pla, @NotNull InteractionHand han){
		Random random = new Random();
		int randomEvent = random.nextInt(2);
		ItemStack stack = pla.getItemInHand(han);
		ShrinkItemStack shr = new ShrinkItemStack();
	if(!lev.isClientSide()) {
		shr.ShrinkItem(pla, stack);
		if(pla.getInventory().getFreeSlot() >= 0){
			pla.addItem(new ItemStack(ItemInit.EMPTY_BOX));
		}else {
			pla.drop(new ItemStack(ItemInit.EMPTY_BOX), true);
		}
		switch (randomEvent) {
			case 0 -> {
				LootTable trash = lev.getServer().getLootTables().get(ShelveLootTable.INFERNAL_TRASH);
				LootContext trashcon = (new LootContext.Builder((ServerLevel) lev))
						.withLuck(pla.getLuck()).withParameter(LootContextParams.THIS_ENTITY, pla)
						.withParameter(LootContextParams.ORIGIN, pla.position()).create(LootContextParamSets.GIFT);
				List<ItemStack> trashLoot = trash.getRandomItems(trashcon);
				if (pla.getInventory().getFreeSlot() >= 0) {
					pla.addItem(trashLoot.get(0));
				} else {
					pla.drop(trashLoot.get(0), true);
				}
				lev.playSound(null, pla.getX(), pla.getY(), pla.getZ(), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.5f, 1.0f);
			}
			case 1 -> {
				LootTable tresure = lev.getServer().getLootTables().get(ShelveLootTable.INFERNAL_TRESURE);
				LootContext tresurecon = (new LootContext.Builder((ServerLevel) lev))
						.withLuck(pla.getLuck()).withParameter(LootContextParams.THIS_ENTITY, pla)
						.withParameter(LootContextParams.ORIGIN, pla.position()).create(LootContextParamSets.GIFT);
				List<ItemStack> tresureLoot = tresure.getRandomItems(tresurecon);
				if (pla.getInventory().getFreeSlot() >= 0) {
					pla.addItem(tresureLoot.get(0));
				} else {
					pla.drop(tresureLoot.get(0), true);
				}
				lev.playSound(null, pla.getX(), pla.getY(), pla.getZ(), SoundEvents.STONE_BREAK, SoundSource.PLAYERS, 0.5f, 1.0f);
			}
		}
		if(random.nextInt(5)==0) {
			pla.setSecondsOnFire(3);
		}
		
	}
	return InteractionResultHolder.sidedSuccess(stack, lev.isClientSide());
}

}
