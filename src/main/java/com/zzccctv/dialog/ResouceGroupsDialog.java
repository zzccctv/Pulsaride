package com.zzccctv.dialog;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.JBPopupMenu;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.Tree;
import com.zzccctv.common.ConfigInputDialog;
import com.zzccctv.model.ResourceGroupsOverview;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.ResourceGroups;
import org.apache.pulsar.client.admin.Tenants;
import org.apache.pulsar.common.policies.data.ResourceGroup;
import org.apache.pulsar.common.policies.data.TenantInfo;
import org.apache.pulsar.common.policies.data.TenantInfoImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class ResouceGroupsDialog extends AbstractDialog {
    private JSplitPane splitPane1;
    private JSplitPane splitPane2;
    private JBScrollPane scrollPane1;
    private Tree tree1;
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JToolBar toolBar1;
    private SearchTextField textField1;
    private JBScrollPane scrollPane3;
    private JBTable table1;
    private JPanel panel4;
    private JBScrollPane scrollPane2;
    private Tree tree2;
    private Project _project;
    private DefaultTableModel defaultTableModel1;

    public ResouceGroupsDialog(@Nullable Project project) {
        super(project);
        _project = project;
        setTitle("pulsar-admin resourcegroups");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setBorder(null);
        splitPane1 = new JSplitPane();
        scrollPane1 = new JBScrollPane();
        splitPane2 = new JSplitPane();
        tree1 = new Tree();
        tabbedPane1 = new JBTabbedPane();
        panel2 = new JPanel();
        toolBar1 = new JToolBar();
        textField1 = new SearchTextField();
        scrollPane3 = new JBScrollPane();
        table1 = new JBTable();
        panel4 = new JPanel();
        scrollPane2 = new JBScrollPane();
        tree2 = new Tree();
        TableRowSorter<DefaultTableModel> rowSorter1 = new TableRowSorter<>();
        //======== this ========
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));

        //======== splitPane1 ========
        {
            splitPane1.setBorder(null);
            splitPane1.setDividerSize(2);
            //splitPane1.setResizeWeight(1);
            //======== scrollPane1 ========
            {
                scrollPane1.setMinimumSize(new Dimension(100, 20));

                //---- tree1 ----
                tree1.setRootVisible(false);
                tree1.setShowsRootHandles(false);
                DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("(root)");
                keys().forEach(i -> treeNode.add(new DefaultMutableTreeNode(i)));
                tree1.setModel(new DefaultTreeModel(treeNode));
                tree1.addTreeSelectionListener(new TreeSelectionListener() {
                    @Override
                    public void valueChanged(TreeSelectionEvent e) {
                        loadGroupsInfo(e.getPath().getLastPathComponent().toString());
                    }
                });
                scrollPane1.setViewportView(tree1);
            }
            splitPane1.setLeftComponent(scrollPane1);
            //======== splitPane2 ========
            //splitPane2.setBorder(new MatteBorder(1, 1, 1, 1, scrollPane2.));
            splitPane2.setBorder(null);
            splitPane2.setDividerSize(2);
            splitPane2.setResizeWeight(0.9);
            //======== panel1 ========
            {
                //panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                //======== tabbedPane1 ========
                {

                    //======== panel2 ========
                    {
                        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

                        //======== toolBar1 ========
                        {
                            toolBar1.setEnabled(false);
                            toolBar1.setBorder(null);
                            toolBar1.setPreferredSize(new Dimension(352, 30));
                            toolBar1.setMaximumSize(new Dimension(32925, 30));
                            toolBar1.setMinimumSize(new Dimension(221, 30));
                            textField1.addDocumentListener(new DocumentAdapter() {
                                @Override
                                protected void textChanged(@NotNull DocumentEvent e) {
                                    try {
                                        rowSorter1.setRowFilter(RowFilter.regexFilter(e.getDocument().getText(0, e.getDocument().getLength())));
                                    } catch (BadLocationException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            toolBar1.add(textField1);
                        }
                        panel2.add(toolBar1);

                        //======== scrollPane3 ========
                        {

                            //---- table1 ----
                            defaultTableModel1 = new DefaultTableModel() {
                                final boolean[] columnEditable = new boolean[]{
                                        false, false
                                };

                                @Override
                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return columnEditable[columnIndex];
                                }

                                final Class<?>[] columnTypes = new Class<?>[]{
                                        String.class, String.class
                                };

                                @Override
                                public Class<?> getColumnClass(int columnIndex) {
                                    return columnTypes[columnIndex];
                                }
                            };
                            defaultTableModel1.addColumn("Key");
                            defaultTableModel1.addColumn("Value");
                            rowSorter1.setModel(defaultTableModel1);
                            table1.setModel(defaultTableModel1);
                            table1.setRowSorter(rowSorter1);
                            rowSorter1.setSortable(0, false);
                            table1.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (e.getClickCount() == 2) {
                                        int row = table1.getSelectedRow();
                                        int col = table1.getSelectedColumn();
                                        TreePath treePath = tree1.getSelectionPath();
                                        TreePath brokerPath = tree2.getSelectionPath();
                                        if (treePath != null && brokerPath != null && row != -1 && col == 1) {

                                            String key = table1.getValueAt(row, 0).toString();
                                            Object value = table1.getValueAt(row, 1);
                                            ConfigInputDialog.initValue(key, value == null ? "" : value.toString());
                                            Messages.InputDialog inputDialog = new ConfigInputDialog("pulsar-admin resourcegroups");
                                            inputDialog.show();
                                            String newValue = inputDialog.getInputString();
                                            if (newValue != null && !newValue.equals(value)) {
                                                boolean result = updateResourceGroup(treePath.getLastPathComponent().toString(), brokerPath.getLastPathComponent().toString(), key.trim(), newValue);
                                                if (result) {
                                                    table1.setValueAt(newValue, row, 1);
                                                }
                                            }
                                        }
                                    }

                                }

                            });
                            scrollPane3.setViewportView(table1);
                        }
                        panel2.add(scrollPane3);
                    }
                    tabbedPane1.setBorder(scrollPane2.getBorder());
                    tabbedPane1.addTab("ResourceGroup", panel2);
                }
                tabbedPane1.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JTabbedPane pane = (JTabbedPane) e.getSource();
                        int selectedIndex = pane.getSelectedIndex();
                        TreePath envName = tree1.getSelectionPath();
                        TreePath group = tree2.getSelectionPath();
                        if (selectedIndex == 0 && envName != null && group != null) {
                            loadResourceGroupsInfo(envName.getLastPathComponent().toString(), group.getLastPathComponent().toString());
                        }
                    }
                });
                splitPane2.setLeftComponent(tabbedPane1);
                //======== panel4 ========
                {
                    panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
                    panel4.setBorder(null);
                    //======== scrollPane2 ========
                    {
                        scrollPane2.setPreferredSize(new Dimension(150, 50));
                        scrollPane2.setMinimumSize(new Dimension(150, 50));
                        JPanel jPanelHeader = new JPanel();
                        jPanelHeader.setBorder(scrollPane2.getBorder());
                        jPanelHeader.setLayout(new BoxLayout(jPanelHeader, BoxLayout.X_AXIS));
                        SearchTextField searchTextField = new SearchTextField();
                        searchTextField.addDocumentListener(new DocumentAdapter() {
                            @Override
                            protected void textChanged(@NotNull DocumentEvent e) {
                                try {
                                    String text = e.getDocument().getText(0, e.getDocument().getLength());
                                    DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("(root)");
                                    resourceGroups.forEach(i -> {
                                        if (i.toLowerCase().contains(text.toLowerCase())) {
                                            treeNode.add(new DefaultMutableTreeNode(i));
                                        }
                                    });
                                    tree2.setModel(new DefaultTreeModel(treeNode));
                                } catch (BadLocationException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                        searchTextField.setMaximumSize(new Dimension(32925, 30));
                        jPanelHeader.add(searchTextField);
                        panel4.add(jPanelHeader, BorderLayout.PAGE_START);
                        //---- tree2 ----
                        tree2.setRootVisible(false);
                        tree2.setShowsRootHandles(false);
                        tree2.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("(root)")));
                        tree2.addTreeSelectionListener(new TreeSelectionListener() {
                            @Override
                            public void valueChanged(TreeSelectionEvent e) {
                                TreePath envName = tree1.getSelectionPath();
                                if (envName != null) {
                                    int selectedIndex = tabbedPane1.getSelectedIndex();
                                    TreePath path = e.getNewLeadSelectionPath();
                                    if (selectedIndex == 0 && path != null) {
                                        loadResourceGroupsInfo(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
                                    }


                                }
                            }
                        });
                        tree2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                if (SwingUtilities.isRightMouseButton(e)) {
                                    JBPopupMenu jPopupMenu = new JBPopupMenu();
                                    JMenuItem create = new JMenuItem("create");
                                    create.setToolTipText("Creates a new resourcegroup");
                                    create.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            TreePath envName = tree1.getSelectionPath();
                                            if (envName != null) {
                                                createResourceGroup(envName.getLastPathComponent().toString());
                                            }
                                        }
                                    });
                                    jPopupMenu.add(create);
                                    JMenuItem delete = new JMenuItem("delete");
                                    delete.setToolTipText(" Deletes an existing ResourceGroup ");
                                    delete.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree1.getSelectionPath() != null && tree2.getSelectionPath() != null) {
                                                String group = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm delete the following resource group: \n" + String.join("\n", group),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree2.getLastSelectedPathComponent();
                                                    deleteResourceGroup(tree1.getSelectionPath().getLastPathComponent().toString(), group, selectedNode);
                                                }
                                            }
                                        }
                                    });
                                    jPopupMenu.add(delete);
                                    jPopupMenu.show(tree2, e.getX(), e.getY());
                                }
                            }
                        });
                        scrollPane2.setViewportView(tree2);
                    }
                    panel4.add(scrollPane2);
                }
                splitPane2.setDividerLocation(440);
                splitPane2.setRightComponent(panel4);
            }
            splitPane1.setRightComponent(splitPane2);
        }
        jPanel.add(splitPane1);
        jPanel.setPreferredSize(new Dimension(750, 420));
        return jPanel;
    }

    private boolean updateResourceGroup(String envName, String group, String key, String newValue) {
        try {
            return ProgressManager.getInstance().run(new Task.WithResult<Boolean, Exception>(_project, envName, true) {
                @Override
                protected Boolean compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        ResourceGroups resourceGroups = pulsarAdmin.resourcegroups();
                        ResourceGroup resource = resourceGroups.getResourceGroup(group);
                        int value = Integer.parseInt(newValue);
                        switch (key) {
                            case "msg-dispatch-rate":
                                resource.setDispatchRateInMsgs(value);
                                break;
                            case "byte-dispatch-rate":
                                resource.setDispatchRateInBytes(value);
                                break;
                            case "msg-publish-rate":
                                resource.setPublishRateInMsgs(value);
                                break;
                            case "byte-publish-rate":
                                resource.setPublishRateInBytes(value);
                                break;
                        }
                        resourceGroups.updateResourceGroup(group, resource);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return true;
                    }
                }
            });
        } catch (Exception e) {
            showException(e);
            return false;
        }
    }

    private void deleteResourceGroup(String envName, String resourceGroup, DefaultMutableTreeNode selectedNode) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        ResourceGroups resourceGroups = pulsarAdmin.resourcegroups();
                        resourceGroups.deleteResourceGroup(resourceGroup);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
            resourceGroups.remove(resourceGroup);
            DefaultTreeModel model = (DefaultTreeModel) tree2.getModel();
            model.removeNodeFromParent(selectedNode);
            cleanTableAllRows(defaultTableModel1);
        } catch (Exception e) {
            showException(e);
        }
    }

    private void createResourceGroup(String envName) {
        JSpinner publishRateInBytes = new JSpinner(new SpinnerNumberModel(-1, -1, Long.MAX_VALUE, 1));
        ((JSpinner.DefaultEditor) publishRateInBytes.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        JSpinner publishRateInMsgs = new JSpinner(new SpinnerNumberModel(-1, -1, Integer.MAX_VALUE, 1));
        ((JSpinner.DefaultEditor) publishRateInMsgs.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        JSpinner dispatchRateInMsgs = new JSpinner(new SpinnerNumberModel(-1, -1, Integer.MAX_VALUE, 1));
        ((JSpinner.DefaultEditor) dispatchRateInMsgs.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        JSpinner dispatchRateInBytes = new JSpinner(new SpinnerNumberModel(-1, -1, Long.MAX_VALUE, 1));
        ((JSpinner.DefaultEditor) dispatchRateInBytes.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin resourcegroups", Messages.getInformationIcon(), "", new InputValidator() {
            @Override
            public boolean checkInput(String inputString) {
                return inputString != null && inputString.length() != 0;

            }

            @Override
            public boolean canClose(String inputString) {
                return true;
            }
        }) {

            @Override
            public JPanel createMessagePanel() {
                JPanel panel = new JPanel();
                myField = new JTextField();
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                JLabel label3 = new JLabel();
                JLabel label4 = new JLabel();
                JLabel label5 = new JLabel();
                JToolBar toolBar1 = new JToolBar();
                JToolBar toolBar2 = new JToolBar();
                JToolBar toolBar3 = new JToolBar();
                JToolBar toolBar4 = new JToolBar();
                JToolBar toolBar5 = new JToolBar();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
                toolBar1.setEnabled(false);
                toolBar1.setBorder(null);
                label1.setText("name ");
                label1.setMaximumSize(new Dimension(122, 16));
                label1.setMinimumSize(new Dimension(122, 16));
                label1.setPreferredSize(new Dimension(122, 16));
                toolBar1.add(label1);
                myField.setMaximumSize(new Dimension(2147483647, 30));
                myField.setMinimumSize(new Dimension(180, 30));
                myField.setPreferredSize(new Dimension(180, 30));
                myField.setToolTipText(" resourcegroup-name ");
                toolBar1.add(myField);
                panel.add(toolBar1);
                toolBar2.setEnabled(false);
                toolBar2.setBorder(null);
                label2.setText("msg-dispatch-rate ");
                label2.setMaximumSize(new Dimension(122, 16));
                label2.setMinimumSize(new Dimension(122, 16));
                label2.setPreferredSize(new Dimension(122, 16));
                toolBar2.add(label2);
                dispatchRateInMsgs.setMaximumSize(new Dimension(2147483647, 30));
                dispatchRateInMsgs.setMinimumSize(new Dimension(180, 30));
                dispatchRateInMsgs.setPreferredSize(new Dimension(180, 30));
                dispatchRateInMsgs.setToolTipText("message-dispatch-rate (default -1 will be overwrite if not passed)");
                toolBar2.add(dispatchRateInMsgs);
                panel.add(toolBar2);
                toolBar3.setEnabled(false);
                toolBar3.setBorder(null);
                label3.setText("byte-dispatch-rate ");
                label3.setMaximumSize(new Dimension(122, 16));
                label3.setMinimumSize(new Dimension(122, 16));
                label3.setPreferredSize(new Dimension(122, 16));
                toolBar3.add(label3);
                dispatchRateInBytes.setMaximumSize(new Dimension(2147483647, 30));
                dispatchRateInBytes.setMinimumSize(new Dimension(180, 30));
                dispatchRateInBytes.setPreferredSize(new Dimension(180, 30));
                dispatchRateInBytes.setToolTipText("byte-dispatch-rate (default -1 will be overwrite if not passed)");
                toolBar3.add(dispatchRateInBytes);
                panel.add(toolBar3);
                toolBar4.setEnabled(false);
                toolBar4.setBorder(null);
                label4.setText("msg-publish-rate ");
                label4.setMaximumSize(new Dimension(122, 16));
                label4.setMinimumSize(new Dimension(122, 16));
                label4.setPreferredSize(new Dimension(122, 16));
                toolBar4.add(label4);
                publishRateInMsgs.setMaximumSize(new Dimension(2147483647, 30));
                publishRateInMsgs.setMinimumSize(new Dimension(180, 30));
                publishRateInMsgs.setPreferredSize(new Dimension(180, 30));
                publishRateInMsgs.setToolTipText("message-publish-rate (default -1 will be overwrite if not passed)");
                toolBar4.add(publishRateInMsgs);
                panel.add(toolBar4);
                toolBar5.setEnabled(false);
                toolBar5.setBorder(null);
                label5.setText("byte-publish-rate ");
                label5.setMaximumSize(new Dimension(122, 16));
                label5.setMinimumSize(new Dimension(122, 16));
                label5.setPreferredSize(new Dimension(122, 16));
                toolBar5.add(label5);
                publishRateInBytes.setMaximumSize(new Dimension(2147483647, 30));
                publishRateInBytes.setMinimumSize(new Dimension(180, 30));
                publishRateInBytes.setPreferredSize(new Dimension(180, 30));
                publishRateInBytes.setToolTipText("byte-dispatch-rate (default -1 will be overwrite if not passed)");
                toolBar5.add(publishRateInBytes);
                panel.add(toolBar5);
                return panel;
            }
        };
        inputDialog.show();
        if (inputDialog.getInputString() != null) {
            try {
                ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                    @Override
                    protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                        try (PulsarAdmin pulsarAdmin = create(envName)) {
                            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                            ResourceGroups resourceGroups = pulsarAdmin.resourcegroups();
                            ResourceGroup group = new ResourceGroup();
                            group.setPublishRateInMsgs((Integer) publishRateInMsgs.getValue());
                            group.setPublishRateInBytes(((Double) publishRateInBytes.getValue()).longValue());
                            group.setDispatchRateInMsgs((Integer) dispatchRateInMsgs.getValue());
                            group.setDispatchRateInBytes(((Double) dispatchRateInBytes.getValue()).longValue());
                            resourceGroups.createResourceGroup(inputDialog.getInputString(), group);
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
                resourceGroups.add(inputDialog.getInputString());
                DefaultTreeModel model = (DefaultTreeModel) tree2.getModel();
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
                if (root.getChildCount() == 0) {
                    root.add(new DefaultMutableTreeNode(inputDialog.getInputString()));
                    model.reload();
                } else {
                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(inputDialog.getInputString());
                    model.insertNodeInto(newNode, root, root.getChildCount());
                }
            } catch (Exception e) {
                showException(e);
            }
        }
    }

    private List<String> resourceGroups = new ArrayList<>();

    private void loadGroupsInfo(String envName) {
        try {
            cleanTableAllRows(defaultTableModel1);
            tree2.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("(root)")));
            ResourceGroupsOverview resourceGroupsOverview = ProgressManager.getInstance().run(new Task.WithResult<ResourceGroupsOverview, Exception>(_project, envName, true) {
                @Override
                protected ResourceGroupsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ResourceGroupsOverview resourceGroupsOverview = new ResourceGroupsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        ResourceGroups resourceGroups = pulsarAdmin.resourcegroups();
                        resourceGroupsOverview.setGroups(resourceGroups.getResourceGroups());
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return resourceGroupsOverview;
                    }
                }
            });
            resourceGroups = resourceGroupsOverview.getGroups();
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("(root)");
            resourceGroupsOverview.getGroups().forEach(i -> {
                treeNode.add(new DefaultMutableTreeNode(i));
            });
            DefaultTreeSelectionModel defaultTreeSelectionModel = new DefaultTreeSelectionModel();
            defaultTreeSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
            tree2.setSelectionModel(defaultTreeSelectionModel);
            tree2.setModel(new DefaultTreeModel(treeNode));
        } catch (Exception e) {
            tree2.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("(root)")));
            showException(e);
        }
    }

    private void loadResourceGroupsInfo(String envName, String group) {
        try {
            ResourceGroupsOverview resourceGroupsOverview = ProgressManager.getInstance().run(new Task.WithResult<ResourceGroupsOverview, Exception>(_project, envName, true) {
                @Override
                protected ResourceGroupsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ResourceGroupsOverview resourceGroupsOverview = new ResourceGroupsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        ResourceGroups resourceGroups = pulsarAdmin.resourcegroups();
                        ResourceGroup resourceGroup = resourceGroups.getResourceGroup(group);
                        resourceGroupsOverview.addDataVector("msg-dispatch-rate", resourceGroup.getDispatchRateInMsgs());
                        resourceGroupsOverview.addDataVector("byte-dispatch-rate", resourceGroup.getDispatchRateInBytes());
                        resourceGroupsOverview.addDataVector("msg-publish-rate", resourceGroup.getPublishRateInMsgs());
                        resourceGroupsOverview.addDataVector("byte-publish-rate", resourceGroup.getPublishRateInBytes());
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return resourceGroupsOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel1, resourceGroupsOverview.getDataVector());
            cellIconRenderer(table1, "Value");
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel1);
            showException(e);
        }
    }

    @Override
    protected JComponent createSouthPanel() {
        return null;
    }
}
