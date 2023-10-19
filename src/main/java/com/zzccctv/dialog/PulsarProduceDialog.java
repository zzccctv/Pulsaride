package com.zzccctv.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.ui.border.IdeaTitledBorder;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.zzccctv.common.HintTextField;
import com.zzccctv.utils.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class PulsarProduceDialog extends DialogWrapper {
    private Project _project;
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JLabel label3;
    private JTextField textField3;
    private JLabel label4;
    private JTextField textField4;
    private JPanel jPanel;

    public PulsarProduceDialog(@Nullable Project project) {
        super(project);
        setTitle("Creates new pulsar produce");
        _project = project;
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        jPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        label1 = new JLabel();
        textField1 = new HintTextField(" package.class");
        label2 = new JLabel();
        textField2 = new HintTextField(" pulsar://my-broker:port ");
        textField2.setToolTipText("the URL of the Pulsar service that the client should connect to");
        label3 = new JLabel();
        textField3 = new HintTextField(" the name of the topic ");
        label4 = new JLabel();
        textField4 = new HintTextField("");
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        //======== this ========
        panel1.setMaximumSize(new Dimension(2147483647, 120));
        panel1.setBorder(new IdeaTitledBorder("Class Name", 0, new Insets(0, 0, 0, 0)));
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 20, 0, 0), 0, 0));

        //---- label1 ----
        label1.setText("Name");
        label1.setMaximumSize(new Dimension(65, 16));
        label1.setMinimumSize(new Dimension(65, 16));
        label1.setPreferredSize(new Dimension(65, 16));
        panel1.add(label1, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
        panel1.add(textField1, new GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                null, null, null));
        jPanel.add(panel1);
        panel2.setMaximumSize(new Dimension(2147483647, 120));
        panel2.setBorder(new IdeaTitledBorder("Base Info", 0, new Insets(0, 0, 0, 0)));
        panel2.setLayout(new GridLayoutManager(3, 2, new Insets(0, 20, 0, 0), 0, 0));
        //---- label2 ----
        label2.setText("ServiceUrl");
        panel2.add(label2, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
        panel2.add(textField2, new GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                null, null, null));

        //---- label3 ----
        label3.setText("Topic");
        panel2.add(label3, new GridConstraints(1, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_FIXED,
                GridConstraints.SIZEPOLICY_FIXED,
                null, null, null));
        panel2.add(textField3, new GridConstraints(1, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                null, null, null));

        //---- label4 ----
        label4.setText("Value");
        panel2.add(label4, new GridConstraints(2, 0, 1, 1,
                GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        panel2.add(textField4, new GridConstraints(2, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                null, null, null));
        jPanel.add(panel2);
        jPanel.setPreferredSize(new Dimension(380, 100));
        return jPanel;
    }

    protected ValidationInfo doValidate() {
        if (StringUtils.isBlank(textField1.getText().trim())) {
            return new ValidationInfo("Class name cannot be empty", textField1);
        }
        if (StringUtils.isBlank(textField2.getText().trim())) {
            return new ValidationInfo("Service url cannot be empty", textField2);
        }
        if (StringUtils.isBlank(textField3.getText().trim())) {
            return new ValidationInfo("Topic name cannot be empty", textField3);
        }

        return null;
    }

    public String getName() {
        return textField1.getText().trim();
    }

    public String getServiceURL() {
        return textField2.getText().trim();
    }
    public String getTopic() {
        return textField3.getText().trim();
    }

    public String getValue(){
        return textField4.getText().trim();
    }
}
