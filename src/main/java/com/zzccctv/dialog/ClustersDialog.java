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
import com.zzccctv.model.ClustersOverview;
import org.apache.pulsar.client.admin.*;
import org.apache.pulsar.common.policies.data.ClusterData;
import org.apache.pulsar.common.policies.data.ClusterDataImpl;
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

public class ClustersDialog extends AbstractDialog {
    private JSplitPane splitPane1;
    private JSplitPane splitPane2;
    private JBScrollPane scrollPane1;
    private Tree tree1;
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JToolBar toolBar1;
    private JToolBar toolBar2;
    private SearchTextField textField1;
    private SearchTextField textField2;
    private JBScrollPane scrollPane3;
    private JBScrollPane scrollPane4;
    private JBTable table1;
    private JBTable table2;
    private JPanel panel4;
    private JPanel panel3;
    private JBScrollPane scrollPane2;
    private Tree tree2;
    private Project _project;
    private DefaultTableModel defaultTableModel1;
    private DefaultTableModel defaultTableModel2;

    public ClustersDialog(@Nullable Project project) {
        super(project);
        _project = project;
        setTitle("pulsar-admin clusters");
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
        toolBar2 = new JToolBar();
        textField1 = new SearchTextField();
        textField2 = new SearchTextField();
        scrollPane3 = new JBScrollPane();
        table1 = new JBTable();
        table2 = new JBTable();
        panel4 = new JPanel();
        panel3 = new JPanel();
        scrollPane2 = new JBScrollPane();
        scrollPane4 = new JBScrollPane();
        tree2 = new Tree();
        TableRowSorter<DefaultTableModel> rowSorter1 = new TableRowSorter<>();
        TableRowSorter<DefaultTableModel> rowSorter2 = new TableRowSorter<>();
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
                        loadClustersInfo(e.getPath().getLastPathComponent().toString());
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
                        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                        //======== toolBar1 ========
                        {
                            toolBar2.setEnabled(false);
                            toolBar2.setBorder(null);
                            toolBar2.setPreferredSize(new Dimension(352, 30));
                            toolBar2.setMaximumSize(new Dimension(32925, 30));
                            toolBar2.setMinimumSize(new Dimension(221, 30));
                            textField2.addDocumentListener(new DocumentAdapter() {
                                @Override
                                protected void textChanged(@NotNull DocumentEvent e) {
                                    try {
                                        rowSorter2.setRowFilter(RowFilter.regexFilter(e.getDocument().getText(0, e.getDocument().getLength())));
                                    } catch (BadLocationException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            toolBar2.add(textField2);
                        }
                        panel3.add(toolBar2);

                        //======== scrollPane3 ========
                        {

                            //---- table1 ----
                            defaultTableModel2 = new DefaultTableModel() {
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
                            defaultTableModel2.addColumn("Key");
                            defaultTableModel2.addColumn("Value");
                            rowSorter2.setModel(defaultTableModel2);
                            table2.setModel(defaultTableModel2);
                            table2.setRowSorter(rowSorter2);
                            rowSorter2.setSortable(0, false);
                            table2.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (e.getClickCount() == 2) {
                                        int row = table2.getSelectedRow();
                                        int col = table2.getSelectedColumn();
                                        TreePath treePath = tree1.getSelectionPath();
                                        TreePath clusterPath = tree2.getSelectionPath();
                                        int type = tabbedPane1.getSelectedIndex();
                                        if (type == 0 && treePath != null && clusterPath != null && row != -1 && col == 1) {
                                            String key = table2.getValueAt(row, 0).toString();
                                            Object value = table2.getValueAt(row, 1);
                                            ConfigInputDialog.initValue(key, value == null ? "" : value.toString());
                                            Messages.InputDialog inputDialog = new ConfigInputDialog("pulsar-admin clusters");
                                            inputDialog.show();
                                            String newValue = inputDialog.getInputString();
                                            if (newValue != null && !newValue.equals(value)) {
                                                boolean result = updateCluster(treePath.getLastPathComponent().toString(), clusterPath.getLastPathComponent().toString(), key, newValue);
                                                if (result) {
                                                    table2.setValueAt(newValue, row, 1);
                                                }
                                            }
                                        }
                                    }
                                }

                            });
                            scrollPane4.setViewportView(table2);
                        }
                        panel3.add(scrollPane4);
                    }
                    tabbedPane1.setBorder(scrollPane2.getBorder());

                    tabbedPane1.addTab("Config", panel3);
                    tabbedPane1.setToolTipTextAt(0, "Get the configuration data for the specified cluster");
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
                                        false,
                                };

                                @Override
                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return columnEditable[columnIndex];
                                }

                                final Class<?>[] columnTypes = new Class<?>[]{
                                        String.class
                                };

