package io.sandstorm.controller.app;

import io.sandstorm.common.query.domain.Pageable;
import io.sandstorm.controller.domain.job.ExecutionStatus;
import org.bson.types.ObjectId;

import java.util.Optional;

import static io.sandstorm.common.InputAssert.notNull;

public class JobExecQParams extends Pageable {

    private ObjectId jobId;
    private Optional<ExecutionStatus> status = Optional.empty();

    public ObjectId getJobId() {
        return jobId;
    }

    public void setJobId(ObjectId jobId) {
        this.jobId = jobId;
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
        notNull(jobId, "jobId");
    }
}
