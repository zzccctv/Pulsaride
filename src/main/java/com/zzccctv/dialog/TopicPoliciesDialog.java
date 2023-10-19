package com.zzccctv.dialog;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.SearchTextField;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import com.zzccctv.model.TopicsOverview;
import org.apache.pulsar.client.admin.PulsarAdmin;
import org.apache.pulsar.client.admin.TopicPolicies;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import java.awt.*;

public class TopicPoliciesDialog extends AbstractDialog {
    private Project _project;
    private DefaultTableModel defaultTableModel0;
    private JBTable table0;
    private SearchTextField textField0;
    private JToolBar toolBar0;
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JBScrollPane scrollPane0;
    private JBScrollPane scrollPane2;

    public TopicPoliciesDialog(@Nullable Project project) {
        super(project);
        _project = project;
        setTitle("pulsar-admin topics-policies");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {

        TableRowSorter<DefaultTableModel> rowSorter0 = new TableRowSorter<>();
        //======== panel1 ========
        {
            panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

            //======== toolBar1 ========
            {
                toolBar0.setEnabled(false);
                toolBar0.setBorder(null);
                toolBar0.setPreferredSize(new Dimension(352, 30));
                toolBar0.setMaximumSize(new Dimension(32925, 30));
                toolBar0.setMinimumSize(new Dimension(221, 30));
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
                            false, false, false, false
                    };

                    @Override
                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return columnEditable[columnIndex];
                    }

                    final Class<?>[] columnTypes = new Class<?>[]{
                            String.class, Integer.class, String.class, String.class
                    };

                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        return columnTypes[columnIndex];
                    }
                };
                defaultTableModel0.addColumn("Key");
                defaultTableModel0.addColumn("Value");
                rowSorter0.setModel(defaultTableModel0);
                table0.setModel(defaultTableModel0);
                table0.setRowSorter(rowSorter0);
                rowSorter0.setSortable(0, false);
                scrollPane0.setViewportView(table0);
            }
            panel1.add(scrollPane0);
        }
        tabbedPane1.setBorder(scrollPane2.getBorder());
        tabbedPane1.addTab("TopicPolicies", panel1);
        return null;
    }

    private void loadEnv(String envName, String topic) {
        try {
            TopicsOverview topicsOverview = ProgressManager.getInstance().run(new Task.WithResult<TopicsOverview, Exception>(_project, envName, true) {
                @Override
                protected TopicsOverview compute(@NotNull ProgressIndicator indicator) throws Exception {
                    try (PulsarAdmin pulsarAdmin = create(envName)) {
                        TopicsOverview topicsOverview = new TopicsOverview();
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        Thread.currentThread().setContextClassLoader(this.getClass().getClassLoader());
                        TopicPolicies topicPolicies = pulsarAdmin.topicPolicies();
                        //topicsOverview.addDataVector("backlogQuota", topicPolicies.getBacklogQuotaMap(topic));
                        topicsOverview.addDataVector("messageTTL", topicPolicies.getMessageTTL(topic));
                        topicsOverview.addDataVector("retention", topicPolicies.getRetention(topic));
                        topicsOverview.addDataVector("deduplicationStatus", topicPolicies.getDeduplicationStatus(topic));
                        topicsOverview.addDataVector("deduplicationSnapshotInterval", topicPolicies.getDeduplicationSnapshotInterval(topic));
                        //topicsOverview.addDataVector("delayedDeliveryPolicy", topicPolicies.getDelayedDeliveryPolicy(topic));
                        topicsOverview.addDataVector("persistence", topicPolicies.getPersistence(topic));
                        //topicsOverview.addDataVector("offloadPolicies", topicPolicies.getOffloadPolicies(topic));
                        topicsOverview.addDataVector("dispatchRate", topicPolicies.getDispatchRate(topic));
                        topicsOverview.addDataVector("subscribeRate", topicPolicies.getSubscribeRate(topic));
                        topicsOverview.addDataVector("subscriptionDispatchRate", topicPolicies.getSubscriptionDispatchRate(topic));
                        topicsOverview.addDataVector("replicatorDispatchRate", topicPolicies.getReplicatorDispatchRate(topic));
                        topicsOverview.addDataVector("compactionThreshold", topicPolicies.getCompactionThreshold(topic));
                        topicsOverview.addDataVector("maxUnackedMessagesOnConsumer", topicPolicies.getMaxUnackedMessagesOnConsumer(topic));
                        topicsOverview.addDataVector("maxUnackedMessagesOnSubscription", topicPolicies.getMaxUnackedMessagesOnSubscription(topic));
                        topicsOverview.addDataVector("maxConsumersPerSubscription", topicPolicies.getMaxConsumersPerSubscription(topic));
                        topicsOverview.addDataVector("maxSubscriptionsPerTopic", topicPolicies.getMaxSubscriptionsPerTopic(topic));
                        topicsOverview.addDataVector("publishRate", topicPolicies.getPublishRate(topic));
                        topicsOverview.addDataVector("subscriptionTypesEnabled", topicPolicies.getSubscriptionTypesEnabled(topic));
                        topicsOverview.addDataVector("maxProducers", topicPolicies.getMaxProducers(topic));
                        topicsOverview.addDataVector("inactiveTopicPolicies", topicPolicies.getInactiveTopicPolicies(topic));
                        topicsOverview.addDataVector("maxConsumers", topicPolicies.getMaxConsumers(topic));
                        topicsOverview.addDataVector("maxMessageSize", topicPolicies.getMaxMessageSize(topic));
                        Thread.currentThread().setContextClassLoader(classLoader);
                        return topicsOverview;
                    }
                }
            });
            tableDataVector(defaultTableModel0, topicsOverview.getDataVector());
        } catch (Exception e) {
            cleanTableAllRows(defaultTableModel0);
            showException(e);
        }

    }
}
