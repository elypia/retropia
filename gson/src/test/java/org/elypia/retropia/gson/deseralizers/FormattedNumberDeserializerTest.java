package org.elypia.retropia.gson.deseralizers;

import com.google.gson.JsonPrimitive;
import org.elypia.retropia.gson.deserializers.FormattedNumberDeserializer;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class FormattedNumberDeserializerTest {

    @Test
    public void testParseNormalValue() {
        JsonPrimitive primitive = new JsonPrimitive("1,000");
        FormattedNumberDeserializer deserializer = new FormattedNumberDeserializer(Locale.US);

        final int expected = 1000;
        final Number actual = deserializer.deserialize(primitive, int.class, null);

        assertEquals(expected, actual);
    }

    @Test
    public void testParseNumericValue() {
        JsonPrimitive primitive = new JsonPrimitive(1000);
        FormattedNumberDeserializer deserializer = new FormattedNumberDeserializer();

        final int expected = 1000;
        final Number actual = deserializer.deserialize(primitive, int.class, null);

        assertEquals(expected, actual);
    }

    @Test
    public void testParseNegativeValue() {
        JsonPrimitive primitive = new JsonPrimitive("-199,300");
        FormattedNumberDeserializer deserializer = new FormattedNumberDeserializer(Locale.US);

        final int expected = -199300;
        final Number actual = deserializer.deserialize(primitive, int.class, null);

        assertEquals(expected, actual);
    }

    @Test
    public void testItalyValue() {
        JsonPrimitive primitive = new JsonPrimitive("-199.300");
        FormattedNumberDeserializer deserializer = new FormattedNumberDeserializer(Locale.ITALY);

        final int expected = -199300;
        final Number actual = deserializer.deserialize(primitive, int.class, null);

        assertEquals(expected, actual);
    }
}
