package net.r4mble.simplecoords;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.r4mble.simplecoords.config.ModConfig;
import net.r4mble.simplecoords.config.ModConfigData;
import net.r4mble.simplecoords.config.ModConfigDefaults;
import net.r4mble.simplecoords.gui.CoordsHud;

public class SimpleCoordsClient implements ClientModInitializer {
	public static ModConfig config = new ModConfigDefaults();
	@Override
	public void onInitializeClient() {
		new CoordsHud().register();
		if(FabricLoader.getInstance().isModLoaded("cloth-config")){
			AutoConfig.register(ModConfigData.class, JanksonConfigSerializer::new);
			config = AutoConfig.getConfigHolder(ModConfigData.class).getConfig();
		}


	}
}