package com.shiver.pkgacc.mixin;

import com.shiver.pkgacc.item.ItemSpeedCard;
import com.shiver.pkgacc.speed.SpeedCardHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thelm.packagedauto.block.BlockBase;

@Mixin(value = BlockBase.class, remap = false)
public abstract class MixinBlockBase {

    @Inject(method = "breakBlock", at = @At("HEAD"))
    private void PackagedAcceleration$dropSpeedCards(World world, BlockPos pos, IBlockState state, CallbackInfo ci) {
        if(world.isRemote) {
            return;
        }
        TileEntity tile = world.getTileEntity(pos);
        int cards = SpeedCardHelper.getCards(tile);
        if(cards <= 0) {
            return;
        }
        world.spawnEntity(new EntityItem(world, pos.getX()+0.5D, pos.getY()+0.5D, pos.getZ()+0.5D, new ItemStack(ItemSpeedCard.INSTANCE, cards)));
        SpeedCardHelper.setCards(tile, 0);
    }
}
