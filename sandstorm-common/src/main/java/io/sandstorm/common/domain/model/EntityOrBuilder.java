package io.sandstorm.common.domain.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity
public abstract class EntityOrBuilder {

    @Id
    protected ObjectId id;

    protected String creator;

    protected Date createdAt;

    protected Date lastUpdated;

    /**
     * Begins this domain entity's lifecycle in current business domain.
     * In its whole lifecycle-in-domain, this method should be called only once.
     */
    public void beginDomainLifeCycle() {
        this.autoGenerateId();
        this.recordTimePointInLifecycle();
    }

    public ObjectId id() {
        return this.id;
    }

    public String idAsString() {
        return id().toHexString();
    }

    protected EntityOrBuilder() {
    }

    public String creator() {
        return this.creator;
    }

    public void setSysAsCreator() {
        this.creator = "system";
    }

    public void accept(Visitor visitor) {
        visitor.v_id(id);
        visitor.v_creator(creator);
    }

    protected final <T extends EntityOrBuilder> void copyProps(T fromBuilder, T toEntity) {
        toEntity.creator = fromBuilder.creator;
    }

    protected final void refreshLastUpdated() {
        this.lastUpdated = new Date();
    }

    private void autoGenerateId() {
        this.id = new ObjectId();
    }

    private void recordTimePointInLifecycle() {
        this.createdAt = new Date();
        this.lastUpdated = this.createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityOrBuilder)) return false;

        EntityOrBuilder that = (EntityOrBuilder) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        return lastUpdated != null ? lastUpdated.equals(that.lastUpdated) : that.lastUpdated == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        return result;
    }

    public interface Visitor {

        default void v_id(ObjectId id) {
            // By default do nothing
        }

        void v_creator(String creator);
    }
}
