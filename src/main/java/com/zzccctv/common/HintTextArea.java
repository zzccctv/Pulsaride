package com.zzccctv.common;

import com.intellij.ui.components.JBTextArea;

import java.awt.*;

public class HintTextArea extends JBTextArea {
    private String _hint;

    public HintTextArea(String hint) {
        _hint = hint;
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (this.getText().length() == 0) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.LIGHT_GRAY);
            int y = this.getMargin().top;
            for (String line : _hint.split("\n")) {
                g.drawString(line, this.getInsets().left, y = y + fm.getHeight());
            }
        }
    }
}
