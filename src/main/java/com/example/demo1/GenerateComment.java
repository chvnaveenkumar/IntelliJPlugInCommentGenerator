package com.example.demo1;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorModificationUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

/**
 * Generates a comment for the selected code in the editor.
 * An action class that generates comments for selected code in the editor.
 * This action retrieves the selected code from the editor, generates a comment for it using a comment generator service,
 * and inserts the generated comment at the current caret position in the editor.
 * If there's an exception during the process like unable to connect to AI, it shows an error message.
 */
public class GenerateComment extends AnAction {
    // Comment generator service instance
    private final CommentGeneratorService commentGeneratorService = new CommentGeneratorService();
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return; // If project is null, exit the method
        if (project != null) {

            Editor editor = (Editor) e.getDataContext().getData("editor");
            if (editor == null) return;// If editor is null, exit the method

            String selectedText = editor.getSelectionModel().getSelectedText();
            if (selectedText == null || selectedText.isEmpty()) {
                // If no code is selected, show an error message and exit the method
                Messages.showMessageDialog(project, "No code is selected!", "Error", Messages.getErrorIcon());
                return;
            }
            try {
                /***** Uncomment below function to generate Hardcoded comment ****/
                String comment = generateHardCodedComment(selectedText);

                // Generate a comment for the selected code using the comment generator service using AI
                //String comment = commentGeneratorService.generateComment(selectedText);
                // Insert the generated comment at the current caret position in the editor
                Runnable r = () -> EditorModificationUtil.insertStringAtCaret(editor, comment);
                WriteCommandAction.runWriteCommandAction(project, r);
            } catch (Exception ee) {
                // If an exception occurs during the process, show an error message
                Messages.showInfoMessage("Unable connect to AI, Please check with Admin.", "info");
                ee.printStackTrace(); // Print the stack trace of the exception
            }
        }
    }

    /**
     * Generates a hard-coded comment for the selected code.
     *
     * @param selectedCode The selected code for which the comment is generated.
     * @return The generated hard-coded comment.
     */
    private String generateHardCodedComment(String selectedCode) {
        return selectedCode + "// Generated Comment: " + "Print Hello World";
     }
    }
