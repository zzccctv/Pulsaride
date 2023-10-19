package com.zzccctv.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.zzccctv.common.HintTextArea;
import com.zzccctv.common.HintTextField;
import com.zzccctv.utils.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigEnvironment extends DialogWrapper {

    private JPanel panel1;
    private JLabel label1;
    private JTextField envNameField;
    private JLabel label2;
    private JTextField adminUrlField;
    private JLabel label3;
    private JSpinner requestSpinner;
    private JLabel label4;
    private JScrollPane scrollPane1;
    private JBTextArea propertiesArea;
    private JTextField authPluginField;
    private JTextField authParamsField;

    public ConfigEnvironment(@Nullable Project project) {
        super(project);
        setTitle("Environment");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        panel1 = new JPanel();
        label1 = new JLabel();
        envNameField = new JTextField();
        label2 = new JLabel();
        adminUrlField = new HintTextField(" http://hostname:port,hostname:port");
        adminUrlField.setToolTipText(" Admin Service URL to which to connect");
        label3 = new JLabel();
        requestSpinner = new JSpinner();
        requestSpinner.setToolTipText("Request time out in seconds for the pulsar admin client for any request");
        authPluginField = new HintTextField(" Authentication plugin class name.");
        authPluginField.setToolTipText("Authentication plugin class name.");
        authParamsField = new HintTextField(" \"key1:val1,key2:val2\" or {\"key1\":\"val1\",\"key2\":\"val2\"}");
        authParamsField.setToolTipText("<html>Authentication parameters, whose format is determined by the <br>" +
                "implementation of method `configure` in authentication plugin <br>" +
                "class, for example \"key1:val1,key2:val2\" or {\"key1\":\"val1\",\"key2\":\"val2\"}.</html>");
        label4 = new JLabel();
        scrollPane1 = new JBScrollPane();
        propertiesArea = new HintTextArea("tlsAllowInsecureConnection=...\ntlsEnableHostnameVerification=...");
        propertiesArea.setToolTipText("");
        {
            panel1.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), 0, 0));
            label1.setText("EnvName");
            panel1.add(label1, new GridConstraints(0, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            envNameField.setMaximumSize(new Dimension(2147483647, 30));
            panel1.add(envNameField, new GridConstraints(0, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            label2.setText("AdminUrl");
            panel1.add(label2, new GridConstraints(1, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            adminUrlField.setMaximumSize(new Dimension(2147483647, 30));
            panel1.add(adminUrlField, new GridConstraints(1, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            label3.setText("RequestTimeout(ms)");
            panel1.add(label3, new GridConstraints(2, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            requestSpinner.setModel(new SpinnerNumberModel(5000, 1000, null, 1000));
            panel1.add(requestSpinner, new GridConstraints(2, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            ((JSpinner.DefaultEditor) requestSpinner.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
            panel1.add(new JLabel("AuthPlugin"), new GridConstraints(3, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            authPluginField.setMaximumSize(new Dimension(2147483647, 30));
            panel1.add(authPluginField, new GridConstraints(3, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            panel1.add(new JLabel("AuthParams"), new GridConstraints(4, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            authPluginField.setMaximumSize(new Dimension(2147483647, 30));
            panel1.add(authParamsField, new GridConstraints(4, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            label4.setText("Properties");
            panel1.add(label4, new GridConstraints(5, 0, 1, 1,
                    GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                    GridConstraints.SIZEPOLICY_FIXED,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
            {
                scrollPane1.setViewportView(propertiesArea);
            }
            panel1.add(scrollPane1, new GridConstraints(5, 1, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    null, null, null));
        }
        panel1.setPreferredSize(new Dimension(710, 420));
        return panel1;
    }

    public JComponent getPreferredFocusedComponent() {
        return envNameField;
    }

    public Map<String, String> getAddEnvironment() {
        Map<String, String> map = new HashMap<>();
        map.put("envName", envNameField.getText().trim());
        map.put("adminUrl", adminUrlField.getText().trim());
        map.put("authPlugin", authPluginField.getText().trim());
        map.put("authParams", authParamsField.getText().trim());
        map.put("request", requestSpinner.getValue().toString());
        map.put("properties", propertiesArea.getText().trim());
        return map;
    }

    protected ValidationInfo doValidate() {
        if (StringUtils.isBlank(envNameField.getText())) {
            return new ValidationInfo("EnvName name cannot be empty", envNameField);
        }
        if (StringUtils.isBlank(adminUrlField.getText())) {
            return new ValidationInfo("AdminUrl cannot be empty", adminUrlField);
        }

        if (!adminUrlField.getText().startsWith("http://") && !adminUrlField.getText().startsWith("https://")) {
            return new ValidationInfo("AdminUrl is illegal", adminUrlField);
        }

        return null;
    }
}
