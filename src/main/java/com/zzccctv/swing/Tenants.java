/*
 * Created by JFormDesigner on Thu Nov 24 20:18:00 CST 2022
 */

package com.zzccctv.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import com.intellij.openapi.ui.*;
import com.intellij.ui.*;
import com.intellij.ui.components.*;
import com.intellij.ui.table.*;
import com.intellij.ui.treeStructure.*;
import com.intellij.uiDesigner.core.*;

/**
 * @author zhangLI
 */
public class Tenants extends JPanel {
    public Tenants() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        splitPane1 = new JSplitPane();
        scrollPane1 = new JBScrollPane();
        tree1 = new Tree();
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        label8 = new JLabel();
        label9 = new JLabel();
        label10 = new JLabel();
        toolBar1 = new JToolBar();
        button1 = new JButton();
        button3 = new JButton();
        button2 = new JButton();
        hSpacer1 = new JPanel(null);
        textField1 = new SearchTextField();
        scrollPane2 = new JBScrollPane();
        table1 = new JBTable();

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
                panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

                //======== panel2 ========
                {
                    panel2.setPreferredSize(new Dimension(292, 67));
                    panel2.setMinimumSize(new Dimension(292, 67));
                    panel2.setMaximumSize(new Dimension(2147483647, 67));
                    panel2.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));

                    //---- label1 ----
                    label1.setText("0");
                    panel2.add(label1, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- label2 ----
                    label2.setText("0");
                    panel2.add(label2, new GridConstraints(0, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- label3 ----
                    label3.setText("0");
                    panel2.add(label3, new GridConstraints(0, 2, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- label4 ----
                    label4.setText("0");
                    panel2.add(label4, new GridConstraints(0, 3, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- label5 ----
                    label5.setText("0");
                    panel2.add(label5, new GridConstraints(0, 4, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- label6 ----
                    label6.setText("Topics");
                    panel2.add(label6, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- label7 ----
                    label7.setText("Partitions");
                    panel2.add(label7, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- label8 ----
                    label8.setText("URP");
                    panel2.add(label8, new GridConstraints(1, 2, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- label9 ----
                    label9.setText("NO Leader");
                    panel2.add(label9, new GridConstraints(1, 3, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));

                    //---- label10 ----
                    label10.setText("< Min ISR");
                    panel2.add(label10, new GridConstraints(1, 4, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                        null, null, null));
                }
                panel1.add(panel2);

                //======== toolBar1 ========
                {
                    toolBar1.setEnabled(false);
                    toolBar1.setBorder(null);
                    toolBar1.setPreferredSize(new Dimension(352, 34));
                    toolBar1.setMaximumSize(new Dimension(32925, 34));
                    toolBar1.setMinimumSize(new Dimension(221, 34));
                    toolBar1.setAlignmentY(0.5F);

                    //---- button1 ----
                    button1.setIcon(new ImageIcon(getClass().getResource("/general/add.png")));
                    button1.setMaximumSize(new Dimension(30, 30));
                    button1.setMinimumSize(new Dimension(30, 30));
                    button1.setPreferredSize(new Dimension(30, 30));
                    toolBar1.add(button1);

                    //---- button3 ----
                    button3.setPreferredSize(new Dimension(30, 30));
                    button3.setMaximumSize(new Dimension(30, 30));
                    button3.setIcon(new ImageIcon(getClass().getResource("/general/remove.png")));
                    toolBar1.add(button3);

                    //---- button2 ----
                    button2.setIcon(new ImageIcon(getClass().getResource("/actions/upload.png")));
                    button2.setPreferredSize(new Dimension(30, 30));
                    toolBar1.add(button2);
                    toolBar1.add(hSpacer1);
                    toolBar1.add(textField1);
                }
                panel1.add(toolBar1);

                //======== scrollPane2 ========
                {

                    //---- table1 ----
                    table1.setModel(new DefaultTableModel(
                        new Object[][] {
                        },
                        new String[] {
                            null
                        }
                    ) {
                        Class<?>[] columnTypes = new Class<?>[] {
                            Boolean.class
                        };
                        @Override
                        public Class<?> getColumnClass(int columnIndex) {
                            return columnTypes[columnIndex];
                        }
                    });
                    {
                        TableColumnModel cm = table1.getColumnModel();
                        cm.getColumn(0).setMaxWidth(30);
                    }
                    scrollPane2.setViewportView(table1);
                }
                panel1.add(scrollPane2);
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
    private JPanel panel2;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JToolBar toolBar1;
    private JButton button1;
    private JButton button3;
    private JButton button2;
    private JPanel hSpacer1;
    private SearchTextField textField1;
    private JBScrollPane scrollPane2;
    private JBTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
