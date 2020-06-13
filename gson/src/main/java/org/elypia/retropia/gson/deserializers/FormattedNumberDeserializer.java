/*
 * Copyright 2019-2020 Elypia CIC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elypia.retropia.gson.deserializers;

import com.google.gson.*;
import org.slf4j.*;

import java.lang.reflect.Type;
import java.text.*;
import java.util.*;

/**
 * Returns a localized number as a regular {@link Number}.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class FormattedNumberDeserializer implements JsonDeserializer<Number> {

    private static final Logger logger = LoggerFactory.getLogger(FormattedNumberDeserializer.class);

    private final NumberFormat format;

    public FormattedNumberDeserializer() {
        this(Locale.getDefault());
    }

    public FormattedNumberDeserializer(Locale locale) {
        this(NumberFormat.getNumberInstance(locale));
    }

    public FormattedNumberDeserializer(NumberFormat format) {
        this.format = Objects.requireNonNull(format);
    }

    @Override
    public Number deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        if (!json.isJsonPrimitive())
            throw new JsonParseException("Only supports JSON primitives.");

        JsonPrimitive primitive = json.getAsJsonPrimitive();

        final Number number;

        if (primitive.isNumber()) {
            logger.debug("FormattedNumberDeserializer found value that was just a regular number.");
            number = primitive.getAsNumber();
        } else {
            ParsePosition position = new ParsePosition(0);
            String string = json.getAsString();
            number = format.parse(string, position);

            if (position.getErrorIndex() != -1 || string.length() != position.getIndex())
                throw new JsonParseException("Unable to parse String as number.");
        }

        if (typeOfT == Double.class || typeOfT == double.class)
            return number.doubleValue();
        if (typeOfT == Float.class || typeOfT == float.class)
            return number.floatValue();

        if (typeOfT == Long.class || typeOfT == long.class)
            return number.longValue();
        if (typeOfT == Integer.class || typeOfT == int.class)
            return number.intValue();
        if (typeOfT == Short.class || typeOfT == short.class)
            return number.shortValue();
        if (typeOfT == Byte.class || typeOfT == byte.class)
            return number.byteValue();

        return number;
    }
}
