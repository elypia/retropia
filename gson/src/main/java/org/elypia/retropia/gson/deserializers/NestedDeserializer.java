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

import java.lang.reflect.Type;
import java.util.Set;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public class NestedDeserializer implements JsonDeserializer<Object> {

    private static Gson gson = new Gson();

    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        if (json.isJsonObject()) {
            JsonObject object = json.getAsJsonObject();
            Set<String> keys = object.keySet();

            if (keys.size() > 1)
                throw new JsonParseException("Don't use nested deserializer when there is more than one nested object.");

            String key = keys.iterator().next();
            return gson.fromJson(object.get(key), typeOfT);
        }

        if (json.isJsonArray()) {
            JsonArray array = json.getAsJsonArray();

            if (array.size() > 1)
                throw new JsonParseException("Don't use nested deserializer when there can be more than one element in the array.");

            return gson.fromJson(array.get(0), typeOfT);
        }

        throw new JsonParseException("Element is not of type object or array.");
    }
}
