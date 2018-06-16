package io.sandstorm.agent.domain;

import io.sandstorm.agent.domain.model.LocalTestJob;

public interface LocalTestJobRepo {

    void save(LocalTestJob testJob);
}
