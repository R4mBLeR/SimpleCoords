package net.r4mble.simplecoords.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.r4mble.simplecoords.SimpleCoordsClient;

@Environment(EnvType.CLIENT)
public class CoordsHud implements HudRenderCallback {
    int posX = 2;
    int posY = 2;
    int padding = 2;
    int offset = 5;

    @Override
    public void onHudRender(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        if (player != null) {
            int textHeight = client.textRenderer.fontHeight;
            int textWidth = 0;
            int drawCount = 0;
            String coords;
            String facing;
            String fps;
            String biome;
            if (SimpleCoordsClient.config.HudScale() > 0f) {
                MatrixStack matrices = drawContext.getMatrices();
                matrices.push();
                matrices.scale(SimpleCoordsClient.config.HudScale(), SimpleCoordsClient.config.HudScale(), 1);
                if (SimpleCoordsClient.config.showCoords()) {
                    double x = player.getX();
                    double y = player.getY();
                    double z = player.getZ();
                    if(SimpleCoordsClient.config.showFractionalCoords()) {
                        coords = String.format("X: %.2f Y: %.2f Z: %.2f", x, y, z).replace(',', '.');
                    } else {
                        long IntX = Math.round(x);
                        long IntY = Math.round(y);
                        long IntZ = Math.round(z);
                        coords = String.format("X: %d Y: %d Z: %d", IntX, IntY, IntZ).replace(',', '.');
                    }
                    textWidth = Math.max(textWidth, client.textRenderer.getWidth(coords));
                    drawContext.drawTextWithShadow(client.textRenderer, coords, posX, posY, SimpleCoordsClient.config.textColor());
                    drawCount++;

                }
                if (SimpleCoordsClient.config.showFacing()) {
                    facing = player.getHorizontalFacing().toString();
                    facing = "Facing: " + facing.substring(0, 1).toUpperCase() + facing.substring(1);
                    textWidth = Math.max(textWidth, client.textRenderer.getWidth(facing));
                    drawContext.drawTextWithShadow(client.textRenderer, facing, posX, posY + drawCount * textHeight + 1, SimpleCoordsClient.config.textColor());
                    drawCount++;

                }
                if (SimpleCoordsClient.config.showBiome()) {
                    biome = "Biome: " + player.getWorld().getBiome(player.getBlockPos()).getKey().get().getValue().toString();
                    textWidth = Math.max(textWidth, client.textRenderer.getWidth(biome));
                    drawContext.drawTextWithShadow(client.textRenderer, biome, posX, posY + drawCount * textHeight + 1, SimpleCoordsClient.config.textColor());
                    drawCount++;
                }
                if (SimpleCoordsClient.config.showFPS()) {
                    fps = client.getCurrentFps() + " fps";
                    textWidth = Math.max(textWidth, client.textRenderer.getWidth(fps));
                    drawContext.drawTextWithShadow(client.textRenderer, fps, posX, posY + drawCount * textHeight + 1, SimpleCoordsClient.config.textColor());
                    drawCount++;

                }
                if (drawCount > 0) {
                    drawContext.fill(0, 0, textWidth + padding + offset, posY + drawCount * textHeight + padding, SimpleCoordsClient.config.backgroundColor());
                }
                matrices.pop();
            }
        }
    }
}