package net.r4mble.simplecoords;

import com.mojang.blaze3d.systems.RenderSystem;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfigClient;
import me.shedaniel.clothconfig2.ClothConfigDemo;
import me.shedaniel.clothconfig2.api.Modifier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.r4mble.simplecoords.config.ModConfig;
import net.r4mble.simplecoords.config.ModConfigData;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> {
            if (RenderSystem.isOnRenderThread() && Modifier.current().hasShift()) return AutoConfigClient.getConfigScreen(ModConfigData.class, screen).get();
            return ClothConfigDemo.getConfigBuilderWithDemo().setParentScreen(screen).build();
        };
    }

}