package com.zzccctv.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import com.zzccctv.common.BooleanHeader;
import com.zzccctv.common.PropertiesCellRenderer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class EnvironmentDialog extends AbstractDialog {
    private Project _project;
    private JButton add;
    private JButton remove;
    private JToolBar toolBar1;
    private SearchTextField textField1;
    private JBScrollPane scrollPane1;
    private JBTable table1;
    private DefaultTableModel defaultTableModel;
    private JPanel jbPanel;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public EnvironmentDialog(@Nullable Project project) {
        super(project);
        _project = project;
        setTitle("Environment");
        init();
    }

    @Override
    protected JComponent createSouthPanel() {
        return null;
    }

    @Override
    protected JComponent createCenterPanel() {
        jbPanel = new JPanel();
        rowSorter = new TableRowSorter<>();
        add = new JButton();
        remove = new JButton();
        toolBar1 = new JToolBar();
        textField1 = new SearchTextField();
        scrollPane1 = new JBScrollPane();
        table1 = new JBTable();
        {
            toolBar1.setBorder(null);
            toolBar1.setMaximumSize(new Dimension(32939, 30));
            toolBar1.setEnabled(false);
            add.setIcon(new ImageIcon(getClass().getResource("/add.png")));
            add.setPreferredSize(new Dimension(30, 30));
            add.addActionListener(this::addEnvironment);
            remove.setIcon(new ImageIcon(getClass().getResource("/remove.png")));
            remove.setPreferredSize(new Dimension(30, 30));
            remove.addActionListener(this::removeEnvironment);
            toolBar1.add(add);
            toolBar1.add(remove);
            textField1.setBorder(null);
            textField1.addDocumentListener(new DocumentAdapter() {
                @Override
                protected void textChanged(@NotNull DocumentEvent e) {
                    try {
                        rowSorter.setRowFilter(RowFilter.regexFilter(e.getDocument().getText(0, e.getDocument().getLength())));
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            toolBar1.add(textField1);
        }
        jbPanel.setBorder(null);
        jbPanel.setLayout(new BoxLayout(jbPanel, BoxLayout.Y_AXIS));
        jbPanel.add(toolBar1);
        {

            defaultTableModel = new DefaultTableModel() {
                final boolean[] columnEditable = new boolean[]{
                        true, false, false, false, false, false, false
                };

                @Override
                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return columnEditable[columnIndex];
                }

                final Class<?>[] columnTypes = new Class<?>[]{
                        Boolean.class, String.class, String.class, String.class, String.class, String.class, String.class
                };

                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    return columnTypes[columnIndex];
                }
            };
            defaultTableModel.addColumn("");
            defaultTableModel.addColumn("EnvName");
            defaultTableModel.addColumn("AdminUrl");
            defaultTableModel.addColumn("AuthPlugin");
            defaultTableModel.addColumn("AuthParams");
            defaultTableModel.addColumn("RequestTimeout");
            defaultTableModel.addColumn("Properties");
            values().forEach(i -> defaultTableModel.addRow(new Object[]{false, i.get("envName"), i.get("adminUrl"),i.get("authPlugin"),
                    i.get("authParams"),i.get("request"), i.get("properties")}));
            table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            rowSorter.setModel(defaultTableModel);
            table1.setModel(defaultTableModel);
            table1.setRowSorter(rowSorter);
            table1.getColumnModel().getColumn(4).setCellRenderer(new PropertiesCellRenderer());
            rowSorter.setSortable(0, false);
            TableColumnModel cm = table1.getColumnModel();
            cm.getColumn(0).setMinWidth(50);
            cm.getColumn(0).setMaxWidth(50);
            table1.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(new BooleanHeader());
            table1.getTableHeader().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (table1.getColumnModel().getColumnIndexAtX(e.getX()) == 0) {
                        boolean select = !Boolean.parseBoolean(cm.getColumn(0).getHeaderValue().toString());
                        cm.getColumn(0).setHeaderValue(select);
                        for (int i = 0; i < table1.getRowCount(); i++) {
                            table1.setValueAt(!(Boolean) table1.getValueAt(i, 0), i, 0);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
            scrollPane1.setViewportView(table1);
        }
        jbPanel.add(scrollPane1);
        jbPanel.setPreferredSize(new Dimension(710, 420));
        return jbPanel;
    }

    private void removeEnvironment(ActionEvent actionEvent) {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
            if ((Boolean) defaultTableModel.getValueAt(i, 0)) {
                String name = (String) defaultTableModel.getValueAt(i, 1);
                names.add(name);
            }
        }
        if (names.size() > 0 && Messages.OK == Messages.showOkCancelDialog(
                "Confirm delete the following envNames: \n" + String.join("\n", names),
                "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
            for (int i = 0; i < defaultTableModel.getRowCount(); i++) {
                if ((Boolean) defaultTableModel.getValueAt(i, 0)) {
                    String name = (String) defaultTableModel.getValueAt(i, 1);
                    defaultTableModel.removeRow(i);
                    i--;
                    remove(name);
                }
            }
        }
    }

    private void addEnvironment(ActionEvent actionEvent) {
        Dimension dimension = this.getSize();
        this.getWindow().setVisible(false);
        ConfigEnvironment configEnvironment = new ConfigEnvironment(_project);
        configEnvironment.show();
        if (configEnvironment.getExitCode() == OK_EXIT_CODE) {
            Map<String, String> values = configEnvironment.getAddEnvironment();
            if (keys().contains(values.get("envName"))) {
                values.put("envName", values.get("envName").concat(String.valueOf(keys().size())));
            }
            add(configEnvironment.getAddEnvironment());
            defaultTableModel.addRow(new Object[]{false, values.get("envName"), values.get("adminUrl"), values.get("authPlugin"), values.get("authParams"), values.get("request"), values.get("properties")});
        }
        this.getWindow().setPreferredSize(dimension);
        this.getWindow().setVisible(true);
    }
}
