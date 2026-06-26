package com.shiver.pkgacc.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class PackagedAccelerationConfig {

    public static int maxSpeedCards = 20;
    public static double speedPerCard = 0.5D;

    private PackagedAccelerationConfig() {}

    public static void load(File file) {
        Configuration config = new Configuration(file);
        String category = "speed_card";
        maxSpeedCards = config.get(category, "max_speed_cards", maxSpeedCards, "Maximum speed cards per machine.", 0, 64).getInt();
        speedPerCard = config.get(category, "speed_per_card", speedPerCard, "Speed multiplier added by each card.", 0D, Double.MAX_VALUE).getDouble();
        if(config.hasChanged()) {
            config.save();
        }
    }
}
