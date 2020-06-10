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

package org.elypia.retropia.extensions.redis;

import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.*;
import redis.clients.jedis.*;

import java.io.IOException;
import java.util.List;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class RedisInterceptor implements Interceptor {

    /** The connection to Redis. */
    private Jedis jedis;

    /** The default TTL in seconds. */
    private int ttl;

    public RedisInterceptor(JedisShardInfo info, int ttl) {
        this(new Jedis(info), ttl);
    }

    public RedisInterceptor(Jedis jedis, int ttl) {
        this.jedis = jedis;
        this.ttl = ttl;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = beforeRequest(request);

        if (response != null)
            return response;

        response = chain.proceed(request);
        return afterResponse(request, response);
    }

    /**
     * Check Redis if it has cached a response for this
     * request already. Does nothing for non-GET requests.
     *
     * @param request The request that was executed.
     * @return The response if a response is applicable.
     */
    public Response beforeRequest(Request request) throws IOException {
        if (!request.method().equals("GET"))
            return null;

        String key = request.toString();
        List<String> values = jedis.lrange(key, 0, 4);

        if (values.isEmpty())
            return null;

        String body = values.get(0);
        String mediaType = values.get(1);
        String protocol = values.get(2);
        int code = Integer.parseInt(values.get(3));
        String message = values.get(4);

        if (mediaType == null)
            return null;

        try (ResponseBody response = ResponseBody.create(MediaType.parse(mediaType), body)) {
            return new Response.Builder()
                .request(request)
                .protocol(Protocol.get(protocol))
                .code(code)
                .message(message)
                .body(response)
                .build();
        }
    }

    public Response afterResponse(Request request, Response response) throws IOException {
        ResponseBody body = response.body();

        if (body == null)
            return null;

        String bodyString = body.string();
        MediaType mediaType = body.contentType();
        String protocol = response.protocol().toString();
        int code = response.code();
        String message = response.message();

        if (mediaType == null || request == null || code < 0)
            return null;

        String key = request.toString();

        String[] values = {
            bodyString,
            mediaType.toString(),
            protocol,
            String.valueOf(code),
            message
        };

        jedis.rpush(key, values);
        jedis.expire(key, ttl);

        return response.newBuilder().body(ResponseBody.create(mediaType, bodyString)).build();
    }

    public Jedis getJedis() {
        return jedis;
    }
}
