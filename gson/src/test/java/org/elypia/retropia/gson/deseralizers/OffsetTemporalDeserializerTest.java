package org.elypia.retropia.gson.deseralizers;

import com.google.gson.JsonPrimitive;
import org.elypia.retropia.gson.deserializers.OffsetTemporalDeserializer;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OffsetTemporalDeserializerTest {

    @Test
    public void testLocalDateTime() {
        JsonPrimitive primitive = new JsonPrimitive("2019-06-01 11:37:52");
        OffsetTemporalDeserializer deserializer = new OffsetTemporalDeserializer("yyyy-MM-dd HH:mm:ss", ZoneOffset.UTC);

        final OffsetDateTime expected = OffsetDateTime.of(2019, 6, 1, 11, 37, 52, 0, ZoneOffset.UTC);
        final OffsetDateTime actual = (OffsetDateTime)deserializer.deserialize(primitive, OffsetDateTime.class, null);

        assertEquals(expected, actual);
    }
}
