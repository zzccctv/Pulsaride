package com.zzccctv.dialog;

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
import com.zzccctv.model.NamespacesOverview;
import org.apache.pulsar.client.admin.*;
import org.apache.pulsar.common.policies.data.*;
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

public class NamespacesDialog extends AbstractDialog {
    private JSplitPane splitPane1;
    private JSplitPane splitPane2;
    private JBScrollPane scrollPane1;
    private Tree tree1;
    private JTabbedPane tabbedPane1;
    private JPanel panel2;
    private JPanel panel3;
    private JToolBar toolBar0;
    private JToolBar toolBar1;
    private JToolBar toolBar2;
    private JToolBar toolBar3;
    private SearchTextField textField0;
    private SearchTextField textField1;
    private SearchTextField textField2;
    private SearchTextField textField3;
    private JBScrollPane scrollPane0;
    private JBScrollPane scrollPane3;
    private JBScrollPane scrollPane4;
    private JBScrollPane scrollPane5;
    private JBTable table0;
    private JBTable table1;
    private JBTable table2;
    private JBTable table3;
    private JPanel panel1;
    private JPanel panel4;
    private JPanel panel5;
    private JBScrollPane scrollPane2;
    private Tree tree2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private Project _project;
    private DefaultTableModel defaultTableModel0;
    private DefaultTableModel defaultTableModel1;
    private DefaultTableModel defaultTableModel2;
    private DefaultTableModel defaultTableModel3;

    public NamespacesDialog(@Nullable Project project) {
        super(project);
        _project = project;
        setTitle("pulsar-admin namespaces");
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
        //panel1 = new JPanel();
        tabbedPane1 = new JBTabbedPane();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        toolBar0 = new JToolBar();
        toolBar1 = new JToolBar();
        toolBar2 = new JToolBar();
        textField0 = new SearchTextField();
        textField1 = new SearchTextField();
        textField2 = new SearchTextField();
        toolBar3 = new JToolBar();
        textField3 = new SearchTextField();
        scrollPane5 = new JBScrollPane();
        panel5 = new JPanel();
        scrollPane0 = new JBScrollPane();
        scrollPane3 = new JBScrollPane();
        scrollPane4 = new JBScrollPane();
        table0 = new JBTable();
        table1 = new JBTable();
        table2 = new JBTable();
        table3 = new JBTable();
        panel4 = new JPanel();
        scrollPane2 = new JBScrollPane();
        tree2 = new Tree();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        TableRowSorter<DefaultTableModel> rowSorter0 = new TableRowSorter<>();
        TableRowSorter<DefaultTableModel> rowSorter1 = new TableRowSorter<>();
        TableRowSorter<DefaultTableModel> rowSorter2 = new TableRowSorter<>();
        TableRowSorter<DefaultTableModel> rowSorter3 = new TableRowSorter<>();
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
                        loadNamespacesInfo(e.getPath().getLastPathComponent().toString());
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
                    tabbedPane1.addTab("Policies", panel2);
                    tabbedPane1.setToolTipTextAt(0, "Get the configuration policies of a namespace");
                    {
                        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));

