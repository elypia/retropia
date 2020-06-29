package org.elypia.retropia.core.interceptors;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 2.2.0
 */
public class AuthorizationInterceptor extends HeadersInterceptor {

    /** Name of the authorization header. */
    private static final String KEY = "Authorization";

    /**
     * @param authenticationType The type of authentication, such as <code>Basic</code> or <code>Token</code>.
     * @param authenticationValue The value to authenticate to the resource.
     */
    public AuthorizationInterceptor(String authenticationType, String authenticationValue) {
        this(authenticationType + " " + authenticationValue);
    }

    /**
     * Adds the literal value as the {@link retrofit2.http.Header} value for this
     * authorization request.
     *
     * @param value The base64 to use for authentication as bytes.
     */
    public AuthorizationInterceptor(String value) {
        super(KEY, value);
    }
}
