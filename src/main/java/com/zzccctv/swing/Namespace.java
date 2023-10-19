/*
 * Created by JFormDesigner on Fri Nov 25 18:41:04 CST 2022
 */

package com.zzccctv.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import com.intellij.ui.*;
import com.intellij.ui.components.*;
import com.intellij.ui.table.*;
import com.intellij.ui.treeStructure.*;
import com.intellij.uiDesigner.core.*;

/**
 * @author zhangLI
 */
public class Namespace extends JPanel {
    public Namespace() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        splitPane1 = new JSplitPane();
        scrollPane1 = new JBScrollPane();
        tree1 = new Tree();
        panel1 = new JPanel();
        tabbedPane1 = new JBTabbedPane();
        panel2 = new JPanel();
        toolBar1 = new JToolBar();
        textField1 = new SearchTextField();
        scrollPane3 = new JBScrollPane();
        table1 = new JBTable();
        panel4 = new JPanel();
        toolBar2 = new JToolBar();
        button1 = new JButton();
        textField2 = new SearchTextField();
        scrollPane2 = new JBScrollPane();
        tree2 = new Tree();

        //======== this ========
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        //======== splitPane1 ========
        {

            //======== scrollPane1 ========
            {
                scrollPane1.setMinimumSize(new Dimension(100, 20));

                //---- tree1 ----
                tree1.setRootVisible(false);
                tree1.setModel(new DefaultTreeModel(
                    new DefaultMutableTreeNode("(root)") {
                        {
                            add(new DefaultMutableTreeNode("a"));
                            add(new DefaultMutableTreeNode("b"));
                            add(new DefaultMutableTreeNode("c"));
                            add(new DefaultMutableTreeNode("d"));
                            add(new DefaultMutableTreeNode("e"));
                        }
                    }));
                scrollPane1.setViewportView(tree1);
            }
            splitPane1.setLeftComponent(scrollPane1);

            //======== panel1 ========
            {
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));

                //======== tabbedPane1 ========
                {

                    //======== panel2 ========
                    {
                        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

                        //======== toolBar1 ========
                        {
                            toolBar1.setEnabled(false);
                            toolBar1.setBorder(null);
                            toolBar1.setPreferredSize(new Dimension(352, 34));
                            toolBar1.setMaximumSize(new Dimension(32925, 34));
                            toolBar1.setMinimumSize(new Dimension(221, 34));
                            toolBar1.setAlignmentY(0.5F);
                            toolBar1.add(textField1);
                        }
                        panel2.add(toolBar1);

                        //======== scrollPane3 ========
                        {

                            //---- table1 ----
                            table1.setModel(new DefaultTableModel(
                                new Object[][] {
                                },
                                new String[] {
                                    null, "AdminRoles", "AllowedClusters"
                                }
                            ) {
                                Class<?>[] columnTypes = new Class<?>[] {
                                    Boolean.class, Object.class, Object.class
                                };
                                @Override
                                public Class<?> getColumnClass(int columnIndex) {
                                    return columnTypes[columnIndex];
                                }
                            });
                            scrollPane3.setViewportView(table1);
                        }
                        panel2.add(scrollPane3);
                    }
                    tabbedPane1.addTab("text", panel2);
                }
                panel1.add(tabbedPane1);

                //======== panel4 ========
                {
                    panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));

                    //======== toolBar2 ========
                    {
                        toolBar2.setEnabled(false);
                        toolBar2.setBorder(null);

                        //---- button1 ----
                        button1.setMaximumSize(new Dimension(30, 30));
                        button1.setMinimumSize(new Dimension(30, 30));
                        button1.setBorder(null);
                        button1.setIcon(new ImageIcon(getClass().getResource("/nodes/class.png")));
                        button1.setPreferredSize(new Dimension(30, 30));
                        toolBar2.add(button1);

                        //---- textField2 ----
                        textField2.setMaximumSize(new Dimension(2147483647, 30));
                        toolBar2.add(textField2);
                    }
                    panel4.add(toolBar2);

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setMinimumSize(new Dimension(100, 20));

                        //---- tree2 ----
                        tree2.setRootVisible(false);
                        tree2.setModel(new DefaultTreeModel(
                            new DefaultMutableTreeNode("(root)") {
                                {
                                    add(new DefaultMutableTreeNode("a"));
                                    add(new DefaultMutableTreeNode("b"));
                                    add(new DefaultMutableTreeNode("c"));
                                    add(new DefaultMutableTreeNode("d"));
                                    add(new DefaultMutableTreeNode("e"));
                                }
                            }));
                        scrollPane2.setViewportView(tree2);
                    }
                    panel4.add(scrollPane2);
                }
                panel1.add(panel4);
            }
            splitPane1.setRightComponent(panel1);
        }
        add(splitPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JSplitPane splitPane1;
    private JBScrollPane scrollPane1;
    private Tree tree1;
    private JPanel panel1;
    private JBTabbedPane tabbedPane1;
    private JPanel panel2;
    private JToolBar toolBar1;
    private SearchTextField textField1;
    private JBScrollPane scrollPane3;
    private JBTable table1;
    private JPanel panel4;
    private JToolBar toolBar2;
    private JButton button1;
    private SearchTextField textField2;
    private JBScrollPane scrollPane2;
    private Tree tree2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
