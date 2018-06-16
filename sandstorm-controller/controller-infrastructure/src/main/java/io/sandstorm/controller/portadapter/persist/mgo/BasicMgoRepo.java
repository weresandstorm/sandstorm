package io.sandstorm.controller.portadapter.persist.mgo;

import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpression;
import com.querydsl.mongodb.morphia.MorphiaQuery;
import io.sandstorm.common.domain.model.EntityOrBuilder;
import io.sandstorm.common.domain.model.VersionedEntityOrBuilder;
import io.sandstorm.common.domain.repo.EntityNotFoundException;
import io.sandstorm.common.domain.repo.PersistenceException;
import io.sandstorm.common.domain.repo.Repo;
import io.sandstorm.common.query.domain.Page;
import io.sandstorm.common.query.domain.Pageable;
import io.sandstorm.common.query.domain.Sort;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class BasicMgoRepo<T extends EntityOrBuilder> implements Repo<T> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    protected Morphia morphia;
    @Resource
    protected Datastore datastore;

    private final Class entityClass;
    private final EntityPath<T> entityPath;

    protected BasicMgoRepo() {
        this.entityClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        this.entityPath = serveEntityPath();
    }

    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, "Given entity must not be null!");
        datastore.save(entity);
        return entity;
    }

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        Assert.notNull(entities, "Given entities must not be null!");
        datastore.save(entities);
        return entities;
    }

    @Override
    public <S extends T> S getAndChangeThenSave(ObjectId id, Consumer<S> changeAction) {
        Assert.notNull(id, "Given entity id must not be null!");

        int tryTimes = 0;
        int retryIntervalBase = 100;
        boolean interrupted = false;
        S entity = null;
        try {
            while ((++tryTimes) <= 3) {
                try {
                    entity = get(id);
                    changeAction.accept(entity);
                    return save(entity);
                } catch (ConcurrentModificationException ex) {
                    Assert.state((entity != null), "Unexpected bad state: entity is null");
                    VersionedEntityOrBuilder vEntity = (VersionedEntityOrBuilder) entity;
                    logger.info("ConcurrentModificationException occurred while saving {} [id:{}, version:{}] at {} time: {}",
                            vEntity.getClass().getSimpleName(),
                            vEntity.id(),
                            vEntity.version(),
                            tryTimes,
                            ex.getMessage()
                    );

                    try {
                        long intervalMills = (long) (retryIntervalBase * Math.pow(2, tryTimes - 1));
                        Thread.sleep(intervalMills);
                    } catch (InterruptedException ex1) {
                        interrupted = true;
                        // fall through and retry
                    }
                }
            }

            Assert.state((entity != null), "Unexpected bad state: entity is null");
            throw new PersistenceException(
                    String.format("Tried to save %s [id:%s] %s times, but still failed",
                            entity.getClass().getSimpleName(),
                            entity.id(),
                            (tryTimes - 1))
            );
        } finally {
            if (interrupted) Thread.currentThread().interrupt();
        }
    }

    @Override
    public <S extends T> Iterable<S> getAndChangeThenSave(Collection<ObjectId> ids, Consumer<S> changeAction) {
        return ids.stream()
                .map(id -> CompletableFuture.supplyAsync(() -> getAndChangeThenSave(id, changeAction)))
                .map(future -> future.exceptionally(ex -> {
                    logger.error("One of a batch of [getAndChangeThenSave] actions failed", ex);
                    return null;
                }))
                .map(CompletableFuture::join)
                .filter(item -> item != null)
                .collect(Collectors.toList());
    }

    @Override
    public int delete(ObjectId id) {
        Assert.notNull(id, "Given id must not be null!");
        WriteResult result = datastore.delete(entityClass(), id);
        return result.getN();
    }

    @Override
    public int delete(Collection<ObjectId> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return 0;
        }

        WriteResult result = datastore.delete(entityClass(), ids);
        return result.getN();
    }

    @Override
    public int delete(Predicate criteria) {
        DBCollection collection = datastore.getCollection(entityClass());
        WriteResult result = collection.remove(newDslQuery().where(criteria).asDBObject());
        return result.getN();
    }

    @Override
    public <S extends T> S get(ObjectId id) {
        S result = datastore.get(this.<S>entityClass(), id);
        if (result != null) {
            return result;
        }
        throw new EntityNotFoundException(String.format(
                "%s with id '%s' not exist",
                entityClass.getSimpleName(), id));
    }

    @Override
    public <S extends T> Optional<S> getOptional(ObjectId id) {
        S result = datastore.get(this.<S>entityClass(), id);
        return Optional.ofNullable(result);
    }

    @Override
    public T findOne(Predicate criteria) {
        Optional<T> result = findOptionalOne(criteria);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(String.format(
                    "No %s meets criteria [%s]",
                    entityClass.getSimpleName(), criteria));
        }
    }

    @Override
    public Optional<T> findOptionalOne(Predicate criteria) {
        List<T> result = newDslQuery().where(criteria).fetch();
        T theOne;
        if (result.size() == 1) {
            theOne = result.get(0);
        } else if (result.size() == 0) {
            theOne = null;
        } else {
            throw new PersistenceException("Expect to find only one, but found multiple");
        }
        return Optional.ofNullable(theOne);
    }

    @Override
    public boolean exist(Predicate criteria) {
        return find(criteria).size() > 0;
    }

    @Override
    public List<T> find(Predicate criteria) {
        return this.newDslQuery().where(criteria).fetch();
    }

    @Override
    public List<T> findAll() {
        return this.newDslQuery().fetch();
    }

    @Override
    public List<T> find(Predicate criteria, Pageable pageable) {
        return newDslQuery().where(criteria)
                .orderBy(toOrderSpecs(pageable.sort()))
                .offset(pageable.offset())
                .limit(pageable.pageSize())
                .fetch();
    }

    @Override
    public List<T> find(Pageable pageable) {
        return newDslQuery()
                .orderBy(toOrderSpecs(pageable.sort()))
                .offset(pageable.offset())
                .limit(pageable.pageSize())
                .fetch();
    }

    @Override
    public List<T> find(Optional<Predicate> criteria, Pageable pageable) {
        if (criteria.isPresent()) {
            return find(criteria.get(), pageable);
        } else {
            return find(pageable);
        }
    }

    private static final Function<Pageable, Optional<Predicate>> NO_CRITERIA = pageable -> Optional.empty();

    @Override
    public final Page<T> findPage(Pageable pageable, Supplier<Optional<Predicate>> criteriaSupplier) {
        Optional<Predicate> criteria = criteriaSupplier.get();
        long total = count(criteria);
        List<T> items = find(criteria, pageable);
        return new Page<>(total, items);
    }

    @Override
    public long count(Predicate criteria) {
        return newDslQuery().where(criteria).fetchCount();
    }

    @Override
    public long countAll() {
        return newDslQuery().fetchCount();
    }

    @Override
    public long count(Optional<Predicate> criteria) {
        if (criteria.isPresent()) {
            return count(criteria.get());
        } else {
            return countAll();
        }
    }

    protected final List<T> find(Predicate criteria, long numRecords) {
        return newDslQuery().where(criteria)
                .limit(numRecords)
                .fetch();
    }

    protected final <S extends T> long update(Function<Query<S>, Query<S>> queryFunc,
                                              Function<UpdateOperations<S>, UpdateOperations<S>> updateOpsFunc) {
        UpdateResults updateResult = datastore.update(
                queryFunc.apply(newMorphiaQuery()),
                updateOpsFunc.apply(newUpdateOps()));
        return updateResult.getUpdatedCount();
    }

    protected <S extends T> Class<S> entityClass() {
        return this.entityClass;
    }

    protected abstract EntityPath<T> serveEntityPath();

    protected void setMorphia(Morphia morphia) {
        this.morphia = morphia;
    }

    protected void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

    protected MorphiaQuery<T> newDslQuery() {
        return new MorphiaQuery<>(morphia, datastore, entityClass());
    }

    protected <S extends T> Query<S> newMorphiaQuery() {
        return datastore.createQuery(this.<S>entityClass());
    }

    protected <S extends T> UpdateOperations<S> newUpdateOps() {
        return datastore.createUpdateOperations(entityClass());
    }

    private static final OrderSpecifier[] EMPTY_ORDER_SPECS = new OrderSpecifier[]{};

    private OrderSpecifier[] toOrderSpecs(Sort sort) {
        List<OrderSpecifier<?>> orderSpecs = new ArrayList<>(3);

        for (Sort.Order order : sort) {
            ComparableExpression propertyPath;
            try {
                Field field = entityPath.getClass().getDeclaredField(order.getProperty());
                propertyPath = (ComparableExpression) field.get(entityPath);
            } catch (ReflectiveOperationException e) {
                throw new PersistenceException(String.format(
                        "Field %s isn't defined in %s or allowed to access",
                        order.getProperty(), entityPath.getClass()));
            }

            OrderSpecifier orderSpec;
            if (order.isAscending()) {
                orderSpec = propertyPath.asc();
            } else {
                orderSpec = propertyPath.desc();
            }
            orderSpecs.add(orderSpec);
        }

        return orderSpecs.toArray(EMPTY_ORDER_SPECS);
    }

}
