package com.shiver.pkgacc.container;

import com.shiver.pkgacc.item.ItemSpeedCard;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nonnull;

public class ContainerSpeedCard extends Container {

    private final InventorySpeedCard speedInventory;

    public ContainerSpeedCard(InventoryPlayer playerInventory, TileEntity tile) {
        speedInventory = new InventorySpeedCard(tile);
        addSlotToContainer(new SlotSpeedCard(speedInventory, 0, 80, 36));

        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 9; col++) {
                addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for(int col = 0; col < 9; col++) {
            addSlotToContainer(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer player) {
        return speedInventory.isUsableByPlayer(player);
    }

    @Override
    @MethodsReturnNonnullByDefault
    public ItemStack transferStackInSlot(@Nonnull EntityPlayer player, int index) {
        ItemStack ret;
        Slot slot = inventorySlots.get(index);
        if(slot == null || !slot.getHasStack()) {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getStack();
        ret = stack.copy();
        if(index == 0) {
            if(!mergeItemStack(stack, 1, inventorySlots.size(), true)) {
                return ItemStack.EMPTY;
            }
        }
        else if(stack.getItem() == ItemSpeedCard.INSTANCE) {
            if(!mergeItemStack(stack, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
        }
        else {
            return ItemStack.EMPTY;
        }
        if(stack.isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
        }
        else {
            slot.onSlotChanged();
        }
        return ret;
    }
}
