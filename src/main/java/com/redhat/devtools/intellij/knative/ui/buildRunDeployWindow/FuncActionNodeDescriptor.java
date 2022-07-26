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
package com.redhat.devtools.intellij.knative.ui.buildRunDeployWindow;

import com.intellij.ide.util.treeView.NodeDescriptor;
import com.intellij.openapi.project.Project;
import com.redhat.devtools.intellij.common.tree.LabelAndIconDescriptor;
import com.redhat.devtools.intellij.knative.func.IFuncAction;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;
import java.util.function.Supplier;

public class FuncActionNodeDescriptor extends LabelAndIconDescriptor<IFuncAction> {
    public FuncActionNodeDescriptor(Project project, IFuncAction element, Supplier<String> label, Supplier<String> location, Supplier<Icon> nodeIcon, @Nullable NodeDescriptor parentDescriptor) {
        super(project, element, label, location, nodeIcon, parentDescriptor);
    }
}
