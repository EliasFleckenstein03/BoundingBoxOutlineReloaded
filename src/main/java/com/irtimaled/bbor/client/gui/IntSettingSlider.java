package com.irtimaled.bbor.client.gui;

import com.irtimaled.bbor.client.config.Setting;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;

import java.util.HashMap;
import java.util.Map;

class IntSettingSlider extends AbstractSlider {
    private final String format;
    private final Map<Integer, String> displayValues = new HashMap<>();

    final Setting<Integer> setting;
    final int minValue;

    IntSettingSlider(int width, int minValue, int maxValue, String format, Setting<Integer> setting) {
        super(width, maxValue - minValue);
        this.setting = setting;
        this.minValue = minValue;
        this.format = format;
        setInitialPosition();
        this.updateText();
    }

    protected void setInitialPosition() {
        this.setPosition(this.setting.get() - this.minValue);
    }

    IntSettingSlider addDisplayValue(int value, String displayValue) {
        displayValues.put(value, displayValue);
        if (setting.get() == value) {
            this.updateText();
        }
        return this;
    }

    protected Integer getSettingValue() {
        return minValue + getPosition();
    }

    private void updateText() {
        Integer value = setting.get();
        this.setMessage(MutableText.of(new LiteralTextContent(I18n.translate(format, displayValues.getOrDefault(value, value.toString())))));
    }

    @Override
    protected void onProgressChanged() {
        this.setting.set(this.getSettingValue());
        updateText();
    }

    @Override
    public void appendNarrations(NarrationMessageBuilder narrationMessageBuilder) {
        this.appendDefaultNarrations(narrationMessageBuilder);
    }
}
