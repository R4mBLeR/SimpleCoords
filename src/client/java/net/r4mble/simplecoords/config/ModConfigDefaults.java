package net.r4mble.simplecoords.config;

public class ModConfigDefaults implements ModConfig {
    public static final float HudScale = 1f;
    public static final boolean showCoords = true;
    public static final boolean showFractionalCoords = true;
    public static final boolean showFacing = false;
    public static final boolean showBiome = false;
    public static final boolean showFPS = false;
    public static final int textColor = 0xFFFFFF;
    public static final int backgroundColor = 0x30000000;

    @Override
    public float HudScale() {
        return HudScale;
    }

    @Override
    public boolean showCoords() {
        return showCoords;
    }

    @Override
    public boolean showFractionalCoords() {
        return showFractionalCoords;
    }

    @Override
    public boolean showFacing() {
        return showFacing;
    }

    @Override
    public boolean showBiome() {
        return showBiome;
    }

    @Override
    public boolean showFPS() {
        return showFPS;
    }

    @Override
    public int textColor() {
        return textColor;
    }

    @Override
    public int backgroundColor() {
        return backgroundColor;
    }
}