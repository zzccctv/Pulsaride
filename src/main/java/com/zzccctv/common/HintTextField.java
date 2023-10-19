package com.zzccctv.common;

import javax.swing.*;
import java.awt.*;

public class HintTextField extends JTextField {
    private String _hint;

    public HintTextField(String hint) {
        _hint = hint;
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (this.getText().length() == 0) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            FontMetrics fm = g.getFontMetrics();
            g.setColor(Color.LIGHT_GRAY);
            g.drawString(_hint, this.getInsets().left, this.getHeight() / 2 + fm.getAscent() / 2 - 2);
        }
    }
}
