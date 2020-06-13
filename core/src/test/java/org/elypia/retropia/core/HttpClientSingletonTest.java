package org.elypia.retropia.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class HttpClientSingletonTest {

    @Test
    public void getSingleton() {
        assertDoesNotThrow(HttpClientSingleton::getClient);
    }

    @Test
    public void getBuilderOfSingleton() {
        assertDoesNotThrow(HttpClientSingleton::getBuilder);
    }
}
