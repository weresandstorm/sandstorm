package io.sandstorm.controller.portadapter.persist.mgo.impl;

import com.querydsl.core.types.EntityPath;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.DataSetRepo;
import io.sandstorm.controller.domain.resource.QDataSet;
import io.sandstorm.controller.portadapter.persist.mgo.BasicMgoRepo;
import org.springframework.stereotype.Repository;

@Repository
public class MgoDataSetRepo extends BasicMgoRepo<DataSet> implements DataSetRepo {

    @Override
    public boolean exist(String feederFileName) {
        return exist(QDataSet.dataSet.feederFileName.eq(feederFileName));
    }

    @Override
    protected EntityPath<DataSet> serveEntityPath() {
        return QDataSet.dataSet;
    }
}
