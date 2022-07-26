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
package com.redhat.devtools.intellij.knative.ui.buildRunDeployWindow.runFuncWindowTab;

import com.intellij.openapi.wm.ToolWindow;
import com.redhat.devtools.intellij.knative.ui.buildRunDeployWindow.BuildRunDeployFuncPanel;
import com.redhat.devtools.intellij.knative.func.FuncActionTask;
import com.redhat.devtools.intellij.knative.func.IFuncAction;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.List;

import static com.redhat.devtools.intellij.knative.Constants.RUNFUNC_CONTENT_NAME;

public class RunFuncPanel extends BuildRunDeployFuncPanel {

    public RunFuncPanel(ToolWindow toolWindow) {
        super(toolWindow, RUNFUNC_CONTENT_NAME);
    }

    @Override
    protected DefaultMutableTreeNode createFuncActionTreeNode(List<IFuncAction> actionFuncHandlers) {
        IFuncAction runningAction = actionFuncHandlers.get(0);
        if (showHistory) {
            DefaultMutableTreeNode runNode = createTreeNode(
                    runningAction,
                    () -> runningAction.getFuncName() + " [latest-" + getRunningStep(runningAction).getActionName() + "]:" ,
                    () -> getNodeLocation(runningAction)
            );
            for (IFuncAction actionFuncHandler: actionFuncHandlers) {
                runNode.add(createRunFuncTreeNode(actionFuncHandler));
            }
            return runNode;
        } else {
            return createRunFuncTreeNode(runningAction);
        }
    }

    private DefaultMutableTreeNode createRunFuncTreeNode(IFuncAction runFuncHandler) {
        DefaultMutableTreeNode runNode = createTreeNode(
                runFuncHandler,
                () -> runFuncHandler.getFuncName() + " [" + getRunningStep(runFuncHandler).getActionName() + "]:",
                () -> getNodeLocation(runFuncHandler)
        );

        List<FuncActionTask> tasks = getSteps(runFuncHandler);
        for (FuncActionTask task: tasks) {
            DefaultMutableTreeNode stepNode = createTreeNode(
                    task,
                    () -> ":" + task.getActionName(),
                    () -> getTaskLocation(task)
            );
            runNode.add(stepNode);
        }
        return runNode;
    }
}
