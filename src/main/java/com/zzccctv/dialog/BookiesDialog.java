package com.zzccctv.dialog;


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
import com.zzccctv.model.BookiesOverview;
import org.apache.pulsar.client.admin.Bookies;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.common.policies.data.BookieInfo;
import org.apache.pulsar.common.policies.data.BookiesClusterInfo;
import org.apache.pulsar.common.policies.data.RawBookieInfo;
import org.apache.pulsar.common.policies.data.impl.BookieInfoImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.tree.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookiesDialog extends AbstractDialog {
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

    public BookiesDialog(@Nullable Project project) {
        super(project);
        _project = project;
        setTitle("pulsar-admin bookies");
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
                        loadBookiesInfo(e.getPath().getLastPathComponent().toString());
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
                            defaultTableModel1.addColumn("Hostname");
                            defaultTableModel1.addColumn("Rack");
                            rowSorter1.setModel(defaultTableModel1);
                            table1.setModel(defaultTableModel1);
                            table1.setRowSorter(rowSorter1);
                            rowSorter1.setSortable(0, false);
                            scrollPane3.setViewportView(table1);
                        }
                        panel2.add(scrollPane3);
                    }
                    tabbedPane1.setBorder(scrollPane2.getBorder());
                    tabbedPane1.addTab("RackInfo", panel2);
                    tabbedPane1.setToolTipTextAt(0, "Gets the rack placement information for a specific bookie in the cluster");
                }
                tabbedPane1.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JTabbedPane pane = (JTabbedPane) e.getSource();
                        int selectedIndex = pane.getSelectedIndex();
                        TreePath envName = tree1.getSelectionPath();
                        TreePath bookie = tree2.getSelectionPath();
                        if (selectedIndex == 0 && envName != null && bookie != null) {
                            loadRackInfo(envName.getLastPathComponent().toString(), bookie.getLastPathComponent().toString());
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
                                    bookies.forEach(i -> {
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
                                        loadRackInfo(envName.getLastPathComponent().toString(), path.getLastPathComponent().toString());
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

    private List<String> bookies = new ArrayList<>();

    private void loadBookiesInfo(String envName) {
        try {
            cleanTableAllRows(defaultTableModel1);
            tree2.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("(root)")));
            BookiesOverview bookiesOverview = ProgressManager.getInstance().run(new Task.WithResult<BookiesOverview, Exception>(_project, envName, true) {
                @Override
                protected BookiesOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        BookiesOverview bookiesOverview = new BookiesOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Bookies bookies = pulsarAdmin.bookies();
                        BookiesClusterInfo bookiesClusterInfo = bookies.getBookies();
                        List<RawBookieInfo> bookieInfoList = bookiesClusterInfo.getBookies();
                        for (RawBookieInfo info : bookieInfoList) {
                            bookiesOverview.addBookies(info.getBookieId());
                        }
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return bookiesOverview;
                    }
                }
            });
            bookies = bookiesOverview.getBookies();
            DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode("(root)");
            bookiesOverview.getBookies().forEach(i -> {
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

    private void loadRackInfo(String envName, String bookie) {
        try {
            BookiesOverview bookiesOverview = ProgressManager.getInstance().run(new Task.WithResult<BookiesOverview, Exception>(_project, envName, true) {
                @Override
                protected BookiesOverview compute(@NotNull ProgressIndicator indicator) throws Exception {

                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        BookiesOverview bookiesOverview = new BookiesOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        Bookies bookies = pulsarAdmin.bookies();
                        BookieInfo bookieInfo = bookies.getBookiesRackInfo().getBookie(bookie).orElseGet(() -> new BookieInfoImpl("", ""));
                        bookiesOverview.addDataVector(bookieInfo.getHostname(), bookieInfo.getRack());
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return bookiesOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel1, bookiesOverview.getDataVector());
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
