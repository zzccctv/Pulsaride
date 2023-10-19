package com.zzccctv.dialog;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.Tree;
import com.zzccctv.common.PulsarManagerTopicStats;
import com.zzccctv.model.BrokerStatsOverview;
import com.zzccctv.utils.FileUtils;
import org.apache.pulsar.client.admin.*;
import org.apache.pulsar.policies.data.loadbalancer.LoadManagerReport;
import org.apache.pulsar.policies.data.loadbalancer.ResourceUsage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.tree.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class BrokerStatsDialog extends AbstractDialog {
    private JSplitPane splitPane1;
    private JSplitPane splitPane2;
    private JBScrollPane scrollPane1;
    private Tree tree1;
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JPanel panel3;
    private JToolBar toolBar1;
    private JToolBar toolBar2;
    private SearchTextField textField1;
    private SearchTextField textField2;
    private JBScrollPane scrollPane3;
    private JBScrollPane scrollPane4;
    private JBTable table1;
    private JBTable table2;
    private JPanel panel4;
    private JBScrollPane scrollPane2;
    private Tree tree2;
    private Project _project;
    private DefaultTableModel defaultTableModel1;
    private DefaultTableModel defaultTableModel2;

    public BrokerStatsDialog(@Nullable Project project) {
        super(project);
        _project = project;
        setTitle("pulsar-admin broker-stats");
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
        panel3 = new JPanel();
        toolBar1 = new JToolBar();
        toolBar2 = new JToolBar();
        textField1 = new SearchTextField();
        textField2 = new SearchTextField();
        scrollPane3 = new JBScrollPane();
        scrollPane4 = new JBScrollPane();
        table1 = new JBTable();
        table2 = new JBTable();
        panel4 = new JPanel();
        scrollPane2 = new JBScrollPane();
        tree2 = new Tree();
        TableRowSorter<DefaultTableModel> rowSorter1 = new TableRowSorter<>();
        TableRowSorter<DefaultTableModel> rowSorter2 = new TableRowSorter<>();
        //======== this ========
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));

        //======== splitPane1 ========
        {
            splitPane1.setBorder(null);
            splitPane1.setDividerSize(2);
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
                        loadBrokerInfo(e.getPath().getLastPathComponent().toString());
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
                            scrollPane3.setViewportView(table1);
                        }
                        panel2.add(scrollPane3);
                    }
                    tabbedPane1.setBorder(scrollPane2.getBorder());
                    tabbedPane1.addTab("LoadReport", panel2);
                    tabbedPane1.setToolTipTextAt(0, "dump broker load-report");
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
                                        false, false, false, false, false, false, false, false, false, false
                                };

                                @Override
                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return columnEditable[columnIndex];
                                }

                                final Class<?>[] columnTypes = new Class<?>[]{
                                        String.class, String.class, String.class, String.class, String.class, Double.class, Double.class, Double.class, Double.class, String.class
                                };

                                @Override
                                public Class<?> getColumnClass(int columnIndex) {
                                    return columnTypes[columnIndex];
                                }
                            };
                            defaultTableModel2.addColumn("Tenant");
                            defaultTableModel2.addColumn("Namespace");
                            defaultTableModel2.addColumn("Topic");
                            defaultTableModel2.addColumn("Domain");
                            defaultTableModel2.addColumn("Bundle");
                            defaultTableModel2.addColumn("InRate");
                            defaultTableModel2.addColumn("OutRate");
                            defaultTableModel2.addColumn("InThroughput");
                            defaultTableModel2.addColumn("OutThroughput");
                            defaultTableModel2.addColumn("StorageSize");
                            rowSorter2.setModel(defaultTableModel2);
                            table2.setModel(defaultTableModel2);
                            table2.setRowSorter(rowSorter2);
                            rowSorter2.setSortable(0, false);
                            scrollPane4.setViewportView(table2);
                        }
                        panel3.add(scrollPane4);
                    }
                    tabbedPane1.addTab("Topics", panel3);
                    tabbedPane1.setToolTipTextAt(1, "dump topics stats");
                }
                tabbedPane1.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JTabbedPane pane = (JTabbedPane) e.getSource();
                        int selectedIndex = pane.getSelectedIndex();
                        TreePath envName = tree1.getSelectionPath();
                        TreePath broker = tree2.getSelectionPath();
                        if (envName != null && broker != null) {
                            if (selectedIndex == 0) {
                                loadBrokerStatusInfo(envName.getLastPathComponent().toString(), "http://" + broker.getLastPathComponent().toString());
                            } else if (selectedIndex == 1) {
                                loadTopicsInfo(envName.getLastPathComponent().toString(), "http://" + broker.getLastPathComponent().toString());
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
                                    brokerList.forEach(i -> {
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
                                        loadBrokerStatusInfo(envName.getLastPathComponent().toString(), "http://" + path.getLastPathComponent().toString());
                                    } else if (selectedIndex == 1) {
                                        loadTopicsInfo(envName.getLastPathComponent().toString(), "http://" + path.getLastPathComponent().toString());
                                    }


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

    private void loadTopicsInfo(String envName, String broker) {
        try {
            BrokerStatsOverview brokerStatsOverview = ProgressManager.getInstance().run(new Task.WithResult<BrokerStatsOverview, Exception>(_project, broker, true) {
                @Override
                protected BrokerStatsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName, broker)) {
                        BrokerStatsOverview brokerStatsOverview = new BrokerStatsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        BrokerStats brokerStats = pulsarAdmin.brokerStats();
                        String topicStatus = brokerStats.getTopics();
                        ObjectMapper objectMapper = new ObjectMapper();
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        HashMap<String, HashMap<String, HashMap<String, HashMap<String, PulsarManagerTopicStats>>>> brokerStatsTopicEntity = objectMapper.readValue(topicStatus, new TypeReference<HashMap<String, HashMap<String, HashMap<String, HashMap<String, PulsarManagerTopicStats>>>>>() {
                        });
                        brokerStatsTopicEntity.forEach((namespace, namespaceStats) -> {
                            namespaceStats.forEach((bundle, bundleStats) -> {
                                bundleStats.forEach((persistent, persistentStats) -> {
                                    persistentStats.forEach((topic, topicStats) -> {
                                        String[] topicPath = parseTopic(topic);
                                        brokerStatsOverview.addDataVector(topicPath[0], topicPath[1], topicPath[2], persistent, bundle, topicStats.getMsgRateIn(), topicStats.getMsgRateOut(), topicStats.getMsgThroughputIn(), topicStats.getMsgThroughputOut(), FileUtils.byteCountToDisplaySize(topicStats.getStorageSize()));
                                    });
                                });

                            });

                        });
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return brokerStatsOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel2, brokerStatsOverview.getDataVector());
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel2);
            showException(e);
        }
    }

    private void loadBrokerStatusInfo(String envName, String broker) {
        try {
            BrokerStatsOverview brokerStatsOverview = ProgressManager.getInstance().run(new Task.WithResult<BrokerStatsOverview, Exception>(_project, broker, true) {
                @Override
                protected BrokerStatsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName, broker)) {
                        BrokerStatsOverview brokerStatsOverview = new BrokerStatsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        BrokerStats brokerStats = pulsarAdmin.brokerStats();
                        LoadManagerReport loadManagerReport = brokerStats.getLoadReport();
                        brokerStatsOverview.addDataVector("BandwidthIn", resourceUsage(loadManagerReport.getBandwidthIn()));
                        brokerStatsOverview.addDataVector("BandwidthOut", resourceUsage(loadManagerReport.getBandwidthOut()));
                        brokerStatsOverview.addDataVector("Cpu", resourceUsage(loadManagerReport.getCpu()));
                        brokerStatsOverview.addDataVector("DirectMemory", resourceUsage(loadManagerReport.getDirectMemory()));
                        brokerStatsOverview.addDataVector("LastUpdate", loadManagerReport.getLastUpdate());
                        brokerStatsOverview.addDataVector("Memory", resourceUsage(loadManagerReport.getMemory()));
                        brokerStatsOverview.addDataVector("MsgRateIn", loadManagerReport.getMsgRateIn());
                        brokerStatsOverview.addDataVector("MsgRateOut", loadManagerReport.getMsgRateOut());
                        brokerStatsOverview.addDataVector("MsgThroughputIn", loadManagerReport.getMsgThroughputIn());
                        brokerStatsOverview.addDataVector("MsgThroughputOut", loadManagerReport.getMsgThroughputOut());
                        brokerStatsOverview.addDataVector("NumBundles", loadManagerReport.getNumBundles());
                        brokerStatsOverview.addDataVector("NumConsumers", loadManagerReport.getNumConsumers());
                        brokerStatsOverview.addDataVector("NumTopics", loadManagerReport.getNumTopics());
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return brokerStatsOverview;
                    }
                }
            });

            tableDataVector(defaultTableModel1, brokerStatsOverview.getDataVector());
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel1);
            showException(e);
        }
    }

    private Set<String> brokerList = new HashSet<>();

    private void loadBrokerInfo(String envName) {
        try {
            cleanTableAllRows(defaultTableModel1);
            cleanTableAllRows(defaultTableModel2);
            tree2.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("(root)")));
            BrokerStatsOverview brokerStatsOverview = ProgressManager.getInstance().run(new Task.WithResult<BrokerStatsOverview, Exception>(_project, envName, true) {
                @Override
                protected BrokerStatsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        BrokerStatsOverview brokerStatsOverview = new BrokerStatsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Clusters clusters = pulsarAdmin.clusters();
                        List<String> clusterList = clusters.getClusters();
                        Brokers brokers = pulsarAdmin.brokers();
                        for (String c : clusterList) {
                            brokerStatsOverview.addBrokers(brokers.getActiveBrokers(c));
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return brokerStatsOverview;
                    }
                }
            });
            brokerList = brokerStatsOverview.getBrokers();
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("(root)");
            brokerStatsOverview.getBrokers().forEach(i -> {
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

    @Override
    protected JComponent createSouthPanel() {
        return null;
    }

    private String resourceUsage(ResourceUsage usage) {
        DecimalFormat df = new DecimalFormat("#.00");
        return String.format("{\"usage\":%s,\"limit\":%s,\"percentUsage\":%s}", df.format(usage.usage), df.format(usage.limit), df.format(usage.percentUsage()));
    }
}
