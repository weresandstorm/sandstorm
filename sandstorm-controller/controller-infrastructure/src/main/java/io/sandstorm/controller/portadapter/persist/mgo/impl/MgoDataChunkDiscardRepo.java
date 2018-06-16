package io.sandstorm.controller.portadapter.persist.mgo.impl;

import com.querydsl.core.types.EntityPath;
import io.sandstorm.controller.domain.resource.DataChunkDiscard;
import io.sandstorm.controller.domain.resource.DataChunkDiscardRepo;
import io.sandstorm.controller.domain.resource.QDataChunkDiscard;
import io.sandstorm.controller.portadapter.persist.mgo.BasicMgoRepo;
import io.sandstorm.controller.portadapter.persist.mgo.BasicMgoRepo;
import org.springframework.stereotype.Repository;

@Repository
public class MgoDataChunkDiscardRepo extends BasicMgoRepo<DataChunkDiscard> implements DataChunkDiscardRepo {

    @Override
    protected EntityPath<DataChunkDiscard> serveEntityPath() {
        return QDataChunkDiscard.dataChunkDiscard;
    }
}
