package com.shiver.pkgacc.container;

import com.shiver.pkgacc.config.PackagedAccelerationConfig;
import com.shiver.pkgacc.item.ItemSpeedCard;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSpeedCard extends Slot {

    public SlotSpeedCard(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem() == ItemSpeedCard.INSTANCE;
    }

    @Override
    public int getSlotStackLimit() {
        return PackagedAccelerationConfig.maxSpeedCards;
    }
}
