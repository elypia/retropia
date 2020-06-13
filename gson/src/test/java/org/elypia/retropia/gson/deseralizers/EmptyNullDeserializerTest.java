package org.elypia.retropia.gson.deseralizers;

import com.google.gson.JsonPrimitive;
import org.elypia.retropia.gson.deserializers.EmptyNullDeserializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmptyNullDeserializerTest {

    @Test
    public void testNormalString() {
        JsonPrimitive primitive = new JsonPrimitive("Hello");
        EmptyNullDeserializer deserializer = new EmptyNullDeserializer();

        final String expected = "Hello";
        final String actual = deserializer.deserialize(primitive, String.class, null);

        assertEquals(expected, actual);
    }

    @Test
    public void testEmptyString() {
        JsonPrimitive primitive = new JsonPrimitive("");
        EmptyNullDeserializer deserializer = new EmptyNullDeserializer();

        final String actual = deserializer.deserialize(primitive, String.class, null);

        assertNull(actual);
    }
}
