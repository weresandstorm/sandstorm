package io.sandstorm.controller.domain.job;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBasicExecution is a Querydsl query type for BasicExecution
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QBasicExecution extends EntityPathBase<BasicExecution> {

    private static final long serialVersionUID = 1761784260L;

    public static final QBasicExecution basicExecution = new QBasicExecution("basicExecution");

    public final io.sandstorm.common.domain.model.QVersionedEntityOrBuilder _super = new io.sandstorm.common.domain.model.QVersionedEntityOrBuilder(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final BooleanPath ended = createBoolean("ended");

    public final DateTimePath<java.util.Date> endedAt = createDateTime("endedAt", java.util.Date.class);

    public final StringPath failureInfo = createString("failureInfo");

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final DateTimePath<java.util.Date> startedAt = createDateTime("startedAt", java.util.Date.class);

    public final EnumPath<ExecutionStatus> status = createEnum("status", ExecutionStatus.class);

    public final ListPath<ExecutionStatus, EnumPath<ExecutionStatus>> statusFlow = this.<ExecutionStatus, EnumPath<ExecutionStatus>>createList("statusFlow", ExecutionStatus.class, EnumPath.class, PathInits.DIRECT2);

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QBasicExecution(String variable) {
        super(BasicExecution.class, forVariable(variable));
    }

    public QBasicExecution(Path<? extends BasicExecution> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBasicExecution(PathMetadata metadata) {
        super(BasicExecution.class, metadata);
    }

}

