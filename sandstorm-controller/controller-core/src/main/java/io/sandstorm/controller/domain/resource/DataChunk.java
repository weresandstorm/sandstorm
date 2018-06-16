package io.sandstorm.controller.domain.resource;

import io.sandstorm.common.domain.model.EntityOrBuilder;
import io.sandstorm.common.domain.model.ValueObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;

/**
 * A chunk is a part of a {@link DataSet}.
 */
@Entity(value = "data_chunks", noClassnameStored = true)
public class DataChunk extends EntityOrBuilder {

    protected int no;
    protected String url;
    protected String signature;
    protected ObjectId dataSetId;

    protected DataChunk() {
    }

    public DataChunk(int no, String url, String signature, ObjectId dataSetId, String creator) {
        this.no = no;
        this.url = url;
        this.signature = signature;
        this.dataSetId = dataSetId;
        this.creator = creator;
        this.beginDomainLifeCycle();
    }

    public Traits newTraits() {
        Traits traits = new Traits();
        traits.no = this.no;
        traits.url = this.url;
        traits.signature = this.signature;
        return traits;
    }

    public static final class Traits implements ValueObject {
        private int no;
        private String url;
        private String signature;

        private Traits() {
        }
    }
}
