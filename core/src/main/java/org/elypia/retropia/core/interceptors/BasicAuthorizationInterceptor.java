package org.elypia.retropia.core.interceptors;

import java.nio.charset.*;
import java.util.Base64;

/**
 * An interceptor to add an authorization header to the request.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 2.2.0
 */
public class BasicAuthorizationInterceptor extends HeadersInterceptor {

    /** Name of the authorization header. */
    private static final String KEY = "Authorization";

    /** Static Base64 encoder instance. */
    private static final Base64.Encoder encoder = Base64.getEncoder();

    /** Charset to use when converting {@link String} to {@link Byte bytes}. */
    private static final Charset utf8 = StandardCharsets.UTF_8;

    /**
     * Sets an authorization header with only the username.
     * The header will essentially be set to:
     * <code>Authorization: Basic {username}:</code>
     *
     * @param username The username to authorize requests by.
     */
    public BasicAuthorizationInterceptor(String username) {
        this(username, "");
    }

    /**
     * Sets an authorization header with the username and password.
     * The header will appear like:
     * <code>Authorization: Basic {username}:{password}</code>
     *
     * @param username The username to authorise requests by.
     * @param password The password for this user.
     */
    public BasicAuthorizationInterceptor(String username, String password) {
        this((username + ":" + password).getBytes(utf8));
    }

    /**
     * Sets an authrozation header with the Base64 bytes provided.
     * The {@link Base64#getEncoder() Base64 Encoder} will be used to
     * convert this into a {@link String} and used literally.
     *
     * This is helpful if you have <em>already</em> have a base64 string,
     * rather than a username and password.
     *
     * @param base64Bytes The base64 to use for authentication as bytes.
     */
    public BasicAuthorizationInterceptor(byte[] base64Bytes) {
        super(KEY, "Basic " + encoder.encodeToString(base64Bytes));
    }
}
