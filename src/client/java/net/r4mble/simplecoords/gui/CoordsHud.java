package net.r4mble.simplecoords.gui;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.r4mble.simplecoords.SimpleCoordsClient;
import org.joml.Matrix3x2fStack;

@Environment(EnvType.CLIENT)
public class CoordsHud {
    int posX = 2;
    int posY = 2;
    int padding = 2;
    int offset = 5;

    public void register() {
        HudElementRegistry.addLast(
                Identifier.fromNamespaceAndPath("simplecoords", "coords_hud"),
                this::render
        );
    }

    private void render(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) {
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;
        if (player != null) {
            int textHeight = client.gui.getFont().lineHeight;
            int textWidth = 0;
            int drawCount = 0;
            String coords = "";
            String facing = "";
            String fps = "";
            String biome = "";
            int facingNumber = 0;
            int biomeNumber = 0;
            int fpsNumber = 0;
            if (SimpleCoordsClient.config.HudScale() > 0f) {
                Matrix3x2fStack matrices = graphics.pose();
                matrices.pushMatrix();
                matrices.scale(SimpleCoordsClient.config.HudScale(), SimpleCoordsClient.config.HudScale());
                if (SimpleCoordsClient.config.showCoords()) {
                    double x = player.getX();
                    double y = player.getY();
                    double z = player.getZ();
                    coords = String.format("X: %.2f Y: %.2f Z: %.2f", x, y, z).replace(',', '.');
                    textWidth = Math.max(textWidth, client.gui.getFont().width(coords));
                    drawCount++;

                }
                if (SimpleCoordsClient.config.showFacing()) {
                    facing = player.getDirection().getName();
                    facing = "Facing: " + facing.substring(0, 1).toUpperCase() + facing.substring(1);
                    textWidth = Math.max(textWidth, client.gui.getFont().width(facing));
                    facingNumber = drawCount;
                    drawCount++;

                }
                if (SimpleCoordsClient.config.showBiome()) {
                    biome = "Biome: " + player.level().getBiome(player.blockPosition());
                    textWidth = Math.max(textWidth, client.gui.getFont().width(biome));
                    biomeNumber = drawCount;
                    drawCount++;
                }
                if (SimpleCoordsClient.config.showFPS()) {
                    fps = client.getFps() + " fps";
                    textWidth = Math.max(textWidth, client.gui.getFont().width(fps));
                    fpsNumber = drawCount;
                    drawCount++;

                }
                if (drawCount > 0) {
                    graphics.fill(0, 0, textWidth + padding + offset, posY + drawCount * textHeight + padding, SimpleCoordsClient.config.backgroundColor());
                    graphics.text(client.font, coords, posX, posY, SimpleCoordsClient.config.textColor(), true);
                    graphics.text(client.font, facing, posX, posY + facingNumber * textHeight + 1, SimpleCoordsClient.config.textColor(), true);
                    graphics.text(client.font, biome, posX, posY + biomeNumber * textHeight + 1, SimpleCoordsClient.config.textColor(), true);
                    graphics.text(client.font, fps, posX, posY + fpsNumber * textHeight + 1, SimpleCoordsClient.config.textColor(), true);
                }
                matrices.popMatrix();
            }
        }
    }
}