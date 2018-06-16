package io.sandstorm.controller.domain.resource;

import io.sandstorm.common.domain.repo.Repo;

public interface TestScriptRepo extends Repo<TestScript> {

    boolean exist(String jarAlias);

}
