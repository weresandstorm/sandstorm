package io.sandstorm.controller.domain.job;

import io.sandstorm.common.domain.repo.Repo;
import org.bson.types.ObjectId;

import java.util.List;

public interface JobSliceExecRepo extends Repo<JobSliceExecution> {

    void deleteByJobExecId(ObjectId theId);

    List<JobSliceExecution> findBy(ObjectId jobExecId);

    List<JobSliceExecution> findBy(ObjectId jobExecId, ExecutionStatus status);

    long countByStatus(ObjectId jobExecId, ExecutionStatus status);

}
