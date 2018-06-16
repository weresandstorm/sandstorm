package io.sandstorm.controller.portadapter.persist.mgo.impl;

import com.querydsl.core.types.EntityPath;
import io.sandstorm.controller.domain.job.QTestJob;
import io.sandstorm.controller.domain.job.TestJob;
import io.sandstorm.controller.domain.job.TestJobRepo;
import io.sandstorm.controller.portadapter.persist.mgo.BasicMgoRepo;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

@Repository
public class MgoTestJobRepo extends BasicMgoRepo<TestJob> implements TestJobRepo {

    @Override
    public boolean exist(String name) {
        return exist(QTestJob.testJob.name.eq(name));
    }

    @Override
    public boolean existWithScript(ObjectId scriptId) {
        return exist(QTestJob.testJob.script.id.eq(scriptId));
    }

    @Override
    public boolean existWithDataSet(ObjectId dataSetId) {
        return exist(QTestJob.testJob.dataSet.id.eq(dataSetId));
    }

    @Override
    protected EntityPath<TestJob> serveEntityPath() {
        return QTestJob.testJob;
    }
}
