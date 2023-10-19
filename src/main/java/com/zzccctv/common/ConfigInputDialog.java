package com.zzccctv.common;

import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;
import java.awt.*;

public class ConfigInputDialog extends Messages.InputDialog {
    private static String _key = "";
    private static String _value = "";

    public ConfigInputDialog(String title) {
        super("", title, Messages.getInformationIcon(), _value, new InputValidator() {
            @Override
            public boolean checkInput(String inputString) {
                return true;
            }

            @Override
            public boolean canClose(String inputString) {
                return true;
            }
        });
    }

    public static void initValue(String key, String value) {
        _key = key;
        _value = value;
    }

    @Override
    public JPanel createMessagePanel() {
        JPanel panel1 = new JPanel();
        myField = new JTextField();
        JToolBar toolBar1 = new JToolBar();
        JLabel label1 = new JLabel();
        JTextField textField1 = new JTextField(_key);
        JToolBar toolBar2 = new JToolBar();
        JLabel label2 = new JLabel();
        JTextField textField2 = new JTextField(_value);
        JToolBar toolBar3 = new JToolBar();
        JLabel label3 = new JLabel();

        //======== panel1 ========
        {
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

            //======== toolBar1 ========
            {
                toolBar1.setEnabled(false);
                toolBar1.setBorder(null);

                //---- label1 ----
                label1.setText("Key");
                label1.setMaximumSize(new Dimension(87, 16));
                label1.setMinimumSize(new Dimension(87, 16));
                label1.setPreferredSize(new Dimension(87, 16));
                toolBar1.add(label1);

                //---- textField1 ----
                textField1.setMaximumSize(new Dimension(2147483647, 30));
                textField1.setEnabled(false);
                toolBar1.add(textField1);
            }
            panel1.add(toolBar1);

            //======== toolBar2 ========
            {
                toolBar2.setEnabled(false);
                toolBar2.setBorder(null);

                //---- label2 ----
                label2.setText("Current Value");
                label2.setMaximumSize(new Dimension(87, 16));
                label2.setMinimumSize(new Dimension(87, 16));
                label2.setPreferredSize(new Dimension(87, 16));
                toolBar2.add(label2);

                //---- textField2 ----
                textField2.setMaximumSize(new Dimension(2147483647, 30));
                textField2.setEnabled(false);
                toolBar2.add(textField2);
            }
            panel1.add(toolBar2);

            //======== toolBar3 ========
            {
                toolBar3.setEnabled(false);
                toolBar3.setBorder(null);

                //---- label3 ----
                label3.setText("New Value");
                label3.setMaximumSize(new Dimension(87, 16));
                label3.setMinimumSize(new Dimension(87, 16));
                label3.setPreferredSize(new Dimension(87, 16));
                toolBar3.add(label3);

                //---- textField3 ----
                myField.setMaximumSize(new Dimension(2147483647, 30));
                toolBar3.add(myField);
            }
            panel1.add(toolBar3);
            panel1.setPreferredSize(new Dimension(200 + Math.max(_key.length(), _value.length()) * 5, 100));
        }
        return panel1;
    }
}
