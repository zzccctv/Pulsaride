package com.zzccctv.dialog;

import com.beust.jcommander.ParameterException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
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
import com.zzccctv.common.BooleanHeader;
import com.zzccctv.common.HintTextField;
import com.zzccctv.model.*;
import com.zzccctv.utils.FileUtils;
import com.zzccctv.utils.StringUtils;
import org.apache.pulsar.client.admin.*;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.impl.MessageIdImpl;
import org.apache.pulsar.client.impl.MessageImpl;
import org.apache.pulsar.common.naming.TopicName;
import org.apache.pulsar.common.policies.data.AuthAction;
import org.apache.pulsar.common.policies.data.PartitionedTopicStats;
import org.apache.pulsar.common.policies.data.SubscriptionStats;
import org.apache.pulsar.common.policies.data.TopicStats;
import org.apache.pulsar.common.util.RelativeTimeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class TopicsDialog extends AbstractDialog {
    private JSplitPane splitPane1;
    private JSplitPane splitPane2;
    private JBScrollPane scrollPane1;
    private Tree tree1;
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JPanel panel3;
    private JToolBar toolBar1;
    private JToolBar toolBar2;
    private JToolBar toolBar3;
    private SearchTextField textField1;
    private SearchTextField textField2;
    private SearchTextField textField3;
    private JBScrollPane scrollPane3;
    private JBScrollPane scrollPane4;
    private JBScrollPane scrollPane5;
    private JBTable table1;
    private JBTable table2;
    private JBTable table3;
    private JPanel panel4;
    private JPanel panel5;
    private JBScrollPane scrollPane2;
    private Tree tree2;
    private Project _project;
    private DefaultTableModel defaultTableModel1;
    private DefaultTableModel defaultTableModel2;
    private DefaultTableModel defaultTableModel3;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    public TopicsDialog(@Nullable Project project) {
        super(project);
        _project = project;
        setTitle("pulsar-admin topics");
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
        toolBar3 = new JToolBar();
        textField1 = new SearchTextField();
        textField2 = new SearchTextField();
        textField3 = new SearchTextField();
        scrollPane3 = new JBScrollPane();
        scrollPane4 = new JBScrollPane();
        scrollPane5 = new JBScrollPane();
        table1 = new JBTable();
        table2 = new JBTable();
        table3 = new JBTable();
        panel4 = new JPanel();
        panel5 = new JPanel();
        scrollPane2 = new JBScrollPane();
        tree2 = new Tree();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        TableRowSorter<DefaultTableModel> rowSorter1 = new TableRowSorter<>();
        TableRowSorter<DefaultTableModel> rowSorter2 = new TableRowSorter<>();
        TableRowSorter<DefaultTableModel> rowSorter3 = new TableRowSorter<>();
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
                        loadTopicsInfo(e.getPath().getLastPathComponent().toString());
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
                            button3.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
                            button3.setPreferredSize(new Dimension(30, 30));
                            button3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    TreePath envName = tree1.getSelectionPath();
                                    TreePath topic = tree2.getSelectionPath();
                                    if (envName != null && topic != null) {
                                        loadPartitionedStats(envName.getLastPathComponent().toString(), topic.getLastPathComponent().toString());
                                    }
                                }
                            });
                            toolBar1.add(button3);
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
                                        false, false, false, false, false, false, false
                                };

                                @Override
                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return columnEditable[columnIndex];
                                }

                                final Class<?>[] columnTypes = new Class<?>[]{
                                        Integer.class, Double.class, Double.class, Double.class, Double.class, String.class, String.class
                                };

                                @Override
                                public Class<?> getColumnClass(int columnIndex) {
                                    return columnTypes[columnIndex];
                                }
                            };
                            defaultTableModel1.addColumn("Partitions");
                            defaultTableModel1.addColumn("InRate");
                            defaultTableModel1.addColumn("OutRate");
                            defaultTableModel1.addColumn("InThroughput");
                            defaultTableModel1.addColumn("OutThroughput");
                            defaultTableModel1.addColumn("StorageSize");
                            defaultTableModel1.addColumn("ServingBroker");
                            rowSorter1.setModel(defaultTableModel1);
                            table1.setModel(defaultTableModel1);
                            table1.setRowSorter(rowSorter1);
                            rowSorter1.setSortable(0, false);
                            scrollPane3.setViewportView(table1);
                        }
                        panel2.add(scrollPane3);
                    }
                    tabbedPane1.setBorder(scrollPane2.getBorder());
                    tabbedPane1.addTab("PartitionedStats", panel2);
                    tabbedPane1.setToolTipTextAt(0, "Get the stats for the partitioned topic and its connected producers and " +
                            "<br>consumers. All the rates are computed over a 1 minute window and are " +
                            "<br>relative the last completed 1 minute period.");
                    {
                        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                        //======== toolBar1 ========
                        {
                            toolBar2.setEnabled(false);
                            toolBar2.setBorder(null);
                            toolBar2.setPreferredSize(new Dimension(352, 30));
                            toolBar2.setMaximumSize(new Dimension(32925, 30));
                            toolBar2.setMinimumSize(new Dimension(221, 30));
                            button4.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
                            button4.setPreferredSize(new Dimension(30, 30));
                            button4.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    TreePath envName = tree1.getSelectionPath();
                                    TreePath topic = tree2.getSelectionPath();
                                    if (envName != null && topic != null) {
                                        loadSubscriptions(envName.getLastPathComponent().toString(), topic.getLastPathComponent().toString());
                                    }
                                }
                            });
                            toolBar2.add(button4);
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
                                        false, false, false, false, false, false, false
                                };

                                @Override
                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return columnEditable[columnIndex];
                                }

                                final Class<?>[] columnTypes = new Class<?>[]{
                                        String.class, Long.class, Double.class, Double.class, Double.class, Long.class, Long.class
                                };

                                @Override
                                public Class<?> getColumnClass(int columnIndex) {
                                    return columnTypes[columnIndex];
                                }
                            };
                            defaultTableModel2.addColumn("SubscriptionName");
                            defaultTableModel2.addColumn("OutCounter");
                            defaultTableModel2.addColumn("OutRate");
                            defaultTableModel2.addColumn("OutThroughput");
                            defaultTableModel2.addColumn("ExpiredRate");
                            defaultTableModel2.addColumn("Backlog");
                            defaultTableModel2.addColumn("Delayed");
                            rowSorter2.setModel(defaultTableModel2);
                            table2.setModel(defaultTableModel2);
                            table2.setRowSorter(rowSorter2);
                            rowSorter2.setSortable(0, false);
                            scrollPane4.setViewportView(table2);
                        }
                        panel3.add(scrollPane4);
                    }
                    tabbedPane1.addTab("Subscriptions", panel3);
                    tabbedPane1.setToolTipTextAt(1, "");
                    //======== panel5 ========
                    {
                        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

                        //======== toolBar1 ========
                        {
                            toolBar3.setEnabled(false);
                            toolBar3.setBorder(null);
                            toolBar3.setPreferredSize(new Dimension(352, 30));
                            toolBar3.setMaximumSize(new Dimension(32925, 30));
                            toolBar3.setMinimumSize(new Dimension(221, 30));
                            button1.setIcon(new ImageIcon(getClass().getResource("/add.png")));
                            button1.setToolTipText("Grant a new permission to a client role on a single topic.");
                            button1.setPreferredSize(new Dimension(30, 30));
                            button1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (tree1.getSelectionPath() != null && tree2.getSelectionPath() != null) {
                                        String envName = tree1.getSelectionPath().getLastPathComponent().toString();
                                        String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                        grantPermission(envName, topic);
                                    }
                                }
                            });
                            toolBar3.add(button1);
                            button2.setIcon(new ImageIcon(getClass().getResource("/remove.png")));
                            button2.setToolTipText("<html>Revoke permissions on a topic. Revoke permissions to " +
                                    "a <br>client role on a single topic. If the permission " +
                                    "was not <br>set at the topic level, but rather at the " +
                                    "namespace level, <br>this operation will return an " +
                                    "error (HTTP status code 412).</html>");
                            button2.setPreferredSize(new Dimension(30, 30));
                            button2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (tree1.getSelectionPath() != null && tree2.getSelectionPath() != null) {
                                        List<String> roles = new ArrayList<>();
                                        String envName = tree1.getSelectionPath().getLastPathComponent().toString();
                                        String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                        for (int i = 0; i < defaultTableModel3.getRowCount(); i++) {
                                            if ((Boolean) defaultTableModel3.getValueAt(i, 0)) {
                                                String role = (String) defaultTableModel3.getValueAt(i, 1);
                                                roles.add(role);
                                            }
                                        }
                                        if (roles.size() > 0 && Messages.OK == Messages.showOkCancelDialog(
                                                "Confirm delete the following roles: \n" + String.join("\n", roles),
                                                "", Messages.OK_BUTTON, Messages.CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                            deleteTopicRole(envName, topic, roles);
                                        }
                                    }
                                }
                            });
                            toolBar3.add(button2);
                            textField3.addDocumentListener(new DocumentAdapter() {
                                @Override
                                protected void textChanged(@NotNull DocumentEvent e) {
                                    try {
                                        rowSorter3.setRowFilter(RowFilter.regexFilter(e.getDocument().getText(0, e.getDocument().getLength())));
                                    } catch (BadLocationException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            toolBar3.add(textField3);
                        }
                        panel5.add(toolBar3);

                        //======== scrollPane3 ========
                        {

                            //---- table1 ----
                            defaultTableModel3 = new DefaultTableModel() {
                                final boolean[] columnEditable = new boolean[]{
                                        true, false, false
                                };

                                @Override
                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return columnEditable[columnIndex];
                                }

                                final Class<?>[] columnTypes = new Class<?>[]{
                                        Boolean.class, String.class, String.class
                                };

                                @Override
                                public Class<?> getColumnClass(int columnIndex) {
                                    return columnTypes[columnIndex];
                                }
                            };
                            defaultTableModel3.addColumn("false");
                            defaultTableModel3.addColumn("Role");
                            defaultTableModel3.addColumn("Auth");
                            rowSorter3.setModel(defaultTableModel3);
                            table3.setModel(defaultTableModel3);
                            table3.setRowSorter(rowSorter3);
                            rowSorter3.setSortable(0, false);
                            TableColumnModel cm = table3.getColumnModel();
                            cm.getColumn(0).setMinWidth(50);
                            cm.getColumn(0).setMaxWidth(50);
                            table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                            table3.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(new BooleanHeader());
                            table3.getTableHeader().addMouseListener(new MouseListener() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (table3.getColumnModel().getColumnIndexAtX(e.getX()) == 0) {
                                        boolean select = !Boolean.parseBoolean(cm.getColumn(0).getHeaderValue().toString());
                                        cm.getColumn(0).setHeaderValue(select);
                                        for (int i = 0; i < table3.getRowCount(); i++) {
                                            table3.setValueAt(!(Boolean) table3.getValueAt(i, 0), i, 0);
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
                            scrollPane5.setViewportView(table3);
                        }
                        panel5.add(scrollPane5);
                    }
                    tabbedPane1.setBorder(scrollPane2.getBorder());
                    tabbedPane1.addTab("Permissions", panel5);
                    tabbedPane1.setToolTipTextAt(2, "Get the permissions on a topic. Retrieve the effective permissions " +
                            "<br>for a topic. These permissions are defined by the permissions set " +
                            "<br>at the namespace level combined (union) with any eventual specific " +
                            "<br>permission set on the topic.");
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
                                loadPartitionedStats(envName.getLastPathComponent().toString(), topic.getLastPathComponent().toString());
                            } else if (selectedIndex == 1) {
                                loadSubscriptions(envName.getLastPathComponent().toString(), topic.getLastPathComponent().toString());
                            } else if (selectedIndex == 2) {
                                loadTopicPermissions(envName.getLastPathComponent().toString(), topic.getLastPathComponent().toString());
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
                    scrollPane2.setPreferredSize(new Dimension(150, 50));
                    scrollPane2.setMinimumSize(new Dimension(150, 50));
                    {
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
                                    topicsInfo.forEach(i -> {
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
                                        loadPartitionedStats(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
                                    } else if (selectedIndex == 1) {
                                        loadSubscriptions(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
                                    }
                                    if (selectedIndex == 2) {
                                        loadTopicPermissions(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
                                    }


                                }
                            }
                        });
                        tree2.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mousePressed(MouseEvent e) {
                                if (SwingUtilities.isRightMouseButton(e)) {
                                    JBPopupMenu jPopupMenu = new JBPopupMenu();
                                    JMenuItem bundleRange = new JMenuItem("bundle-range");
                                    bundleRange.setToolTipText("Get Namespace bundle range of a topic");
                                    bundleRange.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                bundleRange(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(bundleRange);

                                    JMenuItem clearBacklog = new JMenuItem("clear-backlog");
                                    clearBacklog.setToolTipText("Skip all the messages for the subscription");
                                    clearBacklog.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                clearBacklog(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(clearBacklog);
                                    JMenuItem compact = new JMenuItem("compact");
                                    compact.setToolTipText("Compact a topic");
                                    compact.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {

                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm compact the following topics: \n" + String.join("\n", topic),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    triggerCompaction(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                                }

                                            }
                                        }
                                    });
                                    jPopupMenu.add(compact);
                                    JMenuItem compactionStatus = new JMenuItem("compaction-status");
                                    compactionStatus.setToolTipText("Status of compaction on a topic");
                                    compactionStatus.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm compaction-status the following topics: \n" + String.join("\n", topic),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    compactionStatus(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                                }
                                            }
                                        }
                                    });
                                    jPopupMenu.add(compactionStatus);
                                    JMenuItem createNonPartition = new JMenuItem("create");
                                    createNonPartition.setToolTipText("Create a non-partitioned topic.");
                                    createNonPartition.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            TreePath envName = tree1.getSelectionPath();
                                            if (envName != null) {
                                                createTopic(envName.getLastPathComponent().toString(), 0);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(createNonPartition);

                                    JMenuItem createMissedPartition = new JMenuItem("create-missed-partitions");
                                    createMissedPartition.setToolTipText("<html>Try to create partitions for partitioned topic. The partitions " +
                                            "<br>of partition topic has to be created, can be used by repair <br>" +
                                            "partitions when topic auto creation is disabled</html>");
                                    createMissedPartition.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            TreePath envName = tree1.getSelectionPath();
                                            if (envName != null) {
                                                createTopic(envName.getLastPathComponent().toString(), 1);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(createMissedPartition);
                                    JMenuItem createPartition = new JMenuItem("create-partitioned-topic");
                                    createPartition.setToolTipText("<html>Create a partitioned topic. The partitioned topic has <br>to be created before creating a producer on it.</html>");
                                    createPartition.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            TreePath envName = tree1.getSelectionPath();
                                            if (envName != null) {
                                                createTopic(envName.getLastPathComponent().toString(), 2);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(createPartition);
                                    JMenuItem delete = new JMenuItem("delete");
                                    delete.setToolTipText("<html>Delete a topic. The topic cannot be deleted if there's <br>any active subscription or producers connected to it.</html>");
                                    delete.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null && tree1.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm delete the following topics: \n" + String.join("\n", topic),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree2.getLastSelectedPathComponent();
                                                    deleteTopic(tree1.getSelectionPath().getLastPathComponent().toString(), topic, selectedNode, 0);
                                                }
                                            }
                                        }
                                    });
                                    jPopupMenu.add(delete);
                                    JMenuItem deletePartitioned = new JMenuItem("delete-partitioned-topic");
                                    deletePartitioned.setToolTipText("<html>Delete a partitioned topic. It will also delete all <br>the partitions of the topic if it exists.</html>");
                                    deletePartitioned.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null && tree1.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm delete-partitioned-topic the following topics: \n" + String.join("\n", topic),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree2.getLastSelectedPathComponent();
                                                    deleteTopic(tree1.getSelectionPath().getLastPathComponent().toString(), topic, selectedNode, 1);
                                                }
                                            }
                                        }
                                    });
                                    jPopupMenu.add(deletePartitioned);

                                    JMenuItem examineMessage = new JMenuItem("examine-messages");
                                    examineMessage.setToolTipText("<html>Examine a specific message on a topic by position relative <br>to the earliest or the latest message.</html>");
                                    examineMessage.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                examineMessage(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(examineMessage);
                                    JMenuItem expireMessages = new JMenuItem("expire-messages");
                                    expireMessages.setToolTipText("<html>Expire messages that older than given expiry time (in seconds) <br>for the subscription</html>");
                                    expireMessages.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                expireMessages(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(expireMessages);
                                    JMenuItem lastMessageId = new JMenuItem("last-message-id");
                                    lastMessageId.setToolTipText("get the last commit message id of topic");
                                    lastMessageId.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm last-message-id the following topics: \n" + String.join("\n", topic),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    lastMessageId(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                                }
                                            }
                                        }
                                    });
                                    jPopupMenu.add(lastMessageId);
                                    JMenuItem offloadStatus = new JMenuItem("offload-status");
                                    offloadStatus.setToolTipText("<html>Check the status of data offloading from a topic to <br>long-term storage</html>");
                                    offloadStatus.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm offload-status the following topics: \n" + String.join("\n", topic),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    offloadStatus(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                                }

                                            }
                                        }
                                    });
                                    jPopupMenu.add(offloadStatus);
                                    JMenuItem peekMessages = new JMenuItem("peek-messages");
                                    peekMessages.setToolTipText("Peek some messages for the subscription");
                                    peekMessages.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                peekMessages(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(peekMessages);
                                    JMenuItem skip = new JMenuItem("skip");
                                    skip.setToolTipText("Skip some messages for the subscription");
                                    skip.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                skipMessages(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(skip);
                                    JMenuItem terminate = new JMenuItem("terminate");
                                    terminate.setToolTipText("<html>Terminate a topic and don't allow any more messages <br>to be published</html>");
                                    terminate.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm terminate the following topics: \n" + String.join("\n", topic),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    terminateTopic(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                                }

                                            }
                                        }
                                    });
                                    jPopupMenu.add(terminate);

                                    JMenuItem resetCursor = new JMenuItem("reset-cursor");
                                    resetCursor.setToolTipText("<html>Reset position for subscription to a position that is <br>closest to timestamp or messageId.</html>");
                                    resetCursor.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                resetCursor(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(resetCursor);

                                    JMenuItem truncate = new JMenuItem("truncate");
                                    truncate.setToolTipText("<html>Truncate a topic. The truncate operation will move allcursors <br>to the end  of the topic and delete all inactive ledgers.</html>");
                                    truncate.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm truncate the following topics: \n" + String.join("\n", topic),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    truncateTopic(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                                }

                                            }
                                        }
                                    });
                                    jPopupMenu.add(truncate);
                                    JMenuItem upload = new JMenuItem("unload");
                                    upload.setToolTipText("Unload a topic.");
                                    upload.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm upload the following topics: \n" + String.join("\n", topic),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    uploadTopic(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                                }

                                            }
                                        }
                                    });
                                    jPopupMenu.add(upload);
                                    JMenuItem updatePartition = new JMenuItem("update-partitioned-topic");
                                    updatePartition.setToolTipText("<html>Update existing non-global partitioned topic.New updating " +
                                            "<br>number of partitions must be greater than existing number " +
                                            "<br>of partitions.</html>");
                                    updatePartition.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String topic = tree2.getSelectionPath().getLastPathComponent().toString();
                                                updatePartition(tree1.getSelectionPath().getLastPathComponent().toString(), topic);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(updatePartition);

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

    private void resetCursor(String envName, String topic) {
        ComboBox<Boolean> exclude = new ComboBox<>(new Boolean[]{false, true});
        exclude.setToolTipText("Exclude the reset position, start consume messages from the next position.");
        JTextField jTextField2 = new HintTextField(" time in minutes to reset back to (or minutes, hours,days,weeks eg: 100m, 3h, 2d, 5w)");
        jTextField2.setToolTipText("time in minutes to reset back to (or minutes, hours,days,weeks eg: 100m, 3h, 2d, 5w)");
        JTextField resetMessageIdStr = new HintTextField(" messageId to reset back to (ledgerId:entryId)");
        resetMessageIdStr.setToolTipText("messageId to reset back to (ledgerId:entryId)");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin topics", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" Subscription to reset position on");
                myField.setToolTipText("Subscription to reset position on");
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                panel.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("subscription ");
                label2.setText("time ");
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
                panel.add(jTextField2, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(new JLabel("position"), new GridConstraints(2, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(resetMessageIdStr, new GridConstraints(2, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(new JLabel("exclude-reset-position"), new GridConstraints(3, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(exclude, new GridConstraints(3, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.setMinimumSize(new Dimension(380, 120));
                panel.setPreferredSize(new Dimension(380, 120));
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
                            Topics topics = pulsarAdmin.topics();
                            String subName = inputDialog.getInputString();
                            String resetTimeStr = jTextField2.getText();
                            String resetMessageId = resetMessageIdStr.getText();
                            if (isNotBlank(resetMessageId)) {
                                MessageId messageId = validateMessageIdString(resetMessageId);
                                if ((Boolean) exclude.getSelectedItem()) {
                                    topics.resetCursor(topic, subName, messageId, true);
                                } else {
                                    topics.resetCursor(topic, subName, messageId);
                                }
                            } else if (isNotBlank(resetTimeStr)) {
                                long resetTimeInMillis;
                                try {
                                    resetTimeInMillis = TimeUnit.SECONDS.toMillis(
                                            RelativeTimeUtil.parseRelativeTimeInSeconds(resetTimeStr));
                                } catch (IllegalArgumentException exception) {
                                    throw new ParameterException(exception.getMessage());
                                }
                                // now - go back time
                                long timestamp = System.currentTimeMillis() - resetTimeInMillis;
                                topics.resetCursor(topic, subName, timestamp);
                            } else {
                                throw new PulsarAdminException(
                                        "Either Timestamp (--time) or Position (--position) has to be provided to reset cursor");
                            }
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
            } catch (Exception e) {
                showException(e);
            }
        }
    }

    private void expireMessages(String envName, String topic) {
        ComboBox<Boolean> exclude = new ComboBox<>(new Boolean[]{false, true});
        exclude.setToolTipText("Exclude the reset position, start consume messages from the next position.");
        JTextField jTextField2 = new HintTextField("Expire messages older than time in seconds");
        jTextField2.setText("-1");
        jTextField2.setToolTipText("Expire messages older than time in seconds");
        JTextField position = new HintTextField(" message position to reset back to (ledgerId:entryId)");
        position.setToolTipText("message position to reset back to (ledgerId:entryId)");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin topics", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" Subscription to be skip messages on");
                myField.setToolTipText("Subscription to be skip messages on");
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                panel.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("subscription ");
                label2.setText("expireTime ");
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
                panel.add(jTextField2, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(new JLabel("position"), new GridConstraints(2, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(position, new GridConstraints(2, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(new JLabel("exclude-reset-position"), new GridConstraints(3, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(exclude, new GridConstraints(3, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.setMinimumSize(new Dimension(380, 120));
                panel.setPreferredSize(new Dimension(380, 120));
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
                            Topics topics = pulsarAdmin.topics();
                            String subName = inputDialog.getInputString();
                            long expireTimeInSeconds = Long.parseLong(jTextField2.getText());
                            String messagePosition = position.getText();
                            if (expireTimeInSeconds >= 0 && StringUtils.isNotBlank(messagePosition)) {
                                throw new ParameterException(String.format("Can't expire message by time and " +
                                        "by message position at the same time."));
                            }
                            if (expireTimeInSeconds >= 0) {
                                topics.expireMessages(topic, subName, expireTimeInSeconds);
                            } else if (isNotBlank(messagePosition)) {
                                int partitionIndex = TopicName.get(topic).getPartitionIndex();
                                MessageId messageId = validateMessageIdString(messagePosition, partitionIndex);
                                topics.expireMessages(topic, subName, messageId, (Boolean) exclude.getSelectedItem());
                            } else {
                                throw new ParameterException(
                                        "Either time (--expireTime) or message position (--position) has to be provided" +
                                                " to expire messages");
                            }
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
            } catch (Exception e) {
                showException(e);
            }

        }
    }

    private MessageId validateMessageIdString(String resetMessageIdStr) throws PulsarAdminException {
        return validateMessageIdString(resetMessageIdStr, -1);
    }

    private MessageId validateMessageIdString(String messagePosition, int partitionIndex) throws PulsarAdminException {
        String[] messageId = messagePosition.split(":");
        try {
            Preconditions.checkArgument(messageId.length == 2);
            return new MessageIdImpl(Long.parseLong(messageId[0]), Long.parseLong(messageId[1]), partitionIndex);
        } catch (Exception e) {
            throw new PulsarAdminException(
                    "Invalid message id (must be in format: ledgerId:entryId) value " + messagePosition);
        }
    }

    private void bundleRange(String envName, String topic) {
        try {
            String range = ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Lookup lookup = pulsarAdmin.lookups();
                        String range = lookup.getBundleRange(topic);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return range;
                    }
                }
            });
            Messages.showInfoMessage(range, "");
        } catch (Exception e) {
            showException(e);
        }
    }

    private void updatePartition(String envName, String topic) {
        ComboBox<Boolean> force = new ComboBox<>(new Boolean[]{false, true});
        force.setToolTipText("Update forcefully without validating existing partitioned topic");
        JSpinner jTextField2 = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        ((JSpinner.DefaultEditor) jTextField2.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        jTextField2.setToolTipText("Number of partitions for the topic");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin topics", Messages.getInformationIcon(), "", new InputValidator() {
            @Override
            public boolean checkInput(String inputString) {
                return true;

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
                panel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("partitions ");
                label2.setText("force ");
                panel.add(label1, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(jTextField2, new GridConstraints(0, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(label2, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(force, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.setMinimumSize(new Dimension(270, 50));
                panel.setPreferredSize(new Dimension(270, 50));
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
                            Topics topics = pulsarAdmin.topics();
                            topics.updatePartitionedTopic(topic, (Integer) jTextField2.getValue(), false, (Boolean) force.getSelectedItem());
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
            } catch (Exception e) {
                showException(e);
            }
        }
    }

    private void lastMessageId(String envName, String topic) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        topics.getLastMessageId(topic);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
        } catch (Exception e) {
            showException(e);
        }
    }

    private void clearBacklog(String envName, String topic) {
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin topics", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" Subscription to be cleared");
                myField.setToolTipText("Subscription to be cleared");
                JLabel label1 = new JLabel();
                panel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("subscription ");
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
                panel.setMinimumSize(new Dimension(270, 50));
                panel.setPreferredSize(new Dimension(270, 50));
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
                            Topics topics = pulsarAdmin.topics();
                            topics.skipAllMessages(topic, inputDialog.getInputString());
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
            } catch (Exception e) {
                showException(e);
            }
        }
    }

    private void skipMessages(String envName, String topic) {
        JSpinner jTextField2 = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        ((JSpinner.DefaultEditor) jTextField2.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        jTextField2.setToolTipText("Number of messages to skip");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin topics", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" Subscription to be skip messages on");
                myField.setToolTipText("Subscription to be skip messages on");
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                panel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("subscription ");
                label2.setText("count ");
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
                panel.add(jTextField2, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.setMinimumSize(new Dimension(270, 50));
                panel.setPreferredSize(new Dimension(270, 50));
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
                            Topics topics = pulsarAdmin.topics();
                            topics.skipMessages(topic, inputDialog.getInputString(), (Integer) jTextField2.getValue());
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
            } catch (Exception e) {
                showException(e);
            }
        }
    }

    private void peekMessages(String envName, String topic) {
        JSpinner jTextField2 = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        ((JSpinner.DefaultEditor) jTextField2.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        jTextField2.setToolTipText("Number of messages (default 1)");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin topics", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" Subscription to get messages from");
                myField.setToolTipText("Subscription to get messages from");
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                panel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("subscription ");
                label2.setText("count ");
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
                panel.add(jTextField2, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.setMinimumSize(new Dimension(270, 50));
                panel.setPreferredSize(new Dimension(270, 50));
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
                            Topics topics = pulsarAdmin.topics();
                            List<Message<byte[]>> messages = topics.peekMessages(topic, inputDialog.getInputString(), (Integer) jTextField2.getValue());
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
            } catch (Exception e) {
                showException(e);
            }
        }
    }

    private void examineMessage(String envName, String topic) {
        ComboBox<String> comboBox = new ComboBox(new String[]{"latest", "earliest"});
        comboBox.setToolTipText("<html>Relative start position to examine message.<br>It can be 'latest' or 'earliest', default is latest</html>");
        JSpinner jTextField2 = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        jTextField2.setToolTipText("The position of messages (default 1)");
        ((JSpinner.DefaultEditor) jTextField2.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin topics", Messages.getInformationIcon(), "", new InputValidator() {
            @Override
            public boolean checkInput(String inputString) {
                return true;
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
                panel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("initialPosition ");
                label2.setText("messagePosition ");
                panel.add(label1, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(comboBox, new GridConstraints(0, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(label2, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(jTextField2, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.setMinimumSize(new Dimension(300, 50));
                panel.setPreferredSize(new Dimension(300, 50));
                return panel;
            }
        };
        inputDialog.show();
        if (inputDialog.getInputString() != null) {
            try {
                MessageImpl message = ProgressManager.getInstance().run(new Task.WithResult<MessageImpl, Exception>(_project, envName, true) {
                    @Override
                    protected MessageImpl compute(@NotNull ProgressIndicator indicator) throws Exception {
                        try (PulsarAdmin pulsarAdmin = create(envName)) {
                            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                            Topics topics = pulsarAdmin.topics();
                            MessageImpl message = (MessageImpl) topics.examineMessage(topic, comboBox.getSelectedItem().toString(), (Integer) jTextField2.getValue());
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return message;
                        }
                    }
                });
                Messages.showInfoMessage(message.toString(), "");
            } catch (Exception e) {
                showException(e);
            }
        }
    }

    private void terminateTopic(String envName, String topic) {
        try {
            MessageId messageId = ProgressManager.getInstance().run(new Task.WithResult<MessageId, Exception>(_project, envName, true) {
                @Override
                protected MessageId compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        MessageId messageId = topics.terminateTopic(topic);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return messageId;
                    }
                }
            });
            Messages.showInfoMessage(messageId.toString(), "");
        } catch (Exception e) {
            showException(e);
        }
    }

    private void offloadStatus(String envName, String topic) {
        try {
            OffloadProcessStatus processStatus = ProgressManager.getInstance().run(new Task.WithResult<OffloadProcessStatus, Exception>(_project, envName, true) {
                @Override
                protected OffloadProcessStatus compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        OffloadProcessStatus processStatus = topics.offloadStatus(topic);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return processStatus;
                    }
                }
            });
            Messages.showInfoMessage(String.format("{\"status\":\"%s\",\"lastError\":\"%s\"}", processStatus.getStatus(), processStatus.getLastError()), "");
        } catch (Exception e) {
            showException(e);
        }
    }

    private void compactionStatus(String envName, String topic) {
        try {
            LongRunningProcessStatus processStatus = ProgressManager.getInstance().run(new Task.WithResult<LongRunningProcessStatus, Exception>(_project, envName, true) {
                @Override
                protected LongRunningProcessStatus compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        LongRunningProcessStatus processStatus = topics.compactionStatus(topic);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return processStatus;
                    }
                }
            });
            Messages.showInfoMessage(String.format("{\"status\":\"%s\",\"lastError\":\"%s\"}", processStatus.status, processStatus.lastError), "");
        } catch (Exception e) {
            showException(e);
        }
    }

    private void triggerCompaction(String envName, String topic) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        topics.triggerCompaction(topic);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
        } catch (Exception e) {
            showException(e);
        }
    }

    private void truncateTopic(String envName, String topic) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        topics.truncate(topic);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
        } catch (Exception e) {
            showException(e);
        }
    }

    private void uploadTopic(String envName, String topic) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        topics.unload(topic);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
        } catch (Exception e) {
            showException(e);
        }
    }

    private void createTopic(String envName, int type) {
        JSpinner jTextField2 = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        ((JSpinner.DefaultEditor) jTextField2.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        jTextField2.setToolTipText("Number of partitions for the topic");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin topics", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" persistent://tenant/namespace/topic");
                JLabel label1 = new JLabel();
                panel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("topic ");
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
                if (type == 2) {
                    JLabel label2 = new JLabel();
                    label2.setText("numPartitions ");
                    panel.add(label2, new GridConstraints(1, 0, 1, 1,
                            GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                            GridConstraints.SIZEPOLICY_FIXED,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));
                    panel.add(jTextField2, new GridConstraints(1, 1, 1, 1,
                            GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                            null, null, null));
                }
                panel.setMinimumSize(new Dimension(280, 50));
                panel.setPreferredSize(new Dimension(280, 50));
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
                            Topics topics = pulsarAdmin.topics();
                            switch (type) {
                                case 0:
                                    topics.createNonPartitionedTopic(inputDialog.getInputString());
                                    break;
                                case 1:
                                    topics.createMissedPartitions(inputDialog.getInputString());
                                    break;
                                case 2:
                                    topics.createPartitionedTopic(inputDialog.getInputString(), (Integer) jTextField2.getValue());
                                    break;

                            }
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
                this.topicsInfo.add(inputDialog.getInputString());
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

    private void deleteTopic(String envName, String topic, DefaultMutableTreeNode selectedNode, int type) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        if (type == 0) {
                            topics.delete(topic);
                        } else {
                            topics.deletePartitionedTopic(topic);
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
            topicsInfo.remove(topic);
            DefaultTreeModel model = (DefaultTreeModel) tree2.getModel();
            model.removeNodeFromParent(selectedNode);
            cleanTableAllRows(defaultTableModel1);
            cleanTableAllRows(defaultTableModel2);
            cleanTableAllRows(defaultTableModel3);
        } catch (Exception e) {
            showException(e);
        }
    }

    Set<String> topicsInfo = new HashSet<>();

    private void loadTopicsInfo(String envName) {
        try {
            cleanTableAllRows(defaultTableModel1);
            cleanTableAllRows(defaultTableModel2);
            cleanTableAllRows(defaultTableModel3);
            tree2.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("(root)")));
            TopicsOverview topicsOverview = ProgressManager.getInstance().run(new Task.WithResult<TopicsOverview, Exception>(_project, envName, true) {
                @Override
                protected TopicsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        TopicsOverview topicsOverview = new TopicsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Tenants tenants = pulsarAdmin.tenants();
                        java.util.List<String> tenantList = tenants.getTenants();
                        Namespaces namespaces = pulsarAdmin.namespaces();
                        Topics topics = pulsarAdmin.topics();
                        for (String t : tenantList) {
                            List<String> namespacesList = namespaces.getNamespaces(t);
                            for (String ns : namespacesList) {
                                List<String> topicsList = topics.getList(ns);
                                Set<String> topicsSet = new HashSet<>();
                                for (String topic : topicsList) {
                                    String[] array = topic.split(PARTITIONED_TOPIC_SUFFIX);
                                    topicsSet.add(array[0]);
                                }
                                topicsSet.addAll(new HashSet<>(topics.getPartitionedTopicList(ns)));
                                topicsOverview.addTopics(topicsSet);
                            }
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return topicsOverview;
                    }
                }
            });
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("(root)");
            topicsOverview.getTopics().forEach(i -> {
                treeNode.add(new DefaultMutableTreeNode(i));
            });
            DefaultTreeSelectionModel defaultTreeSelectionModel = new DefaultTreeSelectionModel();
            defaultTreeSelectionModel.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
            tree2.setSelectionModel(defaultTreeSelectionModel);
            tree2.setModel(new DefaultTreeModel(treeNode));
            topicsInfo = topicsOverview.getTopics();
        } catch (Exception e) {
            tree2.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("(root)")));
            showException(e);
        }
    }

    private void loadPartitionedStats(String envName, String topic) {
        try {
            TopicsOverview topicsOverview = ProgressManager.getInstance().run(new Task.WithResult<TopicsOverview, Exception>(_project, envName, true) {
                @Override
                protected TopicsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        TopicsOverview topicsOverview = new TopicsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        int partitions = topics.getPartitionedTopicMetadata(topic).partitions;
                        if (partitions != 0) {
                            PartitionedTopicStats stats = topics.getPartitionedStats(topic, true);
                            Map<String, String> broker = pulsarAdmin.lookups().lookupPartitionedTopic(topic);
                            Map<String, ? extends TopicStats> topicStats = stats.getPartitions();
                            for (Map.Entry<String, ? extends TopicStats> entry : topicStats.entrySet()) {
                                String k = entry.getKey();
                                TopicStats v = entry.getValue();
                                String[] part = k.split(PARTITIONED_TOPIC_SUFFIX);
                                String current = broker.get(k);
                                if (current == null) {
                                    current = pulsarAdmin.lookups().lookupTopic(k);
                                }
                                current = current.replace("pulsar://", "");
                                if (part.length == 2) {
                                    topicsOverview.addDataVector(Integer.parseInt(part[1]), v.getMsgRateIn(), v.getMsgRateOut(), v.getMsgThroughputIn(), v.getMsgThroughputOut(), FileUtils.byteCountToDisplaySize(v.getStorageSize()), current);
                                } else {
                                    topicsOverview.addDataVector("", v.getMsgRateIn(), v.getMsgRateOut(), v.getMsgThroughputIn(), v.getMsgThroughputOut(), FileUtils.byteCountToDisplaySize(v.getStorageSize()), current);
                                }
                            }
                        } else {
                            TopicStats topicStats = topics.getStats(topic);
                            String current = pulsarAdmin.lookups().lookupTopic(topic).replace("pulsar://", "");
                            topicsOverview.addDataVector("", topicStats.getMsgRateIn(), topicStats.getMsgRateOut(), topicStats.getMsgThroughputIn(), topicStats.getMsgThroughputOut(), FileUtils.byteCountToDisplaySize(topicStats.getStorageSize()), current);
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return topicsOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel1, topicsOverview.getDataVector());
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel1);
            showException(e);
        }
    }

    private void loadSubscriptions(String envName, String topic) {
        try {
            TopicsOverview topicsOverview = ProgressManager.getInstance().run(new Task.WithResult<TopicsOverview, Exception>(_project, envName, true) {
                @Override
                protected TopicsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        TopicsOverview topicsOverview = new TopicsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        int partitions = topics.getPartitionedTopicMetadata(topic).partitions;
                        Map<String, ? extends SubscriptionStats> subscriptions;
                        if (partitions != 0) {
                            subscriptions = pulsarAdmin.topics().getPartitionedStats(topic, false).getSubscriptions();
                        } else {
                            subscriptions = pulsarAdmin.topics().getStats(topic).getSubscriptions();
                        }
                        for (Map.Entry<String, ? extends SubscriptionStats> entry : subscriptions.entrySet()) {
                            SubscriptionStats stats = entry.getValue();
                            topicsOverview.addDataVector(entry.getKey(), stats.getMsgOutCounter(), stats.getMsgRateOut(), stats.getMsgThroughputOut(), stats.getMsgRateExpired(), stats.getMsgBacklog(), stats.getMsgDelayed());
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return topicsOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel2, topicsOverview.getDataVector());
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel2);
            showException(e);
        }
    }

    private void loadTopicPermissions(String envName, String topic) {
        try {
            TopicsOverview topicsOverview = ProgressManager.getInstance().run(new Task.WithResult<TopicsOverview, Exception>(_project, envName, true) {
                @Override
                protected TopicsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        TopicsOverview topicsOverview = new TopicsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        Map<String, Set<AuthAction>> permission = topics.getPermissions(topic);
                        permission.forEach((k, v) -> topicsOverview.addDataVector(false, k, v));
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return topicsOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel3, topicsOverview.getDataVector());
            headerSelectRenderer(table3);
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel3);
            showException(e);
        }
    }

    private void deleteTopicRole(String envName, String topic, List<String> roles) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        for (String role : roles) {
                            topics.revokePermissions(topic, role);
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
            removeTableSelectRows(defaultTableModel3);
        } catch (Exception e) {
            showException(e);
        }
    }

    private void grantPermission(String envName, String topic) {
        JPanel checkPanel = new JPanel();
        Set<AuthAction> authActions = new HashSet<>();
        checkPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JCheckBox produce = new JCheckBox("produce  ");
        produce.setToolTipText("Actions to be granted (produce,consume)");
        produce.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (((JCheckBox) e.getSource()).isSelected()) {
                    authActions.add(AuthAction.produce);
                } else {
                    authActions.remove(AuthAction.produce);
                }
            }
        });
        checkPanel.add(produce);
        JCheckBox consume = new JCheckBox("consume  ");
        consume.setToolTipText("Actions to be granted (produce,consume)");
        consume.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (((JCheckBox) e.getSource()).isSelected()) {
                    authActions.add(AuthAction.consume);
                } else {
                    authActions.remove(AuthAction.consume);
                }
            }
        });
        checkPanel.add(consume);
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin topics", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField("Client role to which grant permissions");
                myField.setToolTipText("Client role to which grant permissions");
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                panel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("role ");
                label2.setText("permission ");
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
                panel.add(checkPanel, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.setMinimumSize(new Dimension(270, 50));
                panel.setPreferredSize(new Dimension(270, 50));
                return panel;
            }
        };
        inputDialog.show();
        if (inputDialog.getInputString() != null && authActions.size() != 0) {
            try {
                ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                    @Override
                    protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                        try (PulsarAdmin pulsarAdmin = create(envName)) {
                            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                            Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                            Topics topics = pulsarAdmin.topics();
                            topics.grantPermission(topic, inputDialog.getInputString(), authActions);
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
                defaultTableModel3.addRow(new Object[]{false, inputDialog.getInputString(), authActions});
            } catch (Exception e) {
                showException(e);
            }
        }
    }

    @Override
    protected JComponent createSouthPanel() {
        return null;
    }
}
