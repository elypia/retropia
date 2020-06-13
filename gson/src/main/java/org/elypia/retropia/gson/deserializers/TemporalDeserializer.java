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
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;

/**
 * Convert date parse by the specified pattern or as unix time
 * if no format is specified.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class TemporalDeserializer implements JsonDeserializer<TemporalAccessor> {

    private static final Logger logger = LoggerFactory.getLogger(TemporalDeserializer.class);

    private DateTimeFormatter format;

    /**
     * @param pattern The date format, or null if unix time.
     */
    public TemporalDeserializer(String pattern) {
        this(DateTimeFormatter.ofPattern(pattern));
    }

    public TemporalDeserializer(DateTimeFormatter format) {
        this.format = Objects.requireNonNull(format);
    }

    @Override
    public TemporalAccessor deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        if (!json.isJsonPrimitive())
            throw new JsonParseException("Object is not a JSON primitive.");

        String date = json.getAsString();

        if (typeOfT == Instant.class)
            return format.parse(date, Instant::from);

        if (typeOfT == OffsetDateTime.class)
            return format.parse(date, OffsetDateTime::from);

        if (typeOfT == LocalDateTime.class)
            return format.parse(date, LocalDateTime::from);

        if (typeOfT == LocalDate.class)
            return format.parse(date, LocalDate::from);

        if (typeOfT == LocalTime.class)
            return format.parse(date, LocalTime::from);

        if (typeOfT == ZonedDateTime.class)
            return format.parse(date, ZonedDateTime::from);

        if (typeOfT == DayOfWeek.class)
            return format.parse(date, DayOfWeek::from);

        throw new JsonParseException("Type not supported.");
    }
}
