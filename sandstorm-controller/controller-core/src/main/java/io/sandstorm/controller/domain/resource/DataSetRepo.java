package io.sandstorm.controller.domain.resource;

import io.sandstorm.common.domain.repo.Repo;

public interface DataSetRepo extends Repo<DataSet> {

    boolean exist(String feederFileName);

}
