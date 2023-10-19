package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.ResourceQuotasDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarResourceQuotas extends AnAction {
    @Override
    public void update(AnActionEvent e) {
        // Using the event, evaluate the context, and enable or disable the action.
        e.getPresentation().setEnabled(false);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ResourceQuotasDialog resourceQuotasDialog = new ResourceQuotasDialog(e.getProject());
        resourceQuotasDialog.show();
    }
}
