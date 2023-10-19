package com.zzccctv.common;


import com.intellij.openapi.util.Couple;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class BooleanHeader extends JCheckBox implements TableCellRenderer {
    public BooleanHeader() {
        super();
        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Couple<Color> colors = UIUtil.getCellColors(table, isSelected, row, column);
        setForeground(colors.getFirst());
        setBackground(colors.getSecond());
        if (value instanceof String) {
            setSelected(Boolean.parseBoolean((String) value));
        } else {
            setSelected(((Boolean) value).booleanValue());
        }
        setBorderPainted(true);
        setBorder(new MatteBorder(0, 0, 1, 1, table.getGridColor()));

        return this;
    }
}
