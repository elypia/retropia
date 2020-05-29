/*
 * Copyright 2019-2020 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.retropia.core;

import org.elypia.retropia.core.extensions.WrapperExtension;

import java.util.*;

/**
 * An abstract wrapper for sharing common code between all
 * API wrappers.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public abstract class ApiWrapper {

    /** A list of extensions enabled for this wrapper. */
    private List<WrapperExtension> exts;

    public ApiWrapper() {
        this(new WrapperExtension[0]);
    }

    /**
     * @param exts An optional list of extensions for this wrapper.
     */
    public ApiWrapper(WrapperExtension... exts) {
        this.exts = new ArrayList<>();

        if (exts != null)
            this.exts.addAll(List.of(exts));
    }

    public Collection<WrapperExtension> getExtensions() {
        return Collections.unmodifiableCollection(exts);
    }
}
