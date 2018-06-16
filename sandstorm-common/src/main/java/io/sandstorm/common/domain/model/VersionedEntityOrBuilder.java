package io.sandstorm.common.domain.model;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Version;

@Entity
public abstract class VersionedEntityOrBuilder extends EntityOrBuilder {

    @Version
    private long version;

    public final long version() {
        return this.version;
    }

}
