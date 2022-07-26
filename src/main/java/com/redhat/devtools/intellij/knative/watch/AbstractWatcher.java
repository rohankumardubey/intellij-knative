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
package com.redhat.devtools.intellij.knative.watch;

import com.redhat.devtools.intellij.knative.kn.Kn;
import io.fabric8.kubernetes.client.Watch;

public abstract class AbstractWatcher {
    protected Kn kn;

    public AbstractWatcher(Kn kn) {
        this.kn = kn;
    }

    public abstract Watch doWatch(Runnable doExecute);
}
