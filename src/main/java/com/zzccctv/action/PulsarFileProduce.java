package com.zzccctv.action;

import com.intellij.ide.IdeView;
import com.intellij.ide.actions.CreateFileAction;
import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.SmartPsiElementPointer;
import com.zzccctv.dialog.PulsarProduceDialog;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

import static com.intellij.util.PlatformIcons.CLASS_ICON;

public class PulsarFileProduce extends AnAction {
    public PulsarFileProduce() {
        super("Produce", "Creates new pulsar produce", CLASS_ICON);
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final DataContext dataContext = e.getDataContext();
        final IdeView view = LangDataKeys.IDE_VIEW.getData(dataContext);
        if (view == null) {
            return;
        }
        final Project project = CommonDataKeys.PROJECT.getData(dataContext);
        final PsiDirectory dir = view.getOrChooseDirectory();
        if (dir == null || project == null) return;
        PulsarProduceDialog produceDialog = new PulsarProduceDialog(e.getProject());
        produceDialog.show();
        if (produceDialog.isOK()) {
            try {
                Properties properties = new Properties();
                properties.setProperty("SERVICE_URL", produceDialog.getServiceURL());
                properties.setProperty("TOPIC", produceDialog.getTopic());
                properties.setProperty("MESSAGE", produceDialog.getValue());
                createFile(view, project, produceDialog.getName(), "PulsarProduce", dir, properties);
            } catch (Exception ex) {
                Messages.showMessageDialog(ex.getMessage(), "Produce", Messages.getErrorIcon());
            }
        }
    }

    protected void createFile(IdeView view, Project project, String newName, String templateName, PsiDirectory directory, Properties properties) throws Exception {
        PsiDirectory dir = directory;
        String className = removeExtension(templateName, newName);
        if (className.contains(".")) {
            String[] names = className.split("\\.");
            for (int i = 0; i < names.length - 1; i++) {
                dir = CreateFileAction.findOrCreateSubdirectory(dir, names[i]);
            }
            className = names[names.length - 1];
        }
        PsiDirectory finalDir = dir;
        String finalClassName = className;
        final FileTemplate template = FileTemplateManager.getInstance(dir.getProject()).getInternalTemplate(templateName);
        PsiFile psiFile = FileTemplateUtil.createFromTemplate(template, finalClassName, properties, finalDir).getContainingFile();
        SmartPsiElementPointer<PsiFile> pointer = SmartPointerManager.getInstance(project).createSmartPsiElementPointer(psiFile);
        VirtualFile virtualFile = psiFile.getVirtualFile();
        if (virtualFile != null) {
            FileEditorManager.getInstance(project).openFile(virtualFile, true);
            Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
            int offset = getOffsetToPreserve(editor);
            view.selectElement(pointer.getElement());
            if (offset != -1 && editor != null && !editor.isDisposed()) {
                editor.getCaretModel().moveToOffset(offset + 100);
            }
        }
    }

    protected String removeExtension(String templateName, String className) {
        final String extension = StringUtil.getShortName(templateName);
        if (StringUtil.isNotEmpty(extension)) {
            className = StringUtil.trimEnd(className, "." + extension);
        }
        return className;
    }

    private static Integer getOffsetToPreserve(Editor editor) {
        if (editor == null) return -1;
        int offset = editor.getCaretModel().getOffset();
        if (offset == 0) return -1;
        return offset;
    }
}
