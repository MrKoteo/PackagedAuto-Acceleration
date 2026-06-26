package com.shiver.pkgacc.network;

import com.shiver.pkgacc.client.gui.GuiSpeedCard;
import com.shiver.pkgacc.container.ContainerSpeedCard;
import com.shiver.pkgacc.speed.SpeedCardHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    public static final GuiHandler INSTANCE = new GuiHandler();

    private GuiHandler() {}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        return SpeedCardHelper.isSupported(tile) ? new ContainerSpeedCard(player.inventory, tile) : null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(new BlockPos(x, y, z));
        return SpeedCardHelper.isSupported(tile) ? new GuiSpeedCard(player.inventory, tile) : null;
    }
}
