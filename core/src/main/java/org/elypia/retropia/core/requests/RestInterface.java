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

package org.elypia.retropia.core.requests;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * @param <T> The type of response to recieve from this interface.
 * @author seth@elypia.org (Seth Falco)
 */
public interface RestInterface<T> {

    Consumer<Throwable> DEFAULT_FAILURE = Throwable::printStackTrace;

    void queue(Consumer<T> success, Consumer<Throwable> ex);
    T complete() throws IOException;
    void cancel();

    default void queue() {
        queue(null);
    }

    default void queue(Consumer<T> success) {
        queue(success, DEFAULT_FAILURE);
    }
}
