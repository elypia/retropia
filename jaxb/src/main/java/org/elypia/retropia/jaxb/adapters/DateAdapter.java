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

package org.elypia.retropia.jaxb.adapters;

import org.slf4j.*;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.*;
import java.util.*;

/**
 * Convert date parse by the specified pattern.
 *
 * @author seth@elypia.org (Seth Falco)
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    private static final Logger logger = LoggerFactory.getLogger(DateAdapter.class);

    private SimpleDateFormat format;

    /**
     * @param formatString The date format.
     */
    public DateAdapter(String formatString) {
        this(new SimpleDateFormat(formatString));
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public DateAdapter(SimpleDateFormat format) {
        this.format = Objects.requireNonNull(format);
    }

    @Override
    public Date unmarshal(String dateString) {
        if (dateString == null)
            return null;

        try {
            return format.parse(dateString);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Unable to parse date in the required format.");
        }
    }

    @Override
    public String marshal(Date v) {
        return (format != null) ? format.format(v) : String.valueOf(v.getTime());
    }
}
