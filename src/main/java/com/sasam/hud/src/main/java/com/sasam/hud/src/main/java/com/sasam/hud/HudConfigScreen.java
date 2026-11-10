package com.sasam.hud;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class HudConfigScreen extends Screen {
    private final Screen parent;

    public HudConfigScreen(Screen parent) {
        super(Text.literal("SASAM HUD Customizer"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2 - 100;
        int centerY = this.height / 4;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Keystrokes HUD ✏️"), b -> {}).dimensions(centerX, centerY, 200, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Armor HUD ✏️"), b -> {}).dimensions(centerX, centerY + 25, 200, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Potion HUD ✏️"), b -> {}).dimensions(centerX, centerY + 50, 200, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Ping Display ✏️"), b -> {}).dimensions(centerX, centerY + 75, 200, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("FPS & Coordinates ✏️"), b -> {}).dimensions(centerX, centerY + 100, 200, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("CPS Counter ✏️"), b -> {}).dimensions(centerX, centerY + 125, 200, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Save & Close"), b -> this.close()).dimensions(centerX, centerY + 160, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 30, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }
}
