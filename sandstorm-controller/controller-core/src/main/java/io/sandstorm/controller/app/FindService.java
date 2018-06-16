package io.sandstorm.controller.app;

import io.sandstorm.common.query.domain.Page;
import io.sandstorm.common.query.domain.SimplePagingQParams;
import io.sandstorm.controller.domain.job.JobExecution;
import io.sandstorm.controller.domain.job.JobSliceExecution;
import io.sandstorm.controller.domain.job.TestJob;
import io.sandstorm.controller.domain.resource.DataChunk;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.LoadInjector;
import io.sandstorm.controller.domain.resource.TestScript;

public interface FindService {

    Page<TestScript> findTestScripts(SimplePagingQParams params);

    Page<DataSet> findDataSets(SimplePagingQParams params);

    Page<DataChunk> findDataChunks(DataChunkQParams params);

    Page<LoadInjector> findLoadInjectors(LoadInjectorQParams params);

    Page<TestJob> findTestJobs(SimplePagingQParams params);

    Page<JobExecution> findJobExecutions(JobExecQParams params);

    Page<JobSliceExecution> findJobSliceExecs(JobSliceExecQParams params);

}
