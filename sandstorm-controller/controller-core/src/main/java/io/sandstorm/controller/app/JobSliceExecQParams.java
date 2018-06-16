package io.sandstorm.controller.app;

import io.sandstorm.common.query.domain.Pageable;
import io.sandstorm.controller.domain.job.ExecutionStatus;
import org.bson.types.ObjectId;

import java.util.Optional;

import static io.sandstorm.common.InputAssert.notNull;

public class JobSliceExecQParams extends Pageable {

    private ObjectId jobExecId;

    private Optional<ExecutionStatus> status = Optional.empty();

    public ObjectId getJobExecId() {
        return jobExecId;
    }

    public void setJobExecId(ObjectId jobExecId) {
        this.jobExecId = jobExecId;
    }

    public Optional<ExecutionStatus> getStatus() {
        return status;
    }

    public void setStatus(ExecutionStatus status) {
        if (status == null) {
            return;
        }
        this.status = Optional.of(status);
    }

    @Override
    public void validate() {
        super.validate();
        notNull(jobExecId, "jobExecId");
    }
}
