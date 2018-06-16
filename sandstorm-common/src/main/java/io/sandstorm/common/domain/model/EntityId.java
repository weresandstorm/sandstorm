package io.sandstorm.common.domain.model;

import io.sandstorm.common.ParamAssert;

public class EntityId implements Identity {

    private String id;

    protected EntityId() {
    }

    public EntityId(String id) {
        ParamAssert.notBlank(id, "id");
        this.id = id;
    }

    @Override
    public String get() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityId)) return false;

        EntityId entityId = (EntityId) o;

        return id.equals(entityId.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + id + "]";
    }
}