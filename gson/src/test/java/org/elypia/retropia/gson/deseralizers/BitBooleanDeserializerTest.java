package org.elypia.retropia.gson.deseralizers;

import com.google.gson.*;
import org.elypia.retropia.gson.deserializers.BitBooleanDeserializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BitBooleanDeserializerTest {

    @Test
    public void testParseFalse() {
        JsonPrimitive primitive = new JsonPrimitive(0);
        BitBooleanDeserializer deserializer = new BitBooleanDeserializer();

        final boolean expected = false;
        final boolean actual = deserializer.deserialize(primitive, Boolean.class, null);

        assertEquals(expected, actual);
    }

    @Test
    public void testParseTrue() {
        JsonPrimitive primitive = new JsonPrimitive(1);
        BitBooleanDeserializer deserializer = new BitBooleanDeserializer();

        final boolean expected = true;
        final boolean actual = deserializer.deserialize(primitive, Boolean.class, null);

        assertEquals(expected, actual);
    }

    /** Allow reading the String value for 0. */
    @Test
    public void testParseString() {
        JsonPrimitive primitive = new JsonPrimitive("0");
        BitBooleanDeserializer deserializer = new BitBooleanDeserializer();

        final boolean expected = false;
        final boolean actual = deserializer.deserialize(primitive, Boolean.class, null);

        assertEquals(expected, actual);
    }

    /** If a literal boolean is found, just parse it anyways. */
    @Test
    public void testParseBoolean() {
        JsonPrimitive primitive = new JsonPrimitive(true);
        BitBooleanDeserializer deserializer = new BitBooleanDeserializer();

        final boolean expected = true;
        final boolean actual = deserializer.deserialize(primitive, Boolean.class, null);

        assertEquals(expected, actual);
    }

    @Test
    public void testJsonObject() {
        JsonObject object = new JsonObject();
        object.addProperty("key", "value");

        BitBooleanDeserializer deserializer = new BitBooleanDeserializer();
        assertThrows(JsonParseException.class, () -> deserializer.deserialize(object, Boolean.class, null));
    }
}
