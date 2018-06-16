package io.sandstorm.controller.portadapter.persist.mgo;

import com.google.common.collect.Lists;
import com.mongodb.DBObject;
import io.sandstorm.common.domain.model.EntityOrBuilder;
import io.sandstorm.controller.portadapter.persist.EnumStatus;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.Collection;

@Entity(value = "jobs", noClassnameStored = true)
public class Job extends EntityOrBuilder {

    String name;
    Status status;
    EnumStatus enumStatus;
    @Reference User user;

    public static final class StatusMapper implements PojoMapper<Status> {
        @Override
        public Collection<Class<? extends Status>> mappablePojoTypes() {
            return Lists.newArrayList(Status.class);
        }

        @Override
        public Status map(Class<? extends Status> pojoType, DBObject dbObject) {
            String statusName = (String) dbObject.get("name");
            return Status.valueOf(statusName);
        }
    }

}
