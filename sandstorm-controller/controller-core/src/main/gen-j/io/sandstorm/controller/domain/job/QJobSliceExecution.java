package io.sandstorm.controller.domain.job;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QJobSliceExecution is a Querydsl query type for JobSliceExecution
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QJobSliceExecution extends EntityPathBase<JobSliceExecution> {

    private static final long serialVersionUID = -1606082231L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QJobSliceExecution jobSliceExecution = new QJobSliceExecution("jobSliceExecution");

    public final QBasicExecution _super = new QBasicExecution(this);

    //inherited
    public final DateTimePath<java.util.Date> createdAt = _super.createdAt;

    //inherited
    public final StringPath creator = _super.creator;

    public final SimplePath<io.sandstorm.controller.domain.resource.DataSet.Slice> dataSet = createSimple("dataSet", io.sandstorm.controller.domain.resource.DataSet.Slice.class);

    //inherited
    public final BooleanPath ended = _super.ended;

    //inherited
    public final DateTimePath<java.util.Date> endedAt = _super.endedAt;

    //inherited
    public final StringPath failureInfo = _super.failureInfo;

    //inherited
    public final ComparablePath<org.bson.types.ObjectId> id = _super.id;

    public final io.sandstorm.controller.domain.resource.QLoadInjector injector;

    public final ComparablePath<org.bson.types.ObjectId> jobExecId = createComparable("jobExecId", org.bson.types.ObjectId.class);

    //inherited
    public final DateTimePath<java.util.Date> lastUpdated = _super.lastUpdated;

    public final SimplePath<LoadProfile.Slice> loadProfile = createSimple("loadProfile", LoadProfile.Slice.class);

    public final io.sandstorm.controller.domain.resource.QTestScript script;

    public final StringPath simulationToRun = createString("simulationToRun");

    //inherited
    public final DateTimePath<java.util.Date> startedAt = _super.startedAt;

    //inherited
    public final EnumPath<ExecutionStatus> status = _super.status;

    //inherited
    public final ListPath<ExecutionStatus, EnumPath<ExecutionStatus>> statusFlow = _super.statusFlow;

    //inherited
    public final NumberPath<Long> version = _super.version;

    public QJobSliceExecution(String variable) {
        this(JobSliceExecution.class, forVariable(variable), INITS);
    }

    public QJobSliceExecution(Path<? extends JobSliceExecution> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QJobSliceExecution(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QJobSliceExecution(PathMetadata metadata, PathInits inits) {
        this(JobSliceExecution.class, metadata, inits);
    }

    public QJobSliceExecution(Class<? extends JobSliceExecution> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.injector = inits.isInitialized("injector") ? new io.sandstorm.controller.domain.resource.QLoadInjector(forProperty("injector")) : null;
        this.script = inits.isInitialized("script") ? new io.sandstorm.controller.domain.resource.QTestScript(forProperty("script")) : null;
    }

}

