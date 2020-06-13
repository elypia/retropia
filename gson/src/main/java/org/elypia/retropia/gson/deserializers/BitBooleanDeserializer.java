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

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class BitBooleanDeserializer implements JsonDeserializer<Boolean> {

    private static final Logger logger = LoggerFactory.getLogger(BitBooleanDeserializer.class);

    @Override
    public Boolean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        if (!json.isJsonPrimitive())
            throw new JsonParseException("Must be a JSON primitive.");

        JsonPrimitive primitive = json.getAsJsonPrimitive();

        if (primitive.isBoolean()) {
            logger.debug("BitBooleanDeserliazer was used on a type that was a boolean anyways.");
            return primitive.getAsBoolean();
        }

        int number = json.getAsNumber().intValue();

        switch (number) {
            case 0: return false;
            case 1: return true;
            default: throw new JsonParseException("JSON Primitive is not a binary value.");
        }
    }
}
