/*******************************************************************************
 * Copyright (c) 2021 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc.
 ******************************************************************************/
package com.redhat.devtools.intellij.knative.actions.func;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.redhat.devtools.intellij.common.utils.ExecHelper;
import com.redhat.devtools.intellij.knative.actions.KnAction;
import com.redhat.devtools.intellij.knative.kn.Kn;
import com.redhat.devtools.intellij.knative.tree.KnFunctionNode;
import com.redhat.devtools.intellij.knative.tree.ParentableNode;
import java.io.IOException;
import javax.swing.tree.TreePath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunAction extends KnAction {
    private static final Logger logger = LoggerFactory.getLogger(RunAction.class);

    public RunAction() {
        super(KnFunctionNode.class);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent, TreePath path, Object selected, Kn knCli) {
        ParentableNode node = getElement(selected);
        String localPathFunc = ((KnFunctionNode) node).getFunction().getLocalPath();
        if (localPathFunc.isEmpty()) {
            return;
        }

        ExecHelper.submit(() -> {
            try {
                knCli.runFunc(localPathFunc);
            } catch (IOException e) {
                logger.warn(e.getLocalizedMessage(), e);
            }
        });
    }

    @Override
    public boolean isVisible(Object selected) {
        return selected instanceof KnFunctionNode && !((KnFunctionNode) selected).getFunction().getLocalPath().isEmpty();
    }

    @Override
    public boolean isEnabled(Object selected) {
        if (selected instanceof KnFunctionNode) {
            String image = ((KnFunctionNode) selected).getFunction().getImage();
            return image != null && !image.isEmpty();
        }
        return false;
    }
}