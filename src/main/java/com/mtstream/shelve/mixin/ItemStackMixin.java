package com.mtstream.shelve.mixin;

import com.mtstream.shelve.init.BlockInit;
import com.mtstream.shelve.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(method = "useOn", at = @At("RETURN"), cancellable = true)
    public void onUseShear(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        BlockPos pos = context.getClickedPos();
        Player pla = context.getPlayer();
        InteractionHand han = context.getHand();
        ItemStack stack = pla.getItemInHand(han);
        Level lev = context.getLevel();
        BlockState state = lev.getBlockState(pos);
        if(stack.getItem().equals(Items.MILK_BUCKET) && state.getBlock().equals(Blocks.CAULDRON)) {
            if(!lev.isClientSide) {
                pla.setItemInHand(han, new ItemStack(Items.BUCKET));
                lev.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS, 1.0f, 1.0f);
                lev.setBlockAndUpdate(pos, BlockInit.MILK_CAULDRON.defaultBlockState());
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
        if(stack.getItem().equals(Items.SHEARS) && state.getBlock().equals(Blocks.GRASS_BLOCK)) {
            if(!lev.isClientSide) {
                Block.popResourceFromFace(lev, pos, Direction.UP, new ItemStack(ItemInit.TURF));
                lev.setBlockAndUpdate(pos, Blocks.DIRT.defaultBlockState());
                lev.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 1.0f, 1.0f);
                stack.hurtAndBreak(1, pla, (c) -> c.broadcastBreakEvent(han));
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
        if(stack.getItem().equals(Items.SHEARS) && state.getBlock().equals(Blocks.TNT)) {
            if(!lev.isClientSide) {
                Block.popResourceFromFace(lev, pos, Direction.UP, new ItemStack(Items.GUNPOWDER));
                lev.setBlockAndUpdate(pos, BlockInit.INSTANT_TNT.defaultBlockState());
                lev.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1.0f, 1.0f);
                stack.hurtAndBreak(1, pla, (c) -> c.broadcastBreakEvent(han));
                cir.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }
}
