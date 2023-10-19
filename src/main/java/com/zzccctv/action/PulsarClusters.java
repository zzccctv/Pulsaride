package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.ClustersDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarClusters extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ClustersDialog clustersDialog = new ClustersDialog(e.getProject());
        clustersDialog.show();
    }
}
