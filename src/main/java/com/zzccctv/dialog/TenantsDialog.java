package com.zzccctv.dialog;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.ui.JBPopupMenu;
import com.intellij.openapi.ui.Messages;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.zzccctv.common.ConfigInputDialog;
import com.zzccctv.common.HintTextField;
import com.zzccctv.model.TenantsOverview;
import org.apache.pulsar.client.admin.*;
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

public class TenantsDialog extends AbstractDialog {
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

    public TenantsDialog(@Nullable Project project) {
        super(project);
        _project = project;
        setTitle("pulsar-admin tenants");
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
                        loadTenantsInfo(e.getPath().getLastPathComponent().toString());
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
                            table1.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (e.getClickCount() == 2) {
                                        int row = table1.getSelectedRow();
                                        int col = table1.getSelectedColumn();
                                        TreePath treePath = tree1.getSelectionPath();
                                        TreePath clusterPath = tree2.getSelectionPath();
                                        int type = tabbedPane1.getSelectedIndex();
                                        if (type == 0 && treePath != null && clusterPath != null && row != -1 && col == 1) {
                                            String key = table1.getValueAt(row, 0).toString();
                                            Object value = table1.getValueAt(row, 1);
                                            ConfigInputDialog.initValue(key, value == null ? "" : value.toString());
                                            Messages.InputDialog inputDialog = new ConfigInputDialog("pulsar-admin tenants");
                                            inputDialog.show();
                                            String newValue = inputDialog.getInputString();
                                            if (newValue != null && !newValue.equals(value)) {
                                                String result = updateTenant(treePath.getLastPathComponent().toString(), clusterPath.getLastPathComponent().toString(), key, newValue);
                                                if (result != null) {
                                                    table1.setValueAt(result, row, 1);
                                                }
                                            }
                                        }
                                    }
                                }

                            });
                            rowSorter1.setSortable(0, false);
                            scrollPane3.setViewportView(table1);
                        }
                        panel2.add(scrollPane3);
                    }
                    tabbedPane1.setBorder(scrollPane2.getBorder());
                    tabbedPane1.addTab("TenantInfo", panel2);
                    tabbedPane1.setToolTipTextAt(0, "Gets the configuration of a tenant");
                }
                tabbedPane1.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JTabbedPane pane = (JTabbedPane) e.getSource();
                        int selectedIndex = pane.getSelectedIndex();
                        TreePath envName = tree1.getSelectionPath();
                        TreePath topic = tree2.getSelectionPath();
                        if (selectedIndex == 0 && envName != null && topic != null) {
                            loadTenantInfo(envName.getLastPathComponent().toString(), topic.getLastPathComponent().toString());
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
                                    tenants.forEach(i -> {
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
                        tree2.setModel(new DefaultTreeModel(
                                new DefaultMutableTreeNode("(root)")));
                        tree2.addTreeSelectionListener(new TreeSelectionListener() {
                            @Override
                            public void valueChanged(TreeSelectionEvent e) {
                                TreePath envName = tree1.getSelectionPath();
                                TreePath path = e.getNewLeadSelectionPath();
                                if (envName != null && path != null) {
                                    int selectedIndex = tabbedPane1.getSelectedIndex();
                                    if (selectedIndex == 0) {
                                        loadTenantInfo(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
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
                                    create.setToolTipText("Creates a new tenant");
                                    create.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            TreePath envName = tree1.getSelectionPath();
                                            if (envName != null) {
                                                createTenant(envName.getLastPathComponent().toString());
                                            }
                                        }
                                    });
                                    jPopupMenu.add(create);
                                    JMenuItem delete = new JMenuItem("delete");
                                    delete.setToolTipText("Deletes an existing tenant");
                                    delete.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree1.getSelectionPath() != null && tree2.getSelectionPath() != null) {
                                                String tenant = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm delete the following tenants: \n" + String.join("\n", tenant),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree2.getLastSelectedPathComponent();
                                                    deleteTenant(tree1.getSelectionPath().getLastPathComponent().toString(), tenant, selectedNode);
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

    private String updateTenant(String envName, String tenant, String key, String newValue) {
        try {
            return ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Tenants tenants1 = pulsarAdmin.tenants();
                        TenantInfo info = tenants1.getTenantInfo(tenant);
                        String[] values = newValue.split(",");
                        List valueList = Collections.emptyList();
                        if (newValue.length() != 0 && values.length != 0) {
                            valueList = Arrays.asList(values);
                        }
                        switch (key) {
                            case "adminRoles":
                                tenants1.updateTenant(tenant, new TenantInfoImpl(new HashSet<>(valueList), info.getAllowedClusters()));
                                break;
                            case "allowedClusters":
                                if (valueList.size() == 0) {
                                    valueList = pulsarAdmin.clusters().getClusters();
                                }
                                tenants1.updateTenant(tenant, new TenantInfoImpl(info.getAdminRoles(), new HashSet<>(valueList)));
                                break;
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return valueList.toString().replace("[", "").replace("]", "");
                    }
                }
            });
        } catch (Exception e) {
            showException(e);
            return null;
        }
    }

    private void createTenant(String envName) {

        JTextField clusters = new HintTextField(" Comma separated allowed clusters. If empty, the tenant will have access to all clusters");
        clusters.setToolTipText("<html>Comma separated allowed clusters. If empty, the <br>tenant will have access to all clusters</html>");
        JTextField adminRoles = new HintTextField(" Comma separated list of auth principal allowed to administrate the tenant. If empty the current set of roles won't be modified");
        adminRoles.setToolTipText("<html>Comma separated list of auth principal allowed to <br>administrate the tenant. If empty the current set <br>of roles won't be modified</html>");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin tenants", Messages.getInformationIcon(), "", new InputValidator() {
            @Override
            public boolean checkInput(String inputString) {
                String[] roles = adminRoles.getText().split(",");
                return inputString != null && inputString.length() != 0 && roles != null && roles.length >= 0;
            }

            @Override
            public boolean canClose(String inputString) {
                return true;
            }
        }) {

            @Override
            public JPanel createMessagePanel() {
                JPanel panel = new JPanel();
                myField = new HintTextField(" tenant-name");
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                JLabel label3 = new JLabel();
                panel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("tenant ");
                label2.setText("admin-roles ");
                label3.setText("allowed-clusters ");
                panel.add(label1, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(myField, new GridConstraints(0, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(label2, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(adminRoles, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(label3, new GridConstraints(2, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(clusters, new GridConstraints(2, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.setMinimumSize(new Dimension(330, 100));
                panel.setPreferredSize(new Dimension(330, 100));
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
                            Tenants tenants = pulsarAdmin.tenants();
                            TenantInfoImpl tenantInfo = new TenantInfoImpl();
                            List<String> rolesList = Collections.emptyList();
                            String roles = adminRoles.getText();
                            if (roles.length() != 0) {
                                rolesList = Arrays.asList(roles.split(","));
                            }
                            tenantInfo.setAdminRoles(new HashSet<>(rolesList));
                            String value = clusters.getText();
                            if (value.length() != 0) {
                                tenantInfo.setAllowedClusters(new HashSet<>(Arrays.asList(value.split(","))));
                            } else {
                                tenantInfo.setAllowedClusters(new HashSet<>(pulsarAdmin.clusters().getClusters()));
                            }
                            tenants.createTenant(inputDialog.getInputString(), tenantInfo);
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
                this.tenants.add(inputDialog.getInputString());
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

    private void deleteTenant(String envName, String tenant, DefaultMutableTreeNode selectedNode) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Tenants tenants = pulsarAdmin.tenants();
                        tenants.deleteTenant(tenant, false);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
            this.tenants.remove(tenant);
            DefaultTreeModel model = (DefaultTreeModel) tree2.getModel();
            model.removeNodeFromParent(selectedNode);
            cleanTableAllRows(defaultTableModel1);
        } catch (Exception e) {
            showException(e);
        }
    }

    private Set<String> tenants = new HashSet<>();

    private void loadTenantsInfo(String envName) {
        try {
            cleanTableAllRows(defaultTableModel1);
            tree2.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("(root)")));
            TenantsOverview tenantsOverview = ProgressManager.getInstance().run(new Task.WithResult<TenantsOverview, Exception>(_project, envName, true) {
                @Override
                protected TenantsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        TenantsOverview tenantsOverview = new TenantsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Tenants tenants = pulsarAdmin.tenants();
                        tenantsOverview.setTenants(new HashSet<>(tenants.getTenants()));
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return tenantsOverview;
                    }
                }
            });
            tenants = tenantsOverview.getTenants();
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("(root)");
            tenantsOverview.getTenants().forEach(i -> {
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

    private void loadTenantInfo(String envName, String tenant) {
        try {
            TenantsOverview tenantsOverview = ProgressManager.getInstance().run(new Task.WithResult<TenantsOverview, Exception>(_project, envName, true) {
                @Override
                protected TenantsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        TenantsOverview tenantsOverview = new TenantsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Tenants tenants = pulsarAdmin.tenants();
                        TenantInfo tenantInfo = tenants.getTenantInfo(tenant);
                        Set<String> roles = tenantInfo.getAdminRoles();
                        Set<String> clusters = tenantInfo.getAllowedClusters();
                        tenantsOverview.addDataVector("adminRoles", roles.toString().replace("[", "").replace("]", ""));
                        tenantsOverview.addDataVector("allowedClusters", clusters.toString().replace("[", "").replace("]", ""));
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return tenantsOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel1, tenantsOverview.getDataVector());
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
