package net.r4mble.simplecoords.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "simplecoords")
public class ModConfigData implements ConfigData, ModConfig {
    public float HudScale = ModConfigDefaults.HudScale;
    public boolean showCoords = ModConfigDefaults.showCoords;
    public boolean showFractionalCoords = ModConfigDefaults.showFractionalCoords;
    public boolean showFacing = ModConfigDefaults.showFacing;
    public boolean showBiome = ModConfigDefaults.showBiome;
    public boolean showFPS = ModConfigDefaults.showFPS;
    @ConfigEntry.ColorPicker
    public int textColor = ModConfigDefaults.textColor;
    @ConfigEntry.ColorPicker(allowAlpha = true)
    public int backgroundColor = ModConfigDefaults.backgroundColor;

    @Override
    public float HudScale() {
        if (HudScale < 0) {
            HudScale = ModConfigDefaults.HudScale;
        }
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
