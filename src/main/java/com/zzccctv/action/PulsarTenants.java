package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.TenantsDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarTenants extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        TenantsDialog tenantsDialog = new TenantsDialog(e.getProject());
        tenantsDialog.show();
    }
}