                                @Override
                                public Class<?> getColumnClass(int columnIndex) {
                                    return columnTypes[columnIndex];
                                }
                            };
                            defaultTableModel1.addColumn("Value");
                            rowSorter1.setModel(defaultTableModel1);
                            table1.setModel(defaultTableModel1);
                            table1.setRowSorter(rowSorter1);
                            rowSorter1.setSortable(0, false);
                            scrollPane3.setViewportView(table1);
                        }
                        panel2.add(scrollPane3);
                    }
                    tabbedPane1.addTab("Brokers", panel2);
                    tabbedPane1.setToolTipTextAt(1, "List active brokers of the cluster");
                }
                tabbedPane1.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JTabbedPane pane = (JTabbedPane) e.getSource();
                        int selectedIndex = pane.getSelectedIndex();
                        TreePath envName = tree1.getSelectionPath();
                        TreePath topic = tree2.getSelectionPath();
                        if (envName != null && topic != null) {
                            if (selectedIndex == 0) {
                                loadClusterData(envName.getLastPathComponent().toString(), topic.getLastPathComponent().toString());
                            } else if (selectedIndex == 1) {
                                loadBrokersInfo(envName.getLastPathComponent().toString(), topic.getLastPathComponent().toString());
                            }
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
                                    clusters.forEach(i -> {
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
                                        loadClusterData(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
                                    } else if (selectedIndex == 1) {
                                        loadBrokersInfo(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
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
                                    create.setToolTipText("<html>Provisions a new cluster. This operation <br>requires Pulsar super-user privileges</html>");
                                    create.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            TreePath envName = tree1.getSelectionPath();
                                            if (envName != null) {
                                                createCluster(envName.getLastPathComponent().toString());
                                            }
                                        }
                                    });
                                    jPopupMenu.add(create);
                                    JMenuItem delete = new JMenuItem("delete");
                                    delete.setToolTipText("Deletes an existing cluster");
                                    delete.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree1.getSelectionPath() != null && tree2.getSelectionPath() != null) {
                                                String cluster = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm delete the following clusters: \n" + String.join("\n", cluster),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree2.getLastSelectedPathComponent();
                                                    deleteCluster(tree1.getSelectionPath().getLastPathComponent().toString(), cluster, selectedNode);
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

    private void createCluster(String envName) {
        JTextField url = new HintTextField(" service-url");
        JTextField brokerUrl = new HintTextField(" broker-service-url");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin clusters", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" cluster-name");
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                JLabel label3 = new JLabel();
                panel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("cluster ");
                label2.setText("url ");
                label3.setText("broker-url ");
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
                panel.add(url, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(label3, new GridConstraints(2, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(brokerUrl, new GridConstraints(2, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.setMinimumSize(new Dimension(300, 100));
                panel.setPreferredSize(new Dimension(300, 100));
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
                            Clusters clusters = pulsarAdmin.clusters();
                            ClusterDataImpl data = new ClusterDataImpl();
                            data.setServiceUrl(url.getText());
                            data.setBrokerServiceUrl(brokerUrl.getText());
                            clusters.createCluster(inputDialog.getInputString(), data);
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
                this.clusters.add(inputDialog.getInputString());
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

    private void deleteCluster(String envName, String cluster, DefaultMutableTreeNode selectedNode) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Clusters clusters = pulsarAdmin.clusters();
                        clusters.deleteCluster(cluster);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
            this.clusters.remove(cluster);
            DefaultTreeModel model = (DefaultTreeModel) tree2.getModel();
            model.removeNodeFromParent(selectedNode);
            cleanTableAllRows(defaultTableModel1);
            cleanTableAllRows(defaultTableModel2);
        } catch (Exception e) {
            showException(e);
        }
    }

    private boolean updateCluster(String envName, String cluster, String key, String value) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Clusters clusters = pulsarAdmin.clusters();
                        ClusterDataImpl clusterData = (ClusterDataImpl) clusters.getCluster(cluster);
                        switch (key) {
                            case "authenticationParameters":
                                clusterData.setAuthenticationParameters(value);
                                break;
                            case "authenticationPlugin":
                                clusterData.setAuthenticationPlugin(value);
                                break;
                            case "isBrokerClientTlsEnabled":
                                clusterData.setBrokerClientTlsEnabled(Boolean.parseBoolean(value));
                                break;
                            case "isBrokerClientTlsEnabledWithKeyStore":
                                clusterData.setBrokerClientTlsEnabledWithKeyStore(Boolean.parseBoolean(value));
                                break;
                            case "brokerClientTlsTrustStore":
                                clusterData.setBrokerClientTlsTrustStore(value);
                                break;
                            case "brokerClientTlsTrustStorePassword":
                                clusterData.setBrokerClientTlsTrustStorePassword(value);
                                break;
                            case "brokerClientTlsTrustStoreType":
                                clusterData.setBrokerClientTlsTrustStoreType(value);
                                break;
                            case "brokerClientTrustCertsFilePath":
                                clusterData.setBrokerClientTrustCertsFilePath(value);
                                break;
                            case "brokerServiceUrl":
                                clusterData.setBrokerServiceUrl(value);
                                break;
                            case "brokerServiceUrlTls":
                                clusterData.setBrokerServiceUrlTls(value);
                                break;
                            case "listenerName":
                                clusterData.setListenerName(value);
                                break;
                            case "proxyServiceUrl":
                                clusterData.setProxyServiceUrl(value);
                                break;
                            case "isTlsAllowInsecureConnection":
                                clusterData.setTlsAllowInsecureConnection(Boolean.parseBoolean(value));
                                break;
                            case "serviceUrl":
                                clusterData.setServiceUrl(value);
                                break;
                            case "serviceUrlTls":
                                clusterData.setServiceUrlTls(value);
                                break;


                        }
                        clusters.updateCluster(cluster, clusterData);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
            return true;
        } catch (Exception e) {
            showException(e);
            return false;
        }
    }

    private Set<String> clusters = new HashSet<>();

    private void loadClustersInfo(String envName) {
        try {
            cleanTableAllRows(defaultTableModel1);
            cleanTableAllRows(defaultTableModel2);
            tree2.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("(root)")));
            ClustersOverview clustersOverview = ProgressManager.getInstance().run(new Task.WithResult<ClustersOverview, Exception>(_project, envName, true) {
                @Override
                protected ClustersOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClustersOverview clustersOverview = new ClustersOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Clusters clusters = pulsarAdmin.clusters();
                        clustersOverview.setClusters(new HashSet<>(clusters.getClusters()));
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return clustersOverview;
                    }
                }
            });
            clusters = clustersOverview.getClusters();
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("(root)");
            clustersOverview.getClusters().forEach(i -> {
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

    private void loadClusterData(String envName, String cluster) {
        try {
            ClustersOverview clustersOverview = ProgressManager.getInstance().run(new Task.WithResult<ClustersOverview, Exception>(_project, envName, true) {
                @Override
                protected ClustersOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClustersOverview clustersOverview = new ClustersOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Clusters clusters = pulsarAdmin.clusters();
                        ClusterData data = clusters.getCluster(cluster);
                        clustersOverview.addDataVector("authenticationParameters", data.getAuthenticationParameters());
                        clustersOverview.addDataVector("authenticationPlugin", data.getAuthenticationPlugin());
                        clustersOverview.addDataVector("brokerClientTlsTrustStore", data.getBrokerClientTlsTrustStore());
                        clustersOverview.addDataVector("brokerClientTlsTrustStoreType", data.getBrokerClientTlsTrustStoreType());
                        clustersOverview.addDataVector("brokerClientTlsTrustStorePassword", data.getBrokerClientTlsTrustStorePassword());
                        clustersOverview.addDataVector("brokerClientTrustCertsFilePath", data.getBrokerClientTrustCertsFilePath());
                        clustersOverview.addDataVector("brokerServiceUrl", data.getBrokerServiceUrl());
                        clustersOverview.addDataVector("brokerServiceUrlTls", data.getBrokerServiceUrlTls());
                        clustersOverview.addDataVector("listenerName", data.getListenerName());
                        clustersOverview.addDataVector("proxyServiceUrl", data.getProxyServiceUrl());
                        clustersOverview.addDataVector("serviceUrl", data.getServiceUrl());
                        clustersOverview.addDataVector("serviceUrlTls", data.getServiceUrlTls());
                        clustersOverview.addDataVector("isBrokerClientTlsEnabled", data.isBrokerClientTlsEnabled());
                        clustersOverview.addDataVector("isBrokerClientTlsEnabledWithKeyStore", data.isBrokerClientTlsEnabledWithKeyStore());
                        clustersOverview.addDataVector("isTlsAllowInsecureConnection", data.isTlsAllowInsecureConnection());
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return clustersOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel2, clustersOverview.getDataVector());
            cellIconRenderer(table2, "Value");
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel2);
            showException(e);
        }

    }

    private void loadBrokersInfo(String envName, String cluster) {
        try {
            ClustersOverview clustersOverview = ProgressManager.getInstance().run(new Task.WithResult<ClustersOverview, Exception>(_project, envName, true) {
                @Override
                protected ClustersOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClustersOverview clustersOverview = new ClustersOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Brokers brokers = pulsarAdmin.brokers();
                        List<String> active = brokers.getActiveBrokers(cluster);
                        active.forEach(i -> {
                            clustersOverview.addDataVector(i);
                        });
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return clustersOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel1, clustersOverview.getDataVector());
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
