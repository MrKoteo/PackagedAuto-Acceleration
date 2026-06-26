package com.shiver.pkgacc.mixin;

import com.shiver.pkgacc.speed.SpeedContext;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import thelm.packagedauto.tile.TileBase;

@Mixin(World.class)
public abstract class MixinWorld {

    @Redirect(method = "updateEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ITickable;update()V"))
    private void PackagedAcceleration$wrapTileUpdate(ITickable tickable) {
        if(tickable instanceof TileBase) {
            TileBase tile = (TileBase)tickable;
            SpeedContext.begin(tile);
            try {
                tickable.update();
            }
            finally {
                SpeedContext.end(tile);
            }
        }
        else {
            tickable.update();
        }
    }
}
