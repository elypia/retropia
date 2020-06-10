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
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Convert the specified number of seconds to a date object.
 *
 * @author seth@elypia.org (Seth Falco)
 * @since 1.3.0
 */
public class SecondsDateDeseralizer implements JsonDeserializer<Date> {

    private static final Logger logger = LoggerFactory.getLogger(SecondsDateDeseralizer.class);

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        if (!json.isJsonPrimitive() || json.isJsonNull())
            throw new JsonParseException("Object is not a JSON primitive.");

        int seconds = json.getAsInt();
        long milliseconds = TimeUnit.SECONDS.toMillis(seconds);
        return new Date(milliseconds);
    }
}
