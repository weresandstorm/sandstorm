package io.sandstorm.controller.portadapter.persist.mgo.impl;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import io.sandstorm.common.query.domain.Page;
import io.sandstorm.common.query.domain.SimplePagingQParams;
import io.sandstorm.controller.domain.resource.DataChunk;
import io.sandstorm.controller.domain.resource.DataChunkRepo;
import io.sandstorm.controller.domain.resource.QDataChunk;
import io.sandstorm.controller.portadapter.persist.mgo.BasicMgoRepo;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MgoDataChunkRepo extends BasicMgoRepo<DataChunk> implements DataChunkRepo {

    @Override
    public void deleteByDataSet(ObjectId dataSetId) {
        delete(QDataChunk.dataChunk.dataSetId.eq(dataSetId));
    }

    @Override
    public List<DataChunk> find(ObjectId dataSetId, int startNo, int limit) {
        QDataChunk q = QDataChunk.dataChunk;
        Predicate criteria = q.dataSetId.eq(dataSetId)
                .and(q.no.between(startNo, (startNo + limit -1)));
        return find(criteria);
    }

    @Override
    public Page<DataChunk> findPage(SimplePagingQParams params) {
        return findPage(
                params.asPageable(),
                () -> params.valToMatch().map(
                        val -> QDataChunk.dataChunk.dataSetId.eq(new ObjectId(val))
                ));
    }

    @Override
    protected EntityPath<DataChunk> serveEntityPath() {
        return QDataChunk.dataChunk;
    }

}
