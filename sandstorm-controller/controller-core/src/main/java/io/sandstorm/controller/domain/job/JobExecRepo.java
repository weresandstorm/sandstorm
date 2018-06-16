package io.sandstorm.controller.domain.job;

import io.sandstorm.common.domain.repo.Repo;
import io.sandstorm.controller.domain.resource.DataSet;
import io.sandstorm.controller.domain.resource.TestScript;

public interface JobExecRepo extends Repo<JobExecution> {

    boolean notEndedExistByScript(TestScript script);

    boolean notEndedExistByDataSet(DataSet dataSet);

}
