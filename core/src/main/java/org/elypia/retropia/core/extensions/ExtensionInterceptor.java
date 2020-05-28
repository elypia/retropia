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

package org.elypia.retropia.core.extensions;

import okhttp3.*;

import java.io.IOException;
import java.util.List;

/**
 * This allows us to add a series of extensions to the
 * client instance of the wrapper to perform functionality
 * before and after requests.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class ExtensionInterceptor implements Interceptor {

    private final Iterable<WrapperExtension> extensions;

    public ExtensionInterceptor(WrapperExtension... extensions) {
        this(List.of(extensions));
    }

    public ExtensionInterceptor(Iterable<WrapperExtension> extensions) {
        this.extensions = extensions;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = pre(request);

        if (response != null)
            return response;

        response = chain.proceed(request);
        return post(request, response);
    }

    /**
     * A pre action to do before executing the request.
     *
     * @param request The request that was performed.
     * @return Non-null if one of the extensions are providing the response.
     * ie an external cache.
     */
    private Response pre(final Request request) throws IOException {
        for (WrapperExtension ext : extensions) {
            Response response = ext.beforeRequest(request);

            if (response != null)
                return response;
        }

        return null;
    }

    /**
     * A post action to do after executing a request, this should only
     * be called after an HTTP request, not obtaining a response from cache.
     *
     * @param request The request that was executed.
     * @param response The response returned from the API.
     */
    private Response post(final Request request, Response response) throws IOException {
        for (WrapperExtension ext : extensions)
            response = ext.afterResponse(request, response);

        return response;
    }
}
