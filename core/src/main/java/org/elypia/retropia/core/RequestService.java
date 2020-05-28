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

import okhttp3.OkHttpClient;
import org.elypia.retropia.core.extensions.*;

import java.util.List;

/**
 * Store global singleton HTTP client instance.
 * This improves performance between APIs.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class RequestService {

    private static OkHttpClient client;

    /**
     * This should be used when some runtime modifications
     * are required such as custom inceptors.
     *
     * This doesn't include the {@link ExtensionInterceptor} itself
     * as inceptor order might matter so each implementation should
     * add it its self.
     *
     * @return Get a builder that will share the same core
     * as the global {@link OkHttpClient} client.
     */
    public static OkHttpClient.Builder getBuilder() {
        if (client == null)
            client = new OkHttpClient();

        return client.newBuilder();
    }

    public static OkHttpClient withExtensions(WrapperExtension... extensions) {
        return withExtensions(List.of(extensions));
    }

    public static OkHttpClient withExtensions(Iterable<WrapperExtension> extensions) {
        return getBuilder().addInterceptor(new ExtensionInterceptor(extensions)).build();
    }
}
