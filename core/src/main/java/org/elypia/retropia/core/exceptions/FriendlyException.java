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

package org.elypia.retropia.core.exceptions;

/**
 * Friendly exceptions occur when the web API
 * returns data in a different schema than expected, usually to
 * represent some kind of error, such as an 2XX status code,
 * response with an <code>error</code> property in the response.
 *
 * It can be wrapped in a FriendlyException with a description of what
 * happened and a unique tag so the receiver can handle it
 * appropriately.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class FriendlyException extends RuntimeException {

    /** @see #getTag() */
    private final String tag;

    /** @see #isInternal() */
    private final boolean isInternal;

    /**
     * @param tag The tag associated with this exception.
     * @param message A friendly default error message which can be safely printed.
     */
    public FriendlyException(String tag, String message) {
        this(tag, message, false);
    }

    /**
     * @param tag The tag associated with this exception.
     * @param message A friendly default error message which can be safe printed.
     * @param isInternal If the tag defined is internally managed or by the API.
     */
    public FriendlyException(String tag, String message, boolean isInternal) {
        super(message);
        this.tag = tag;
        this.isInternal = isInternal;
    }

    /**
     * @return A tag to identify the error.
     */
    public String getTag() {
        return tag;
    }

    /**
     * @return If the tag associated with it is from the API, or internally managed.
     */
    public boolean isInternal() {
        return isInternal;
    }
}
