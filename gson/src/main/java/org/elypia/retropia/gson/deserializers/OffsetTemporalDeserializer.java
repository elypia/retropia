package org.elypia.retropia.gson.deserializers;

import com.google.gson.*;
import org.slf4j.*;

import java.lang.reflect.Type;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.util.Objects;

/**
 * This deserializer is intended for APIs that return datetime objects,
 * that document the respective zone offset in the documentation,
 * but the API itself doesn't return the time with the respective zone information.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 2.1.0
 */
public class OffsetTemporalDeserializer implements JsonDeserializer<TemporalAdjuster> {

    private static final Logger logger = LoggerFactory.getLogger(OffsetTemporalDeserializer.class);

    private DateTimeFormatter format;

    /**
     * @param pattern The date format, or null if unix time.
     */
    public OffsetTemporalDeserializer(String pattern, ZoneOffset offset) {
        this(DateTimeFormatter.ofPattern(pattern).withZone(offset));
    }

    public OffsetTemporalDeserializer(DateTimeFormatter format) {
        this.format = Objects.requireNonNull(format);
    }

    @Override
    public TemporalAdjuster deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        if (!json.isJsonPrimitive())
            throw new JsonParseException("Object is not a JSON primitive.");

        String date = json.getAsString();

        if (typeOfT == OffsetDateTime.class)
            return format.parse(date, OffsetDateTime::from);

        if (typeOfT == OffsetTime.class)
            return format.parse(date, OffsetTime::from);

        throw new JsonParseException("Type not supported.");
    }
}
