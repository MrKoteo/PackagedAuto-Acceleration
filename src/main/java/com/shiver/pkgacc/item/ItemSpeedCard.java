package com.shiver.pkgacc.item;

import com.shiver.pkgacc.PackagedAcceleration;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public class ItemSpeedCard extends Item {

    public static final ItemSpeedCard INSTANCE = new ItemSpeedCard();

    private ItemSpeedCard() {
        setRegistryName("packaged_acceleration:speed_card");
        setTranslationKey("packaged_acceleration.speed_card");
        setCreativeTab(PackagedAcceleration.CREATIVE_TAB);
    }

    @SideOnly(Side.CLIENT)
    public void registerModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(Objects.requireNonNull(getRegistryName()), "inventory"));
    }
}
