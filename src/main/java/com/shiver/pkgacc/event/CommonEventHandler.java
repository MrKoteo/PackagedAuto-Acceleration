package com.shiver.pkgacc.event;

import com.shiver.pkgacc.PackagedAcceleration;
import com.shiver.pkgacc.item.ItemSpeedCard;
import com.shiver.pkgacc.speed.SpeedCardHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonEventHandler {

    public static final CommonEventHandler INSTANCE = new CommonEventHandler();
    private static final int GUI_SPEED_CARD = 0;

    private CommonEventHandler() {}

    public static void register() {
        MinecraftForge.EVENT_BUS.register(INSTANCE);
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack held = player.getHeldItem(event.getHand());
        if(!player.isSneaking() || held.getItem() != ItemSpeedCard.INSTANCE) {
            return;
        }
        TileEntity tile = event.getWorld().getTileEntity(event.getPos());
        if(!SpeedCardHelper.isSupported(tile)) {
            return;
        }
        event.setCanceled(true);
        if(!event.getWorld().isRemote) {
            player.openGui(PackagedAcceleration.instance, GUI_SPEED_CARD, event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if(event.getWorld().isRemote) {
            return;
        }
        TileEntity tile = event.getWorld().getTileEntity(event.getPos());
        int cards = SpeedCardHelper.getCards(tile);
        if(cards > 0) {
            event.getWorld().spawnEntity(new EntityItem(
                    event.getWorld(),
                    event.getPos().getX()+0.5D,
                    event.getPos().getY()+0.5D,
                    event.getPos().getZ()+0.5D,
                    new ItemStack(ItemSpeedCard.INSTANCE, cards)));
            SpeedCardHelper.setCards(tile, 0);
        }
    }
}
