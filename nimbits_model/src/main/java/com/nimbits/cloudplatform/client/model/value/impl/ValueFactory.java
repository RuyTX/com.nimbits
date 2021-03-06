/*
 * Copyright (c) 2013 Nimbits Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.  See the License for the specific language governing permissions and limitations under the License.
 */

package com.nimbits.cloudplatform.client.model.value.impl;


import com.nimbits.cloudplatform.client.constants.Const;
import com.nimbits.cloudplatform.client.enums.AlertType;
import com.nimbits.cloudplatform.client.model.email.EmailAddress;
import com.nimbits.cloudplatform.client.model.entity.Entity;
import com.nimbits.cloudplatform.client.model.location.Location;
import com.nimbits.cloudplatform.client.model.location.LocationFactory;
import com.nimbits.cloudplatform.client.model.simple.SimpleValue;
import com.nimbits.cloudplatform.client.model.user.User;
import com.nimbits.cloudplatform.client.model.value.Value;
import com.nimbits.cloudplatform.client.model.value.ValueContainer;
import com.nimbits.cloudplatform.client.model.value.ValueData;

import java.util.Date;

/**
 * Created by bsautner
 * User: benjamin
 * Date: 4/16/11
 * Time: 2:27 PM
 */
public class ValueFactory {
    private ValueFactory() {
    }

    public static ValueContainer createValueContainer(final User user, final Entity entity, final String accessKey, final Value value) {

        return new ValueContainerModel(user.getEmail().getValue(), entity.getKey(),accessKey, value);

    }


    public static ValueContainer createValueContainer(final EmailAddress user, final SimpleValue<String> entity, final SimpleValue<String> accessKey, final Value value) {

        return new ValueContainerModel(user.getValue(), entity.getValue(), accessKey.getValue(), value);

    }

    public static Value createValueFromString(final SimpleValue<String> valueAndNote, final Date timestamp) {
        double d = 0;
        String note = null;
        String sample = valueAndNote.getValue().trim();
        if (sample != null && !sample.isEmpty()) {

            if (sample.contains(" ")) {
                String a[] = sample.split(" ");
                try {
                    d = Double.parseDouble(a[0]);
                    note = sample.replace(a[0], "").trim();
                } catch (NumberFormatException ex) {
                    note = sample;
                    d = Const.CONST_IGNORED_NUMBER_VALUE;
                }
            } else {
                try {
                    d = Double.parseDouble(sample);
                    note = "";
                } catch (NumberFormatException ex) {
                    note = sample;
                    d = Const.CONST_IGNORED_NUMBER_VALUE;
                }
            }
        }

        return new ValueModel(LocationFactory.createLocation(), d, timestamp, note, ValueDataModel.getInstance(SimpleValue.getInstance("")), AlertType.OK);
    }
    public static ValueModel createValueModel(final Value v) {

        return new ValueModel(v);

    }


    public static ValueModel createValueModel(final Value v, final AlertType alertType) {

        return new ValueModel(v.getLocation(),
                v.getDoubleValue(),
                v.getTimestamp(),
                v.getNote(),
                v.getData(),
                alertType);

    }


    public static ValueModel createValueModel(final Value v, final String value) {

        return new ValueModel(v, value);

    }

    public static ValueModel createValueModel(final Location location,
                                              final double d,
                                              final Date timestamp,
                                              final String note,
                                              final ValueData data,
                                              final AlertType alert) {

        return new ValueModel(location, d, timestamp, note, data, alert);

    }


    public static ValueModel createValueModel(final double d) {

        return new ValueModel(LocationFactory.createLocation(), d, new Date(), "", ValueDataModel.getInstance(SimpleValue.getInstance("")), AlertType.OK);

    }

    public static ValueModel createValueModel(final double d, final String note) {

        return new ValueModel(LocationFactory.createLocation(), d, new Date(), note, ValueDataModel.getInstance(SimpleValue.getInstance("")), AlertType.OK);

    }

    public static ValueModel createValueModel(final double d, final Date timestamp) {

        return new ValueModel(LocationFactory.createLocation(), d, timestamp, "", ValueDataModel.getInstance(SimpleValue.getInstance("")), AlertType.OK);

    }

    public static ValueModel createValueModel(final double d, final String note, final Date timestamp) {

        return new ValueModel(LocationFactory.createLocation(), d, timestamp, note, ValueDataModel.getInstance(SimpleValue.getInstance("")), AlertType.OK);

    }


    public static Value createValueModel(Value value, Date date) {
        return ValueModel.getInstance(value, date);
    }
}
