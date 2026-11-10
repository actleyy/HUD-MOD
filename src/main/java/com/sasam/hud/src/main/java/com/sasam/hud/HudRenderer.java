package com.sasam.hud;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;

public class HudRenderer {
    public static void renderHud(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options.hudHidden || client.player == null || client.world == null) return;

        TextRenderer textRenderer = client.textRenderer;
        int x = 10, y = 10;

        int fps = client.getCurrentFps();
        BlockPos pos = client.player.getBlockPos();
        context.drawText(textRenderer, "FPS: " + fps, x, y, 0xFFFFFF, true);
        context.drawText(textRenderer, String.format("XYZ: %d, %d, %d", pos.getX(), pos.getY(), pos.getZ()), x, y + 10, 0xFFFFFF, true);

        int ping = 0;
        if (client.getNetworkHandler() != null && client.getNetworkHandler().getPlayerListEntry(client.player.getUuid()) != null) {
            ping = client.getNetworkHandler().getPlayerListEntry(client.player.getUuid()).getLatency();
        }
        context.drawText(textRenderer, "Ping: " + ping + "ms", x, y + 22, 0xFFFFFF, true);
        context.drawText(textRenderer, "CPS: L: 0 | R: 0", x, y + 34, 0xFFFFFF, true);

        int armorY = y + 50;
        for (ItemStack armorStack : client.player.getArmorItems()) {
            if (!armorStack.isEmpty()) {
                context.drawItem(armorStack, x, armorY);
                int durability = armorStack.getMaxDamage() - armorStack.getDamage();
                context.drawText(textRenderer, durability + "", x + 20, armorY + 4, 0xFFFFFF, true);
                armorY += 18;
            }
        }
    }
}
