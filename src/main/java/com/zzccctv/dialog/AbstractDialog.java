package com.zzccctv.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.JBColor;
import com.intellij.ui.table.JBTable;
import com.zzccctv.common.BooleanHeader;
import com.zzccctv.common.ExceptionMessage;
import com.zzccctv.storage.PulsarStorage;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.internal.PulsarAdminBuilderImpl;
import org.apache.pulsar.client.api.PulsarClientException;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

abstract class AbstractDialog extends DialogWrapper {
    public static final String PARTITIONED_TOPIC_SUFFIX = "-partition-";
    public static final String OK_BUTTON = "OK";
    public static final String CANCEL_BUTTON = "Cancel";
    private static final PulsarStorage config = PulsarStorage.getInstance();

    protected AbstractDialog(@Nullable Project project) {
        super(project, true);
    }

    public Set<String> keys() {
        return config.envNames.keySet();
    }

    public Collection<Map<String, String>> values() {
        return config.envNames.values();
    }

    public void add(Map<String, String> envName) {
        config.addEnvName(envName);
    }

    public void remove(String envName) {
        config.removeEnvName(envName);
    }

    public void cleanTableAllRows(DefaultTableModel defaultTableModel) {
        defaultTableModel.setRowCount(0);
    }

    public void showException(Exception e) {
        System.setProperty("line.separator", "/");
        Throwable throwable = e.getCause();
        if (throwable instanceof NullPointerException || throwable == null) {
            Messages.showErrorDialog(e + "\n" + ExceptionMessage.FEEDBACK_MESSAGE, "");
        } else {
            Messages.showErrorDialog(e.getCause() + "\n" + ExceptionMessage.FEEDBACK_MESSAGE, "");
        }

        System.setProperty("line.separator", "\n");
    }

    public PulsarAdmin create(String envName) throws Exception {
        return create(envName, "");
    }

    public PulsarAdmin create(String envName, String serviceHttpUrl) throws Exception {
        Properties properties = new Properties();
        Map<String, String> envConfig = config.envNames.get(envName);
        try {
            properties.load(new StringReader(envConfig.get("properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        PulsarAdminBuilderImpl pulsarAdminBuilder = new PulsarAdminBuilderImpl();
        pulsarAdminBuilder.loadConf(new HashMap<String, Object>((Map) properties));
        pulsarAdminBuilder.serviceHttpUrl(serviceHttpUrl.length() == 0 ? envConfig.get("adminUrl") : serviceHttpUrl);
        pulsarAdminBuilder.requestTimeout(Integer.parseInt(envConfig.get("request")), TimeUnit.MILLISECONDS);
        pulsarAdminBuilder.readTimeout(Integer.parseInt(envConfig.get("request")), TimeUnit.MILLISECONDS);
        pulsarAdminBuilder.connectionTimeout(Integer.parseInt(envConfig.get("request")), TimeUnit.MILLISECONDS);
        pulsarAdminBuilder.authentication(envConfig.get("authPlugin"), envConfig.get("authParams"));
        return pulsarAdminBuilder.build();
    }

    public void headerSelectRenderer(JBTable table) {
        ((TableRowSorter) table.getRowSorter()).setSortable(0, false);
        TableColumnModel cm = table.getColumnModel();
        cm.getColumn(0).setMinWidth(50);
        cm.getColumn(0).setMaxWidth(50);
        table.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(new BooleanHeader());
    }

    public void tableDataVector(DefaultTableModel defaultTableModel, Vector<Vector> rows) {
        int count = defaultTableModel.getColumnCount();
        Vector column = new Vector();
        for (int i = 0; i < count; i++) {
            column.add(defaultTableModel.getColumnName(i));
        }
        defaultTableModel.setDataVector(rows, column);
    }

    public void cellIconRenderer(JBTable table, String column) {
        table.getColumn(column).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setIcon(new ImageIcon(getClass().getResource("/inline_edit.png")));
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
    }

    public void cellBooleanRenderer(JBTable table, String column) {
        table.getColumn(column).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if ("true".equals(value)) {
                    setForeground(JBColor.blue);
                } else {
                    setForeground(JBColor.black);
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
    }

    public void removeTableSelectRows(DefaultTableModel defaultTableModel) {
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if ((Boolean) defaultTableModel.getValueAt(i, 0)) {
                defaultTableModel.removeRow(i);
                i--;
            }
        }
    }

    public String[] parseTopic(String topic) {
        String tntPath = topic.split("://")[1];
        return tntPath.split("/");
    }
}
