package org.elypia.retropia.core.exceptions;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidEnumTest {

    @Test
    public void throwInvalidEnum() {
        InvalidEnumException ex = new InvalidEnumException(TimeUnit.DAYS);

        final String expected = "Invalid use of DAYS constant.";
        final String actual = ex.getMessage();

        assertEquals(expected, actual);
    }
}
