package io.sandstorm.controller.portadapter.persist.mgo.impl;

import com.querydsl.core.types.EntityPath;
import io.sandstorm.controller.domain.job.ExecutionStatus;
import io.sandstorm.controller.domain.job.JobSliceExecRepo;
import io.sandstorm.controller.domain.job.JobSliceExecution;
import io.sandstorm.controller.domain.job.QJobSliceExecution;
import io.sandstorm.controller.portadapter.persist.mgo.BasicMgoRepo;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MgoJobSliceExecRepo extends BasicMgoRepo<JobSliceExecution> implements JobSliceExecRepo {

    @Override
    public void deleteByJobExecId(ObjectId theId) {
        delete(QJobSliceExecution.jobSliceExecution.jobExecId.eq(theId));
    }

    @Override
    public List<JobSliceExecution> findBy(ObjectId jobExecId) {
        return find(QJobSliceExecution.jobSliceExecution.jobExecId.eq(jobExecId));
    }

    @Override
    public List<JobSliceExecution> findBy(ObjectId jobExecId, ExecutionStatus status) {
        QJobSliceExecution qBuilder = QJobSliceExecution.jobSliceExecution;
        return find(qBuilder.jobExecId.eq(jobExecId).and(qBuilder.status.eq(status)));
    }

    @Override
    public long countByStatus(ObjectId jobExecId, ExecutionStatus status) {
        QJobSliceExecution qBuilder = QJobSliceExecution.jobSliceExecution;
        return count(qBuilder.jobExecId.eq(jobExecId).and(qBuilder.status.eq(status)));
    }

    @Override
    protected EntityPath<JobSliceExecution> serveEntityPath() {
        return QJobSliceExecution.jobSliceExecution;
    }
}
