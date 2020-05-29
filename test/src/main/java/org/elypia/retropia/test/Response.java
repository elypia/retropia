package org.elypia.retropia.test;

import java.lang.annotation.*;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 1.2.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Response {

    /**
     * @return The body of response.
     */
    String value();

    /**
     * @return The HTTP response code of the request.
     */
    int responseCode() default 200;

    /**
     * @return If the {@link #value()} refers to a resource
     * in the classpath, or is the literal response.
     */
    boolean isResource() default true;
}
