package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.EnvironmentDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarEnvironment extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        EnvironmentDialog addClusterDialog = new EnvironmentDialog(e.getProject());
        addClusterDialog.show();
    }
}
