/*
 * Created by JFormDesigner on Thu Dec 01 21:04:38 CST 2022
 */

package com.zzccctv.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
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
public class Topics extends JPanel {
    public Topics() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        splitPane1 = new JSplitPane();
        scrollPane1 = new JBScrollPane();
        tree1 = new Tree();
        splitPane2 = new JSplitPane();
        tabbedPane1 = new JBTabbedPane();
        panel1 = new JPanel();
        toolBar1 = new JToolBar();
        textField1 = new SearchTextField();
        scrollPane2 = new JScrollPane();
        table1 = new JBTable();
        panel2 = new JPanel();
        scrollPane3 = new JScrollPane();
        table2 = new JBTable();

        //======== this ========
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

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

            //======== splitPane2 ========
            {
                splitPane2.setDividerLocation(100);
                splitPane2.setLastDividerLocation(50);
                splitPane2.setResizeWeight(0.8);
                splitPane2.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));

                //======== tabbedPane1 ========
                {

                    //======== panel1 ========
                    {
                        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                        //======== toolBar1 ========
                        {
                            toolBar1.setEnabled(false);
                            toolBar1.setBorder(null);
                            toolBar1.setMaximumSize(new Dimension(2147483647, 30));
                            toolBar1.add(textField1);
                        }
                        panel1.add(toolBar1);

                        //======== scrollPane2 ========
                        {

                            //---- table1 ----
                            table1.setModel(new DefaultTableModel(
                                new Object[][] {
                                    {"", null},
                                },
                                new String[] {
                                    "A", "B"
                                }
                            ));
                            scrollPane2.setViewportView(table1);
                        }
                        panel1.add(scrollPane2);
                    }
                    tabbedPane1.addTab("text", panel1);
                }
                splitPane2.setLeftComponent(tabbedPane1);

                //======== panel2 ========
                {
                    panel2.setMaximumSize(new Dimension(100, 32767));
                    panel2.setPreferredSize(new Dimension(100, 412));
                    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));

                    //======== scrollPane3 ========
                    {

                        //---- table2 ----
                        table2.setModel(new DefaultTableModel(
                            new Object[][] {
                                {"1"},
                                {"1"},
                            },
                            new String[] {
                                null
                            }
                        ));
                        table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                        table2.setShowHorizontalLines(false);
                        scrollPane3.setViewportView(table2);
                    }
                    panel2.add(scrollPane3);
                }
                splitPane2.setRightComponent(panel2);
            }
            splitPane1.setRightComponent(splitPane2);
        }
        add(splitPane1);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JSplitPane splitPane1;
    private JBScrollPane scrollPane1;
    private Tree tree1;
    private JSplitPane splitPane2;
    private JBTabbedPane tabbedPane1;
    private JPanel panel1;
    private JToolBar toolBar1;
    private SearchTextField textField1;
    private JScrollPane scrollPane2;
    private JBTable table1;
    private JPanel panel2;
    private JScrollPane scrollPane3;
    private JBTable table2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
