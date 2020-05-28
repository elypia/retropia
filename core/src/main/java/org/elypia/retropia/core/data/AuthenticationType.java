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

package org.elypia.retropia.core.data;

/**
 * @author seth@elypia.org (Seth Falco)
 */
public enum AuthenticationType {

    UNKNOWN(null),
    BEARER("bearer"),
    BASIC("basic"),
    DIGEST("digest"),
    OAUTH1("oauth 1.0"),
    OAUTH2("oauth 2.0"),
    HAWK("hawk"),
    AWS("aws"),
    NTML("ntml");

    private final String apiName;

    AuthenticationType(final String apiName) {
        this.apiName = apiName;
    }

    public String getApiName() {
        return apiName;
    }

    public static AuthenticationType get(String apiName) {
        AuthenticationType[] types = values();

        for (int i = 1; i < types.length; i++) {
            AuthenticationType scope = types[i];

            if (scope.apiName.equals(apiName))
                return scope;
        }

        return UNKNOWN;
    }
}
