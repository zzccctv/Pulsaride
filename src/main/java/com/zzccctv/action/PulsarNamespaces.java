package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.NamespacesDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarNamespaces extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        NamespacesDialog namespaceDialog = new NamespacesDialog(e.getProject());
        namespaceDialog.show();
    }
}
