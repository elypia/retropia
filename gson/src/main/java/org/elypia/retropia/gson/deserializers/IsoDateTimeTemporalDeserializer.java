package org.elypia.retropia.gson.deserializers;

import java.time.format.DateTimeFormatter;

/**
 * @author seth@elypia.org (Seth Falco)
 * @since 2.1.0
 */
public class IsoDateTimeTemporalDeserializer extends TemporalDeserializer {

    public IsoDateTimeTemporalDeserializer() {
        super(DateTimeFormatter.ISO_DATE_TIME);
    }
}
