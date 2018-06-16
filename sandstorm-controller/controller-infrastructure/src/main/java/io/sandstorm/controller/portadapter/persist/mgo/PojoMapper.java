package io.sandstorm.controller.portadapter.persist.mgo;

import com.mongodb.DBObject;

import java.util.Collection;

public interface PojoMapper<T> {

    Collection<Class<? extends T>> mappablePojoTypes();

    T map(Class<? extends T> pojoType, DBObject dbObject);

}