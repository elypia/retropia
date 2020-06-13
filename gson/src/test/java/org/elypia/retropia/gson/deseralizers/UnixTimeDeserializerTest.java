package org.elypia.retropia.gson.deseralizers;

import com.google.gson.JsonPrimitive;
import org.elypia.retropia.gson.deserializers.UnixDateDeserializer;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnixTimeDeserializerTest {

    @Test
    public void testNormalString() {
        JsonPrimitive primitive = new JsonPrimitive(1591926621);
        UnixDateDeserializer deserializer = new UnixDateDeserializer(TimeUnit.SECONDS);

        final Date expected = new Date(1591926621000L);
        final Date actual = deserializer.deserialize(primitive, Date.class, null);

        assertEquals(expected, actual);
    }

    @Test
    public void testEmptyString() {
        JsonPrimitive primitive = new JsonPrimitive(1591926621231L);
        UnixDateDeserializer deserializer = new UnixDateDeserializer();

        final Date expected = new Date(1591926621231L);
        final Date actual = deserializer.deserialize(primitive, Date.class, null);

        assertEquals(expected, actual);
    }
}
