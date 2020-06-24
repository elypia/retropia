package org.elypia.retropia.core.interceptors;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * An interceptor to add query parameters to the request.
 * This is primarily intended for requests where a particular parameter
 * should be sent with every request, such as a client name, or API key.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 2.1.0
 */
public class QueryParametersInterceptor implements Interceptor {

    /** Query parameters to add to all requests. */
    private final Map<String, String> queryParameters;

    /**
     * Overloads the primary constructor to take a single key and value.
     * This just calls {@link Map#of()} with the respective parameters.
     *
     * @param queryParameterName The key of the query parameter.
     * @param queryParameterValue The value of the query parameter.
     */
    public QueryParametersInterceptor(final String queryParameterName, final String queryParameterValue) {
        this(Map.of(queryParameterName, queryParameterValue));
    }

    /**
     * @param queryParameters A map of key/value pairs to add as query parameters to all requests.
     */
    public QueryParametersInterceptor(final Map<String, String> queryParameters) {
        this.queryParameters = queryParameters;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl.Builder urlBuilder = request.url().newBuilder();
        queryParameters.forEach(urlBuilder::addQueryParameter);

        HttpUrl url = urlBuilder.build();
        Request newRequest = request.newBuilder().url(url).build();
        return chain.proceed(newRequest);
    }
}
