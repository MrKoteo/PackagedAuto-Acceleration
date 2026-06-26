package com.shiver.pkgacc.mixin;

import com.shiver.pkgacc.speed.ISpeedCardHolder;
import com.shiver.pkgacc.speed.SpeedCardHelper;
import org.spongepowered.asm.mixin.Mixin;
import thelm.packagedauto.tile.TileBase;

@Mixin(value = TileBase.class, remap = false)
public abstract class MixinTileBase implements ISpeedCardHolder {

    @Override
    public int PackagedAcceleration$getSpeedCards() {
        return SpeedCardHelper.getCards((TileBase)(Object)this);
    }

    @Override
    public void PackagedAcceleration$setSpeedCards(int count) {
        SpeedCardHelper.setCards((TileBase)(Object)this, count);
    }
}
