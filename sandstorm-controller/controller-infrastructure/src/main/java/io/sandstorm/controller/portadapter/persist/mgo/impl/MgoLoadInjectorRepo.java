package io.sandstorm.controller.portadapter.persist.mgo.impl;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;
import io.sandstorm.controller.domain.resource.LoadInjector;
import io.sandstorm.controller.domain.resource.LoadInjectorRepo;
import io.sandstorm.controller.domain.resource.QLoadInjector;
import io.sandstorm.controller.portadapter.persist.mgo.BasicMgoRepo;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class MgoLoadInjectorRepo extends BasicMgoRepo<LoadInjector> implements LoadInjectorRepo {

    @Override
    public Optional<LoadInjector> getOptional(String hostname) {
        Predicate criteria = QLoadInjector.loadInjector.hostname.eq(hostname);
        return findOptionalOne(criteria);
    }

    @Override
    public List<LoadInjector> findByIds(Collection<ObjectId> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return find(QLoadInjector.loadInjector.id.in(ids));
    }

    @Override
    public List<LoadInjector> findUsable(long numInjectors) {
        return find(QLoadInjector.loadInjector.state.eq(LoadInjector.State.usable), numInjectors);
    }

    @Override
    public long markAsInUse(List<ObjectId> ids) {
        return update(query -> query.field("id").in(ids), updateOps -> updateOps.set("state", LoadInjector.State.in_use));
    }

    @Override
    protected EntityPath<LoadInjector> serveEntityPath() {
        return QLoadInjector.loadInjector;
    }
}