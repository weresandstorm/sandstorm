package io.sandstorm.controller.portadapter.persist.mgo.impl;

import com.querydsl.core.types.EntityPath;
import io.sandstorm.controller.domain.job.ExecutionStatus;
import io.sandstorm.controller.domain.job.JobExecRepo;
import io.sandstorm.controller.domain.job.JobExecution;
import io.sandstorm.controller.domain.job.QJobExecution;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.TestScript;
import io.sandstorm.controller.portadapter.persist.mgo.BasicMgoRepo;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MgoJobExecRepo extends BasicMgoRepo<JobExecution> implements JobExecRepo {

    @Override
    public boolean notEndedExistByScript(TestScript script) {
        Query<JobExecution> query = newMorphiaQuery();
        query.and(
                query.criteria("script").equal(script),
                query.criteria("status").notIn(ExecutionStatus.endedStatuses())
        );
        return (query.count() > 0);
    }

    @Override
    public boolean notEndedExistByDataSet(DataSet dataSet) {
        Query<JobExecution> query = newMorphiaQuery();
        query.and(
                query.criteria("dataSet").equal(dataSet),
                query.criteria("status").notIn(ExecutionStatus.endedStatuses())
        );
        return (query.count() > 0);
    }

    @Override
    protected EntityPath<JobExecution> serveEntityPath() {
        return QJobExecution.jobExecution;
    }
}
