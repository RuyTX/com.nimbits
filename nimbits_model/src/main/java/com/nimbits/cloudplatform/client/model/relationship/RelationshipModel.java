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

package com.nimbits.cloudplatform.client.model.relationship;

/**
 * Created by Benjamin Sautner
 * User: bsautner
 * Date: 4/4/12
 * Time: 9:48 AM
 */
public class RelationshipModel implements Relationship {

    private String key;
    private String foreignKey;


    protected RelationshipModel() {
    }

    public RelationshipModel(Relationship relationship) {
        this.foreignKey = relationship.getForeignKey();
        this.key = relationship.getKey();
    }
    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getForeignKey() {
        return foreignKey;
    }
}
