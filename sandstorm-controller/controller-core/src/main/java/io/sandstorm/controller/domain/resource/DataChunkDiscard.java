package io.sandstorm.controller.domain.resource;

import io.sandstorm.common.domain.model.EntityOrBuilder;
import org.mongodb.morphia.annotations.Entity;

import java.util.Date;

@Entity(value = "data_chunk_discards", noClassnameStored = true)
public class DataChunkDiscard extends EntityOrBuilder {

    private DataChunk chunk;
    private Date discardedAt;

    private DataChunkDiscard() {
    }

    public DataChunkDiscard(DataChunk dataChunk, String creator) {
        this.chunk = dataChunk;
        this.discardedAt = new Date();
        this.creator = creator;
        this.beginDomainLifeCycle();
    }

}
