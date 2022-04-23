package com.mtstream.shelve.mixin;

import com.mtstream.shelve.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EnchantmentTableBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentTableBlock.class)
public class EnchantmentTableBlockMixin {
    @Inject(method = "isValidBookShelf", at = @At("RETURN"), cancellable = true)
    private static void onIsValidBookShelf(Level level, BlockPos blockPos, BlockPos blockPos2, CallbackInfoReturnable<Boolean> cir) {
        if (level.getBlockState(blockPos.offset(blockPos2)).is(BlockInit.CRYSTAL_BALL) && level.isEmptyBlock(blockPos.offset(blockPos2.getX() / 2, blockPos2.getY(), blockPos2.getZ() / 2))) {
            // Since we don't have that many patches in Fabric, we use mixin to implement Crystal Ball functionality, which is a very stupid method and lacks of editing the enchantment power bonus
            cir.setReturnValue(true);
        }
    }
}
