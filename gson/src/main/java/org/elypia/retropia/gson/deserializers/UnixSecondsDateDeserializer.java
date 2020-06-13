package org.elypia.retropia.gson.deserializers;

import java.util.concurrent.TimeUnit;

/**
 * The same as the {@link UnixDateDeserializer} except it
 * has the default value of {@link TimeUnit#SECONDS}.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 2.1.0
 */
public class UnixSecondsDateDeserializer extends UnixDateDeserializer {

    public UnixSecondsDateDeserializer() {
        super(TimeUnit.SECONDS);
    }
}
