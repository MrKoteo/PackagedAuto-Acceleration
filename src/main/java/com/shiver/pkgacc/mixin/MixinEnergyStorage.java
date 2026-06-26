package com.shiver.pkgacc.mixin;

import com.shiver.pkgacc.config.PackagedAccelerationConfig;
import com.shiver.pkgacc.speed.SpeedContext;
import com.shiver.pkgacc.speed.SpeedCardHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import thelm.packagedauto.tile.TileBase;

@Mixin(value = thelm.packagedauto.energy.EnergyStorage.class, remap = false)
public abstract class MixinEnergyStorage extends net.minecraftforge.energy.EnergyStorage {

    @Shadow
    @Final
    public TileBase tile;

    protected MixinEnergyStorage(int capacity) {
        super(capacity);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extracted = super.extractEnergy(maxExtract, simulate);
        if(simulate || extracted <= 0 || tile == null || tile.getWorld().isRemote || !SpeedContext.isTicking(tile)) {
            return extracted;
        }
        int cards = SpeedCardHelper.getCards(tile);
        if(cards <= 0) {
            return extracted;
        }
        return extracted + (int)Math.floor(extracted * cards * PackagedAccelerationConfig.speedPerCard);
    }
}
