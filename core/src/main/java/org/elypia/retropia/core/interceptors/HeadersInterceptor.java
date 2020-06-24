package org.elypia.retropia.core.interceptors;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * An interceptor to add headers to the request.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 2.2.0
 */
public class HeadersInterceptor implements Interceptor {

    /** Headers to add to all requests. */
    private final Map<String, String> headers;

    /**
     * Overloads the primary constructor to take a single key and value.
     * This just calls {@link Map#of()} with the respective parameters.
     *
     * @param headerName The key of the header.
     * @param headerValue The value of the header.
     */
    public HeadersInterceptor(final String headerName, final String headerValue) {
        this(Map.of(headerName, headerValue));
    }

    /**
     * @param headers A map of key/value pairs to add as headers to all requests.
     */
    public HeadersInterceptor(final Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder();
        headers.forEach(builder::header);

        Request newRequest = builder.build();
        return chain.proceed(newRequest);
    }
}
