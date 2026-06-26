package com.shiver.pkgacc;

import com.shiver.packaged_acceleration.Tags;
import com.shiver.pkgacc.config.PackagedAccelerationConfig;
import com.shiver.pkgacc.event.CommonEventHandler;
import com.shiver.pkgacc.item.ItemSpeedCard;
import com.shiver.pkgacc.network.GuiHandler;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
        modid = Tags.MOD_ID,
        name = Tags.MOD_NAME,
        version = Tags.VERSION,
        dependencies = "required-after:packagedauto")
public class PackagedAcceleration {

    @Instance(Tags.MOD_ID)
    public static PackagedAcceleration instance;

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(Tags.MOD_ID) {
        @SideOnly(Side.CLIENT)
        @Override
        @MethodsReturnNonnullByDefault
        public ItemStack createIcon() {
            return new ItemStack(ItemSpeedCard.INSTANCE);
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PackagedAccelerationConfig.load(event.getSuggestedConfigurationFile());
        CommonEventHandler.register();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler.INSTANCE);
    }
}
