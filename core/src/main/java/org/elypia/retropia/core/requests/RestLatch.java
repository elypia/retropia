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
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * @param <T> The type of response to recieve and manage from this instance.
 * @author seth@elypia.org (Seth Falco)
 */
public class RestLatch<T> implements RestInterface<List<T>>, Iterable<RestInterface<T>> {

    private final int timeout;
    private final boolean partial;

    private List<RestInterface<T>> restActions;
    private boolean cancelled;

    public RestLatch() {
        this(4096);
    }

    public RestLatch(int timeout) {
        this(timeout, true);
    }

    public RestLatch(int timeout, boolean partial) {
        this.timeout = timeout;
        this.partial = partial;

        restActions = new ArrayList<>();
    }

    public void add(RestAction<T> restAction) {
        restActions.add(restAction);
    }

    @Override
    public void queue(Consumer<List<T>> success, Consumer<Throwable> ex) {
        if (restActions.isEmpty())
            return;

        new Thread(() -> {
            List<T> results = new ArrayList<>();
            CountDownLatch latch = new CountDownLatch(restActions.size());

            forEach((restAction) -> {
                restAction.queue((result) -> {
                    results.add(result);
                    latch.countDown();
                }, (e) -> {
                    if (!partial)
                        cancel();

                    latch.countDown();
                });
            });

            try {
                boolean completeAllRequests = latch.await(timeout, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                cancel();

                if (!partial || results.isEmpty())
                    ex.accept(e);

                Thread.currentThread().interrupt();
            }

            success.accept(results);
        }).start();
    }

    @Override
    public List<T> complete() throws IOException {
        List<T> results = new ArrayList<>();

        for (RestInterface<T> restAction : restActions) {
            T result = restAction.complete();
            results.add(result);
        }

        return results;
    }

    @Override
    public void cancel() {
        if (!cancelled) {
            restActions.forEach(RestInterface::cancel);
            cancelled = true;
        }
    }

    @Override
    public Iterator<RestInterface<T>> iterator() {
        return restActions.iterator();
    }
}
