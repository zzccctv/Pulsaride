package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.ResouceGroupsDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarResourceGroups extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ResouceGroupsDialog resouceGroupsDialog = new ResouceGroupsDialog(e.getProject());
        resouceGroupsDialog.show();
    }
}
