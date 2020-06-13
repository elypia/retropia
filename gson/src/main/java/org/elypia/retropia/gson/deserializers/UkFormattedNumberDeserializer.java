package org.elypia.retropia.gson.deserializers;

import java.util.Locale;

/**
 * Extends the {@link FormattedNumberDeserializer}
 * but with a default {@link Locale} of {@link Locale#UK}.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 2.1.0
 */
public class UkFormattedNumberDeserializer extends FormattedNumberDeserializer {

    public UkFormattedNumberDeserializer() {
        super(Locale.UK);
    }
}
