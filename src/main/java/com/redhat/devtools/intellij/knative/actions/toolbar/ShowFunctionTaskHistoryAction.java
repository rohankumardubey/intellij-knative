/*******************************************************************************
 * Copyright (c) 2022 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc.
 ******************************************************************************/
package com.redhat.devtools.intellij.knative.actions.toolbar;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.IconLoader;
import com.redhat.devtools.intellij.knative.ui.buildRunDeployWindow.BuildRunDeployFuncPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;

public class ShowFunctionTaskHistoryAction extends DumbAwareAction {

    private final BuildRunDeployFuncPanel panel;
    private static final Icon showHistoryIcon = AllIcons.General.InspectionsEye;

    public ShowFunctionTaskHistoryAction(BuildRunDeployFuncPanel panel) {
        super("Show Action History", "Show function action execution history", showHistoryIcon);
        this.panel = panel;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        panel.switchHistoryMode();
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        if (panel.isShowHistory()) {
            e.getPresentation().setText("Hide Action History");
            e.getPresentation().setDescription("Hide function action execution history");
            e.getPresentation().setIcon(IconLoader.getTransparentIcon(showHistoryIcon));
        } else {
            e.getPresentation().setText("Show Action History");
            e.getPresentation().setDescription("Show function action execution history");
            e.getPresentation().setIcon(showHistoryIcon);
        }
    }
}
