package io.sandstorm.controller.domain.job;

import io.sandstorm.common.domain.repo.Repo;
import org.bson.types.ObjectId;

public interface TestJobRepo extends Repo<TestJob> {

    boolean exist(String name);

    boolean existWithScript(ObjectId scriptId);

    boolean existWithDataSet(ObjectId dataSetId);

}
