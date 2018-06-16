package io.sandstorm.controller.portadapter.persist.mgo;

import io.sandstorm.common.domain.model.EntityId;
import org.bson.types.ObjectId;
import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

@Deprecated
public class EntityIdConverter extends TypeConverter implements SimpleValueConverter {

    public EntityIdConverter() {
        super(EntityId.class);
    }

    @Override
    public Object encode(Object value, MappedField mappedField) {
        if (value == null) {
            return value;
        }

        if (value instanceof EntityId) {
            EntityId id = (EntityId) value;
            return new ObjectId(id.get());
        }

        throw new RuntimeException(String.format(
                "Can't encode java field to ObjectId, java field: %s, java type: %s",
                mappedField.getJavaFieldName(), mappedField.getType()));
    }

    /**
     * @param targetClass the java type of the given mappedField
     * @param objectFromDb    the DBObject val corresponding to the given mappedField
     * @param mappedField the given mappedField
     * @return
     */
    @Override
    public Object decode(final Class targetClass, final Object objectFromDb, final MappedField mappedField) {
        if (objectFromDb == null) {
            return null;
        }

        if (objectFromDb instanceof ObjectId) {
            ObjectId objectId = (ObjectId) objectFromDb;
            return new EntityId(objectId.toHexString());
        }

        throw new RuntimeException(String.format(
                "Can't decode db field: %s, DBObject type: %s, target java type: %s",
                mappedField.getNameToStore(), mappedField.getType(), objectFromDb.getClass()));
    }

}

