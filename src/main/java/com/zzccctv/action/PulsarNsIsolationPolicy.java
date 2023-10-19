package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.NsIsolationPolicyDialog;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class PulsarNsIsolationPolicy extends AnAction {
    @Override
    public void update(AnActionEvent e) {
        // Using the event, evaluate the context, and enable or disable the action.
        e.getPresentation().setEnabled(false);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        NsIsolationPolicyDialog nsIsolationPolicyDialog = new NsIsolationPolicyDialog(e.getProject());
        nsIsolationPolicyDialog.show();
    }
}
