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
package com.redhat.devtools.intellij.knative.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.redhat.devtools.intellij.knative.kn.Kn;
import com.redhat.devtools.intellij.knative.telemetry.TelemetryService;
import com.redhat.devtools.intellij.knative.tree.KnServingNode;
import com.redhat.devtools.intellij.knative.tree.ParentableNode;
import com.redhat.devtools.intellij.knative.ui.CreateServiceDialog;
import com.redhat.devtools.intellij.knative.utils.TreeHelper;
import com.redhat.devtools.intellij.telemetry.core.service.TelemetryMessageBuilder;

import javax.swing.tree.TreePath;

import static com.redhat.devtools.intellij.knative.telemetry.TelemetryService.NAME_PREFIX_CRUD;
import static com.redhat.devtools.intellij.knative.telemetry.TelemetryService.NAME_PREFIX_MISC;

public class CreateServiceAction extends KnAction {
    public CreateServiceAction() {
        super(KnServingNode.class);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent, TreePath path, Object selected, Kn knCli) {
        TelemetryMessageBuilder.ActionMessage telemetry = TelemetryService.instance().action(NAME_PREFIX_CRUD + "create new service");
        ParentableNode node = getElement(selected);
        String namespace = node.getRootNode().getKn().getNamespace();
        CreateServiceDialog createDialog = new CreateServiceDialog(
                "Create New Service",
                anActionEvent.getProject(),
                namespace,
                () -> TreeHelper.getKnTreeStructure(getEventProject(anActionEvent)).fireModified(getElement(selected)),
                telemetry);
        createDialog.setModal(false);
        createDialog.show();
    }
}
