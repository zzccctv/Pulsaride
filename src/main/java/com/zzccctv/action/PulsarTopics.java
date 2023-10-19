package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.TopicsDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarTopics extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        TopicsDialog topicsDialog = new TopicsDialog(e.getProject());
        topicsDialog.show();
    }
}
