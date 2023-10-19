package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.BrokersDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarBrokers extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        BrokersDialog brokersDialog = new BrokersDialog(e.getProject());
        brokersDialog.show();
    }
}
