package net.r4mble.simplecoords.client.gui;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;

@Environment(EnvType.CLIENT)
public class CoordsHud implements HudRenderCallback {

    @Override
    public void onHudRender(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;

        if (player != null) {
            double x = player.getX();
            double y = player.getY();
            double z = player.getZ();
            String facing = player.getHorizontalFacing().toString();
            facing = "Facing: " + facing.substring(0, 1).toUpperCase() + facing.substring(1);
            String coords = String.format("X: %.2f Y: %.2f Z: %.2f", x, y, z).replace(',', '.');

            int posX = 2;
            int posY = 2;
            int textWidth = client.textRenderer.getWidth(coords);
            int textHeight = client.textRenderer.fontHeight;
            int padding = 2;
            int offset = 5;
            drawContext.fill(0, 0, textWidth + padding + offset, posY + 2 * textHeight + padding, 0x30000000);
            drawContext.drawTextWithShadow(client.textRenderer, coords, posX, posY, 0xFFFFFF);
            drawContext.drawTextWithShadow(client.textRenderer, facing, posX, posY + textHeight + 1, 0xFFFFFF);
        }
    }
}