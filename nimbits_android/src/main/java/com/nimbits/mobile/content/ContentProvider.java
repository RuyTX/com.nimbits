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

package com.nimbits.mobile.content;

import com.nimbits.cloudplatform.Nimbits;
import com.nimbits.cloudplatform.client.constants.Const;
import com.nimbits.cloudplatform.client.enums.EntityType;
import com.nimbits.cloudplatform.client.model.entity.Entity;
import com.nimbits.cloudplatform.client.model.value.Value;
import com.nimbits.cloudplatform.client.model.value.impl.ValueFactory;

import java.util.*;

/**
 * Created by benjamin on 7/25/13.
 */
public class ContentProvider {
    //todo move to application class singlton
    public static List<Entity> tree;
    public static Entity currentEntity;
    public static Map<Entity, Value> currentValueMap;


    public static List<Entity> getTree() {
        return tree == null ? Collections.<Entity>emptyList() : tree;
    }

    public static void setTree(List<Entity> tree) {
        ContentProvider.tree = tree;
        currentValueMap = new HashMap<Entity, Value>(tree.size());
    }

    public static void updateCurrentValue(Entity point, Value value) {

        currentValueMap.put(point, value);
    }

    public static Value getCurrentValue(Entity point) {
        if (currentValueMap == null) {
            return ValueFactory.createValueModel(Const.CONST_IGNORED_NUMBER_VALUE);
        }
        if (currentValueMap.containsKey(point)) {
            return currentValueMap.get(point);
        } else {
            return ValueFactory.createValueModel(Const.CONST_IGNORED_NUMBER_VALUE);
        }

    }

    public static Entity getCurrentEntity() {
        return currentEntity == null ? Nimbits.session : currentEntity;
    }

    public static void setCurrentEntity(Entity currentEntity) {
        ContentProvider.currentEntity = currentEntity;
    }

    public static void setCurrentEntityToParent() {
        ContentProvider.setCurrentEntity(getParentEntity());
    }

    public static Entity getParentEntity() {
        for (Entity e : tree) {
            if (ContentProvider.currentEntity.getParent().equals(e.getKey())) {
                return e;

            }
        }
        return Nimbits.session;
    }

    public static List<Entity> getChildEntities() {
        List<Entity> current = new ArrayList<Entity>();
        if (tree != null) {
            for (Entity entity : tree) {
                if (entity.getParent().equals(currentEntity.getKey()) && !entity.getEntityType().equals(EntityType.user) && entity.getEntityType().isTreeGridItem()) {

                    current.add(entity);
                }
            }
            return current;
        } else {
            return Collections.emptyList();
        }
    }

    public static void addEntities(List response) {
        tree.addAll(response);

    }

}
