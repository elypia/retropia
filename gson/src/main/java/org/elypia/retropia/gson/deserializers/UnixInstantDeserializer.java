package org.elypia.retropia.gson.deserializers;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Convert a number to a date by using it as Unix time.
 * If the time is provided in a unit other than {@link TimeUnit#MILLISECONDS}
 * then the {@link TimeUnit} to use can provided in the constructor.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 2.1.0
 */
public class UnixInstantDeserializer implements JsonDeserializer<Instant> {

    private final TimeUnit unit;

    public UnixInstantDeserializer() {
        this(TimeUnit.MILLISECONDS);
    }

    public UnixInstantDeserializer(TimeUnit unit) {
        this.unit = unit;
    }

    @Override
    public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        if (!json.isJsonPrimitive())
            throw new JsonParseException("Must be a JSON primitive.");

        Number number = json.getAsNumber();
        long milliseconds = unit.toMillis(number.longValue());
        return Instant.ofEpochMilli(milliseconds);
    }
}
