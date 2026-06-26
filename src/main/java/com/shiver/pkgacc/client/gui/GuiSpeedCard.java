package com.shiver.pkgacc.client.gui;

import com.shiver.pkgacc.container.ContainerSpeedCard;
import com.shiver.pkgacc.speed.SpeedCardHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiSpeedCard extends GuiContainer {

    private static final ResourceLocation TEXTURE = new ResourceLocation("packaged_acceleration", "gui/gui.png");
    private final InventoryPlayer playerInventory;
    private final TileEntity tile;

    public GuiSpeedCard(InventoryPlayer playerInventory, TileEntity tile) {
        super(new ContainerSpeedCard(playerInventory, tile));
        this.playerInventory = playerInventory;
        this.tile = tile;
        xSize = 176;
        ySize = 166;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = I18n.format("container.packaged_acceleration.speed_card");
        fontRenderer.drawString(title, 8, 6, 4210752);
        int cards = SpeedCardHelper.getCards(tile);
        String speed = I18n.format("container.packaged_acceleration.speed_card.multiplier", cards, SpeedCardHelper.getMultiplier(cards));
        fontRenderer.drawString(speed, 8, 20, 4210752);
        fontRenderer.drawString(playerInventory.getDisplayName().getUnformattedText(), 8, 74, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1F, 1F, 1F, 1F);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        mc.getTextureManager().bindTexture(TEXTURE);
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
