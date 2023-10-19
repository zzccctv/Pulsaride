package com.zzccctv.dialog;

import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ResourceQuotasDialog extends AbstractDialog{
    public ResourceQuotasDialog(@Nullable Project project) {
        super(project);
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return null;
    }
}
