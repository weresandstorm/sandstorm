package io.sandstorm.common.domain.repo;

import com.querydsl.core.types.Predicate;
import io.sandstorm.common.domain.model.EntityOrBuilder;
import io.sandstorm.common.query.domain.Page;
import io.sandstorm.common.query.domain.Pageable;
import org.bson.types.ObjectId;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Repo<T extends EntityOrBuilder> {

    <S extends T> S save(S entity);

    <S extends T> Iterable<S> save(Iterable<S> entities);

    <S extends T> S getAndChangeThenSave(ObjectId id, Consumer<S> changeAction);

    <S extends T> Iterable<S> getAndChangeThenSave(Collection<ObjectId> ids, Consumer<S> changeAction);

    int delete(ObjectId id);

    int delete(Collection<ObjectId> ids);

    int delete(Predicate criteria);

    <S extends T> S get(ObjectId id);

    <S extends T> Optional<S> getOptional(ObjectId id);

    T findOne(Predicate criteria);

    Optional<T> findOptionalOne(Predicate criteria);

    boolean exist(Predicate criteria);

    List<T> find(Predicate criteria);

    List<T> findAll();

    List<T> find(Predicate criteria, Pageable pageable);

    List<T> find(Pageable pageable);

    List<T> find(Optional<Predicate> criteria, Pageable pageable);

    Page<T> findPage(Pageable pageable, Supplier<Optional<Predicate>> criteriaSupplier);

    long count(Predicate criteria);

    long countAll();

    long count(Optional<Predicate> criteria);

}