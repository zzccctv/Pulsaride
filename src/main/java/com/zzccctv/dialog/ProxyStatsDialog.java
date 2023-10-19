package com.zzccctv.dialog;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ProxyStatsDialog extends AbstractDialog{
    public ProxyStatsDialog(@Nullable Project project) {
        super(project);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return null;
    }
}
