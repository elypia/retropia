package org.elypia.retropia.core.interceptors;

/**
 * An interceptor to add authorize a request with a token.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 2.2.0
 */
public class TokenAuthorizationInterceptor extends AuthorizationInterceptor {

    public TokenAuthorizationInterceptor(String token) {
        super("Token", token);
    }
}
