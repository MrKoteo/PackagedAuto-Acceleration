package com.shiver.pkgacc.speed;

import thelm.packagedauto.tile.TileBase;

public class SpeedContext {

    private static TileBase tickingTile;

    private SpeedContext() {}

    public static void begin(TileBase tile) {
        tickingTile = tile;
    }

    public static void end(TileBase tile) {
        if(tickingTile == tile) {
            tickingTile = null;
        }
    }

    public static boolean isTicking(TileBase tile) {
        return tickingTile == tile;
    }
}
