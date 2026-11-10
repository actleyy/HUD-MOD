package com.sasam.hud;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class SasamHudClient implements ClientModInitializer {
    public static KeyBinding openConfigKey;

    @Override
    public void onInitializeClient() {
        openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.sasamhud.config",
                InputUtil.Type.KEY_SYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.sasamhud.main"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openConfigKey.wasPressed()) {
                client.setScreen(new HudConfigScreen(null));
            }
        });

        HudRenderCallback.EVENT.register(HudRenderer::renderHud);
    }
}
