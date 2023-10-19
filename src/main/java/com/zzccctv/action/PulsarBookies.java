package com.zzccctv.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.zzccctv.dialog.BookiesDialog;
import org.jetbrains.annotations.NotNull;

public class PulsarBookies extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        BookiesDialog bookiesDialog = new BookiesDialog(e.getProject());
        bookiesDialog.show();
    }
}
