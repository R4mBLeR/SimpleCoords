package net.r4mble.simplecoords;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.autoconfig.AutoConfigClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.network.chat.Component;
import net.r4mble.simplecoords.config.ModConfigData;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> {
            if (FabricLoader.getInstance().isModLoaded("cloth-config")) {
                return AutoConfigClient.getConfigScreen(ModConfigData.class, screen).get();
            }
            return new ConfirmScreen(
                    confirmed -> Minecraft.getInstance().setScreen(screen),
                    Component.literal("§cCloth Config API Required"),
                    Component.literal("Install Cloth Config API to use the configuration screen."),
                    Component.literal("Download from CurseForge or Modrinth"),
                    Component.empty()
            );
        };
    }

}