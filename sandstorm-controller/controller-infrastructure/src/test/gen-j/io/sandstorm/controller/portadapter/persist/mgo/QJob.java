package io.sandstorm.controller.portadapter.persist.mgo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJob is a Querydsl query type for Job
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJob extends EntityPathBase<Job> {

    private static final long serialVersionUID = 211670921L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJob job = new QJob("job");

    public final io.sandstorm.common.domain.model.QEntityOrBuilder _super = new io.sandstorm.common.domain.model.QEntityOrBuilder(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final EnumPath<io.sandstorm.controller.portadapter.persist.EnumStatus> enumStatus = createEnum("enumStatus", io.sandstorm.controller.portadapter.persist.EnumStatus.class);

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final StringPath name = createString("name");

    public final QStatus status;

    public final QUser user;

    public QJob(String variable) {
        this(Job.class, forVariable(variable), INITS);
    }

    public QJob(Path<? extends Job> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJob(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJob(PathMetadata metadata, PathInits inits) {
        this(Job.class, metadata, inits);
    }

    public QJob(Class<? extends Job> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.status = inits.isInitialized("status") ? new QStatus(forProperty("status")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user"), inits.get("user")) : null;
    }

}

