package io.sandstorm.controller.app;

import io.sandstorm.common.query.domain.Pageable;
import io.sandstorm.controller.domain.resource.DataChunk;
import org.bson.types.ObjectId;

import static io.sandstorm.common.InputAssert.notNull;

/**
 * Input params for finding and paging {@link DataChunk}s.
 */
public class DataChunkQParams extends Pageable {

    private ObjectId dataSetId;

    public ObjectId getDataSetId() {
        return dataSetId;
    }

    public void setDataSetId(ObjectId dataSetId) {
        this.dataSetId = dataSetId;
    }

    @Override
    public void validate() {
        super.validate();
        notNull(dataSetId, "dataSetId");
    }
}
