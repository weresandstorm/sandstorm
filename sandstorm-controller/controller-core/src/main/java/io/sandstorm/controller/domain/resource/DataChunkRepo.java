package io.sandstorm.controller.domain.resource;

import io.sandstorm.common.domain.repo.Repo;
import io.sandstorm.common.query.domain.Page;
import io.sandstorm.common.query.domain.SimplePagingQParams;
import org.bson.types.ObjectId;

import java.util.List;

public interface DataChunkRepo extends Repo<DataChunk> {

    void deleteByDataSet(ObjectId dataSetId);

    List<DataChunk> find(ObjectId dataSetId, int startNo, int limit);

    Page<DataChunk> findPage(SimplePagingQParams params);

}