                        //======== toolBar1 ========
                        {
                            toolBar2.setEnabled(false);
                            toolBar2.setBorder(null);
                            toolBar2.setPreferredSize(new Dimension(352, 30));
                            toolBar2.setMaximumSize(new Dimension(32925, 30));
                            toolBar2.setMinimumSize(new Dimension(221, 30));
                            button3.setIcon(new ImageIcon(getClass().getResource("/refresh.png")));
                            button3.setPreferredSize(new Dimension(30, 30));
                            button3.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    TreePath envName = tree1.getSelectionPath();
                                    TreePath topic = tree2.getSelectionPath();
                                    if (envName != null && topic != null) {
                                        loadBundlesConfig(envName.getLastPathComponent().toString(), topic.getLastPathComponent().toString());
                                    }
                                }
                            });
                            toolBar2.add(button3);
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
                                        false
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
                            defaultTableModel2.addColumn("Bundle");
                            rowSorter2.setModel(defaultTableModel2);
                            table2.setModel(defaultTableModel2);
                            table2.setRowSorter(rowSorter2);
                            rowSorter2.setSortable(0, false);
                            scrollPane4.setViewportView(table2);
                        }
                        panel3.add(scrollPane4);
                    }
                    tabbedPane1.addTab("Bundles", panel3);
                    tabbedPane1.setToolTipTextAt(1, "Get the list of bundles for a namespace");

                    {
                        panel5.setLayout(new BoxLayout(panel5, BoxLayout.Y_AXIS));

                        //======== toolBar1 ========
                        {
                            toolBar3.setEnabled(false);
                            toolBar3.setBorder(null);
                            toolBar3.setPreferredSize(new Dimension(352, 30));
                            toolBar3.setMaximumSize(new Dimension(32925, 30));
                            toolBar3.setMinimumSize(new Dimension(221, 30));
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
                            defaultTableModel3.addColumn("Topic");
                            defaultTableModel3.addColumn("Domain");
                            rowSorter3.setModel(defaultTableModel3);
                            table3.setModel(defaultTableModel3);
                            table3.setRowSorter(rowSorter3);
                            rowSorter3.setSortable(0, false);
                            scrollPane5.setViewportView(table3);
                        }
                        panel5.add(scrollPane5);
                    }
                    tabbedPane1.addTab("Topics", panel5);
                    tabbedPane1.setToolTipTextAt(2, "Get the list of topics for a namespace");
                    {
                        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                        //======== toolBar1 ========
                        {
                            toolBar0.setEnabled(false);
                            toolBar0.setBorder(null);
                            toolBar0.setPreferredSize(new Dimension(352, 30));
                            toolBar0.setMaximumSize(new Dimension(32925, 30));
                            toolBar0.setMinimumSize(new Dimension(221, 30));
                            button1.setIcon(new ImageIcon(getClass().getResource("/add.png")));
                            button1.setPreferredSize(new Dimension(30, 30));
                            button1.setToolTipText("Grant permissions on a namespace");
                            button1.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (tree1.getSelectionPath() != null && tree2.getSelectionPath() != null) {
                                        String envName = tree1.getSelectionPath().getLastPathComponent().toString();
                                        String namespace = tree2.getSelectionPath().getLastPathComponent().toString();
                                        grantPermission(envName, namespace);
                                    }
                                }
                            });
                            toolBar0.add(button1);
                            button2.setIcon(new ImageIcon(getClass().getResource("/remove.png")));
                            button2.setPreferredSize(new Dimension(30, 30));
                            button2.setToolTipText("Revoke permissions on a namespace");
                            button2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (tree1.getSelectionPath() != null && tree2.getSelectionPath() != null) {
                                        List<String> roles = new ArrayList<>();
                                        String envName = tree1.getSelectionPath().getLastPathComponent().toString();
                                        String namespace = tree2.getSelectionPath().getLastPathComponent().toString();
                                        for (int i = 0; i < defaultTableModel0.getRowCount(); i++) {
                                            if ((Boolean) defaultTableModel0.getValueAt(i, 0)) {
                                                String role = (String) defaultTableModel0.getValueAt(i, 1);
                                                roles.add(role);
                                            }
                                        }
                                        if (roles.size() > 0 && Messages.OK == Messages.showOkCancelDialog(
                                                "Confirm delete the following roles: \n" + String.join("\n", roles),
                                                "", Messages.OK_BUTTON, Messages.CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                            deleteNamespaceRole(envName, namespace, roles);
                                        }
                                    }
                                }
                            });
                            toolBar0.add(button2);
                            textField0.addDocumentListener(new DocumentAdapter() {
                                @Override
                                protected void textChanged(@NotNull DocumentEvent e) {
                                    try {
                                        rowSorter0.setRowFilter(RowFilter.regexFilter(e.getDocument().getText(0, e.getDocument().getLength())));
                                    } catch (BadLocationException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            });
                            toolBar0.add(textField0);
                        }
                        panel1.add(toolBar0);

                        //======== scrollPane3 ========
                        {

                            //---- table1 ----
                            defaultTableModel0 = new DefaultTableModel() {
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
                            defaultTableModel0.addColumn("false");
                            defaultTableModel0.addColumn("Role");
                            defaultTableModel0.addColumn("Auth");
                            rowSorter0.setModel(defaultTableModel0);
                            table0.setModel(defaultTableModel0);
                            table0.setRowSorter(rowSorter0);
                            rowSorter0.setSortable(0, false);
                            TableColumnModel cm = table0.getColumnModel();
                            cm.getColumn(0).setMinWidth(50);
                            cm.getColumn(0).setMaxWidth(50);
                            table0.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                            table0.getTableHeader().getColumnModel().getColumn(0).setHeaderRenderer(new BooleanHeader());
                            table0.getTableHeader().addMouseListener(new MouseListener() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (table0.getColumnModel().getColumnIndexAtX(e.getX()) == 0) {
                                        boolean select = !Boolean.parseBoolean(cm.getColumn(0).getHeaderValue().toString());
                                        cm.getColumn(0).setHeaderValue(select);
                                        for (int i = 0; i < table0.getRowCount(); i++) {
                                            table0.setValueAt(!(Boolean) table0.getValueAt(i, 0), i, 0);
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
                            scrollPane0.setViewportView(table0);
                        }
                        panel1.add(scrollPane0);
                    }
                    tabbedPane1.setBorder(scrollPane2.getBorder());
                    tabbedPane1.addTab("Permissions", panel1);
                    tabbedPane1.setToolTipTextAt(3, "Get the permissions on a namespace");
                }

                tabbedPane1.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JTabbedPane pane = (JTabbedPane) e.getSource();
                        int selectedIndex = pane.getSelectedIndex();
                        TreePath envName = tree1.getSelectionPath();
                        TreePath namespace = tree2.getSelectionPath();
                        if (envName != null && namespace != null) {
                            if (selectedIndex == 0) {
                                loadPoliciesConfig(envName.getLastPathComponent().toString(), namespace.getLastPathComponent().toString());
                            } else if (selectedIndex == 1) {
                                loadBundlesConfig(envName.getLastPathComponent().toString(), namespace.getLastPathComponent().toString());
                            } else if (selectedIndex == 2) {
                                loadTopicsConfig(envName.getLastPathComponent().toString(), namespace.getLastPathComponent().toString());
                            } else if (selectedIndex == 3) {
                                loadNamespacePermissions(envName.getLastPathComponent().toString(), namespace.getLastPathComponent().toString());
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
                                    namespace.forEach(i -> {
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
                                        loadPoliciesConfig(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
                                    } else if (selectedIndex == 1) {
                                        loadBundlesConfig(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
                                    } else if (selectedIndex == 2) {
                                        loadTopicsConfig(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
                                    } else if (selectedIndex == 3) {
                                        loadNamespacePermissions(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
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
                                    create.setToolTipText("Creates a new namespace");
                                    create.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            TreePath envName = tree1.getSelectionPath();
                                            if (envName != null) {
                                                createNamespace(envName.getLastPathComponent().toString());
                                            }
                                        }
                                    });
                                    jPopupMenu.add(create);
                                    JMenuItem delete = new JMenuItem("delete");
                                    delete.setToolTipText("Deletes a namespace.");
                                    delete.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null && tree1.getSelectionPath() != null) {
                                                String namespace = tree2.getSelectionPath().getLastPathComponent().toString();
                                                if (Messages.OK == Messages.showOkCancelDialog(
                                                        "Confirm delete the following namespaces: \n" + String.join("\n", namespace),
                                                        "", OK_BUTTON, CANCEL_BUTTON, Messages.getQuestionIcon())) {
                                                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree2.getLastSelectedPathComponent();
                                                    deleteNamespace(tree1.getSelectionPath().getLastPathComponent().toString(), namespace, selectedNode);
                                                }
                                            }
                                        }
                                    });
                                    jPopupMenu.add(delete);
                                    JMenuItem splitNamespaceBundle = new JMenuItem("split-bundle");
                                    splitNamespaceBundle.setToolTipText("Split a namespace-bundle from the current serving broker");
                                    splitNamespaceBundle.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String namespace = tree2.getSelectionPath().getLastPathComponent().toString();
                                                splitNamespaceBundle(tree1.getSelectionPath().getLastPathComponent().toString(), namespace);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(splitNamespaceBundle);
                                    JMenuItem unloadNamespaceBundle = new JMenuItem("unload");
                                    unloadNamespaceBundle.setToolTipText("Unload a namespace from the current serving broker");
                                    unloadNamespaceBundle.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String namespace = tree2.getSelectionPath().getLastPathComponent().toString();
                                                unloadNamespaceBundle(tree1.getSelectionPath().getLastPathComponent().toString(), namespace);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(unloadNamespaceBundle);
                                    JMenuItem unsubscribeNamespace = new JMenuItem("unsubscribe");
                                    unsubscribeNamespace.setToolTipText("Unsubscribe the given subscription on all topics on a namespace");
                                    unsubscribeNamespace.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if (tree2.getSelectionPath() != null) {
                                                String namespace = tree2.getSelectionPath().getLastPathComponent().toString();
                                                unsubscribeNamespace(tree1.getSelectionPath().getLastPathComponent().toString(), namespace);
                                            }
                                        }
                                    });
                                    jPopupMenu.add(unsubscribeNamespace);
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

    private void grantPermission(String envName, String namespace) {
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
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin namespaces", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" Client role to which grant permissions");
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
                            Namespaces namespaces = pulsarAdmin.namespaces();
                            namespaces.grantPermissionOnNamespace(namespace, inputDialog.getInputString(), authActions);
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
                defaultTableModel0.addRow(new Object[]{false, inputDialog.getInputString(), authActions});
            } catch (Exception e) {
                showException(e);
            }
        }
    }

    private void deleteNamespaceRole(String envName, String namespace, List<String> roles) {
        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Namespaces namespaces = pulsarAdmin.namespaces();
                        for (String role : roles) {
                            namespaces.revokePermissionsOnNamespace(namespace, role);
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
            removeTableSelectRows(defaultTableModel0);
        } catch (Exception e) {
            showException(e);
        }
    }

    private void unsubscribeNamespace(String envName, String namespace) {
        HintTextField bundle = new HintTextField(" {start-boundary}_{end-boundary}");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin namespaces", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" subscription name");
                JLabel label1 = new JLabel();
                panel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("sub ");
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
                panel.add(new JLabel("bundle"), new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(bundle, new GridConstraints(1, 1, 1, 1,
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
                            Namespaces namespaces = pulsarAdmin.namespaces();
                            if (bundle.getText() != null && bundle.getText().length() != 0) {
                                namespaces.unsubscribeNamespaceBundle(namespace, bundle.getText(), inputDialog.getInputString());
                            } else {
                                namespaces.unsubscribeNamespace(namespace, inputDialog.getInputString());
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

    private void unloadNamespaceBundle(String envName, String namespace) {
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin namespaces", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" {start-boundary}_{end-boundary}");
                JLabel label1 = new JLabel();
                panel.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("bundle ");
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
                            Namespaces namespaces = pulsarAdmin.namespaces();
                            if (inputDialog.getInputString() != null && inputDialog.getInputString().length() != 0) {
                                namespaces.unloadNamespaceBundle(namespace, inputDialog.getInputString());
                            } else {
                                namespaces.unload(namespace);

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

    private void splitNamespaceBundle(String envName, String namespace) {
        ComboBox<Boolean> unloadSplitBundles = new ComboBox<>(new Boolean[]{false, true});
        unloadSplitBundles.setToolTipText("Unload newly split bundles after splitting old bundle");
        ComboBox<String> splitAlgorithmName = new ComboBox<>(new String[]{"range_equally_divide", "topic_count_equally_divide"});
        splitAlgorithmName.setToolTipText("<html>Algorithm name for split namespace bundle. Valid options are: <br> [range_equally_divide, topic_count_equally_divide]. Use broker <br>side config if absent</html>");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin namespaces", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" {start-boundary}_{end-boundary} / LARGEST(bundle with highest topics)");
                myField.setToolTipText("{start-boundary}_{end-boundary} / LARGEST(bundle with highest topics)");
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                JLabel label3 = new JLabel();
                panel.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("bundle ");
                label2.setText("unload ");
                label3.setText("split-algorithm-name ");
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
                panel.add(unloadSplitBundles, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(label3, new GridConstraints(2, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.add(splitAlgorithmName, new GridConstraints(2, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                panel.setMinimumSize(new Dimension(390, 100));
                panel.setPreferredSize(new Dimension(390, 100));
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
                            Namespaces namespaces = pulsarAdmin.namespaces();
                            if ((Boolean) unloadSplitBundles.getSelectedItem()) {
                                namespaces.splitNamespaceBundle(namespace, inputDialog.getInputString(), true, null);
                            } else {
                                namespaces.splitNamespaceBundle(namespace, inputDialog.getInputString(), true, splitAlgorithmName.getSelectedItem().toString());
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

    private void loadNamespacePermissions(String envName, String namespace) {
        try {
            NamespacesOverview namespacesOverview = ProgressManager.getInstance().run(new Task.WithResult<NamespacesOverview, Exception>(_project, envName, true) {
                @Override
                protected NamespacesOverview compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        NamespacesOverview namespacesOverview = new NamespacesOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Namespaces namespaces = pulsarAdmin.namespaces();
                        Map<String, Set<AuthAction>> permission = namespaces.getPermissions(namespace);
                        permission.forEach((k, v) -> namespacesOverview.addDataVector(false, k, v));
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return namespacesOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel0, namespacesOverview.getDataVector());
            headerSelectRenderer(table0);
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel0);
            showException(e);
        }
    }

    private void createNamespace(String envName) {
        JSpinner jTextField2 = new JSpinner(new SpinnerNumberModel(4, 1, Integer.MAX_VALUE, 1));
        ((JSpinner.DefaultEditor) jTextField2.getEditor()).getTextField().setHorizontalAlignment(JTextField.LEFT);
        jTextField2.setToolTipText(" number of bundles to activate");
        Messages.InputDialog inputDialog = new Messages.InputDialog("", "pulsar-admin namespaces", Messages.getInformationIcon(), "", new InputValidator() {
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
                myField = new HintTextField(" tenant/namespace");
                JLabel label1 = new JLabel();
                JLabel label2 = new JLabel();
                panel.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), 0, 0));
                label1.setText("namespace ");
                label2.setText("numBundles ");
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
                            Namespaces namespaces = pulsarAdmin.namespaces();
                            namespaces.createNamespace(inputDialog.getInputString(), (Integer) jTextField2.getValue());
                            Thread.currentThread().setContextClassLoader(classLoader);
                            return "";
                        }
                    }
                });
                this.namespace.add(inputDialog.getInputString());
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

    private void deleteNamespace(String envName, String namespace, DefaultMutableTreeNode selectedNode) {

        try {
            ProgressManager.getInstance().run(new Task.WithResult<String, Exception>(_project, envName, true) {
                @Override
                protected String compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Namespaces namespaces = pulsarAdmin.namespaces();
                        namespaces.deleteNamespace(namespace);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return "";
                    }
                }
            });
            this.namespace.remove(namespace);
            DefaultTreeModel model = (DefaultTreeModel) tree2.getModel();
            model.removeNodeFromParent(selectedNode);
            cleanTableAllRows(defaultTableModel0);
            cleanTableAllRows(defaultTableModel1);
            cleanTableAllRows(defaultTableModel2);
            cleanTableAllRows(defaultTableModel3);
        } catch (Exception e) {
            showException(e);
        }

    }

    private void loadTopicsConfig(String envName, String namespace) {
        try {
            NamespacesOverview namespacesOverview = ProgressManager.getInstance().run(new Task.WithResult<NamespacesOverview, Exception>(_project, envName, true) {
                @Override
                protected NamespacesOverview compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        NamespacesOverview namespacesOverview = new NamespacesOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Topics topics = pulsarAdmin.topics();
                        Set<String> allTopic = new HashSet<>();
                        topics.getPartitionedTopicList(namespace).forEach(topic -> {
                            String[] topicPath = topic.split("://");
                            allTopic.add(topicPath[1].split("/")[2]);
                            namespacesOverview.addDataVector(topicPath[1].split("/")[2], topicPath[0]);
                        });
                        topics.getList(namespace).forEach(topic -> {
                            String[] topicPath = topic.split("://");
                            String[] path = topicPath[1].split("/")[2].split(PARTITIONED_TOPIC_SUFFIX);
                            if (!allTopic.contains(path[0])) {
                                namespacesOverview.addDataVector(path[0].split(PARTITIONED_TOPIC_SUFFIX)[0], topicPath[0]);
                            }
                        });
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return namespacesOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel3, namespacesOverview.getDataVector());
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel3);
            showException(e);
        }
    }

    private void loadPoliciesConfig(String envName, String namespace) {

        try {
            NamespacesOverview namespacesOverview = ProgressManager.getInstance().run(new Task.WithResult<NamespacesOverview, Exception>(_project, envName, true) {
                @Override
                protected NamespacesOverview compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        NamespacesOverview namespacesOverview = new NamespacesOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Namespaces namespaces = pulsarAdmin.namespaces();
                        Policies policies = namespaces.getPolicies(namespace);
                        namespacesOverview.addDataVector("compaction_threshold", policies.compaction_threshold);
                        namespacesOverview.addDataVector("deduplicationEnabled", policies.deduplicationEnabled);
                        namespacesOverview.addDataVector("deduplicationSnapshotIntervalSeconds", policies.deduplicationSnapshotIntervalSeconds);
                        namespacesOverview.addDataVector("deleted", policies.deleted);
                        namespacesOverview.addDataVector("encryption_required", policies.encryption_required);
                        namespacesOverview.addDataVector("is_allow_auto_update_schema", policies.is_allow_auto_update_schema);
                        namespacesOverview.addDataVector("max_consumers_per_subscription", policies.max_consumers_per_subscription);
                        namespacesOverview.addDataVector("max_consumers_per_topic", policies.max_consumers_per_topic);
                        namespacesOverview.addDataVector("max_producers_per_topic", policies.max_producers_per_topic);
                        namespacesOverview.addDataVector("max_subscriptions_per_topic", policies.max_subscriptions_per_topic);
                        namespacesOverview.addDataVector("max_topics_per_namespace", policies.max_topics_per_namespace);
                        namespacesOverview.addDataVector("max_unacked_messages_per_consumer", policies.max_unacked_messages_per_consumer);
                        namespacesOverview.addDataVector("max_unacked_messages_per_subscription", policies.max_unacked_messages_per_subscription);
                        namespacesOverview.addDataVector("message_ttl_in_seconds", policies.message_ttl_in_seconds);
                        namespacesOverview.addDataVector("offload_deletion_lag_ms", policies.offload_deletion_lag_ms);
                        namespacesOverview.addDataVector("offload_threshold", policies.offload_threshold);
                        namespacesOverview.addDataVector("schema_compatibility_strategy", policies.schema_compatibility_strategy);
                        namespacesOverview.addDataVector("subscription_auth_mode", policies.subscription_auth_mode);
                        namespacesOverview.addDataVector("schema_validation_enforced", policies.schema_validation_enforced);
                        namespacesOverview.addDataVector("subscription_expiration_time_minutes", policies.subscription_expiration_time_minutes);
                        namespacesOverview.addDataVector("resource_group_name", policies.resource_group_name);
                        policies.latency_stats_sample_rate.forEach(namespacesOverview::addDataVector);
                        RetentionPolicies retentionPolicies = policies.retention_policies;
                        if (retentionPolicies != null) {
                            namespacesOverview.addDataVector("retentionSizeInMB", retentionPolicies.getRetentionSizeInMB());
                            namespacesOverview.addDataVector("retentionTimeInMinutes", retentionPolicies.getRetentionTimeInMinutes());

                        }
                        DelayedDeliveryPolicies delayedDeliveryPolicies = policies.delayed_delivery_policies;
                        if (delayedDeliveryPolicies != null) {
                            namespacesOverview.addDataVector("tickTime", delayedDeliveryPolicies.getTickTime());
                            namespacesOverview.addDataVector("isActive", delayedDeliveryPolicies.isActive());

                        }
                        InactiveTopicPolicies inactiveTopicPolicies = policies.inactive_topic_policies;
                        if (inactiveTopicPolicies != null) {
                            namespacesOverview.addDataVector("isDeleteWhileInactive", inactiveTopicPolicies.isDeleteWhileInactive());
                            namespacesOverview.addDataVector("maxInactiveDurationSeconds", inactiveTopicPolicies.getMaxInactiveDurationSeconds());
                            namespacesOverview.addDataVector("InactiveTopicDeleteMode", inactiveTopicPolicies.getInactiveTopicDeleteMode());
                        }
                        PersistencePolicies persistencePolicies = policies.persistence;
                        if (persistencePolicies != null) {
                            namespacesOverview.addDataVector("bookkeeperAckQuorum", persistencePolicies.getBookkeeperAckQuorum());
                            namespacesOverview.addDataVector("bookkeeperEnsemble", persistencePolicies.getBookkeeperEnsemble());
                            namespacesOverview.addDataVector("bookkeeperWriteQuorum", persistencePolicies.getBookkeeperWriteQuorum());
                            namespacesOverview.addDataVector("banagedLedgerMaxMarkDeleteRate", persistencePolicies.getManagedLedgerMaxMarkDeleteRate());
                        }
                        AutoSubscriptionCreationOverride autoSubscriptionCreationOverride = policies.autoSubscriptionCreationOverride;
                        if (autoSubscriptionCreationOverride != null) {
                            namespacesOverview.addDataVector("isAllowAutoSubscriptionCreation", autoSubscriptionCreationOverride.isAllowAutoSubscriptionCreation());
                        }
                        BundlesData bundles = policies.bundles;
                        if (bundles != null) {
                            namespacesOverview.addDataVector("numBundles", bundles.getNumBundles());
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return namespacesOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel1, namespacesOverview.getDataVector());
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel1);
            showException(e);
        }
    }

    private void loadBundlesConfig(String envName, String namespace) {
        try {
            NamespacesOverview namespacesOverview = ProgressManager.getInstance().run(new Task.WithResult<NamespacesOverview, Exception>(_project, envName, true) {
                @Override
                protected NamespacesOverview compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        NamespacesOverview namespacesOverview = new NamespacesOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Namespaces namespaces = pulsarAdmin.namespaces();
                        BundlesData bundlesData = namespaces.getBundles(namespace);
                        bundlesData.getBoundaries().forEach(namespacesOverview::addDataVector);
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return namespacesOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel2, namespacesOverview.getDataVector());
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel2);
            showException(e);
        }

    }

    private Set<String> namespace = new HashSet<>();

    private void loadNamespacesInfo(String envName) {
        try {
            cleanTableAllRows(defaultTableModel0);
            cleanTableAllRows(defaultTableModel1);
            cleanTableAllRows(defaultTableModel2);
            cleanTableAllRows(defaultTableModel3);
            tree2.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("(root)")));
            NamespacesOverview namespacesOverview = ProgressManager.getInstance().run(new Task.WithResult<NamespacesOverview, Exception>(_project, envName, true) {
                @Override
                protected NamespacesOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        NamespacesOverview namespacesOverview = new NamespacesOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Tenants tenants = pulsarAdmin.tenants();
                        List<String> tenantList = tenants.getTenants();
                        Namespaces namespaces = pulsarAdmin.namespaces();
                        for (String t : tenantList) {
                            List<String> namespacesList = namespaces.getNamespaces(t);
                            namespacesOverview.addNamespaces(new HashSet<>(namespacesList));
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return namespacesOverview;
                    }
                }
            });
            namespace = namespacesOverview.getNamespaces();
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("(root)");
            namespacesOverview.getNamespaces().forEach(i -> {
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
}
