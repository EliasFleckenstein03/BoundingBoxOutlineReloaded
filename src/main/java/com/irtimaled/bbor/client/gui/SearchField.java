package com.irtimaled.bbor.client.gui;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;

public class SearchField extends TextFieldWidget implements IControl {
    private final ControlList controlList;

    SearchField(TextRenderer fontRenderer, int left, int top, int width, int height, ControlList controlList) {
        super(fontRenderer, left, top, width, height, MutableText.of(new LiteralTextContent("")));

        this.controlList = controlList;
        this.setChangedListener(text -> this.controlList.filter(removeLeadingSpaces(text.toLowerCase())));
        this.setRenderTextProvider((text, id) -> MutableText.of(new LiteralTextContent(removeLeadingSpaces(text))).asOrderedText());
        this.setFocused(true);
    }

    private String removeLeadingSpaces(String text) {
        return text.replaceFirst("^\\s++", "");
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY) {
        this.render(matrixStack, mouseX, mouseY, 0f);
    }

    @Override
    public boolean isVisible() {
        return super.isVisible();
    }
}
