package io.sandstorm.controller.domain.resource;

import io.sandstorm.common.domain.repo.Repo;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface LoadInjectorRepo extends Repo<LoadInjector> {

    Optional<LoadInjector> getOptional(String hostname);

    List<LoadInjector> findByIds(Collection<ObjectId> ids);

    List<LoadInjector> findUsable(long numInjectors);

    long markAsInUse(List<ObjectId> ids);

}
