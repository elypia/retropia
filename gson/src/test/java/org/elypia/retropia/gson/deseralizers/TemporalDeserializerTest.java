package org.elypia.retropia.gson.deseralizers;

import com.google.gson.JsonPrimitive;
import org.elypia.retropia.gson.deserializers.TemporalDeserializer;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemporalDeserializerTest {

    @Test
    public void testLocalDateTime() {
        JsonPrimitive primitive = new JsonPrimitive("24-Jan-2019 20:50");
        TemporalDeserializer deserializer = new TemporalDeserializer("dd-MMM-yyyy HH:mm");

        final LocalDateTime expected = LocalDateTime.ofEpochSecond(1548363000, 0, ZoneOffset.UTC);
        final LocalDateTime actual = (LocalDateTime)deserializer.deserialize(primitive, LocalDateTime.class, null);

        assertEquals(expected, actual);
    }
}
