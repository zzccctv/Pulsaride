package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.ProxyStatsDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarProxyStats extends AnAction {
    @Override
    public void update(AnActionEvent e) {
        // Using the event, evaluate the context, and enable or disable the action.
        e.getPresentation().setEnabled(false);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ProxyStatsDialog proxyStatsDialog = new ProxyStatsDialog(e.getProject());
        proxyStatsDialog.show();
    }
}
