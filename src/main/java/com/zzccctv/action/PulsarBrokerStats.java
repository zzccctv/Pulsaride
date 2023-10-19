package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.BrokerStatsDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarBrokerStats extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        BrokerStatsDialog brokerStatsDialog = new BrokerStatsDialog(e.getProject());
        brokerStatsDialog.show();
    }
}
