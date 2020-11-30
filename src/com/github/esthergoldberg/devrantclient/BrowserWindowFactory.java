package com.github.esthergoldberg.devrantclient;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;


public class BrowserWindowFactory implements ToolWindowFactory {

    public BrowserWindowFactory() {

    }

    @Override
    public void createToolWindowContent(@NotNull Project project, ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(new Browser(new JavaFxBrowser()), "", false);
        toolWindow.getContentManager().addContent(content);
    }


}