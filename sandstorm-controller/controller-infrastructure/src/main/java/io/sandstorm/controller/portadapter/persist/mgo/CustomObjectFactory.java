/*
 * Copyright (C) 2014, BeautySight Inc. All rights reserved.
 */

package io.sandstorm.controller.portadapter.persist.mgo;

import com.google.common.base.Preconditions;
import com.mongodb.DBObject;
import org.mongodb.morphia.mapping.DefaultCreator;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomObjectFactory extends DefaultCreator {

    /**
     * Key is the class of pojo, value is the mapper that map a {@link DBObject} to a pojo.
     */
    private Map<Class<?>, PojoMapper> pojoTypeToMapper = Collections.EMPTY_MAP;

    public void setPojoMappers(List<PojoMapper> pojoMappers) {
        if (CollectionUtils.isEmpty(pojoMappers)) {
            return;
        }

        final Map<Class<?>, PojoMapper> mappings = new HashMap<>(pojoMappers.size());
        for (PojoMapper pojoMapper : pojoMappers) {
            Preconditions.checkNotNull(pojoMapper, "pojoMapper is null");
            Collection<Class<?>> pojoTypes = pojoMapper.mappablePojoTypes();
            if (pojoTypes != null || !pojoTypes.isEmpty()) {
                pojoTypes.forEach(pojoType -> mappings.put(pojoType, pojoMapper));
            }
        }

        this.pojoTypeToMapper = mappings;
    }

    @Override
    public <T> T createInstance(final Class<T> clazz, final DBObject dbObj) {
        PojoMapper pojoMapper = pojoTypeToMapper.get(clazz);
        if (pojoMapper != null) {
            return (T) pojoMapper.map(clazz, dbObj);
        }
        return super.createInstance(clazz, dbObj);
    }

}
