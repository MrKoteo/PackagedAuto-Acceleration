package com.shiver.pkgacc.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.shiver.pkgacc.speed.SpeedContext;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thelm.packagedauto.tile.TileBase;

@Mixin(World.class)
public abstract class MixinWorld {

    @WrapOperation(method = "updateEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ITickable;update()V"))
    private void PackagedAcceleration$wrapTileUpdate(ITickable tickable, Operation<Void> original) {
        if(tickable instanceof TileBase) {
            TileBase tile = (TileBase)tickable;
            SpeedContext.begin(tile);
            try {
                original.call(tickable);
            }
            finally {
                SpeedContext.end(tile);
            }
        }
        else {
            original.call(tickable);
        }
    }
}
