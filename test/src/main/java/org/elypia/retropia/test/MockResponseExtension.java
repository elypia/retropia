package org.elypia.retropia.test;

import okhttp3.mockwebserver.MockResponse;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;
import org.slf4j.*;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Extension for JUnit to create {@link MockResponse}
 * through annotations
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.2.0
 */
public class MockResponseExtension implements TestInstancePostProcessor {

    /** Logging with slf4j. */
    private static final Logger logger = LoggerFactory.getLogger(MockResponseExtension.class);

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        Class<?> testClazz = testInstance.getClass();
        Collection<Field> fields = AnnotationSupport.findPublicAnnotatedFields(testClazz, MockResponse.class, Response.class);

        for (Field field : fields) {
            if (field.get(testInstance) != null)
                continue;

            Response response = field.getAnnotation(Response.class);
            int responseCode = response.responseCode();
            String resourceBody = response.value();
            String resourceContent = (response.isResource()) ? TestUtils.getAsString(resourceBody) : resourceBody;

            MockResponse mockResponse = new MockResponse()
                .setResponseCode(responseCode)
                .setBody(resourceContent);

            field.set(testInstance, mockResponse);
        }
    }
}
